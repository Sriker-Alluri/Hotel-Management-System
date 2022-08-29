package com.project.hm.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.hm.entity.RoomBookingStatus;
import com.project.hm.entity.Rooms;
import com.project.hm.entity.UserRegistration;
import com.project.hm.repository.RoomRepository;
import com.project.hm.repository.RoomStatusRepository;
import com.project.hm.repository.UserRepository;
import com.project.hm.response.AvailableRoomResponse;
import com.project.hm.service.RoomStatusService;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class RoomStatusController {
	@Autowired
	private RoomStatusService  roomStatusService;
	@Autowired
	private RoomStatusRepository roomStatusRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/room-booking")
    public ResponseEntity<?> createBooking(@RequestBody RoomBookingStatus booking,  
    		                                                @RequestParam Long roomId,
    		                                                Principal principal, Rooms rooms) {
		String username=principal.getName();
		
	UserRegistration user=this.userRepository.findByUsername(username);

		
    
		Optional<Rooms> room=this.roomRepository.findById(roomId).map(id->{

			   booking.setRooms(id);
		   
			return id;
		});
		   this.userRepository.findById(user.getId()).map(id->{
			   booking.setUserRegistration(id);
			   return id;
		   });
		  
		
		   Double roomPrice=room.get().getRoomPrice();
		   if(booking.getCheckIn()!=null && booking.getCheckOut()!=null) {
		    	 //room=this.roomRepository.findById(roomId);
		    
		    	//if(room.isPresent())
		          //room.get().setRoomStatus(true);
		    	this.roomRepository.save(room.get());
		    } 
		   
		   LocalDate date=LocalDate.now();
			   
		   if (booking.getCheckOut().equals(date)  && booking.getRooms().isRoomStatus()==true)  {
	        booking.getRooms().setRoomStatus(false);
			roomStatusRepository.save(booking);
			 //  return new  ResponseEntity<String>(" Already booked",HttpStatus.BAD_REQUEST);
			  
		   }
		   if (booking.getCheckIn().equals(date)  && booking.getRooms().isRoomStatus()==true)  {
		     
				   return new  ResponseEntity<String>(" Already booked",HttpStatus.BAD_REQUEST);
				  
			   }
		   if (booking.getCheckOut().isAfter(date)  && booking.getRooms().isRoomStatus()==true)  {
		       
				   return new  ResponseEntity<String>(" Already booked",HttpStatus.BAD_REQUEST);
				  
			   }
		   if(booking.getCheckIn().isBefore(date)  || booking.getCheckOut().isBefore(date) ){

			  
			   return new  ResponseEntity<String>(" cannot select previous dates",HttpStatus.BAD_REQUEST); 
			   }

			   if(booking.getCheckIn().equals(date) && booking.getRooms().isRoomStatus()==false){
				   booking.getRooms().setRoomStatus(true);
				   roomStatusRepository.save(booking);
				   //booking.getRooms().setRoomStatus(true);
			   }
			   if(booking.getCheckIn().isAfter(date) && booking.getRooms().isRoomStatus()==false){
				   roomStatusRepository.save(booking);
			   }
		   
		   
//		   if (booking.getCheckIn().equals(date)) {
//			booking.getRooms().setRoomStatus(true);
//			roomStatusRepository.save(booking);
//		   }
		   
		   
//		  
//		         if(booking.getCheckIn().isBefore(date) &&(booking.getRooms().isRoomStatus()==true))
//		         {
//		        
//		        	 
//	         return new  ResponseEntity<String>(" Cannot be boooked",HttpStatus.BAD_REQUEST);     
//	         
//	         
//		         
//		         
//		         }
		         
		         
		         if(booking.getCheckIn().isAfter(date) &&(booking.getRooms().isRoomStatus()==false)) 
		         {
		        
		        	 
	        // return new  ResponseEntity<String>(" Cannot be boooked",HttpStatus.BAD_REQUEST);     
		        	 booking.getRooms().setRoomStatus(true);
		        	 roomStatusRepository.save(booking);
		         
		         }
//		           else {
//		        	   booking.getRooms().setRoomStatus(false);
//		        	   roomStatusRepository.save(booking);
//		           }
//		   
		   
//		   else {
//			 
////			 if(room.isPresent())
////		          room.get().setRoomStatus(true);
//			   //return new  ResponseEntity<String>(" Already boooked",HttpStatus.ALREADY_REPORTED);
//		   }
//		   
//		   
		   
     
		   
		    Long days=ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
		    
		    
		    
		    booking.setTotal(roomPrice*days);
		    booking.setTotalDays(days);
		    booking.setGstTax((booking.getTotal()*18/100));
		    booking.setTotalPrice(booking.getTotal()+booking.getGstTax());
		    System.out.println("=========="+days);
        	   roomStatusRepository.save(booking);
	           
	
		    
		    
		    
	   return new ResponseEntity<RoomBookingStatus>(booking,HttpStatus.OK);
		
    }
	
	@GetMapping("/getAvailableRooms")
	public ResponseEntity<List<AvailableRoomResponse>> getAvailabeRooms() {
		List<AvailableRoomResponse> room=this.roomRepository.getAllAvailabeRooms();
		return new ResponseEntity<List<AvailableRoomResponse>>(room,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllBookings")
	public ResponseEntity<List<RoomBookingStatus>> getAllBookings() {
		List<RoomBookingStatus> room=this.roomStatusRepository.findAll();
		return new ResponseEntity<List<RoomBookingStatus>>(room,HttpStatus.OK);
		
	}
	

	
	
	

}
