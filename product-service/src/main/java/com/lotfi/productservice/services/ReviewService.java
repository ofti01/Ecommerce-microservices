package com.lotfi.productservice.services;

import com.lotfi.productservice.dtos.ProductDto;
import com.lotfi.productservice.dtos.ReviewDto;
import com.lotfi.productservice.entities.Review;
import com.lotfi.productservice.exceptions.ResourceNotFound;
import com.lotfi.productservice.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> getAll(){
        log.debug("request to get all review");
        return this.reviewRepository.findAll()
                .stream()
                .map(ReviewService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReviewDto getById(long id){
        log.debug("get review with id",id);
        return this.reviewRepository.findById(id)
                .map(ReviewService::mapToDto)
                .orElseThrow(
                        () -> new ResourceNotFound(id)
                );
    }

    public ReviewDto save(ReviewDto reviewDto){
        log.debug("request to save id: {}", reviewDto);
        Review rev = this.reviewRepository.save(mapFromDto(reviewDto));
        return mapToDto(rev);
    }

    public ReviewDto edit(ReviewDto reviewDto,long id) {
        log.debug("Request to update review : {}", id);
        Review review = this.reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound(id));
        return mapToDto(this.reviewRepository.save(mapFromDto(reviewDto)));
    }

    public void delete(long id){
        log.debug("Request to delete Student : {}", id);
        Review student = reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound(id));
        this.reviewRepository.deleteById(id);
    }

    public void deleteAll(){
        log.debug("Request to delete all students ");
        this.reviewRepository.deleteAll();
    }
    public static Review mapFromDto(ReviewDto reviewDto){
        if (reviewDto ==null) return null;
        else
            return
            Review.builder()
                    .id(reviewDto.getId())
                    .title(reviewDto.getTitle())
                    .description(reviewDto.getDescription())
                    .rating(reviewDto.getRating())
                    .build();
    }

    public static ReviewDto mapToDto(Review review){

        if (review ==null) return null;
        else
            return
                    ReviewDto.builder()
                            .id(review.getId())
                            .title(review.getTitle())
                            .description(review.getDescription())
                            .rating(review.getRating())
                            .build();
    }
}
