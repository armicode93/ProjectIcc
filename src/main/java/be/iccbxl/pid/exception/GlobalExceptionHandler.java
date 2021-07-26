package be.iccbxl.pid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice //Annotation to handler globally
public class GlobalExceptionHandler {

    //handling custom validation errors
    @ExceptionHandler
    public ResponseEntity<?> customValidationErrorHandling(MethodArgumentNotValidException exception)
    {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validation error",
                exception.getBindingResult().getFieldError().getDefaultMessage()); //to show annotation default message
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
