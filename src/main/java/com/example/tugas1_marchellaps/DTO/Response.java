package com.example.tugas1_marchellaps.DTO;

import java.util.ArrayList;
import java.util.List;

public class Response<T>{
    private int status;

    private String message;
    private Object payload;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
