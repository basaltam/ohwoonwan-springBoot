package com.patrick.ohwoonwan.domain;

import com.patrick.ohwoonwan.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user")
public class UserEntity {

    @Id
    private String userId;

    @Column(name = "userEmail", nullable = false, length = 150)
    private String userEmail; //유저 이메일

    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @Column(name="userName", nullable = false, length = 10)
    private String userName;

    @Column(name = "userAge", nullable = false, length = 2)
    private int userAge;

    @Column(name = "regDate", nullable = false)
    private LocalDateTime regDate;

    @Column(name="gender", updatable = true)
    private String gender;

    @Column(name="role", updatable = false)
    private String role;

    @Column(name="type", updatable = false)
    private String type;

    public UserEntity(SignUpRequestDto dto){
        this.userId = dto.getUserId();
        this.userEmail = dto.getEmail();
        this.userPassword = dto.getPassword();
        this.userName = dto.getUserName();
        this.gender = dto.getGender();
        this.userAge = dto.getUserAge();
        this.type = "app";
        this.role = "ROLE_USER";
        this.regDate = LocalDateTime.now();
    }

    public UserEntity(String userEmail, String type){
        this.userEmail = userEmail;
        this.userPassword = "password";
        this.type = type;
        this.role = "ROLE_USER";
    }

}
