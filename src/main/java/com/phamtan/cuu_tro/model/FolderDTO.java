package com.phamtan.cuu_tro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FolderDTO {
    private String name;
    private String url;
    private String pathDropBox;
}
