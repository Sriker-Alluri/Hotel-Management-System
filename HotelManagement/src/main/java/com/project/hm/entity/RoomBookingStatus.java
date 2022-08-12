package com.project.hm.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="roomBookingStatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingStatus {
	
	@Id 
	 @GeneratedValue(generator = "bookin_seq",strategy = GenerationType.AUTO)
  private Long id;
	//private boolean roomStatus;
	//private LocalDate checkIn;
	//private LocalDate checkOut;

    //@Temporal(TemporalType.DATE)
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    @NotNull(message = "Please enter start date")
    private LocalDate checkIn;

  //  @Temporal(TemporalType.DATE)
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
    @NotNull(message = "Please enter end date")
    private LocalDate checkOut;
    
    private Double total;
    
    private Double gstTax;
    
    private Long totalDays;
    private Double totalPrice;

	@ManyToOne()
	@JoinColumn(name="room_fk")
	private Rooms rooms;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id_fk")
	private UserRegistration userRegistration;
	
}
