package com.patrick.ohwoonwan.dto.request.report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReportSearchRequestDto {

    @NotNull
    private Long wId;

    @NotBlank
    private String wSub;

    @NotNull
    private int wSet;

    @NotNull
    private int wRm;

    @NotNull
    private int wWeights;

    @NotBlank
    private String wUnit;
}
