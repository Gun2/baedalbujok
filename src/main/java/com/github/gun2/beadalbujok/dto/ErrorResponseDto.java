package com.github.gun2.beadalbujok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDto extends BaseResponseDto {

    private List<ClientFieldError> errors = new ArrayList<>();


    public ErrorResponseDto(List<FieldError> errors){
        this.errors = errors.stream().map(ClientFieldError::new).toList();
    }

    public final static ErrorResponseDto of(List<FieldError> errors){
        return new ErrorResponseDto(errors);
    }



    @Getter
    class ClientFieldError{
        String field;
        String defaultMessage;
        ClientFieldError(FieldError fieldError){
            this.field = fieldError.getField();
            this.defaultMessage = fieldError.getDefaultMessage();
        }
    }
}