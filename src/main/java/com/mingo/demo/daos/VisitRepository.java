package com.mingo.demo.daos;

import com.mingo.demo.models.Business;
import com.mingo.demo.models.User;
import com.mingo.demo.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findAllByUserEqualsOrderByStartAsc (User user);
    List<Visit> findAllByBusinessOrderByStartAsc (Business business);

}
