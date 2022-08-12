package com.project.hm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.hm.customexceptions.BadRequestException;
import com.project.hm.customexceptions.ResourceNotFoundException;
import com.project.hm.entity.Rooms;
import com.project.hm.repository.RoomRepository;


@Service
public class RoomService {

	
	
	@Autowired
	 private RoomRepository roomRepository;
	  public List<Rooms> fetchAllRooms() {
	        return roomRepository.findAll();
	    }

	    public Rooms findById(Long id) throws ResourceNotFoundException {
	        return roomRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("709","resource not found"));
	    }

		public Rooms createRoom( Rooms room){
			
			       // try {
			             return roomRepository.save(room);
			        //} catch (Exception e) {
			           // throw new BadRequestException("invalid request");
			       // }
			    }

		public void deleteAllRooms() {
		roomRepository.deleteAll();
			
		}

		public void deleteRoom(Long id) {
			
			roomRepository.deleteById(id);
		}

		public void updateRoom(@Valid Rooms room)  throws BadRequestException{
			try {
	            roomRepository.save(room);
	        } catch (Exception e) {
	            throw new BadRequestException("invalid request");
	        }
			
		}
			
		}

