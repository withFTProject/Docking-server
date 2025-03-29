package com.lgcns.Docking.user.service;

import com.lgcns.Docking.user.dto.CustomOAuth2User;
import com.lgcns.Docking.user.dto.KakaoResponse;
import com.lgcns.Docking.user.dto.OAuth2Response;
import com.lgcns.Docking.user.dto.UserDTO;
import com.lgcns.Docking.user.entity.User;
import com.lgcns.Docking.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null; // 인터페이스 바구니 생성,,

        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        // 우리 서버에서 관리할 OAuth2 인증 시 제공하는 사용자의 고유 ID를 반환
        String username = oAuth2Response.getProviderId();

        User existData = userRepository.findByUsername(username);

        if (existData == null) {

            User userEntity = new User();

            userEntity.setUsername(username);
            userEntity.setName(oAuth2Response.getName());

            User user = userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();

            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            userDTO.setId(String.valueOf(user.getId()));

            return new CustomOAuth2User(userDTO);
        }
        else {

            existData.setName(oAuth2Response.getName());

            User user = userRepository.save(existData);

            UserDTO userDTO = new UserDTO();

            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());

            /*
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());
            */

            userDTO.setId(String.valueOf(user.getId()));

            return new CustomOAuth2User(userDTO);
        }
    }
}