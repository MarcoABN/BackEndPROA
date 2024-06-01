package com.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ueg.model.Motor;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long>{

}
