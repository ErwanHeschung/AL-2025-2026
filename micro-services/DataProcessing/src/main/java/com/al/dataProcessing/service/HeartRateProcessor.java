package com.al.dataProcessing.service;

import com.al.dataProcessing.model.HeartMetric;
import com.al.dataProcessing.model.Message;
import com.al.dataProcessing.repository.HeartMetricRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Slf4j
@Component
public class HeartRateProcessor {

    private final HeartMetricRepository heartMetricRepository;

    public HeartRateProcessor(HeartMetricRepository heartMetricRepository) {
        this.heartMetricRepository = heartMetricRepository;
    }

    void process(Message message) {
        log.info("HearthRateProcessor process message: {}", message);
        HeartMetric heartMetric = new HeartMetric();
        heartMetric.setTimestamp(OffsetDateTime.now());
        heartMetric.setBraceletId(message.getBraceletId());
        heartMetric.setValue(Double.valueOf(message.getValue()));
        heartMetricRepository.save(heartMetric);
    }
}
