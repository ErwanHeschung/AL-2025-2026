package org.al.form.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormRequest {

    @NotNull
    private UUID patientId;

    @NotNull
    private UUID issuerId;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String data;

    private String type;
}