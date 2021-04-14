package com.phamtan.cuu_tro.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsualGoods {
    NOODLE("Mỳ Tôm"),
    RICE("Gạo trắng"),
    TOILET_ISSUE("Giấy vệ sinh"),
    CLOTHES("Quần áo"),
    HOUSEWARES("Đồ gia dụng");
    private final String description;

}
