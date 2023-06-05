package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findAll();

}




