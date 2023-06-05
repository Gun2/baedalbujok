package com.github.gun2.beadalbujok.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 기프티콘
 * @TableName gifticon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gifticon {
    /**
     * 
     */
    private Integer id;

    /**
     * 리워드 포인트
     */
    private Long amount;

    /**
     * 사용유무
     */
    private String use;

    /**
     * 사용자 member id
     */
    private Integer useMemberId;

    private Member useMember;

    /**
     * 기프티콘 번호
     */
    private String serialNumber;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

}