package com.lotfi.productservice.web;


import com.lotfi.productservice.dtos.ReviewDto;
import com.lotfi.productservice.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @GetMapping
    public ResponseEntity<List<ReviewDto>> findAll() {
        List<ReviewDto> reviews = reviewService.getAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> findById(@PathVariable Long id) {
        ReviewDto reviewDto = reviewService.getById(id);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody @Valid ReviewDto reviewDto) {
        ReviewDto pr = reviewService.save(reviewDto);
        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
