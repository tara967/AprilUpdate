package com.example.SwimApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SwimApp.model.TimeTable;
import com.example.SwimApp.repositories.TimeTableRepository;
import com.example.SwimApp.service.TimeTableService;


@Service
public class TimeTableServiceImpl implements TimeTableService{

	@Autowired
	TimeTableRepository repository;

	@Override
	public void save(TimeTable timeTable) {
		
		repository.save(timeTable);
	}
	
}


