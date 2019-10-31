package com.house.microlendingassistant.exceptions;

public class UserNotFoundExceptionResponse {
    private String userNotFound;
    public UserNotFoundExceptionResponse(String userNotFound){
        this.userNotFound = userNotFound;
    }
    public String getUserNotFound(){
        return this.userNotFound;
    }

    public void setUserNotFound(String userNotFound) {
        this.userNotFound = userNotFound;
    }
}
