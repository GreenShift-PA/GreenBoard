package com.greenboard.enums;

public enum TaskStatus {
    TODO("To do"),
    IN_PROGRESS("In progress"),
    DONE("Done"),
    BACKLOG("Backlog");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return getStatus();
    }

    public static TaskStatus fromString(String text) {
        for (TaskStatus b : TaskStatus.values()) {
            if (b.status.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
