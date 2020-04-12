package com.example.SwimApp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwimApp.model.Slot;
import com.example.SwimApp.model.TimeTable;


@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

	
	TimeTable findBySelectedDay(Date date);
}
