package com.house.microlendingassistant.Web;

import com.house.microlendingassistant.Services.ErrorValidationService;
import com.house.microlendingassistant.Services.UserService;
import com.house.microlendingassistant.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
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
        // Note how errorValidationService calls the errorValidationService method not as a constructor. This is because errorValidationService has already been autowired.
        ResponseEntity<Map<String, String>> errorMap = (ResponseEntity<Map<String, String>>) errorValidationService.ErrorValidationService(result);
        if(errorMap!=null){
            return errorMap;
        }
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{username}") //{username} is a variable
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers(){
        return userService.finaAllUsers();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUserbyUsername(username);
        return new ResponseEntity<String>("User with username: '"+username+"' is deleted", HttpStatus.OK);
    }
}
