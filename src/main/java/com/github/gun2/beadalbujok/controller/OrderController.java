package com.github.gun2.beadalbujok.controller;

import com.github.gun2.beadalbujok.constant.SuccessCode;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Order;
import com.github.gun2.beadalbujok.dto.MenuDto;
import com.github.gun2.beadalbujok.dto.SuccessResponseDto;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.MenuService;
import com.github.gun2.beadalbujok.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final MenuService menuService;

    private final MemberService memberService;

    @GetMapping("")
    public String list(Model model) {
        List<MenuDto.CardResponse> cardResponseList = menuService.findAllCardType();
        model.addAttribute("menuList", cardResponseList);
        return "order/order";
    }

    @GetMapping("/history")
    public String history(Model model, Authentication authentication) {
        return "order/orderHistory";
    }

    @RestController
    @RequestMapping("/api")
    public class Api {
        @PostMapping("/v1/order-menu/{menuId}")
        public ResponseEntity orderMenu(
                Authentication authentication,
                @PathVariable("menuId") Integer menuId) {
            Optional<Member> memberOptional = memberService.findByUsernameEager(authentication.getName());
            if (memberOptional.isEmpty()){
                throw new IllegalArgumentException("사용자 정보를 찾을 수 없습니다.");
            }
            orderService.order(memberOptional.get(), menuId);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.OK);
        }

        @GetMapping("/v1/order")
        public ResponseEntity list(
                Authentication authentication
        ){
            List<Order> orderList = orderService.findAll(authentication);
            return SuccessResponseDto.of(orderList).toResponseEntity(SuccessCode.OK);
        }
    }
}
