package com.github.gun2.beadalbujok.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 주문
 * @TableName order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    /**
     * 
     */
    private Integer id;

    /**
     * 주문자 member id
     */
    private Integer memberId;
    private Menu menu;

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

}