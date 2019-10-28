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
@CrossOrigin
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
        User userUpdate = userService.saveOrUpdateUser(user);
        return new ResponseEntity<User>(userUpdate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") //{username} is a variable
    public ResponseEntity<?> getUserById(@PathVariable String id){
        User user = userService.findUserById(Long.parseLong(id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers(){
        return userService.finaAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUserbyId(Long.parseLong(id));
        return new ResponseEntity<String>("User with id: '"+id+"' is deleted", HttpStatus.OK);
    }
}
