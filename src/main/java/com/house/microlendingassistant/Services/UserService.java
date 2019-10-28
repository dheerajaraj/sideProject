package com.house.microlendingassistant.Services;

import com.house.microlendingassistant.domain.User;
import com.house.microlendingassistant.domain.UserCategories;
import com.house.microlendingassistant.exceptions.UserIdException;
import com.house.microlendingassistant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user){
        try{
            user.setUsername(user.getUsername().toLowerCase());
            //When the user is created for the first time, there will be no id assigned
           if(user.getId()==null){
               //means that there are no categories created.
               //add Expenditure that you get from DBS API.
               UserCategories userCategories = new UserCategories();
               userCategories.setCategoryName("Uncategorized Expenditure");
               Set<UserCategories> userCategoriesSet =  new HashSet<UserCategories>();
               userCategoriesSet.add(userCategories);
           }

            /*
            //if we are updating the user, then we set the backlog so that we don't have to pass in
            // the backlog object each time an update is made in postman. Else if this condition is
            // not present then a null backlog will be persisted.
            else{
                user.setBacklog(backlogRepository.findByUsername(user.getUsername().toLowerCase()));
            }*/
            return userRepository.save(user);
        } catch (Exception e){
            //This will only catch errors encountered when trying to persist in the database.
            //The errorValidationService class on the other hand is used to organize nicely any bad HTTP requests and
            //it is blind to errors thrown when trying to persist in the database.
            throw new UserIdException("User ID: '"+user.getUsername().toLowerCase()+"' already exists!");
        }
    }

    public User findUserById(Long id){
        User user = userRepository.findUserById(id);
        if(user == null){
            throw new UserIdException("User ID: '"+id+"' with username: "+user.getUsername()+" does not exist!");
        }
        return user;
    }

    public Iterable<User> finaAllUsers(){
        return userRepository.findAll();
    }

    public Boolean deleteUserbyId(Long id){
        User user = userRepository.findUserById(id);
        if(user == null){
           throw new UserIdException("User ID: '"+user.getUsername().toLowerCase()+"' does not exist!");
        }
        userRepository.delete(user);
        return true;
    }
}
