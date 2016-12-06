package com.lab31.admin.lab4_4_3;

public class Region {
    String regionName;
    String areaName;
    int population;
    int regionCode;
    public Region(int regionCode, String regionName, String areaName, int population){
        this.regionName = regionName;
        this.areaName = areaName;
        this.regionCode = regionCode;
        this.population = population;
    }
}
