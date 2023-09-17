package com.inmar.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private long categoryId;
    private String category;
    private String description;
}
