package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Review;
import com.github.gun2.beadalbujok.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface ReviewMapper {

    List<ReviewDto.SearchResponse> search(ReviewDto.SearchRequest searchRequest);
    int insert(Review review);
}




