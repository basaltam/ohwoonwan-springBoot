package com.patrick.ohwoonwan.service.implement;

import com.patrick.ohwoonwan.domain.ReportEntity;
import com.patrick.ohwoonwan.domain.UserEntity;
import com.patrick.ohwoonwan.dto.request.report.ReportCreationRequestDto;
import com.patrick.ohwoonwan.dto.request.report.ReportPatchRequestDto;
import com.patrick.ohwoonwan.dto.request.report.ReportSearchRequestDto;
import com.patrick.ohwoonwan.dto.response.ResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportCreationResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportDeleteResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportPatchResponseDto;
import com.patrick.ohwoonwan.dto.response.report.ReportSearchResponseDto;
import com.patrick.ohwoonwan.repository.ReportRepository;
import com.patrick.ohwoonwan.repository.UserRepository;
import com.patrick.ohwoonwan.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    public ResponseEntity<? super ReportCreationResponseDto> post(ReportCreationRequestDto dto, String userId) {
        try {

            UserEntity user = userRepository.findByUserId(userId);
            if (user== null) return ReportCreationResponseDto.notExistUser();
            ReportEntity reportEntity = new ReportEntity(dto, user);
            reportRepository.save(reportEntity);
            return ReportCreationResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }

    @Override
    public ResponseEntity<? super ReportDeleteResponseDto> delete(Integer wId, String userId) {
        try {
            UserEntity existedUser = userRepository.findByUserId(userId);
            if(existedUser == null) return ReportDeleteResponseDto.notExistUser();

            ReportEntity reportEntity = reportRepository.findBywId(wId);
            if(reportEntity == null) return ReportDeleteResponseDto.notExistReport();

            reportRepository.deleteBywId(wId);

            return ReportDeleteResponseDto.success();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }

    }

    @Override
    public ResponseEntity<? super ReportPatchResponseDto> update(ReportPatchRequestDto dto, String userId, Integer wId) {
        try{
            UserEntity user = userRepository.findByUserId(userId);
            if(user == null) return ReportPatchResponseDto.notExistUser();

            ReportEntity reportEntity = reportRepository.findBywId(wId);
            if(reportEntity==null) return ReportPatchResponseDto.notExistReport();

            reportEntity.patchReportEntity(dto, user);
            reportRepository.save(reportEntity);

            return ReportPatchResponseDto.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }

    @Override
    public ResponseEntity<? super ReportSearchResponseDto> findBywId(Integer wId, String userId) {
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity==null) return ReportSearchResponseDto.notExistUser();

            ReportEntity reportEntity = reportRepository.findBywId(wId);
            if(reportEntity==null) return ReportSearchResponseDto.notExistReport();

            return ReportSearchResponseDto.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }

    @Override
    public ResponseEntity<? super ReportSearchResponseDto> finaAll(String userId) {
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity==null) return ReportSearchResponseDto.notExistUser();

            List<ReportEntity> reportEntity = reportRepository.findAll();
            if(reportEntity.isEmpty()) return ReportSearchResponseDto.notExistReport();

            return ReportSearchResponseDto.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.dataBaseError();
        }
    }


}
