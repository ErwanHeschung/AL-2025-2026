package com.al.dataProcessing.utils;

import com.al.dataProcessing.model.AccelerometerMessage;
import com.al.dataProcessing.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static Message getFieldsFromMessage(String message) {
        try {
            Message messageReceived = objectMapper.readValue(message, Message.class);
            log.info("Message extracted successfully : {}", messageReceived);
            return messageReceived;

        } catch (Exception e) {
            log.error("Error when extracting the message : {}", message, e);
            return new Message();
        }
    }

    public static AccelerometerMessage getAccelerometerFieldsFromMessage(String message) {
        try {
            AccelerometerMessage accelerometerMessage = objectMapper.readValue(message, AccelerometerMessage.class);
            log.info("AccelerometerMessage extracted with success : {}", accelerometerMessage);
            return accelerometerMessage;

        } catch (Exception e) {
            log.error("Error when extracting : {}", message, e);
            return new AccelerometerMessage();
        }
    }
}