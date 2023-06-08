package com.knimbus.mbta.services.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Relationships{
    private Prediction prediction;
    private Route route;
    private Stop stop;
    private Trip trip;
}
