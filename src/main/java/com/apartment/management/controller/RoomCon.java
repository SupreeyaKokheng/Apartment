package com.apartment.management.controller;
import com.apartment.management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/rooms")
public class RoomCon {

        @Autowired
        private RoomService roomService;

        @PostMapping("/meter")
        public ResponseEntity<String> updateMeterValue(@RequestBody MeterRequest request) {
            try {
                roomService.updateMeterValue(request.getRoomId(), request.getMeterValue());
                return ResponseEntity.ok("Meter value updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update meter value");
            }
        }
    }

    class MeterRequest {
        private Long roomId;
        private Double meterValue;

        // Getter and Setter
        public Long getRoomId() {
            return roomId;
        }

        public void setRoomId(Long roomId) {
            this.roomId = roomId;
        }

        public Double getMeterValue() {
            return meterValue;
        }

        public void setMeterValue(Double meterValue) {
            this.meterValue = meterValue;
        }


}
