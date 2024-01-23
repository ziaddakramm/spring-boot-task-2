package com.tasks.testingapp.exception;


import com.tasks.testingapp.error.EmployeeErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {
    // add exception handling code

    //EmployeeErrorResponse = Type of the response body
    //EmployeeNotFoundException exc= Exception type to handle / cath
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exc)
    {
        // create a EmployeeErrorResponse
        EmployeeErrorResponse error= new EmployeeErrorResponse();


        //404 Not found
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        // return ResponseEntity
        // error = actual body of the response
        // HttpStatus.NOT_FOUND = actual status of the response
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    //add another exception handler ... to catch any exception (catch all)
    // handles generic exceptions
    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc)
    {

        // create a EmployeeErrorResponse
        EmployeeErrorResponse error= new EmployeeErrorResponse();

        //400 Bad request
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        // return ResponseEntity
        // error = actual body of the response
        // HttpStatus.NOT_FOUND = actual status of the response
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
