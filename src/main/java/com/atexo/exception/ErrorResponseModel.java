package com.atexo.exception;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ErrorResponseModel {

    private String date;

    private String error;

    private String errorDescription;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public ErrorResponseModel(String error, String errorDescription) {
        super();

        LocalDateTime dateTime = LocalDateTime.now();
        date = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ErrorResponse [date=" + date + ", error=" + error + ", errorDescription=" + errorDescription + "]";
    }

}
