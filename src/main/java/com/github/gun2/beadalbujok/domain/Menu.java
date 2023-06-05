package com.github.gun2.beadalbujok.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 배달메뉴
 * @TableName menu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    /**
     * 
     */
    private Integer id;

    /**
     * 음식명
     */
    private String name;

    /**
     * 가격
     */
    private Long price;

    /**
     * 메뉴 설명
     */
    private String desc;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

    /**
     * image 파일 이름
     */
    private String imageName;

    /**
     * 메뉴를 생성한 member id
     */
    private Integer memberId;
}