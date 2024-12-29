package com.apartment.management.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "water_meters")
public class WaterMeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private Double previousMeter;
    private Double currentMeter;
    private Double pricePerUnit;
    //private Double totalCost;

//    @Column(name = "record_date")
//    private LocalDate recordDate;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Double getPreviousMeter() {
        return previousMeter;
    }

    public void setPreviousMeter(Double previousMeter) {
        this.previousMeter = previousMeter;
    }

    public Double getCurrentMeter() {
        return currentMeter;
    }

    public void setCurrentMeter(Double currentMeter) {
        this.currentMeter = currentMeter;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
//
//    public Double getTotalCost() {
//        return totalCost;
//    }
//
//    public void setTotalCost(Double totalCost) {
//        this.totalCost = totalCost;
//    }
//
//    public LocalDate getRecordDate() {
//        return recordDate;
//    }
//
//    public void setRecordDate(LocalDate recordDate) {
//        this.recordDate = recordDate;
//    }
}
