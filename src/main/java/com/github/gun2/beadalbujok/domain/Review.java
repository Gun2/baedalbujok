package com.github.gun2.beadalbujok.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 음식 리뷰
 * @TableName review
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    /**
     * 
     */
    private Integer id;

    /**
     * 리뷰내용
     */
    private String content;

    /**
     * 작성자 member id
     */
    private Integer memberId;

    /**
     * 리뷰 음식 menu id
     */
    private Integer menuId;

    /**
     * 평점
     */
    private Integer evaluation;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

    private String imageName;
}