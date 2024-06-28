package com.pillgood.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 세션을 가져옴. 없다면 새로 생성.
        HttpSession session = request.getSession();
        // 인증된 사용자 정보 세션에 저장
        session.setAttribute("loggedInMember", authentication.getPrincipal());
        // 인증 성공 후 "/mypage"로 리디렉션
        response.sendRedirect("/mypage");
    }
}
