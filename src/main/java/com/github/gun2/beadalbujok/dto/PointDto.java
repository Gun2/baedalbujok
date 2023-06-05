package com.github.gun2.beadalbujok.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class PointDto {

    /**
     *
     */
    private Integer id;

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


    @Getter
    @Setter
    @ToString
    public static class PresentRequest{

        /**
         * 선물받을 아이디
         */
        @NotBlank
        private String username;

        /**
         * 선물할 포인트
         */
        @NotNull
        @Min(0)
        private Long point;
        
    }


}
