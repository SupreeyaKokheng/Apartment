package com.apartment.management.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "electric_meter", uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "recordDate"}))
public class ElectricMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private Room room;

    @Column(nullable = false)
    private BigDecimal meterValue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate recordDate;

    // Constructor
    public ElectricMeter() {}

    public ElectricMeter(Room room, BigDecimal meterValue, LocalDate recordDate) {
        this.room = room;
        this.meterValue = meterValue;
        this.recordDate = recordDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public BigDecimal getMeterValue() { return meterValue; }
    public void setMeterValue(BigDecimal meterValue) { this.meterValue = meterValue; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
}


// package com.apartment.management.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import jakarta.persistence.*;
// import java.time.LocalDate;

// @Entity
// public class ElectricMeter {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "room_id", nullable = false)
//     @JsonIgnore
//     private Room room;

//     @Column(nullable = false)
//     private Double meterValue;

//     @Column(nullable = false)
//     private LocalDate recordDate;  // วันที่บันทึกค่า

//     // Constructor
//     public ElectricMeter() {}

//     public ElectricMeter(Room room, Double meterValue, LocalDate recordDate) {
//         this.room = room;
//         this.meterValue = meterValue;
//         this.recordDate = recordDate;
//     }

//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Room getRoom() {
//         return room;
//     }

//     public void setRoom(Room room) {
//         this.room = room;
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
