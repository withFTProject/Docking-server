package com.lgcns.Docking.letter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;


import java.util.Optional;


@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("admin"); //TODO: 카카오 로그인 붙일 때, 수정
    }
}
