package com.github.gun2.beadalbujok.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

/**
 * 사전 인증 컴포넌트
 */
@Component
public class PreAuthComponent {
    private static final String KEY_NAME = "PRE_AUTH_UUID";
    private static final String KEY = UUID.randomUUID().toString();

    @Value("${app.pre-auth.password:}")
    private String preAuthPassword;

    /**
     * 요청에 사전 인증 쿠키값이 옳바른지 확인
     * @param request
     * @return 옳바른 값이면 true반환
     */
    public boolean isPreAuthenticated(HttpServletRequest request){
        if(request != null){
            Optional<Cookie> preAuthCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> KEY_NAME.equals(cookie.getName()))
                    .findAny();
            if(preAuthCookie.isPresent()){
                if(KEY.equals(preAuthCookie.get().getValue())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 응답에 사전 인증 쿠키값을 설정한다.
     * @param response
     */
    public void setPreAuthKeyInCookie(HttpServletResponse response){
        response.addCookie(new Cookie(KEY_NAME, KEY));
    }

    /**
     * 사전인증번호가 일치하는지 확인
     * @param preAuthPassword
     * @return
     */
    public boolean isEqualsPreAuthPassword(String preAuthPassword){
        return this.preAuthPassword.equals(preAuthPassword);
    }

    /**
     * 사전 인증 기능이 활성화 상태인지 확인
     * @return
     */
    public boolean isActive(){
        return StringUtils.isNotBlank(this.preAuthPassword);
    }

}
