package com.sudhakar.visual.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapMaker {
    private String name;
    private double latitude;
    private double longitude;
    private int population;
    private String color;
    private String shape;
}
