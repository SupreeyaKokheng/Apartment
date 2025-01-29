package com.apartment.management.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apartment.management.model.ElectricMeter;
import com.apartment.management.model.WaterMeter;
import com.apartment.management.service.MeterService;

@RestController
@RequestMapping("/api/meter")
public class MeterController {

    @Autowired
    private MeterService meterService;

    @PostMapping("/water")
    public ResponseEntity<WaterMeter> saveWaterMeter(@RequestParam Long roomId, @RequestParam BigDecimal meterValue, @RequestParam String recordDate) throws Exception {
        LocalDate date = LocalDate.parse(recordDate);
        WaterMeter waterMeter = meterService.saveWaterMeter(roomId, meterValue, date);
        return ResponseEntity.status(201).body(waterMeter);
    }

    @PostMapping("/electric")
    public ResponseEntity<ElectricMeter> saveElectricMeter(@RequestParam Long roomId, @RequestParam BigDecimal meterValue, @RequestParam String recordDate) throws Exception {
        LocalDate date = LocalDate.parse(recordDate);
        ElectricMeter electricMeter = meterService.saveElectricMeter(roomId, meterValue, date);
        return ResponseEntity.status(201).body(electricMeter);
    }

    @GetMapping("/latest-water/{roomId}")
    public ResponseEntity<Optional<WaterMeter>> getLatestWaterMeterReadings(@PathVariable Long roomId) {
        return ResponseEntity.ok(meterService.getLatestWaterMeter(roomId));
    }

    @GetMapping("/latest-electric/{roomId}")
    public ResponseEntity<Optional<ElectricMeter>> getLatestElectricMeterReadings(@PathVariable Long roomId) {
        return ResponseEntity.ok(meterService.getLatestElectricMeter(roomId));
    }

    @GetMapping("/water/{roomId}/{month}")
    public ResponseEntity<List<WaterMeter>> getWaterMeterByRoomAndMonth(@PathVariable Long roomId, @PathVariable String month) {
        LocalDate parsedMonth = LocalDate.parse(month + "-01");
        return ResponseEntity.ok(meterService.getWaterMeterByRoomAndMonth(roomId, parsedMonth));
    }

    @GetMapping("/electric/{roomId}/{month}")
    public ResponseEntity<List<ElectricMeter>> getElectricMeterByRoomAndMonth(@PathVariable Long roomId, @PathVariable String month) {
        LocalDate parsedMonth = LocalDate.parse(month + "-01");
        return ResponseEntity.ok(meterService.getElectricMeterByRoomAndMonth(roomId, parsedMonth));
    }
}


// package com.apartment.management.controller;

// import com.apartment.management.dto.MeterDTO;
// import com.apartment.management.model.ElectricMeter;
// import com.apartment.management.model.Room;
// import com.apartment.management.service.MeterService;
// import com.apartment.management.service.RoomService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.time.YearMonth;
// import java.util.List;

// @RestController
// @RequestMapping("/api/meters")
// public class MeterController {

//     @Autowired
//     private MeterService meterService;

//     @Autowired
//     private RoomService roomService;

//     // บันทึกมิเตอร์น้ำ
//     @PostMapping("/water")
//     public ResponseEntity<?> saveWaterMeter(@RequestBody MeterDTO meterDTO) {
//         try {
//             meterService.saveWaterMeter(meterDTO.getRoomId(), meterDTO.getMeterValue(), meterDTO.getRecordDate());
//             return ResponseEntity.status(HttpStatus.CREATED).body("Water meter recorded successfully");
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body("Failed to record water meter: " + e.getMessage());
//         }
//     }

//     // บันทึกมิเตอร์ไฟ
//     @PostMapping("/electric")
//     public ResponseEntity<?> saveElectricMeter(@RequestBody MeterDTO meterDTO) {
//         try {
//             meterService.saveElectricMeter(meterDTO.getRoomId(), meterDTO.getMeterValue(), meterDTO.getRecordDate());
//             return ResponseEntity.status(HttpStatus.CREATED).body("Electric meter recorded successfully");
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body("Failed to record electric meter: " + e.getMessage());
//         }
//     }

//     // ดึงข้อมูลมิเตอร์น้ำตามห้องและเดือน
//     @GetMapping("/water/{roomId}")
//     public ResponseEntity<List<?>> getWaterMeterRecordsByRoomAndMonth(
//             @PathVariable Long roomId,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
//         try {
//             List<?> waterMeterRecords = meterService.getWaterMeterRecordsByRoomAndMonth(roomId, month);
//             return ResponseEntity.ok(waterMeterRecords);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//         }
//     }

//     // ดึงข้อมูลมิเตอร์ไฟตามห้องและเดือน
//     @GetMapping("/electric/{roomId}")
//     public ResponseEntity<List<?>> getElectricMeterRecordsByRoomAndMonth(
//             @PathVariable Long roomId,
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
//         try {
//             List<?> electricMeterRecords = meterService.getElectricMeterRecordsByRoomAndMonth(roomId, month);
//             return ResponseEntity.ok(electricMeterRecords);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//         }
//     }

//     @GetMapping("/electric")
//     public ResponseEntity<Object> getElectricMeterRecordsByMonth(
//             @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
//         try {
//             List<ElectricMeter> electricMeterRecords = meterService.getElectricMeterRecordsByMonth(month);
//             return ResponseEntity.ok(electricMeterRecords);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                     .body("Failed to retrieve electric meter records: " + e.getMessage());
//         }
//     }


//     // ดึงข้อมูลมิเตอร์น้ำและไฟทั้งหมดในห้อง
// //    @GetMapping("/{roomNumber}")
// //    public ResponseEntity<List<?>> getMeterRecords(@PathVariable String roomNumber) {
// //            List<?> records = meterService.getMeterRecordsByRoomNumber(roomNumber);
// //            return ResponseEntity.ok(records);
// //
// //    }
// }
