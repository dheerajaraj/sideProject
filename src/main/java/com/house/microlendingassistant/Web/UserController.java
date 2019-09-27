package com.house.microlendingassistant.Web;

import com.house.microlendingassistant.Services.ErrorValidationService;
import com.house.microlendingassistant.Services.UserService;
import com.house.microlendingassistant.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ErrorValidationService errorValidationService;

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result){
        ResponseEntity<Map<String, String>> errorMap = (ResponseEntity<Map<String, String>>) errorValidationService.ErrorValidationService(result);
        if(errorMap!=null){
            return errorMap;
        }
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
