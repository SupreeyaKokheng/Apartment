package com.apartment.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MeterDTO {
    @NotNull
    private Long roomId;
    private BigDecimal meterValue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    // Constructors
    public MeterDTO() {}

    public MeterDTO(Long roomId, BigDecimal meterValue, LocalDate recordDate) {
        this.roomId = roomId;
        this.meterValue = meterValue;
        this.recordDate = recordDate;
    }

    // Getters and Setters
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public BigDecimal getMeterValue() { return meterValue; }
    public void setMeterValue(BigDecimal meterValue) { this.meterValue = meterValue; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
}

// package com.apartment.management.dto;

// import java.time.LocalDate;

// public class MeterDTO {
//     private Long roomId;
//     private Double meterValue;
//     private LocalDate recordDate;

//     // Getters and setters
//     public Long getRoomId() {
//         return roomId;
//     }

//     public void setRoomId(Long roomId) {
//         this.roomId = roomId;
//     }

//     public Double getMeterValue() {
//         return meterValue;
//     }

//     public void setMeterValue(Double meterValue) {
//         this.meterValue = meterValue;
//     }

//     public LocalDate getRecordDate() {
//         return recordDate;
//     }

//     public void setRecordDate(LocalDate recordDate) {
//         this.recordDate = recordDate;
//     }
// }