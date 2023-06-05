package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.domain.Menu;
import com.github.gun2.beadalbujok.dto.MenuDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MenuService {


    int insert(MenuDto.FormRequest formRequest,
               Authentication authentication) throws IOException;

    int update(MenuDto.FormRequest formRequest,
               Authentication authentication) throws IOException;

    void delete(Integer id);

    List<MenuDto.CardResponse> findAllCardType();

    Optional<Menu> findById(Integer id);
}
