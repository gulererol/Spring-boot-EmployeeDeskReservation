package com.UserDeskReservation.backend.Message;

public class Message {
    String message;
    Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Message(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}