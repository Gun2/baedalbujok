package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.domain.Role;
import com.github.gun2.beadalbujok.mapper.RoleMapper;
import com.github.gun2.beadalbujok.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public List<Role> findAll(){
        return roleMapper.findAll();
    }
}




