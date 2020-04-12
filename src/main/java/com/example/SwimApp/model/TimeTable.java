package com.example.SwimApp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="timetable")
public class TimeTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date selectedDay;
	
	 @OneToMany(mappedBy="timeTable", cascade=CascadeType.ALL)
	private List<Slot> slot;

	public Date getselectedDay() {
		return selectedDay;
	}

	public void setselectedDay(Date day) {
		selectedDay = day;
	}

	public List<Slot> getSlot() {
		return slot;
	}

	public void setSlot(List<Slot> slot) {
		this.slot = slot;
	}
	
}
