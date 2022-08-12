package com.project.hm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.repository.RoomStatusRepository;

@Service
public class RoomStatusService {
@Autowired
private RoomStatusRepository roomStatusRepository;
	public List<RoomBookingStatus> createBooking() {
		return roomStatusRepository.findAll();
		
	}

}
  