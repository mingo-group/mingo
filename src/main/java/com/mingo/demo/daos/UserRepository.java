package com.mingo.demo.daos;

import com.mingo.demo.models.Interest;
import com.mingo.demo.models.Offer;
import com.mingo.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByInterestsEqualsOrderByUsernameAsc(Interest interest);
    List<User> findUsersByInterestsEqualsAndOffersEqualsOrderByUsernameAsc (Interest interest, Offer offer);
    List<User> findUsersByLatitudeGreaterThanEqualAndLatitudeIsLessThanEqualAndLongitudeIsGreaterThanEqualAndLongitudeIsLessThanEqual(Double lattitudeLowerbound, Double latitudeUpperbound, Double  longitudeLowerbound, Double longitudeUpperbound);

}
