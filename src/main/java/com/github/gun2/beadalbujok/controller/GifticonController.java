package com.github.gun2.beadalbujok.controller;

import com.github.gun2.beadalbujok.constant.SuccessCode;
import com.github.gun2.beadalbujok.domain.Gifticon;
import com.github.gun2.beadalbujok.dto.GifticonDto;
import com.github.gun2.beadalbujok.dto.SuccessResponseDto;
import com.github.gun2.beadalbujok.service.GifticonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gifticon")
public class GifticonController {

    private final GifticonService gifticonService;

    @GetMapping("/use")
    public String useForm(){
        return "gifticon/gifticonForm";
    }


    @GetMapping("")
    public String gifticon(){
        return "gifticon/gifticon";
    }

    @RestController
    @RequestMapping("/api")
    public class Api{

        @PostMapping("/v1/gifticon/use")
        public ResponseEntity use(
                @Validated @RequestBody GifticonDto.UseRequest useRequest,
                Authentication authentication
                ){
            Gifticon gifticon = gifticonService.use(authentication, useRequest);
            return SuccessResponseDto.of(gifticon).toResponseEntity(SuccessCode.OK);
        }

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @GetMapping("/v1/gifticon")
        public ResponseEntity list(){
            List<GifticonDto> allEager = gifticonService.findAllEager();
            return SuccessResponseDto.of(allEager).toResponseEntity(SuccessCode.OK);
        }

        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @PostMapping("/v1/gifticon")
        public ResponseEntity create() {
            gifticonService.createGificon();
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.CREATED);
        }
    }
}
