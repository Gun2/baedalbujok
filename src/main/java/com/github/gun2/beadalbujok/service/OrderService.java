package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Order;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    @Transactional
    void order(Member orderMember, Integer menuId);

    List<Order> findAll(Authentication authentication);
}
