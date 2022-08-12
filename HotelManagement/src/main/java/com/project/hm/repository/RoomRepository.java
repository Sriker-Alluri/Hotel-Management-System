package com.project.hm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hm.entity.Rooms;
import com.project.hm.response.AvailableRoomResponse;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long> {
	
	@Query(nativeQuery = true, value="select rooms.room_number AS roomNo, rooms.room_price AS roomPrice, rooms.type AS roomType, rooms.room_status AS roomStatus from(rooms LEFT JOIN room_booking_status ON room_booking_status.id=rooms.id) where rooms.room_status= 0")
	List<AvailableRoomResponse> getAllAvailabeRooms();

}
