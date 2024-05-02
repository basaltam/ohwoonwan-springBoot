package com.patrick.ohwoonwan.common;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

public class CertificationNumber {

    public static String getCertificationNumber(){
        String certificationNumber = "";

        for(int count = 0; count<4; count++){
            certificationNumber += (int) (Math.random()*10);
        }

        return certificationNumber;
    }
}
