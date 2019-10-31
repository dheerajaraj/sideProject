package com.house.microlendingassistant.Services;

import com.house.microlendingassistant.domain.User;
import com.house.microlendingassistant.domain.UserCategories;
import com.house.microlendingassistant.exceptions.UserIdException;
import com.house.microlendingassistant.exceptions.UserNotFoundException;
import com.house.microlendingassistant.repositories.UserCategoriesRepository;
import com.house.microlendingassistant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCategoriesService {
    @Autowired
    private UserCategoriesRepository userCategoriesRepository;

    @Autowired
    private UserRepository userRepository;

    public UserCategories addUserCategories(String username, UserCategories userCategories){

        try{
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
        } catch(Exception e){
            throw new UserNotFoundException("User has not been registered!");
        }
        //Exception: user not found
        //UserCategories to be added to a specific user where user != null

    }

    public Iterable<UserCategories>findUserCategoriesByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserIdException("User with username '"+username+"' does not exist");
        }
        return userCategoriesRepository.findUserCategoriesByUser(user);
    }

    public UserCategories findUserCategoryByUsername(String username, String categoryName){
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UserIdException("User with id '"+username+"' does not exist");
        }

        UserCategories userCategories = userCategoriesRepository.findUserCategoriesByCategoryNameAndUser(categoryName, user);
        if(userCategories==null){
            throw new UserIdException("User category with name '"+categoryName+"' does not exist");
        }
       // The condition covered below is taken care of by the lines of codes above.
/*        if(!userCategories.getUser().getId().equals(user.getId())){
            throw new UserIdException("user category with name "+categoryName+"does not exist in user with username: "+username);
        }*/
        return userCategories;
    }

    public UserCategories updateByCategoryId(UserCategories updateUserCategories, String username, String categoryName){
        //User user = userRepository.findByUsername(username);
        //UserCategories userCategories1 = userCategoriesRepository.findUserCategoriesByCategoryNameAndUser(categoryName, user);
        UserCategories userCategories = findUserCategoryByUsername(username, categoryName);
        userCategories = updateUserCategories;

        return userCategoriesRepository.save(userCategories); // will automatically update the id in the database.
    }

    public void deleteUserCategoryByUserAndCategoryName(String username, String categoryName){
        UserCategories userCategories = findUserCategoryByUsername(username, categoryName);
        userCategoriesRepository.delete(userCategories);
    }
}
