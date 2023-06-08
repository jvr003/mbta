package com.knimbus.mbta.services.route;

import com.knimbus.mbta.services.MBTAServiceResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@lombok.Data
@NoArgsConstructor
public class RouteResponse implements MBTAServiceResponse{
    public ArrayList<Data> data;
    public Jsonapi jsonapi;
}
