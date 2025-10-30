package com.al.dataProcessing.utils;

import com.al.dataProcessing.model.Message;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@Slf4j
public class MessageUtils {

    public static Message getFieldsFromMessage(String message) {
        Message messageReceived = new Message();
        String[] parts = message.replace("{", "")
                .replace("}", "")
                .replace("\"", "")
                .split(",");
        try {
            for (String part : parts) {
                String[] keyValue = part.split(":", 2);
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) {
                    case "braceletId" -> messageReceived.setBraceletId(value);
                    case "type" -> messageReceived.setType(value);
                    case "timestamp" -> messageReceived.setTimestamp(OffsetDateTime.parse(value));
                    case "value" -> messageReceived.setValue(Integer.parseInt(value));
                }
            }
        } catch (Exception e) {
            log.error("Error parsing message: {}", message, e);
        } finally {
            log.info("messageReceived: {}", messageReceived);
        }
        return messageReceived;
    }
}
