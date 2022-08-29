package com.project.hm.scheduler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.entity.Rooms;
import com.project.hm.repository.RoomStatusRepository;

@EnableScheduling
public class Scheduler {
	
	
	
	@Autowired
	RoomBookingStatus booking;

	@Autowired
	Rooms room;
	
	@Autowired
	RoomStatusRepository roomStatusRepository;
	
	@Scheduled(cron="* * * * * *")
	public void ScheduleConfig() {
		
		LocalDate date=LocalDate.now();
		if (booking.getCheckOut().equals(date)) {
			booking.getRooms().setRoomStatus(false);
			roomStatusRepository.save(booking);
		   }
}
}

