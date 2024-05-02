package com.patrick.ohwoonwan.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
