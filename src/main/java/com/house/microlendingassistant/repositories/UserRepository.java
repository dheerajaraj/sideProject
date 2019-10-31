package com.house.microlendingassistant.repositories;

import com.house.microlendingassistant.domain.User;
import com.house.microlendingassistant.domain.UserCategories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findUserById(Long id);
}
