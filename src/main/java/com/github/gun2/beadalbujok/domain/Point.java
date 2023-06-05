package com.github.gun2.beadalbujok.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 포인트
 * @TableName point
 */
@Data
public class Point {
    /**
     * 
     */
    private Integer id;

    private Member member;

    /**
     * 사용자 member id
     */
    private Integer memberId;

    /**
     * 잔액
     */
    private Long balance;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

}