package com.apartment.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apartment.management.model.Room;
import com.apartment.management.model.WaterMeter;

@Repository
public interface WaterMeterRepository extends JpaRepository<WaterMeter, Long> {

    @Transactional(readOnly = true)
    List<WaterMeter> findByRoomAndRecordDateBetween(Room room, LocalDate startDate, LocalDate endDate);

    // ✅ ค้นหามิเตอร์น้ำล่าสุดของห้อง
    @Query("SELECT w FROM WaterMeter w WHERE w.room = :room ORDER BY w.recordDate DESC LIMIT 1")
    WaterMeter findLatestWaterMeterByRoom(Room room);
}


// package com.apartment.management.repository;

// import com.apartment.management.model.Room;
// import com.apartment.management.model.WaterMeter;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDate;
// //import java.time.YearMonth;
// import java.util.List;

// @Repository
// public interface WaterMeterRepository extends JpaRepository<WaterMeter, Long> {
//     // ค้นหามิเตอร์น้ำตามห้องและเดือน
//     //List<WaterMeter> findByRoomAndRecordDateBetween(Room room, YearMonth startDate, YearMonth endDate);
//     List<WaterMeter> findByRoomAndRecordDateBetween(Room room, LocalDate startDate, LocalDate endDate);

// }
