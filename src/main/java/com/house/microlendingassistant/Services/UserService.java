package com.house.microlendingassistant.Services;

import com.house.microlendingassistant.domain.User;
import com.house.microlendingassistant.exceptions.UserIdException;
import com.house.microlendingassistant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user){
        try{
            user.setUsername(user.getUsername().toLowerCase());
            return userRepository.save(user);
        } catch (Exception e){
            //This will only catch errors encountered when trying to persist in the database.
            //The errorValidationService class on the other hand is used to organize nicely any bad HTTP requests and
            //it is blind to errors thrown when trying to persist in the database.
            throw new UserIdException("User ID: '"+user.getUsername().toLowerCase()+"' already exists!");
        }
    }

    public User findUserByUsername(String username){
        User user = userRepository.findByUsername(username.toLowerCase());
        if(user == null){
            throw new UserIdException("User ID: '"+username+"' does not exist!");
        }
        return user;
    }

    public Iterable<User> finaAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserbyUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
           throw new UserIdException("User ID: '"+user.getUsername().toLowerCase()+"' does not exist!");
        }
    }
}
