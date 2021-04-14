package com.phamtan.cuu_tro.web.dto.request;

import com.phamtan.cuu_tro.common.enumeration.UsualGoods;

import java.util.Map;

public class RescueGoodsDTO {
    private String idRescuer;
    //  Specific short description for list goods
    private String nameArea;
    private Map<UsualGoods,Integer> coreGoods;
    private Map<String,Integer> additionalGoods;
    private boolean isArchived;
}
