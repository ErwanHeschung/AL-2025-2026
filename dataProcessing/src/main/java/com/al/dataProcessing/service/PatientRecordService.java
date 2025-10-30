package com.al.dataProcessing.service;

import com.al.dataProcessing.model.Activity;
import com.al.dataProcessing.model.HourlyActivity;
import com.al.dataProcessing.model.HourlyAverage;
import com.al.dataProcessing.model.PatientRecordSummaryDTO;
import com.al.dataProcessing.repository.AccelerometerRepository;
import com.al.dataProcessing.repository.BloodOxygenRepository;
import com.al.dataProcessing.repository.HeartMetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class PatientRecordService {

    @Autowired private HeartMetricRepository heartMetricRepository;
    @Autowired private BloodOxygenRepository bloodOxygenRepository;
    @Autowired private AccelerometerRepository accelerometerRepository;

    public List<PatientRecordSummaryDTO> getHourlyMetrics(
            String braceletId, LocalDate startDate, LocalDate endDate, int limit) {

        OffsetDateTime startDT = startDate.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
        OffsetDateTime endDT = endDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();

        List<HourlyAverage> heartAvgs = heartMetricRepository.findHourlyAverages(braceletId, startDT, endDT);
        List<HourlyAverage> bloodOxyAvgs = bloodOxygenRepository.findHourlyAverages(braceletId, startDT, endDT);
        List<HourlyActivity> accelActivities = accelerometerRepository.findHourlyActivity(braceletId, startDT, endDT);

        Map<Instant, PatientRecordSummaryDTO> summaryMap = new TreeMap<>();

        for (HourlyAverage avg : heartAvgs) {
            PatientRecordSummaryDTO dto = summaryMap
                    .computeIfAbsent(avg.getHour(), k -> new PatientRecordSummaryDTO(k));
            dto.setBpm(avg.getAverage());
        }

        for (HourlyAverage avg : bloodOxyAvgs) {
            PatientRecordSummaryDTO dto = summaryMap
                    .computeIfAbsent(avg.getHour(), k -> new PatientRecordSummaryDTO(k));
            dto.setBloodOxygen(avg.getAverage());
        }

        for (HourlyActivity act : accelActivities) {
            PatientRecordSummaryDTO dto = summaryMap
                    .computeIfAbsent(act.getHour(), k -> new PatientRecordSummaryDTO(k));
            dto.setActivity(determineActivity(act.getActivityMetric()));
        }

        return summaryMap.values().stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Activity determineActivity(Double activity) {
        if (activity == null) {
            return Activity.LOW;
        }
        if (activity < 0.5) {
            return Activity.LOW;
        } else if (activity < 2.5) {
            return Activity.MEDIUM;
        } else {
            return Activity.HIGH;
        }
    }
}