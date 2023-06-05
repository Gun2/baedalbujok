package com.github.gun2.beadalbujok.interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 인증 확인 인터셉터
 * 애플리케이션 재 실행 시 세션이 유지되어 인증은 되었으나 인증객체 정보가 없는 경우를 탐지하기 위함
 */
public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
            if (securityContext != null) {
                Authentication authentication = securityContext.getAuthentication();
                // Authentication 객체를 사용하여 원하는 로직을 수행합니다.
                if (authentication.isAuthenticated() &&
                        authentication.getPrincipal() == null || authentication.getName() == null){
                    response.sendRedirect("/logout");
                    return false;
                }
            }
        }
        return true;
    }
}
