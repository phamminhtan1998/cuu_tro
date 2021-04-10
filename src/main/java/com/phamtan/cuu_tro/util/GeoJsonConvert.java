package com.phamtan.cuu_tro.util;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class GeoJsonConvert {
    public static GeoJsonPoint convertLatLonToGeoPoint(double lon ,double lat){
        GeoJsonPoint geoJsonPoint  = new GeoJsonPoint(lon,lat);
        return geoJsonPoint;
    }
}
