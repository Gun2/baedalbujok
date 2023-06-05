package com.github.gun2.beadalbujok.dto;

import lombok.Getter;

@Getter
public class SuccessResponseDto<T> extends BaseResponseDto {
    private T data;

    public SuccessResponseDto(T data){
        this.data = data;
    }

    public final static <T> SuccessResponseDto of(T data){
        return new SuccessResponseDto(data);
    }
}
