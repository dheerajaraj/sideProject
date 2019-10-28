package com.house.microlendingassistant.repositories;

import com.house.microlendingassistant.domain.UserCategories;
import org.springframework.data.repository.CrudRepository;

public interface UserCategoriesRepository extends CrudRepository<UserCategories, Long> {
    UserCategories findByUserSequence(String userSequence); //"<username>" + "_" + "<categoryName>"
    UserCategories findUserCategoriesById(Long id);
}
