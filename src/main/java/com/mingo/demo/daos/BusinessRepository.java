package com.mingo.demo.daos;

import com.mingo.demo.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findBusinessesByLatitudeGreaterThanEqualAndLatitudeIsLessThanEqualAndLongitudeIsGreaterThanEqualAndLongitudeIsLessThanEqual (Double lattitudeLowerbound, Double latitudeUpperbound, Double  longitudeLowerbound, Double longitudeUpperbound);
}
