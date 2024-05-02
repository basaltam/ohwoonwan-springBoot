package com.patrick.ohwoonwan.dto.response.auth;

import com.patrick.ohwoonwan.common.ResponseCode;
import com.patrick.ohwoonwan.common.ResponseMessage;
import com.patrick.ohwoonwan.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;
    private String userId;
    private SignInResponseDto(String token, String userId){
        super();
        this.token = token;
        this.expirationTime = 3600;
        this.userId = userId;

    }

    public static ResponseEntity<SignInResponseDto> success(String token, String userId){
        SignInResponseDto responseBody = new SignInResponseDto(token, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
