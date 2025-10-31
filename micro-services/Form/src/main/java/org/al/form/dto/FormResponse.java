package org.al.form.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormResponse {

    private UUID id;
    private UUID patientId;
    private UUID issuerId;
    private String data;
    private String type;
}