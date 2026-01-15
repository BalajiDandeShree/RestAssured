package com.w2a.exception;

public class AutomationException extends Exception{
    private String message;

    public AutomationException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AutomationException{" +
                "message='" + message + '\'' +
                '}';
    }
}
