package com.mingo.demo.daos;

import com.mingo.demo.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
