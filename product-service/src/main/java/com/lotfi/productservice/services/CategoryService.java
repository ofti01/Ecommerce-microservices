package com.lotfi.productservice.services;

import com.lotfi.productservice.dtos.CategoryDto;
import com.lotfi.productservice.entities.Category;
import com.lotfi.productservice.exceptions.ResourceAlreadyExist;
import com.lotfi.productservice.exceptions.ResourceNotFound;
import com.lotfi.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAll(){
        log.debug("request to get all categories");
        return this.categoryRepository
                .findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(long id){
        log.debug("Request to get Category : {}", id);
        return this.categoryRepository.findById(id)
                .map(CategoryService::mapToDto)
                .orElseThrow(
                        () -> new ResourceNotFound(id)
                );
    }

    public CategoryDto save(CategoryDto categoryDto){
        log.debug("Request to create Category : {}", categoryDto);
        Optional<CategoryDto> cat = this.categoryRepository.findByName(categoryDto.getName()).map(CategoryService::mapToDto);
        if(cat.isPresent()) throw  new ResourceAlreadyExist(categoryDto.getId());
        else return
        mapToDto(this.categoryRepository.save(CategoryService.mapFromDto(categoryDto)));
    }

    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        this.categoryRepository.deleteById(id);
    }


    public static Category mapFromDto(CategoryDto categoryDto){
        if(categoryDto == null) return null;
        else {
            return Category.builder()
                    .id(categoryDto.getId())
                    .name(categoryDto.getName())
                    .description(categoryDto.getDescription())
                    .products(categoryDto.getProducts()
                            .stream()
                            .map(ProductService::mapFromDto)
                            .collect(Collectors.toSet()))
                    .build();
        }
    }


    public static CategoryDto mapToDto(Category category){
        if (category ==null) return null;
        else {
            return
            CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .products(category.getProducts()
                            .stream()
                            .map(ProductService::mapToDto)
                            .collect(Collectors.toSet()))
                    .build();
        }
    }
}
