package com.al.dataProcessing.service;

import com.al.dataProcessing.model.AccelerometerMessage;
import com.al.dataProcessing.model.Message;
import com.al.dataProcessing.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private final HeartRateProcessor heartRateProcessor;
    private final BloodOxygenProcessor bloodOxygenProcessor;
    private final AccelerometerProcessor accelerometerProcessor;

    @Autowired
    public KafkaConsumer(HeartRateProcessor hearthRateProcessor, BloodOxygenProcessor bloodOxygenProcessor, AccelerometerProcessor accelerometerProcessor) {
        this.heartRateProcessor = hearthRateProcessor;
        this.bloodOxygenProcessor = bloodOxygenProcessor;
        this.accelerometerProcessor = accelerometerProcessor;
    }

    @KafkaListener(topics = "heartRate")
    public void listenToHearthRate(ConsumerRecord<String, String> record) {
        logMessageInfo(record);
        Message message = MessageUtils.getFieldsFromMessage(record.value());
        heartRateProcessor.process(message);
    }


    @KafkaListener(topics = "bloodOxygen")
    public void listenToBloodOxygen(ConsumerRecord<String, String> record) {
        logMessageInfo(record);
        Message message = MessageUtils.getFieldsFromMessage(record.value());
        bloodOxygenProcessor.process(message);
    }

    @KafkaListener(topics = "accelerometer")
    public void listenToAccelerometer(ConsumerRecord<String, String> record) {
        logMessageInfo(record);
        AccelerometerMessage message = MessageUtils.getAccelerometerFieldsFromMessage(record.value());
        accelerometerProcessor.process(message);
    }

    private static void logMessageInfo(ConsumerRecord<String, String> record) {
        log.info("Received data through topic : {}, value : {}", record.topic(), record.value());
    }

}
