package com.apartment.management.dto;

import java.math.BigDecimal;

public class ReportDTO {
    private String roomNumber;
    private BigDecimal waterCharge;
    private BigDecimal electricCharge;
    private BigDecimal otherCharges;
    private BigDecimal totalAmount;

    // Constructors
    public ReportDTO() {}

    public ReportDTO(String roomNumber, BigDecimal waterCharge, BigDecimal electricCharge,
                     BigDecimal otherCharges, BigDecimal totalAmount) {
        this.roomNumber = roomNumber;
        this.waterCharge = waterCharge;
        this.electricCharge = electricCharge;
        this.otherCharges = otherCharges;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public BigDecimal getWaterCharge() { return waterCharge; }
    public void setWaterCharge(BigDecimal waterCharge) { this.waterCharge = waterCharge; }

    public BigDecimal getElectricCharge() { return electricCharge; }
    public void setElectricCharge(BigDecimal electricCharge) { this.electricCharge = electricCharge; }

    public BigDecimal getOtherCharges() { return otherCharges; }
    public void setOtherCharges(BigDecimal otherCharges) { this.otherCharges = otherCharges; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
