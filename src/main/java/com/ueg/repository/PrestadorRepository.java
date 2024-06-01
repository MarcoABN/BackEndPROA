package com.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ueg.model.Prestador;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long>  {

}
