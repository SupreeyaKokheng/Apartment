package com.apartment.management.repository;

import com.apartment.management.model.WaterMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WaterMeterRepository extends JpaRepository<WaterMeter, Long> {
    Optional<WaterMeter> findByRoom_Id(Long roomId);

}

