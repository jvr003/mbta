package com.knimbus.mbta;

import com.knimbus.mbta.services.DepartingData;
import com.knimbus.mbta.services.MBTAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/def")
public class DefController {
    @Autowired
    private MBTAService mbtaService;
    @GetMapping("/get")
    ResponseEntity<String> getNextTrains( @RequestParam("station") @Validated String stationName ) throws Exception {
        if(!stationName.isEmpty()){
            var list = mbtaService.getTrainsFrom(10,stationName, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20));
            return new ResponseEntity<>(list.stream().collect(Collectors.groupingBy(DepartingData::getLineName)).toString(), HttpStatus.OK);
        }else
            throw new Exception("no station code");
    }

}
