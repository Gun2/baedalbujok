package com.github.gun2.beadalbujok.dto;

import com.github.gun2.beadalbujok.constant.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseResponseDto {
    private String code = "";
    private String message = "";

    public ResponseEntity toResponseEntity(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        return ResponseEntity.status(responseCode.getStatus()).body(this);
    }
}
