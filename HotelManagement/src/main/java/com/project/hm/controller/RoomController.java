package com.project.hm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.entity.Rooms;
import com.project.hm.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController {

	 @Autowired
	    RoomService roomservice;

	    

	    @GetMapping("/getAllRooms")
	    public ResponseEntity<List<Rooms>> getAllRooms() {
	    	 List<Rooms> rooms = roomservice.fetchAllRooms();
	    	  return new ResponseEntity<>(rooms, HttpStatus.OK);

	    }

	    @GetMapping("/getByRoomId/{id}")
	    public ResponseEntity<Rooms> getRoomById(@PathVariable Long id) {
	        Rooms room = roomservice.findById(id);
	        return new ResponseEntity<>(room, HttpStatus.OK);
	    }

	    
	    @PostMapping("")

	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Map<String, Boolean>> createRoom(@Valid @RequestBody Rooms room) {
	        roomservice.createRoom(room);
	        Map<String, Boolean> map = new HashMap<>();
	        map.put("success", true);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
	    }
	    @PutMapping("/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Map<String, Boolean>> updateRoom(@PathVariable Long id,
	                                                            @Valid @RequestBody Rooms room) {
	        room.setId(id);
	        roomservice.updateRoom(room);
	        Map<String, Boolean> map = new HashMap<>();
	        map.put("success", true);
	        return new ResponseEntity<>(map, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Map<String, Boolean>> deleteRoom(@PathVariable Long id) {
	        roomservice.deleteRoom(id);
	        Map<String, Boolean> map = new HashMap<>();
	        map.put("success", true);
	        return new ResponseEntity<>(map, HttpStatus.OK);
	    }
	    @DeleteMapping("/deleteAllRooms")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<Map<String, Boolean>> deleteAllRooms() {
	        roomservice.deleteAllRooms();
	        Map<String, Boolean> map = new HashMap<>();
	        map.put("success", true);
	        return new ResponseEntity<>(map, HttpStatus.OK);
	    }
	    
	}
	
	

