package com.knimbus.mbta.services.schedule;

import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class Data{
    private String id;
    private String type;
    private Attributes attributes;
    private Relationships relationships;
}
