package com.ivan.thehealthsoftwarecompany.hisv3.exception;

import java.util.Date;


@SuppressWarnings("unused")
public class ErrorResponse {
    private int statusCode;
    private String errorMessage;
    private Date errorDate;


    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String errorMessage, Date errorDate) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errorDate = errorDate;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }
}
