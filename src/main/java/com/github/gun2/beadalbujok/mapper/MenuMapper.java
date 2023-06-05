package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Menu;
import com.github.gun2.beadalbujok.dto.MenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper

public interface MenuMapper {

    int update(Menu menu);
    int insert(Menu menu);
    Optional<Menu> findById(Integer id);
    int delete(Integer id);
    int softDelete(Integer id);
    List<Menu> findAll();

    List<MenuDto.CardResponse> findAllCardType();

}




