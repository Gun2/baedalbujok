package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface OrderMapper {
    int insert(Order order);

    List<Order> findAllByMemberId(Integer memberId);
}




