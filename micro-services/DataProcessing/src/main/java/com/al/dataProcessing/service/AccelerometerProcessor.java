package com.al.dataProcessing.service;

import com.al.dataProcessing.model.AccelerometerMessage;
import com.al.dataProcessing.model.AccelerometerMetric;
import com.al.dataProcessing.model.HeartMetric;
import com.al.dataProcessing.model.Message;
import com.al.dataProcessing.repository.AccelerometerRepository;
import com.al.dataProcessing.repository.HeartMetricRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Slf4j
@Component
public class AccelerometerProcessor {

    private final AccelerometerRepository accelerometerRepository;

    public AccelerometerProcessor(AccelerometerRepository accelerometerRepository) {
        this.accelerometerRepository = accelerometerRepository;
    }

    void process(AccelerometerMessage accelerometerMessage) {
        log.info("AccelerometerProcessor process message: {}", accelerometerMessage);
        AccelerometerMetric accelerometerMetric = new AccelerometerMetric();
        accelerometerMetric.setTimestamp(OffsetDateTime.now());
        accelerometerMetric.setBraceletId(accelerometerMessage.getBraceletId());
        accelerometerMetric.setX(accelerometerMessage.getValue().getX());
        accelerometerMetric.setY(accelerometerMessage.getValue().getY());
        accelerometerMetric.setZ(accelerometerMessage.getValue().getZ());
        accelerometerRepository.save(accelerometerMetric);
    }
}
