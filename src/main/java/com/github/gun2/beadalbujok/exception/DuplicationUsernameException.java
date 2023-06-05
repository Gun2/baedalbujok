package com.github.gun2.beadalbujok.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 아이디 생성 시 username가 중복됐을 경우 발생
 */
public class DuplicationUsernameException extends AuthenticationException {

    public DuplicationUsernameException(String msg) {
        super(msg);
    }
}
