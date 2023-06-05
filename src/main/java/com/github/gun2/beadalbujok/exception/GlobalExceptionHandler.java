package com.github.gun2.beadalbujok.exception;

import com.github.gun2.beadalbujok.constant.ErrorCode;
import com.github.gun2.beadalbujok.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * <b>{@link org.springframework.web.bind.annotation.RequestBody}로 받은 값 중 Validation 실패 시 발생</b>
     * @param e {@link MethodArgumentNotValidException}
     * @return {@link ResponseEntity}
     * @see org.springframework.validation.annotation.Validated
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException: {}", e);
        return ErrorResponseDto.of(e.getFieldErrors()).toResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * <b>{@link org.springframework.web.bind.annotation.ModelAttribute}로 받은 값 중 validation 실패 시 발생</b>
     * @param e {@link BindException}
     * @return {@link ResponseEntity}
     * @see org.springframework.validation.annotation.Validated
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponseDto> handleBindException(BindException e) {
        log.error("handleBindException: {}", e);
        return ErrorResponseDto.of(e.getFieldErrors()).toResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * <b>지원하지 않는 메소드 호출 시 발생</b>
     * @param e {@link HttpRequestMethodNotSupportedException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * <b>접근 권한이 없는 경우 발생</b>
     * @param e {@link AccessDeniedException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler({org.springframework.security.access.AccessDeniedException.class})
    protected ResponseEntity<ErrorResponseDto> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException e) {
        log.error("handleAccessDeniedException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.FORBIDDEN);
    }

    /**
     * <b>접근 권한이 없는 경우 발생</b>
     * @param e {@link AccessDeniedException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.HANDLE_ACCESS_DENIED);
    }

    /**
     * <b>잘못된 타입으로 요청한 경우</b>
     * @param e {@link MethodArgumentTypeMismatchException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.error("handleMethodArgumentTypeMismatchException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.INVALID_TYPE_VALUE);
    }

    /**
     * <b>지원하지 않는 Content type 으로 요청한 경우 발생</b>
     * @param e {@link HttpMediaTypeNotSupportedException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        log.error("handleHttpMediaTypeNotSupportedException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.CONTENT_TYPE_NOT_SUPPORTED);
    }

    /**
     * <b>요청한 값을 읽을 수 없는 경우 발생 ex => JSON format이 옳바르지 않은경우</b>
     * @param e {@link HttpMessageNotReadableException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.error("handleHttpMessageNotReadableException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * <b>요청한 값을 읽을 수 없는 경우 발생 ex => JSON format이 옳바르지 않은경우</b>
     * @param e {@link InvalidDataAccessApiUsageException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected ResponseEntity<ErrorResponseDto> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e){
        log.error("handleInvalidDataAccessApiUsageException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * 이미 존재하는 중복된 아이디로 회원가입을 시도한 경우 발생
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicationUsernameException.class)
    protected ResponseEntity<ErrorResponseDto> handleDuplicationUsernameException(DuplicationUsernameException e){
        log.error("handleDuplicateMemberException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.USERNAME_ALREADY_EXIST);
    }

    /**
     * 포인트가 충분하지 않는 경우 발생
     * @param e
     * @return
     */
    @ExceptionHandler(NotEnoughPointException.class)
    protected  ResponseEntity<ErrorResponseDto> handleNotEnoughPointException(NotEnoughPointException e){
        log.error("handleNotEnoughPointException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.NOT_ENOUGH_POINT);
    }

    @ExceptionHandler(NotFoundMemberException.class)
    protected ResponseEntity<ErrorResponseDto> handleNotFoundMemberException(NotFoundMemberException e){
        log.error("handleNotFoundMemberException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.NOT_FOUND_MEMBER);
    }


    @ExceptionHandler(NotFoundGifticonException.class)
    protected ResponseEntity<ErrorResponseDto> handleNotFoundGifticonException(NotFoundGifticonException e){
        log.error("handleNotFoundGifticonException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.NOT_FOUND_GIFTICON);
    }

    @ExceptionHandler(AlreadyUsedGifticonException.class)
    protected ResponseEntity<ErrorResponseDto> handleAlreadyUsedGifticonException(AlreadyUsedGifticonException e){
        log.error("handleAlreadyUsedGifticonException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.ALREADY_USED_GIFTICON);
    }

    @ExceptionHandler(NotEqualsMemberPasswordException.class)
    protected ResponseEntity<ErrorResponseDto> handleNotEqualsMemberPassword(NotEqualsMemberPasswordException e){
        log.error("handleNotEqualsMemberPassword: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.NOT_EQUALS_MEMBER_PASSWORD);
    }


    @ExceptionHandler(ImpossibleUpdateSelfException.class)
    protected ResponseEntity<ErrorResponseDto> handleImpossibleUpdateSelfException(ImpossibleUpdateSelfException e){
        log.error("handleImpossibleUpdateSelfException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.IMPOSSIBLE_UPDATE_SELF);
    }

    /**
     * <b>기타 정의하지 않은 예외 발생 시</b>
     * @param e {@link Exception}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception e){
        log.error("handleException: {}", e);
        return new ErrorResponseDto().toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
