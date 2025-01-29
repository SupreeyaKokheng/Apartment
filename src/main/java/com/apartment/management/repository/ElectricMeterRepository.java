package com.apartment.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apartment.management.model.ElectricMeter;
import com.apartment.management.model.Room;

@Repository
public interface ElectricMeterRepository extends JpaRepository<ElectricMeter, Long> {

    @Transactional(readOnly = true)
    List<ElectricMeter> findByRoomAndRecordDateBetween(Room room, LocalDate startDate, LocalDate endDate);

    @Transactional(readOnly = true)
    List<ElectricMeter> findByRecordDateBetween(LocalDate startDate, LocalDate endDate);

    // ✅ ค้นหามิเตอร์ไฟล่าสุดของห้อง
    @Query("SELECT e FROM ElectricMeter e WHERE e.room = :room ORDER BY e.recordDate DESC LIMIT 1")
    ElectricMeter findLatestElectricMeterByRoom(Room room);
}

// package com.apartment.management.repository;

// import com.apartment.management.model.ElectricMeter;
// import com.apartment.management.model.Room;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// //import java.time.YearMonth;
// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface ElectricMeterRepository extends JpaRepository<ElectricMeter, Long> {
//     // ค้นหามิเตอร์ไฟตามห้องและเดือน
//     List<ElectricMeter> findByRoomAndRecordDateBetween(Room room, LocalDate startDate, LocalDate endDate);
//     List<ElectricMeter> findByRecordDateBetween(LocalDate startDate, LocalDate endDate);
// }
