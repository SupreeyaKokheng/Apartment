package com.apartment.management.dto;

import jakarta.validation.constraints.NotNull;

public class RoomDTO {
    @NotNull
    private Long id;
    private String roomNumber;
    private String tenantName;
    private Boolean isOccupied;

    // Constructors
    public RoomDTO() {}

    public RoomDTO(Long id, String roomNumber, String tenantName, Boolean isOccupied) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.tenantName = tenantName;
        this.isOccupied = isOccupied;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public Boolean getIsOccupied() { return isOccupied; }
    public void setIsOccupied(Boolean isOccupied) { this.isOccupied = isOccupied; }
}
