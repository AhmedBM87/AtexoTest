package com.atexo.exception;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
@Slf4j
public class ApiExceptionHandler  {

    private static final String CARD_NOT_FOUND = "card_not_found";
    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseModel> handleApiException(
    		CardNotFoundException ex) {
        ErrorResponseModel response = new ErrorResponseModel(CARD_NOT_FOUND, ex.getLocalizedMessage());
        log.error("error="+CARD_NOT_FOUND+", errorDescription="+ex.getLocalizedMessage());
        return new ResponseEntity<ErrorResponseModel>(response, HttpStatus.NOT_FOUND);
    }
}
