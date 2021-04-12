package com.phamtan.cuu_tro.common.enumeration;

public enum MetricType {
    KM("KiloMeter"),
    M("Meters"),
    MILE("Mile")
    ;
    private final String description;

    MetricType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
