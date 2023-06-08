package com.knimbus.mbta.services;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class DepartingData {
    private String lineName;
    private String destination;
    private long minsLeft;
}
