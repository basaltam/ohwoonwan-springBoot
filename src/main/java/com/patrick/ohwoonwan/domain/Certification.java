package com.patrick.ohwoonwan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="cerification")
public class Certification {

    @Id
    private String userId;

    @Column(name ="email", updatable = false)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "certificationNumber")
    private String certificationNumber;

    public Certification(String userId,
                         String email,
                         String certificationNumber){
        this.userId=userId;
        this.email = email;
        this.certificationNumber = certificationNumber;

    }

}
