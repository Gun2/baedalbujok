package com.github.gun2.beadalbujok.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 사용자 정보
 * @TableName user
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("Member")
public class Member {
    /**
     * 
     */
    private Integer id;

    private Point point;

    /**
     * 사용자 아이디
     */
    private String username;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 닉네임
     */
    private String name;

    /**
     * 역할 id
     */
    private String roleId;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

}