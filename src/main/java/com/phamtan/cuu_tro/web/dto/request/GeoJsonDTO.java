package com.phamtan.cuu_tro.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoJsonDTO {
    @NotNull
    @NotEmpty
    @NotBlank
    private double lat;
    @NotNull
    @NotEmpty
    @NotBlank
    private double lon;
    private Double distance=1.0;


}
