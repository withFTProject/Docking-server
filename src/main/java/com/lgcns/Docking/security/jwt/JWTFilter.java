package com.lgcns.Docking.security.jwt;

import com.lgcns.Docking.user.dto.CustomOAuth2User;
import com.lgcns.Docking.user.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // JWT가 헤더에 없거나 Bearer로 시작하지 않으면 다음 필터로 넘김
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug("Authorization header missing or malformed");
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer 제거하고 순수 토큰 추출
        String token = authHeader.substring(7);

        // 만료된 토큰
        if (jwtUtil.isExpired(token)) {
            log.debug("Token expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"토큰이 만료되었습니다.\"}");
            return;
        }

        // 토큰에서 사용자 정보 추출
        String username = jwtUtil.getUsername(token);
        String id = jwtUtil.getId(token);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setId(id);

        CustomOAuth2User customUser = new CustomOAuth2User(userDTO);
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                customUser, null, customUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        log.info("JWTFilter 인증 완료: {}", username);

        filterChain.doFilter(request, response);
    }
}
