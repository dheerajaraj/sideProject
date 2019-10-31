package com.house.microlendingassistant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Expenditure {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column
    private Integer amount;
    @Column
    private String itemName;
    @Column
    private Date purchaseDate;
    @Column
    private String Location;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="userCategories_id", updatable=false, nullable=false)
    @JsonIgnore
    UserCategories userCategories;

    public Expenditure() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public UserCategories getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(UserCategories userCategories) {
        this.userCategories = userCategories;
    }
}
