package com.greenboard.enums;

public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public String toString() {
        return priority;
    }
}
