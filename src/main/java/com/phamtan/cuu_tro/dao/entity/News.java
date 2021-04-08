package com.phamtan.cuu_tro.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "new")
public class News  extends BaseEntity{

    @Id
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
