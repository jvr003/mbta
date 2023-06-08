package com.knimbus.mbta.services;

import com.knimbus.mbta.exception.ServiceException;
import com.knimbus.mbta.services.route.RouteResponse;
import com.knimbus.mbta.services.schedule.ScheduleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class MBTAService {

    private static final String MBTA_HOST = "https://api-v3.mbta.com";
    private static final DateTimeFormatter TIME_FMT = ofPattern("HH:mm");

    RouteResponse getRoutes(String stopName) throws ServiceException {
        var res = new RestTemplate().getForEntity(URI.create(MBTA_HOST + "/routes?filter[stop]=" + stopName), RouteResponse.class);
        if (res.getStatusCode().equals(HttpStatus.OK))
            return res.getBody();
        else
            throw new ServiceException("Exception occured in Routes service - getting StausCode as " + res.getStatusCode());
    }

    ScheduleResponse getSchedules(String stopName, LocalDateTime min, LocalDateTime max) throws ServiceException {
        var res = new RestTemplate()
                .getForEntity(URI.create(MBTA_HOST
                        + "/schedules?include=prediction&filter[min_time]=" + min.format(TIME_FMT)
                        + "&filter[max_time]=" + max.format(TIME_FMT) + "&filter[stop]=" + stopName), ScheduleResponse.class);
        if (res.getStatusCode().equals(HttpStatus.OK))
            return res.getBody();
        else
            throw new ServiceException("Exception occured in schedules service - getting StausCode as " + res.getStatusCode());
    }


    public List<DepartingData> getTrainsFrom(int count, String stationCode, LocalDateTime min, LocalDateTime max) throws ServiceException {
        var routes = getRoutes(stationCode);
        var schedules = getSchedules( stationCode, min, max);
        var departList = new ArrayList<DepartingData>();

        schedules.getData().stream()
                .filter(data -> data.getAttributes().getDeparture_time().after( Date.from(Instant.now()) ) )
                .forEach(data -> {
                    var route = routes.getData().stream()
                            .filter(r -> data.getRelationships().getRoute().getData().getId().equals(r.getId()))
                            .findFirst().get();
                    var trainData = new DepartingData(
                            route.getId(),
                            route.getAttributes().getDirection_destinations().get(data.getAttributes().getDirection_id()),
                            minsLeft(data.getAttributes().getDeparture_time(), min ) );
                    departList.add(trainData);
                }
        );
        departList.sort(Comparator.comparing(DepartingData::getMinsLeft));
        return departList.stream().filter( d -> d.getMinsLeft()>0).limit(count).collect(Collectors.toList());
    }

    private long minsLeft(Date d, LocalDateTime min){
        // due to time diff to -4:00 to +5:30 = 570
        return ((d.getTime() - Date.from(Instant.now()).getTime() ) /60000) -570 ;
    }



}
