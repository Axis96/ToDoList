package it.dstech.repository;

import it.dstech.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

	Utente findByUsername(String username);

}