package com.knimbus.mbta.services.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class Attributes{
    private Date arrival_time;
    private Date departure_time;
    private int direction_id;
    private int drop_off_type;
    private int pickup_type;
    private Object stop_headsign;
    private int stop_sequence;
    private boolean timepoint;
}
