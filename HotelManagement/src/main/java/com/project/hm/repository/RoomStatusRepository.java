package com.project.hm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hm.entity.RoomBookingStatus;

public interface RoomStatusRepository  extends JpaRepository<RoomBookingStatus, Long>{

	//List<RoomBookingStatus> findAll();

}
