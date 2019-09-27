package com.house.microlendingassistant.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class ErrorValidationService {
    public ResponseEntity<?> ErrorValidationService(BindingResult result){
    if(result.hasErrors()){
            Map<String, String> errorMap= new HashMap<>();
            Iterator errorIterator = result.getFieldErrors().iterator();
            while(errorIterator.hasNext()){
                FieldError error = (FieldError) errorIterator.next();
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
    return null;
    }
}
