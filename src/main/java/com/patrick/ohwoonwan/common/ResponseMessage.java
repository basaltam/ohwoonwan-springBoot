package com.patrick.ohwoonwan.common;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/
public interface ResponseMessage {
    String SUCCESS = "success";
    String VALIDATION_FAIL = "validation failed";
    String DUPLICATE_ID = "duplicate id";
    String SIGN_IN_FAIL = "sing in fail";
    String CERTIFICATION_FAIL = "certification fail";
    String DATABASE_ERROR = "database_error";
    String MAIL_FAIL = "Mail Send Failed";
    String NOT_EXIST_USER = "Not Exist User";
    String NO_EXIST_REPORT= "Not Exist Report";

    String UPDATE_FAIL = "Update Fail";
}
