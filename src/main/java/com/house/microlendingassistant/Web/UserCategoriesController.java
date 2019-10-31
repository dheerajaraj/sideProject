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
import java.util.List;

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

    @GetMapping("/{username}")
    public Iterable<UserCategories> getAllCategories(@PathVariable String username){
       return userCategoriesService.findUserCategoriesByUsername(username);

    }

    @GetMapping("/{username}/{categoryName}")
    public UserCategories getCategory(@PathVariable String username, @PathVariable String categoryName){
        return userCategoriesService.findUserCategoryByUsername(username,categoryName);
    }

    @PatchMapping("/{username}/{categoryName}")
    public ResponseEntity<?> updateUserCategories(@Valid @RequestBody UserCategories userCategories, BindingResult result,
                                                  @PathVariable String username, @PathVariable String categoryName){
        ResponseEntity<?> errorMap = errorValidationService.ErrorValidationService(result);
        if(errorMap!=null){
            return errorMap;
        }
        UserCategories userCategories1 = userCategoriesService.updateByCategoryId(userCategories, username, categoryName);
    return new ResponseEntity<UserCategories>(userCategories1,HttpStatus.OK);
    }

    @DeleteMapping("/{username}/{categoryName}")
    public ResponseEntity<?> deleteUserCategory(@PathVariable String username, @PathVariable String categoryName){
        userCategoriesService.deleteUserCategoryByUserAndCategoryName(username, categoryName);
        return new ResponseEntity<String>("Deleted category under user: "+username+" and category with name: "
                +categoryName, HttpStatus.OK);
    }
}
