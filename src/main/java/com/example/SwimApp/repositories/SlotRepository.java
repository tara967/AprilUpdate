package com.example.SwimApp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwimApp.model.Slot;


@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

	
	List<Slot> findAllByBookedTimeBetween(
		      Date TimeStart,
		      Date TimeEnd);
	
	//List<Slot> findByDate(Date date);
}
