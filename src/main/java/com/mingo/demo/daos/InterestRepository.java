package com.mingo.demo.daos;

import com.mingo.demo.models.Category;
import com.mingo.demo.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findInterestByCategoriesIsOrderByNameAsc(Category category);
}
