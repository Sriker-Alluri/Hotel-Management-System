package com.project.hm.response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public interface AvailableRoomResponse {
	Integer getRoomNo();
	Long getRoomPrice();
	//@Enumerated(EnumType.STRING)
	String getRoomType();
	String getRoomStatus();

}
