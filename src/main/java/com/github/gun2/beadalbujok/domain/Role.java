package com.github.gun2.beadalbujok.domain;

import lombok.Data;

import java.util.Date;

/**
 * 역할
 * @TableName role
 */
@Data
public class Role {
    /**
     * 
     */
    private String id;

    /**
     * 역할 명
     */
    private String name;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

}