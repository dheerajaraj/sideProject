package com.house.microlendingassistant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
public class UserCategories {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "Please specify name of category")
    private String categoryName;
    @Column
    private String userSequence;
    @Column
    private Integer priority;
    @Column
    private Integer Totalexpenditure;

    @ManyToOne(fetch=FetchType.EAGER) //refresh the state of the instance from the db and also refresh usercategories.
    @JoinColumn(name="user_id", updatable = false, nullable = false)
    @JsonIgnore
    User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="userCategories")
    List<Expenditure> expenditures = new LinkedList<Expenditure>();

    public UserCategories() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTotalexpenditure() {
        return Totalexpenditure;
    }

    public void setTotalexpenditure(Integer totalexpenditure) {
        Totalexpenditure = totalexpenditure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserSequence() {
        return userSequence;
    }

    public void setUserSequence(String userSequence) {
        this.userSequence = userSequence;
    }

    public List<Expenditure> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(List<Expenditure> expenditures) {
        this.expenditures = expenditures;
    }
}
