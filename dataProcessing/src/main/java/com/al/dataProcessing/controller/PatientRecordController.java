package com.al.dataProcessing.controller;

import com.al.dataProcessing.model.PatientRecordSummaryDTO;
import com.al.dataProcessing.service.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PatientRecordController {

    @RestController
    @RequestMapping("/api/patient")
    public class MetricController {

        @Autowired
        private PatientRecordService patientRecordService;

        @GetMapping("/records")
        public List<PatientRecordSummaryDTO> getMetrics(
                @RequestParam String braceletId,
                @RequestParam LocalDate start,
                @RequestParam LocalDate end,
                @RequestParam int limit) {

            return patientRecordService.getHourlyMetrics(braceletId, start, end, limit);
        }
    }
}
