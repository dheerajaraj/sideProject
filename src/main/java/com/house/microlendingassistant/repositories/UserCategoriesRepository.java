package com.house.microlendingassistant.repositories;

import com.house.microlendingassistant.domain.User;
import com.house.microlendingassistant.domain.UserCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCategoriesRepository extends CrudRepository<UserCategories, Long> {
    UserCategories findByUserSequence(String userSequence); //"<username>" + "_" + "<categoryName>"
    List<UserCategories> findUserCategoriesByUser(User user);
    UserCategories findUserCategoriesById(Long id);
    UserCategories findUserCategoriesByCategoryNameAndUser(String categoryName, User user);

}
