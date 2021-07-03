package com.mingo.demo.daos;

import com.mingo.demo.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
