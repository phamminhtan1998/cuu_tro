package com.phamtan.cuu_tro.web.dto.request;

import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DisasterReqDTO {
    private String id;
    @NotNull
    @NotBlank
    private String area;
    @NotNull
    @NotBlank
    private Double lat;
    @NotNull
    @NotBlank
    private Double lon;
    private Double level=0.5;
    private DisasterType disasterType=DisasterType.FLOODING;
}
