package com.knimbus.mbta.services;

import com.knimbus.mbta.Conatants;
import com.knimbus.mbta.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class MBTAServiceTest {

    private final MBTAService mbtaService = new MBTAService();

    @Test
    void getRoutes() throws ServiceException {
        var res =  mbtaService.getRoutes(Conatants.PARK_STATION_CODE);
        Assertions.assertNotNull(res);
    }

    @Test
    void getSchedules() throws ServiceException {
        var res = mbtaService.getSchedules(Conatants.PARK_STATION_CODE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        Assertions.assertNotNull(res);
    }

    @Test
    void getTrainsFrom() throws ServiceException {
        var list = mbtaService.getTrainsFrom( 10, Conatants.PARK_STATION_CODE,LocalDateTime.now(), LocalDateTime.now().plusMinutes(40) );
        Assertions.assertNotNull(list);
    }

}