package com.github.gun2.beadalbujok.interceptor;

import com.github.gun2.beadalbujok.component.PreAuthComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 사전 인증 확인 인터셉터
 * 웹 페이지 공개 시 사전 인증 번호 입력을 통하여 인증받은 사용자 인지 확인을 위함
 */
@RequiredArgsConstructor
@Slf4j
public class PreAuthCheckInterceptor implements HandlerInterceptor {

    private final PreAuthComponent preAuthComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!preAuthComponent.isPreAuthenticated(request)){
            response.sendRedirect("/pre-auth");
            return false;
        }
        return true;
    }
}
