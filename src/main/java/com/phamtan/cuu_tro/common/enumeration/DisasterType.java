package com.phamtan.cuu_tro.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DisasterType {
    LANDSLIDE("Đồi núi sạt lở "),
    FLOODING("Khu vực ngập lụt "),
    EARTHQUAKE("Khu vực xảy ra động đất");
    private final String message;



}
