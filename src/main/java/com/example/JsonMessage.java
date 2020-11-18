package com.example;

public class JsonMessage {
    private final String id;
    private final String message;
    private final String note;

    public JsonMessage(String id, String message, String note) {

        this.id = id;
        this.message = message;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getNote() {
        return note;
    }
}
