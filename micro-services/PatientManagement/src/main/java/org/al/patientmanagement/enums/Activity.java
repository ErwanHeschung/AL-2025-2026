package org.al.patientmanagement.enums;

import lombok.Getter;

@Getter
public enum Activity {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high");

    private final String label;

    Activity(String label) {
        this.label = label;
    }

}