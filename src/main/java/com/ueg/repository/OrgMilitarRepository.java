package com.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ueg.model.OrgMilitar;

@Repository
public interface OrgMilitarRepository extends JpaRepository<OrgMilitar, Long>  {

}
