package com.wpCorp.dsCommerce.Controller.Exception;

import com.wpCorp.dsCommerce.Service.Exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jdk.dynalink.linker.MethodHandleTransformer;
import org.apache.ibatis.type.NStringTypeHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandartError> prodNotFound(HttpServletRequest request, ProductNotFoundException e) {
        HttpStatus hsts = HttpStatus.NOT_FOUND;
        StandartError stdErr = new StandartError();
        stdErr.setPath(request.getRequestURI());
        stdErr.setTimeStamp(Instant.now());
        stdErr.setErr(e.getMessage());
        stdErr.setMessage(e.getMessage());
        stdErr.setStatus(hsts.value());
        return ResponseEntity.status(hsts).body(stdErr);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> fieldErr(HttpServletRequest request, MethodArgumentNotValidException e) {
        HttpStatus hsts = HttpStatus.BAD_REQUEST;
        ValidationError val = new ValidationError();
        val.setErr(e.getMessage());
        val.setPath(request.getRequestURI());
        val.setMessage(e.getMessage());
        val.setTimeStamp(Instant.now());
        val.setStatus(hsts.value());
        for (FieldError field : e.getBindingResult().getFieldErrors()) {
            val.addFields(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.status(hsts).body(val);
    }


}
