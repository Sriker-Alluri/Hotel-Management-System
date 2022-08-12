package com.project.hm.request;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.entity.Rooms;

import lombok.Data;
@Data
public class RoomRequest {
	
	private RoomBookingStatus bookingStatus;
	private Rooms room;

}
