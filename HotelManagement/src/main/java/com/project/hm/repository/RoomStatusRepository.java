package com.project.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.hm.entity.RoomBookingStatus;

public interface RoomStatusRepository  extends JpaRepository<RoomBookingStatus, Long>{

}
