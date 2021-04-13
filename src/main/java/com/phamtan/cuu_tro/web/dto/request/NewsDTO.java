package com.phamtan.cuu_tro.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsDTO implements Serializable {
    private String id;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    private String description;
    private String shortDescription;
    @NotBlank
    @NotNull
    private String content;
    private String author="Anonymous";
}
