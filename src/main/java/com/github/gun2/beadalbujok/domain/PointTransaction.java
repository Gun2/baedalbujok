package com.github.gun2.beadalbujok.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 포인트 사용 내역
 * @TableName point_transaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointTransaction {
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

    private Point targetPoint;

    /**
     * 
     */
    private Date createdDate;

}