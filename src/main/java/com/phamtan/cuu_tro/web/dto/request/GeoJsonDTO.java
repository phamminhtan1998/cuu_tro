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
    private Long lat;
    @NotNull
    @NotEmpty
    @NotBlank
    private Long lon;
    private Double distance=1.0;
}
