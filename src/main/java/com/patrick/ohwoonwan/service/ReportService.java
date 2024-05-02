package com.patrick.ohwoonwan.service;

import com.patrick.ohwoonwan.domain.UserEntity;
import com.patrick.ohwoonwan.dto.request.report.ReportCreationRequestDto;
import com.patrick.ohwoonwan.dto.request.report.ReportPatchRequestDto;
import com.patrick.ohwoonwan.dto.request.report.ReportSearchRequestDto;
import com.patrick.ohwoonwan.dto.response.report.ReportCreationResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportDeleteResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportPatchResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportSearchResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/
public interface ReportService {
    ResponseEntity<? super ReportCreationResponseDto> post(ReportCreationRequestDto dto, String userId);
    ResponseEntity<? super ReportDeleteResponseDto> delete(Integer wId, String userId);
    ResponseEntity<? super ReportPatchResponseDto> update(ReportPatchRequestDto dto, String userId, Integer wId);
    ResponseEntity<? super ReportSearchResponseDto> findBywId(Integer wId, String userId);
    ResponseEntity<? super ReportSearchResponseDto> finaAll(String userId);

}
