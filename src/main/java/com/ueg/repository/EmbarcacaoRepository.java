package com.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ueg.model.Embarcacao;

@Repository
public interface EmbarcacaoRepository extends JpaRepository<Embarcacao, Long> {

}
