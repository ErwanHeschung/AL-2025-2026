package com.al.dataProcessing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccelerometerMessage {
    private String braceletId;
    private String type;
    private OffsetDateTime timestamp;
    private AccelerometerValue value;
}
