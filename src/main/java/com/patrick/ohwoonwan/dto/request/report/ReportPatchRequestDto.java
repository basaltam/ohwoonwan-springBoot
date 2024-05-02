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
public class ReportPatchRequestDto {

    @NotBlank
    private String sub;

    @NotNull
    private int set;

    @NotNull
    private int weights;

    @NotNull
    private int rm;

    @NotBlank
    private String unit;

}
