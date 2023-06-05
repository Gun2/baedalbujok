package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.dto.ReviewDto;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface ReviewService {

    List<ReviewDto.SearchResponse> search(ReviewDto.SearchRequest searchRequest);

    @Transactional
    void insert(Authentication authentication, ReviewDto.FormRequest formRequest) throws IOException;
}
