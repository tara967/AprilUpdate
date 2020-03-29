package com.example.SwimApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SwimApp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
