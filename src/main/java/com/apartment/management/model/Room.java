package com.apartment.management.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaterMeter> waterMeters;

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElectricMeter> electricMeters;

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Billing> billings;

    // Constructor
    public Room() {}

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
}


// package com.apartment.management.model;

// import jakarta.persistence.*;
// import java.util.List;

// @Entity
// public class Room {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true)
//     private String roomNumber;

//     @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<WaterMeter> waterMeters;

//     @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<ElectricMeter> electricMeters;

//     @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Billing> billings;

//     // Constructor
//     public Room() {}

//     public Room(String roomNumber) {
//         this.roomNumber = roomNumber;
//     }

//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getRoomNumber() {
//         return roomNumber;
//     }

//     public void setRoomNumber(String roomNumber) {
//         this.roomNumber = roomNumber;
//     }

//     public List<WaterMeter> getWaterMeters() {
//         return waterMeters;
//     }

//     public void setWaterMeters(List<WaterMeter> waterMeters) {
//         this.waterMeters = waterMeters;
//     }

//     public List<ElectricMeter> getElectricMeters() {
//         return electricMeters;
//     }

//     public void setElectricMeters(List<ElectricMeter> electricMeters) {
//         this.electricMeters = electricMeters;
//     }

//     public List<Billing> getBillings() {
//         return billings;
//     }

//     public void setBillings(List<Billing> billings) {
//         this.billings = billings;
//     }
// }
