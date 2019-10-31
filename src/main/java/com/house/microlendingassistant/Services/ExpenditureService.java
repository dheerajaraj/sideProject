package com.house.microlendingassistant.Services;

import com.house.microlendingassistant.domain.Expenditure;
import com.house.microlendingassistant.domain.UserCategories;
import com.house.microlendingassistant.repositories.ExpenditureRepository;
import com.house.microlendingassistant.repositories.UserCategoriesRepository;
import com.house.microlendingassistant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenditureService {
    @Autowired
    private UserCategoriesRepository userCategoriesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    public Expenditure addExpenditureToCategories(Long id, Expenditure expenditure){
        UserCategories userCategories = userCategoriesRepository.findUserCategoriesById(id);
        if(userCategories!=null){
            expenditure.setUserCategories(userCategories);
        }

     return expenditureRepository.save(expenditure);
    }
}
