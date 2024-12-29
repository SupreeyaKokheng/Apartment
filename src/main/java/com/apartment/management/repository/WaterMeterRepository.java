package com.apartment.management.repository;

import com.apartment.management.model.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterMeterRepository extends JpaRepository<WaterMeter, Long> {
}

