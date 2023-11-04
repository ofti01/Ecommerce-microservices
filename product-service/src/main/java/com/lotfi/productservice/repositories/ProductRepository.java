package com.lotfi.productservice.repositories;

import com.lotfi.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select c from Product c where c.name like :pr")
    Optional<List<Product>> findProduct(@Param("pr") String keyword);
}
