package com.phamtan.cuu_tro.util;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class GeoJsonConvert {
    public static GeoJsonPoint convertLatLonToGeoPoint(double lat ,double lon){
        GeoJsonPoint geoJsonPoint  = new GeoJsonPoint(lat,lon);
        return geoJsonPoint;
    }
}
