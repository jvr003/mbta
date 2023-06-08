package com.knimbus.mbta.services.schedule; 
import com.knimbus.mbta.services.MBTAServiceResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@lombok.Data
@NoArgsConstructor
public class ScheduleResponse implements MBTAServiceResponse {
    private ArrayList<Data> data;
    private Jsonapi jsonapi;
}
