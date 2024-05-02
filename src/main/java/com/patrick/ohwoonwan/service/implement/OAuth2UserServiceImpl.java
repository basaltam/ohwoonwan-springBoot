package com.patrick.ohwoonwan.service.implement;

import com.patrick.ohwoonwan.domain.CustomOauth2User;
import com.patrick.ohwoonwan.domain.UserEntity;
import com.patrick.ohwoonwan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String oauth2ClientName = request.getClientRegistration().getClientName();
//        try{
//            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        UserEntity userEntity = null;
        String userId = null;
        String email  = "email@email.com";

        if(oauth2ClientName.equals("kakao")){
            userId = "kakao_"+oAuth2User.getAttributes().get("id");
            userEntity = new UserEntity("email@email.com", "kakao");
        }

        if(oauth2ClientName.equals("naver")){
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            userId = "naver_"+responseMap.get("id").substring(0, 14);
            email = responseMap.get("email");
            userEntity = new UserEntity(email, "naver");
        }
        userRepository.save(userEntity);

//        if(oauth2ClientName.equals("google")){
//
//        }

        return new CustomOauth2User(userId);
    }
}
