package com.lotfi.productservice.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import java.util.Set;

@Data
@Builder
public class ProductDto {

    private long id;
    @NotBlank(message = "Username must not be null and must contain 4 or more characters")
    private String name;

    private String description;

    @NotEmpty(message = "First name cannot be null and must have size greater than 0")
    @NotNull
    private BigDecimal price;

    @NotEmpty(message = "First name cannot be null and must have size greater than 0")
    private Integer quantity;

    private String status;

    private Integer salesCounter;

    @NotEmpty(message = "First name cannot be null and must have size greater than 0")
    private CategoryDto category;

    private Set<ReviewDto> reviews;
}
