package com.house.microlendingassistant.exceptions;

public class UserIdExceptionResponse {
    private String userIdentifier;

    public UserIdExceptionResponse(String userIdentifier){
        this.userIdentifier=userIdentifier;
    }

    public String getUserIdentifier(){
        return this.userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}
