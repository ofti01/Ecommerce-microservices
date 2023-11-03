package com.lotfi.productservice.services;

import com.lotfi.productservice.dtos.ProductDto;
import com.lotfi.productservice.entities.Product;
import com.lotfi.productservice.enums.ProductStatus;
import com.lotfi.productservice.exceptions.ResourceNotFound;
import com.lotfi.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAll(){
        log.debug("request to get all categories");
        return this.productRepository
                .findAll()
                .stream()
                .map(ProductService::mapToDto)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public ProductDto getById(long id){
        log.debug("Request to get Category : {}", id);
        return this.productRepository.findById(id)
                .map(ProductService::mapToDto)
                .orElseThrow(
                        () -> new ResourceNotFound(id)
                );
    }

    public ProductDto create(ProductDto productDto){
        return mapToDto(productRepository.save(mapFromDto(productDto)));
    }

    public void delete(long id){
        log.debug("Request to delete product: {}", id);
        Product product= productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound(id)
        );
        if(product != null) productRepository.deleteById(id);

    }

    public static ProductDto mapToDto(Product product) {

        if (product==null) return null;
        else
            return
                    ProductDto.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .quantity(product.getQuantity())
                            .salesCounter(product.getSalesCounter())
                            .category(CategoryService.mapToDto(product.getCategory()))
                            .reviews(product.getReviews()
                                    .stream()
                                    .map(ReviewService::mapToDto)
                                    .collect(Collectors.toSet()))
                            .build();
    }

    public static Product mapFromDto(ProductDto productDto){
        if (productDto==null) return null;
        else {
            return Product.builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .status(ProductStatus.valueOf(productDto.getStatus()))
                    .description(productDto.getDescription())
                    .price(productDto.getPrice())
                    .quantity(productDto.getQuantity())
                    .category(CategoryService.mapFromDto(productDto.getCategory()))
                    .salesCounter(productDto.getSalesCounter())
                    .reviews(productDto.getReviews()
                            .stream()
                            .map(ReviewService::mapFromDto)
                            .collect(Collectors.toSet()))
                    .build();

        }
    }


}
