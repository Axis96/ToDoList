package it.dstech.repository;

import it.dstech.model.Attività;
import it.dstech.model.Utente;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AttivitàRepository extends JpaRepository<Attività, Long> {

	List<Attività> findByDocente(Utente utente);

	
}