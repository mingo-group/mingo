package com.mingo.demo.daos;

import com.mingo.demo.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
