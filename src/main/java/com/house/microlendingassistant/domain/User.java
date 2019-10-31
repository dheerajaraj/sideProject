package com.house.microlendingassistant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Username is required")
    @Column(unique=true)
    private String username;

    @NotBlank(message="First Name is required")
    private String firstName;

    @NotBlank(message="Last Name is required")
    private String lastName;

    @Digits(integer=15, fraction = 2)
    private double salary;

    @Digits(integer=10, fraction=0)
    private Long bankAccount;

    @JsonFormat(pattern="dd-mm-yyyy")
    private Date createdAt;

    //OnetoMany relationship with userCategories.
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
    private Set<UserCategories> userCategoriesSet = new HashSet<UserCategories>();

    public Set<UserCategories> getUserCategoriesSet() {
        return userCategoriesSet;
    }

    public void setUserCategoriesSet(Set<UserCategories> userCategoriesSet) {
        this.userCategoriesSet = userCategoriesSet;
    }


    public User(){
    }

    public Long getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
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
