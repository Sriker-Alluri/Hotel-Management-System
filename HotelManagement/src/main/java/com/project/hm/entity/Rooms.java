package com.project.hm.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rooms {

	@Id
	 @GeneratedValue(generator = "r_seq",strategy = GenerationType.AUTO)
	private Long id;
	private int roomNumber;
	private double roomPrice;
	//@Column(nullable = false)
	private boolean roomStatus=false;
	private String photos;
	private String description;
	 
	
	@Enumerated(value=EnumType.STRING)
	private Type type;
	
	
	
}
