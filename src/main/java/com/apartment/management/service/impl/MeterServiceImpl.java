package com.apartment.management.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apartment.management.model.ElectricMeter;
import com.apartment.management.model.Room;
import com.apartment.management.model.WaterMeter;
import com.apartment.management.repository.ElectricMeterRepository;
import com.apartment.management.repository.RoomRepository;
import com.apartment.management.repository.WaterMeterRepository;
import com.apartment.management.service.MeterService;

@Service
public class MeterServiceImpl implements MeterService {

    @Autowired
    private WaterMeterRepository waterMeterRepository;

    @Autowired
    private ElectricMeterRepository electricMeterRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public WaterMeter saveWaterMeter(Long roomId, BigDecimal meterValue, LocalDate recordDate) throws Exception {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        WaterMeter waterMeter = new WaterMeter(room, meterValue, recordDate);
        return waterMeterRepository.save(waterMeter); // ✅ Return WaterMeter
    }

    @Override
    @Transactional
    public ElectricMeter saveElectricMeter(Long roomId, BigDecimal meterValue, LocalDate recordDate) throws Exception {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        ElectricMeter electricMeter = new ElectricMeter(room, meterValue, recordDate);
        return electricMeterRepository.save(electricMeter); // ✅ Return ElectricMeter
    }

    @Override
    @Transactional(readOnly = true)
    public List<WaterMeter> getWaterMeterByRoomAndMonth(Long roomId, LocalDate month) {
        LocalDate start = month.withDayOfMonth(1);
        LocalDate end = month.withDayOfMonth(month.lengthOfMonth());
        return waterMeterRepository.findByRoomAndRecordDateBetween(roomRepository.findById(roomId).orElse(null), start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectricMeter> getElectricMeterByRoomAndMonth(Long roomId, LocalDate month) {
        LocalDate start = month.withDayOfMonth(1);
        LocalDate end = month.withDayOfMonth(month.lengthOfMonth());
        return electricMeterRepository.findByRoomAndRecordDateBetween(roomRepository.findById(roomId).orElse(null), start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WaterMeter> getLatestWaterMeter(Long roomId) {
        return Optional.ofNullable(waterMeterRepository.findLatestWaterMeterByRoom(roomRepository.findById(roomId).orElse(null)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ElectricMeter> getLatestElectricMeter(Long roomId) {
        return Optional.ofNullable(electricMeterRepository.findLatestElectricMeterByRoom(roomRepository.findById(roomId).orElse(null)));
    }
}


// package com.apartment.management.service.impl;

// import com.apartment.management.model.ElectricMeter;
// import com.apartment.management.model.Room;
// import com.apartment.management.model.WaterMeter;
// import com.apartment.management.repository.ElectricMeterRepository;
// import com.apartment.management.repository.RoomRepository;
// import com.apartment.management.repository.WaterMeterRepository;
// import com.apartment.management.service.MeterService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.time.YearMonth;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// @Service
// public class MeterServiceImpl implements MeterService {

//     @Autowired
//     private WaterMeterRepository waterMeterRepository;

//     @Autowired
//     private ElectricMeterRepository electricMeterRepository;

//     @Autowired
//     private RoomRepository roomRepository;

//     @Override
//     public void saveWaterMeter(Long roomId, Double meterValue, LocalDate recordDate) throws Exception {
//         Room room = roomRepository.findById(roomId)
//                 .orElseThrow(() -> new Exception("Room not found with id: " + roomId));

//         WaterMeter waterMeter = new WaterMeter();
//         waterMeter.setRoom(room);
//         waterMeter.setMeterValue(meterValue);
//         waterMeter.setRecordDate(recordDate);

//         waterMeterRepository.save(waterMeter);
//     }

//     @Override
//     public void saveElectricMeter(Long roomId, Double meterValue, LocalDate recordDate) throws Exception {
//         Room room = roomRepository.findById(roomId)
//                 .orElseThrow(() -> new Exception("Room not found with id: " + roomId));

//         ElectricMeter electricMeter = new ElectricMeter();
//         electricMeter.setRoom(room);
//         electricMeter.setMeterValue(meterValue);
//         electricMeter.setRecordDate(recordDate);

//         electricMeterRepository.save(electricMeter);
//     }

//     @Override
//     public List<WaterMeter> getWaterMeterRecordsByRoomAndMonth(Long roomId, YearMonth month) {
//         LocalDate start = month.atDay(1);
//         LocalDate end = month.atEndOfMonth();
//         Room room = roomRepository.findById(roomId).orElse(null);
//         if (room == null) {
//             return Collections.emptyList(); // คืนลิสต์เปล่า
//         }
//         return waterMeterRepository.findByRoomAndRecordDateBetween(room, start, end);
//     }

//     @Override
//     public List<ElectricMeter> getElectricMeterRecordsByRoomAndMonth(Long roomId, YearMonth month) {
//         LocalDate start = month.atDay(1);
//         LocalDate end = month.atEndOfMonth();
//         Room room = roomRepository.findById(roomId).orElse(null);
//         if (room == null) {
//             return Collections.emptyList(); // คืนลิสต์เปล่า
//         }
//         return electricMeterRepository.findByRoomAndRecordDateBetween(room, start, end);
//     }

//     @Override
//     public List<ElectricMeter> getElectricMeterRecordsByMonth(YearMonth month) {
//         LocalDate start = month.atDay(1);
//         LocalDate end = month.atEndOfMonth();
//         return electricMeterRepository.findByRecordDateBetween(start, end);
//     }

// //    @Override
// //    public List<?> getMeterRecordsByRoomNumber(String roomNumber) throws Exception {
// //        Room room = roomRepository.findByRoomNumber(roomNumber)
// //                .orElseThrow(() -> new Exception("Room not found with room number: " + roomNumber));
// //        List<WaterMeter> waterMeters = waterMeterRepository.findByRoom(room);
// //        List<ElectricMeter> electricMeters = electricMeterRepository.findByRoom(room);
// //
// //        // รวมข้อมูลทั้งสอง
// //        List<Object> records = new ArrayList<>();
// //        records.addAll(waterMeters);
// //        records.addAll(electricMeters);
// //
// //        return records;
// //    }
// }
