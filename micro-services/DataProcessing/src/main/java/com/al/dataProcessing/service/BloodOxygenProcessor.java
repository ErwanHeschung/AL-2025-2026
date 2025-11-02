package com.al.dataProcessing.service;

import com.al.dataProcessing.model.BloodOxygenMetric;
import com.al.dataProcessing.model.HeartMetric;
import com.al.dataProcessing.model.Message;
import com.al.dataProcessing.repository.BloodOxygenRepository;
import com.al.dataProcessing.repository.HeartMetricRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Slf4j
@Component
public class BloodOxygenProcessor {

    private final BloodOxygenRepository bloodOxygenRepository;

    public BloodOxygenProcessor(BloodOxygenRepository bloodOxygenRepository) {
        this.bloodOxygenRepository = bloodOxygenRepository;
    }

    void process(Message message) {
        log.info("BloodOxygenProcessor process message: {}", message);
        BloodOxygenMetric bloodOxygenMetric = new BloodOxygenMetric();
        bloodOxygenMetric.setTimestamp(OffsetDateTime.now());
        bloodOxygenMetric.setBraceletId(message.getBraceletId());
        bloodOxygenMetric.setValue(message.getValue());
        bloodOxygenRepository.save(bloodOxygenMetric);
    }
}
