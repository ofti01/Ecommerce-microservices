package com.lotfi.productservice.repositories;

import com.lotfi.productservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
