package com.lgcns.Docking.security.oauth2;

import com.lgcns.Docking.security.jwt.JWTUtil;
import com.lgcns.Docking.user.dto.CustomOAuth2User;
import com.lgcns.Docking.user.entity.User;
import com.lgcns.Docking.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.lgcns.Docking.letter.entity.LetterRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final LetterRepository letterRepository;
    private final UserRepository userRepository;


    public CustomSuccessHandler(JWTUtil jwtUtil, LetterRepository letterRepository, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.letterRepository = letterRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        // username
        String password = customUserDetails.getPassword();

        // id
        String id = customUserDetails.getId();

        // role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // token = username + role
        String token = jwtUtil.createJwt(password, role, id, 60 * 60 * 24 * 30L);

        System.out.println(token);

        // 유저 조회
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        boolean hasSubmitted = letterRepository.existsByUser(user);

        // 쿠키 방식 전달 + 프론트에 리다이렉트
        response.addCookie(createCookie("Authorization", token));
        response.addCookie(createCookie("hasSubmittedLetter", String.valueOf(hasSubmitted)));

        response.sendRedirect("http://localhost:3000/after-login");

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(false);

        return cookie;
    }
}