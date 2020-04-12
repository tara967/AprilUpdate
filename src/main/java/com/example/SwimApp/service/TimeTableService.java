package com.example.SwimApp.service;

import java.util.List;

import com.example.SwimApp.model.Slot;
import com.example.SwimApp.model.TimeTable;

public interface TimeTableService {

	
	void save(TimeTable timeTable);
	
	List<TimeTable> allSlots();
	
	TimeTable findById(Long id);
	
	void updateSlot (Slot slot);
	
	Slot findSlotById(Long id);
}
