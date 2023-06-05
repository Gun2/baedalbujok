package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Menu;
import com.github.gun2.beadalbujok.domain.Order;
import com.github.gun2.beadalbujok.mapper.MenuMapper;
import com.github.gun2.beadalbujok.mapper.OrderMapper;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.OrderService;
import com.github.gun2.beadalbujok.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final PointService pointService;
    private final MenuMapper menuMapper;
    private final OrderMapper orderMapper;
    private final MemberService memberService;

    /**
     * 음식을 주문하고 포인트를 지불한다.
     *
     * @param orderMember
     * @param menuId
     */
    @Transactional
    @Override
    public void order(Member orderMember, Integer menuId) {
        Menu menu = menuMapper.findById(menuId).orElseThrow();
        pointService.remittance(orderMember.getId(),
                menu.getMemberId(),
                menu.getPrice());
        orderMapper.insert(Order.builder()
                .memberId(orderMember.getId())
                .menuId(menuId)
                .price(menu.getPrice())
                .build());
    }

    @Override
    public List<Order> findAll(Authentication authentication) {
        Member member = memberService.findByUsername(authentication);
        Map<Integer, Menu> menuMap =
                menuMapper.findAll().stream().collect(Collectors.toMap(Menu::getId, d -> d));

        List<Order> orderList = orderMapper.findAllByMemberId(member.getId());
        for (Order order : orderList) {
            order.setMenu(menuMap.getOrDefault(order.getMenuId(), new Menu()));
        }
        return orderList;
    }


}




