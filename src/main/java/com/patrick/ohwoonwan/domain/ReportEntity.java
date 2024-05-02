package com.patrick.ohwoonwan.domain;

import com.patrick.ohwoonwan.dto.request.report.ReportCreationRequestDto;
import com.patrick.ohwoonwan.dto.request.report.ReportPatchRequestDto;
import com.patrick.ohwoonwan.dto.response.report.ReportPatchResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @작성자:
 * @작성날짜:
 * @파일명:
 **/

@Getter
@Entity
@Table(name="report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wId;

    @Column(name = "wSub")
    private String wSub;

    @Column(name = "wSet")
    private int wSet;

    @Column(name ="wRm")
    private int wRm;

    @Column(name = "wWeights")
    private int wWeights;

    @Column(name ="wUnit")
    private String wUnit;

    @Column(name = "wDate")
    private String date;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity userId;

    public ReportEntity(ReportCreationRequestDto dto, UserEntity user){

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        this.date = simpleDateFormat.format(now);
        this.wSub = dto.getSub();
        this.wSet = dto.getSet();
        this.wRm = dto.getRm();
        this.wWeights = dto.getWeights();
        this.wUnit = dto.getUnit();
        this.userId = user;
    }

    public void patchReportEntity(ReportPatchRequestDto dto, UserEntity user) {
        this.wSub = dto.getSub();
        this.wSet = dto.getSet();
        this.wRm = dto.getRm();
        this.wWeights = dto.getWeights();
        this.wUnit = dto.getUnit();
        this.userId = user;

    }
}
