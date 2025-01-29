package com.apartment.management.repository;

import com.apartment.management.model.Billing;
import com.apartment.management.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Optional<Billing> findFirstByRoomAndMonth(Room room, String month);  // ✅ ใช้ String แทน LocalDate
    List<Billing> findByMonth(String month); // ✅ ใช้ String แทน LocalDate
}
//package com.apartment.management.repository;

//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.apartment.management.model.Billing;
//import com.apartment.management.model.Room;
//
//@Repository
//public interface BillingRepository extends JpaRepository<Billing, Long> {
//
//    @Transactional(readOnly = true)
//    Optional<Billing> findFirstByRoomAndMonth(Room room, LocalDate month); // 🔄 เปลี่ยนเป็น Optional
//
//    @Transactional(readOnly = true)
//    List<Billing> findByMonth(LocalDate month);
//}


// package com.apartment.management.repository;

// import com.apartment.management.model.Billing;
// import com.apartment.management.model.Room;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface BillingRepository extends JpaRepository<Billing, Long> {
//     // ค้นหาบิลตามห้องและเดือน
//     List<Billing> findByRoomAndMonth(Room room, String month);
//     List<Billing> findByMonth(String month);
// }
