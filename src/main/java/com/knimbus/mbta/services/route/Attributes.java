package com.knimbus.mbta.services.route;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
public class Attributes{
    private String color;
    private String description;
    private ArrayList<String> direction_destinations;
    private ArrayList<String> direction_names;
    private String fare_class;
    private String long_name;
    private String short_name;
    private int sort_order;
    private String text_color;
    private int type;
}
