package com.apartment.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BillingDTO {
    @NotNull
    private Long roomId;
    private String roomNumber;
    private BigDecimal waterMeterValue;
    private BigDecimal electricMeterValue;
    private BigDecimal waterCharge;
    private BigDecimal electricCharge;
    private BigDecimal otherCharges;
    private BigDecimal totalAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate billingMonth;

    // Constructors
    public BillingDTO() {}

    public BillingDTO(Long roomId, String roomNumber, BigDecimal waterMeterValue, BigDecimal electricMeterValue,
                      BigDecimal waterCharge, BigDecimal electricCharge, BigDecimal otherCharges, LocalDate billingMonth) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.waterMeterValue = waterMeterValue;
        this.electricMeterValue = electricMeterValue;
        this.waterCharge = waterCharge;
        this.electricCharge = electricCharge;
        this.otherCharges = otherCharges;
        this.billingMonth = billingMonth;
        calculateTotalAmount();
    }

    // Getters and Setters
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public BigDecimal getWaterMeterValue() { return waterMeterValue; }
    public void setWaterMeterValue(BigDecimal waterMeterValue) { this.waterMeterValue = waterMeterValue; }

    public BigDecimal getElectricMeterValue() { return electricMeterValue; }
    public void setElectricMeterValue(BigDecimal electricMeterValue) { this.electricMeterValue = electricMeterValue; }

    public BigDecimal getWaterCharge() { return waterCharge; }
    public void setWaterCharge(BigDecimal waterCharge) { this.waterCharge = waterCharge; }

    public BigDecimal getElectricCharge() { return electricCharge; }
    public void setElectricCharge(BigDecimal electricCharge) { this.electricCharge = electricCharge; }

    public BigDecimal getOtherCharges() { return otherCharges; }
    public void setOtherCharges(BigDecimal otherCharges) { this.otherCharges = otherCharges; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDate getBillingMonth() { return billingMonth; }
    public void setBillingMonth(LocalDate billingMonth) { this.billingMonth = billingMonth; }

    // ✅ คำนวณยอดรวม
    public void calculateTotalAmount() {
        this.totalAmount = (waterCharge != null ? waterCharge : BigDecimal.ZERO)
                .add(electricCharge != null ? electricCharge : BigDecimal.ZERO)
                .add(otherCharges != null ? otherCharges : BigDecimal.ZERO);
    }

    @Override
    public String toString() {
        return "BillingDTO{" +
                "roomId=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                ", waterMeterValue=" + waterMeterValue +
                ", electricMeterValue=" + electricMeterValue +
                ", waterCharge=" + waterCharge +
                ", electricCharge=" + electricCharge +
                ", otherCharges=" + otherCharges +
                ", totalAmount=" + totalAmount +
                ", billingMonth=" + billingMonth +
                '}';
    }
}
// package com.apartment.management.dto;

// import java.time.LocalDate;

// public class BillingDTO {
//     private Long roomId;
//     private String roomNumber;
//     private Double waterMeterValue;
//     private Double electricMeterValue;
//     private Double waterCharge;
//     private Double electricCharge;
//     private Double otherCharges;
//     private Double totalAmount;
//     private LocalDate billingMonth;

//     // Getters and Setters
//     public Long getRoomId() {
//         return roomId;
//     }

//     public void setRoomId(Long roomId) {
//         this.roomId = roomId;
//     }

//     public String getRoomNumber() {
//         return roomNumber;
//     }

//     public void setRoomNumber(String roomNumber) {
//         this.roomNumber = roomNumber;
//     }

//     public Double getWaterMeterValue() {
//         return waterMeterValue;
//     }

//     public void setWaterMeterValue(Double waterMeterValue) {
//         this.waterMeterValue = waterMeterValue;
//     }

//     public Double getElectricMeterValue() {
//         return electricMeterValue;
//     }

//     public void setElectricMeterValue(Double electricMeterValue) {
//         this.electricMeterValue = electricMeterValue;
//     }

//     public Double getWaterCharge() {
//         return waterCharge;
//     }

//     public void setWaterCharge(Double waterCharge) {
//         this.waterCharge = waterCharge;
//     }

//     public Double getElectricCharge() {
//         return electricCharge;
//     }

//     public void setElectricCharge(Double electricCharge) {
//         this.electricCharge = electricCharge;
//     }

//     public Double getOtherCharges() {
//         return otherCharges;
//     }

//     public void setOtherCharges(Double otherCharges) {
//         this.otherCharges = otherCharges;
//     }

//     public Double getTotalAmount() {
//         return totalAmount;
//     }

//     public void setTotalAmount(Double totalAmount) {
//         this.totalAmount = totalAmount;
//     }

//     public LocalDate getBillingMonth() {
//         return billingMonth;
//     }

//     public void setBillingMonth(LocalDate billingMonth) {
//         this.billingMonth = billingMonth;
//     }

//     // Additional Method to Calculate Total Amount
//     public void calculateTotalAmount() {
//         this.totalAmount = (waterCharge != null ? waterCharge : 0) +
//                 (electricCharge != null ? electricCharge : 0) +
//                 (otherCharges != null ? otherCharges : 0);
//     }

//     @Override
//     public String toString() {
//         return "BillingDTO{" +
//                 "roomId=" + roomId +
//                 ", roomNumber='" + roomNumber + '\'' +
//                 ", waterMeterValue=" + waterMeterValue +
//                 ", electricMeterValue=" + electricMeterValue +
//                 ", waterCharge=" + waterCharge +
//                 ", electricCharge=" + electricCharge +
//                 ", otherCharges=" + otherCharges +
//                 ", totalAmount=" + totalAmount +
//                 ", billingMonth=" + billingMonth +
//                 '}';
//     }
// }
