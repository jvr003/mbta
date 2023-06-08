package com.knimbus.mbta.services.route;

import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class Data{
    private String id;
    private String type;
    private Attributes attributes;
    private Links links;
    private Relationships relationships;
}
