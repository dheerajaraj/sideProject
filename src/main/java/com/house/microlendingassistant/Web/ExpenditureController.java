package com.house.microlendingassistant.Web;

import com.house.microlendingassistant.Services.ErrorValidationService;
import com.house.microlendingassistant.Services.ExpenditureService;
import com.house.microlendingassistant.Services.UserCategoriesService;
import com.house.microlendingassistant.domain.Expenditure;
import com.house.microlendingassistant.domain.UserCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/expenditure")
@CrossOrigin
public class ExpenditureController {
    @Autowired
    private ExpenditureService expenditureService;

    @Autowired
    private ErrorValidationService errorValidationService;

    @PostMapping("/{category_id}")
    public ResponseEntity<?> saveExpenditureToCategory(@Valid @RequestBody Expenditure expenditure,
                                                       BindingResult result, @PathVariable String category_id){
        ResponseEntity<?> errorMap = errorValidationService.ErrorValidationService(result);
        if(errorMap!=null)
            return errorMap;
        Expenditure expenditure1 = expenditureService.addExpenditureToCategories(Long.parseLong(category_id),expenditure);
        return new ResponseEntity<Expenditure>(expenditure1, HttpStatus.OK);
    }
}
