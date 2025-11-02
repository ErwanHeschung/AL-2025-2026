package org.al.patientmanagement.clients;

import org.al.patientmanagement.dto.PatientRecordSummaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@FeignClient(name = "process-data-service", url = "${process.data.service.url}")
public interface ProcessDataClient {

    @GetMapping("/api/patient/records")
    List<PatientRecordSummaryDTO> getPatientRecords(
            @RequestParam("braceletId") String braceletId,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam("limit") int limit
    );
}