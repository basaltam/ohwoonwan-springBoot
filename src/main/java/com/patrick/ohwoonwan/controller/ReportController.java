package com.patrick.ohwoonwan.controller;

import com.patrick.ohwoonwan.dto.request.report.ReportCreationRequestDto;
import com.patrick.ohwoonwan.dto.request.report.ReportPatchRequestDto;
import com.patrick.ohwoonwan.dto.response.report.ReportCreationResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportDeleteResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportPatchResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportSearchResponseDto;
import com.patrick.ohwoonwan.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/write")
    public ResponseEntity<? super ReportCreationResponseDto> post
            (@RequestBody @Valid ReportCreationRequestDto requestBody, @AuthenticationPrincipal String userId){
        ResponseEntity<? super ReportCreationResponseDto> response = reportService.post(requestBody, userId);
        return response;
    }

    @DeleteMapping("/delete/{ReportNumber}")
    public ResponseEntity<? super ReportDeleteResponseDto> deleteReport
            (@PathVariable("ReportNumber") Integer wId, @AuthenticationPrincipal String userId){
        ResponseEntity<? super ReportDeleteResponseDto > response = reportService.delete(wId, userId);
        return response;
    }

    @PatchMapping("/update/{ReportNumber}")
    public ResponseEntity<? super ReportPatchResponseDto> updateReport
            (@RequestBody @Valid ReportPatchRequestDto requestBody, @PathVariable("ReportNumber") Integer wId,
             @AuthenticationPrincipal String userId){
        ResponseEntity<? super ReportPatchResponseDto> response = reportService.update(requestBody, userId, wId);
        return response;
    }

    @GetMapping("/get/{ReportNumber}")
    public ResponseEntity<? super ReportSearchResponseDto> findReport
            (@PathVariable("ReportNumber") Integer wId, @AuthenticationPrincipal String userId){
        ResponseEntity<? super ReportSearchResponseDto> response = reportService.findBywId(wId, userId);
        return response;
    }

    @GetMapping("/get/reports")
    public ResponseEntity<? super ReportSearchResponseDto> findAll
            (@AuthenticationPrincipal String userId){
        ResponseEntity<? super ReportSearchResponseDto> response = reportService.finaAll(userId);
        return response;
    }


}
