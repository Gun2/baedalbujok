package com.github.gun2.beadalbujok.dto;

import java.util.Date;

public class PointTransactionDto {

    /**
     *
     */
    private Integer id;

    /**
     * point id
     */
    private Integer pointId;

    /**
     * 당시 잔액
     */
    private Long balance;

    /**
     * 포인트 증감 차감 액
     */
    private Long amount;

    /**
     * 선물하기, 선물받기 관련 사용자
     */
    private Integer targetPointId;

    /**
     *
     */
    private Date createdDate;

}
