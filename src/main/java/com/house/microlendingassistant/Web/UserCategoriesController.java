package com.house.microlendingassistant.Web;

import com.house.microlendingassistant.Services.ErrorValidationService;
import com.house.microlendingassistant.Services.UserCategoriesService;
import com.house.microlendingassistant.domain.UserCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class UserCategoriesController {
    @Autowired
    private UserCategoriesService userCategoriesService;

    @Autowired
    private ErrorValidationService errorValidationService;

    @PostMapping("/{username}")
    public ResponseEntity<?> saveCategoryToUser(@Valid @RequestBody UserCategories userCategories,
                                                BindingResult result, @PathVariable String username){
        ResponseEntity<?> errorMap = errorValidationService.ErrorValidationService(result);
        if(errorMap!=null)
            return errorMap;
        UserCategories userCategories1 = userCategoriesService.addUserCategories(username, userCategories);
        return new ResponseEntity<UserCategories>(userCategories1, HttpStatus.CREATED);
    }

}
