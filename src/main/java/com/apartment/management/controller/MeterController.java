package com.apartment.management.controller;

import com.apartment.management.dto.MeterDTO;
import com.apartment.management.model.ElectricMeter;
import com.apartment.management.model.Room;
import com.apartment.management.service.MeterService;
import com.apartment.management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    @Autowired
    private MeterService meterService;

    @Autowired
    private RoomService roomService;

    // บันทึกมิเตอร์น้ำ
    @PostMapping("/water")
    public ResponseEntity<?> saveWaterMeter(@RequestBody MeterDTO meterDTO) {
        try {
            meterService.saveWaterMeter(meterDTO.getRoomId(), meterDTO.getMeterValue(), meterDTO.getRecordDate());
            return ResponseEntity.status(HttpStatus.CREATED).body("Water meter recorded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to record water meter: " + e.getMessage());
        }
    }

    // บันทึกมิเตอร์ไฟ
    @PostMapping("/electric")
    public ResponseEntity<?> saveElectricMeter(@RequestBody MeterDTO meterDTO) {
        try {
            meterService.saveElectricMeter(meterDTO.getRoomId(), meterDTO.getMeterValue(), meterDTO.getRecordDate());
            return ResponseEntity.status(HttpStatus.CREATED).body("Electric meter recorded successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to record electric meter: " + e.getMessage());
        }
    }

    // ดึงข้อมูลมิเตอร์น้ำตามห้องและเดือน
    @GetMapping("/water/{roomId}")
    public ResponseEntity<List<?>> getWaterMeterRecordsByRoomAndMonth(
            @PathVariable Long roomId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        try {
            List<?> waterMeterRecords = meterService.getWaterMeterRecordsByRoomAndMonth(roomId, month);
            return ResponseEntity.ok(waterMeterRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // ดึงข้อมูลมิเตอร์ไฟตามห้องและเดือน
    @GetMapping("/electric/{roomId}")
    public ResponseEntity<List<?>> getElectricMeterRecordsByRoomAndMonth(
            @PathVariable Long roomId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        try {
            List<?> electricMeterRecords = meterService.getElectricMeterRecordsByRoomAndMonth(roomId, month);
            return ResponseEntity.ok(electricMeterRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/electric")
    public ResponseEntity<Object> getElectricMeterRecordsByMonth(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        try {
            List<ElectricMeter> electricMeterRecords = meterService.getElectricMeterRecordsByMonth(month);
            return ResponseEntity.ok(electricMeterRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve electric meter records: " + e.getMessage());
        }
    }


    // ดึงข้อมูลมิเตอร์น้ำและไฟทั้งหมดในห้อง
//    @GetMapping("/{roomNumber}")
//    public ResponseEntity<List<?>> getMeterRecords(@PathVariable String roomNumber) {
//            List<?> records = meterService.getMeterRecordsByRoomNumber(roomNumber);
//            return ResponseEntity.ok(records);
//
//    }
}
