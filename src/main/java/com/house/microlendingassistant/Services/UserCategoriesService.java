package com.house.microlendingassistant.Services;

import com.house.microlendingassistant.domain.User;
import com.house.microlendingassistant.domain.UserCategories;
import com.house.microlendingassistant.repositories.UserCategoriesRepository;
import com.house.microlendingassistant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCategoriesService {
    @Autowired
    private UserCategoriesRepository userCategoriesRepository;

    @Autowired
    private UserRepository userRepository;

    public UserCategories addUserCategories(String username, UserCategories userCategories){

        //Exception: user not found
        //UserCategories to be added to a specific user where user != null
        if(userRepository.findByUsername(username)!= null){
            User user = userRepository.findByUsername(username);
            userCategories.setUser(user);
            //Initial priority lowest
            if(userCategories.getPriority()==null){ // IN the future, the form needs to handle priority to default to 0.
                userCategories.setPriority(1);
            }
            if(userCategories.getCategoryName()==null || userCategories.getCategoryName()==""){
                userCategories.setCategoryName("Please Set Category");
            }
        }
        return userCategoriesRepository.save(userCategories);
    }
}
