package com.apartment.management.service;

import com.apartment.management.model.Room;
import com.apartment.management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public void updateMeterValue(Long roomId, Double meterValue) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setMeterValue(meterValue); // Assumes Room entity has a meterValue field
        roomRepository.save(room);
    }
}
