package it.dstech.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import it.dstech.model.Ruolo;
import it.dstech.model.Utente;
import it.dstech.repository.RuoloRepository;
import it.dstech.repository.UtenteRepository;
;


@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private RuoloRepository ruoloRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Utente save(Utente utente) {
		Utente studente2 = utenteRepository.findByUsername(utente.getUsername());
		
		if(studente2==null) {
	        utente.setPassword(bCryptPasswordEncoder.encode(utente.getPassword()));
	        utente.setActive(true);
	       
	        Ruolo userRole = ruoloRepository.findByRuolo("UTENTE");
	        utente.setRuolo(new HashSet<Ruolo>(Arrays.asList(userRole)));
		}
	        return utenteRepository.save(utente);
	    }
	
	
	public Utente get(Long id) {
		return utenteRepository.findById(id).get();
	}

	public void delete(Long id) {
		utenteRepository.deleteById(id);
	}
	
	public List<Utente> listaStudenti() {
		return (List<Utente>) utenteRepository.findAll();
	}

	public Utente findUserByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}
		
}


