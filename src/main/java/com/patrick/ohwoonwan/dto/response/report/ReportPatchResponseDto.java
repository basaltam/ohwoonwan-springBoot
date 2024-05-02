package com.patrick.ohwoonwan.dto.response.report;

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
public class ReportPatchResponseDto extends ResponseDto{
    private ReportPatchResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<ReportPatchResponseDto> success(){
        ReportPatchResponseDto responseBody = new ReportPatchResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistReport(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_REPORT, ResponseMessage.NO_EXIST_REPORT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
