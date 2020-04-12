package com.example.SwimApp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="slot")
public class Slot {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	Date bookedTime;
	
	private boolean isBooked;
	
	private String bookedBy;

	public Date getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(Date date) {
		this.bookedTime = date;
	}

	public boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
	
	
	
}
