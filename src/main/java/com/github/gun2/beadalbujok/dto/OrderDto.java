package com.github.gun2.beadalbujok.dto;

import com.github.gun2.beadalbujok.domain.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDto implements DomainDto<Order> {
    /**
     *
     */
    private Integer id;

    /**
     * 주문자 member id
     */
    private Integer memberId;

    /**
     * 주문 menu id
     */
    private Integer menuId;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private Date updatedDate;

    private Long price;

    @Override
    public Order toVo() {
        return Order.builder()
                .id(this.id)
                .memberId(this.memberId)
                .menuId(this.menuId)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .price(this.price)
                .build();
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.memberId = order.getMemberId();
        this.menuId = order.getMenuId();
        this.createdDate = order.getCreatedDate();
        this.updatedDate = order.getUpdatedDate();
        this.price = order.getPrice();
    }
}
