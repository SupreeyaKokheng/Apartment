package com.apartment.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apartment.management.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Transactional(readOnly = true)
    Optional<Room> findByRoomNumber(String roomNumber);
}

// package com.apartment.management.repository;

// import com.apartment.management.model.Room;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.Optional;

// public interface RoomRepository extends JpaRepository<Room, Long> {
//     Optional<Room> findByRoomNumber(String roomNumber);
// }
