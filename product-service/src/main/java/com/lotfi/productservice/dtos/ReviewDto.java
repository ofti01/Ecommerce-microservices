package com.lotfi.productservice.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {

    private long id;
    private String title;
    @NotBlank(message = "description is mandatory not empty")
    private String description;
    private Long rating;
}
