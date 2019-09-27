package com.house.microlendingassistant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Username is required")
    @Column(updatable=false, unique=true)
    private String username;

    @Digits(integer=15, fraction = 2)
    private double salary;

    @Digits(integer=10, fraction=0)
    private Long bankAccount;

    @JsonFormat(pattern="dd-mm-yyyy")
    private Date createdAt;

    public User(){
    }

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public double getSalary(){
        return salary;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setUsername(String username){
        this.username =  username;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public void setData(){
        createdAt = Calendar.getInstance().getTime();
    }
}
