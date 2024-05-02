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
public class ReportDeleteRequestDto {

    @NotNull
    private Long wId;

}
