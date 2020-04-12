package com.example.SwimApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwimApp.model.Slot;
import com.example.SwimApp.model.TimeTable;
import com.example.SwimApp.repositories.SlotRepository;
import com.example.SwimApp.repositories.TimeTableRepository;

import com.example.SwimApp.service.TimeTableService;


@Service
public class TimeTableServiceImpl implements TimeTableService{

	@Autowired
	TimeTableRepository repository;

	@Autowired 
	SlotRepository slotRepository;
	
	@Override
	public void save(TimeTable timeTable) {
		
		repository.save(timeTable);
	}

	@Override
	public List<TimeTable> allSlots() {
		
		return repository.findAll();
		
		
	}

	@Override
	public TimeTable findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void updateSlot(Slot slot) {
		// TODO Auto-generated method stub
		slotRepository.save(slot);
	}

	@Override
	public Slot findSlotById(Long id) {
		return slotRepository.findById(id).orElse(null);
	}
	
}


