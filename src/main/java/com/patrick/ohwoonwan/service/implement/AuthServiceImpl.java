package com.patrick.ohwoonwan.service.implement;


import com.patrick.ohwoonwan.common.CertificationNumber;
import com.patrick.ohwoonwan.dto.request.auth.*;
import com.patrick.ohwoonwan.dto.response.ResponseDto;
import com.patrick.ohwoonwan.dto.response.auth.*;
import com.patrick.ohwoonwan.domain.Certification;
import com.patrick.ohwoonwan.domain.UserEntity;
import com.patrick.ohwoonwan.provider.EmailProvider;
import com.patrick.ohwoonwan.provider.JwtProvider;
import com.patrick.ohwoonwan.repository.CertificationRepository;
import com.patrick.ohwoonwan.repository.ReportRepository;
import com.patrick.ohwoonwan.repository.UserRepository;
import com.patrick.ohwoonwan.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;
    private final JwtProvider jwtProvider;
    private final CertificationRepository certificationRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try{
            String userId = dto.getId();
            boolean isExistId = userRepository.existsById(userId);
            if(isExistId) return IdCheckResponseDto.duplicateId();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try{
            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return EmailCertificationResponseDto.duplicatedId();

            String certificationNumber = CertificationNumber.getCertificationNumber();
            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            Certification certification = new Certification(userId, email, certificationNumber);
            certificationRepository.save(certification);

        }catch (Exception e){
            e.printStackTrace();;
            return ResponseDto.dataBaseError();
        }
        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try{
            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            Certification certification = certificationRepository.findByUserId(userId);

            if(certification==null) return CheckCertificationResponseDto.certificationFail();
            boolean isMatched = certification.getEmail().equals(email) && certification.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return CheckCertificationResponseDto.certificationFail();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> register(SignUpRequestDto dto) {
        try{

            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return SignUpResponseDto.duplicatedId();

            String email =dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            Certification certification = certificationRepository.findByUserId(userId);
            boolean isMatched = certification.getEmail().equals(email) && certification.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return SignUpResponseDto.certificationFail();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity user = new UserEntity(dto);
            userRepository.save(user);


            certificationRepository.deleteByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        String userId = null;
        try{

            userId = dto.getId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity==null)SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getUserPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(userId);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
        return SignInResponseDto.success(token, userId);
    }

}
