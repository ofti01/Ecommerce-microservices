package com.lotfi.productservice.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CategoryDto {

    private long id;

    @NotBlank(message = "Username must not be null and must contain 4 or more characters")
    private String name;

    private String description;

    private Set<ProductDto> products;
}
