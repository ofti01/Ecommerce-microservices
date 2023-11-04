package com.lotfi.productservice.web;

import com.lotfi.productservice.dtos.CategoryDto;
import com.lotfi.productservice.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllStudents() {
        List<CategoryDto> categoryDtos = categoryService.getAll();
        if (categoryDtos == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CategoryDto categoryDto){
        CategoryDto cat = categoryService.save(categoryDto);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
