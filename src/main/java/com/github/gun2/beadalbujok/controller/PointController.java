package com.github.gun2.beadalbujok.controller;

import com.github.gun2.beadalbujok.constant.SuccessCode;
import com.github.gun2.beadalbujok.domain.PointTransaction;
import com.github.gun2.beadalbujok.dto.PointDto;
import com.github.gun2.beadalbujok.dto.SuccessResponseDto;
import com.github.gun2.beadalbujok.service.PointService;
import com.github.gun2.beadalbujok.service.PointTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/point")
@Slf4j
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final PointTransactionService pointTransactionService;

    @GetMapping("/present")
    public String present(Model model){

        return "point/present";
    }

    @GetMapping("/history")
    public String pointHistory(Model model){

        return "point/pointHistory";
    }

    @RestController
    @RequestMapping("/api")
    public class Api{
        @PostMapping("/v1/point/present")
        public ResponseEntity present(Authentication authentication,
                                      @Validated @RequestBody PointDto.PresentRequest presentRequest){
            pointService.present(authentication, presentRequest);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.OK);
        }

        //TODO: [취약점포인트] member 데이터 노출
        @GetMapping("/v1/point-transaction")
        public ResponseEntity pointTransaction(Authentication authentication){
            List<PointTransaction> pointTransactionList = pointTransactionService.findAllByAuthentication(authentication);
            return SuccessResponseDto.of(pointTransactionList).toResponseEntity(SuccessCode.OK);
        }
    }
}
