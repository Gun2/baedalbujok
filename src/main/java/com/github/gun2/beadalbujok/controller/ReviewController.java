package com.github.gun2.beadalbujok.controller;

import com.github.gun2.beadalbujok.constant.SuccessCode;
import com.github.gun2.beadalbujok.dto.ReviewDto;
import com.github.gun2.beadalbujok.dto.SuccessResponseDto;
import com.github.gun2.beadalbujok.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/menu/{menuId}")
    public String review(@PathVariable("menuId") Integer menuId,
                         @ModelAttribute ReviewDto.SearchRequest searchRequest,
                         Model model){
        searchRequest.setMenuId(menuId);
        List<ReviewDto.SearchResponse> reviewList =
                reviewService.search(searchRequest);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("search", searchRequest);
        return "order/review";
    }

    @GetMapping("/insert/{menuId}")
    public String review(@PathVariable("menuId") Integer menuId,
                         Model model){
        model.addAttribute("menuId", menuId);
        return "order/reviewForm";
    }

    @RestController
    @RequestMapping("/api")
    public class Api{

        @PostMapping("/v1/review")
        public ResponseEntity insert(
                @Validated @ModelAttribute ReviewDto.FormRequest formRequest,
                Authentication authentication
                ) throws IOException {
            reviewService.insert(authentication, formRequest);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.CREATED);
        }
    }
}
