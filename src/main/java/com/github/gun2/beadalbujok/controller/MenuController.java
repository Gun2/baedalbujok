package com.github.gun2.beadalbujok.controller;

import com.github.gun2.beadalbujok.constant.SuccessCode;
import com.github.gun2.beadalbujok.domain.Menu;
import com.github.gun2.beadalbujok.dto.MenuDto;
import com.github.gun2.beadalbujok.dto.SuccessResponseDto;
import com.github.gun2.beadalbujok.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping({"/menu/form", "/menu/form/{id}"})
    public String menuFrom(Model model,
                           @PathVariable(required = false) Integer id,
                           @RequestParam(defaultValue = "false") boolean update) {
        if (id == null) {
            model.addAttribute("mode", "CREATE");
        } else {
            model.addAttribute("mode", "UPDATE");
            Optional<Menu> byId = menuService.findById(id);
            if (!byId.isPresent()) {
                throw new IllegalArgumentException("값이 존재하지 않습니다.");
            }
            model.addAttribute("menu", byId.get());
        }
        return "order/menu";
    }

    @GetMapping({"/menu/order/{id}"})
    public String readMenuFrom(Model model,
                               @PathVariable Integer id,
                               @RequestParam(defaultValue = "false") boolean update) {

        model.addAttribute("mode", "READ");

        Optional<Menu> byId = menuService.findById(id);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("값이 존재하지 않습니다.");
        }
        model.addAttribute("menu", byId.get());
        return "order/menu";
    }

    @RestController
    @RequestMapping("/api")
    public class Api {

        @PostMapping("/v1/menu")
        public ResponseEntity create(
                @Validated @ModelAttribute MenuDto.FormRequest formRequest,
                Authentication authentication
        ) throws IOException {
            menuService.insert(formRequest, authentication);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.CREATED);
        }


        @PutMapping("/v1/menu")
        public ResponseEntity update(
                @Validated @ModelAttribute MenuDto.FormRequest formRequest,
                Authentication authentication
        ) throws IOException {
            menuService.update(formRequest, authentication);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.OK);
        }

        @DeleteMapping("/v1/menu/{id}")
        public ResponseEntity delete(
                @PathVariable Integer id,
                Authentication authentication
        ) throws IOException {
            menuService.delete(id);
            return SuccessResponseDto.of("OK").toResponseEntity(SuccessCode.CREATED);
        }

    }
}
