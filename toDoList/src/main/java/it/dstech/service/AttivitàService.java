package it.dstech.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import it.dstech.model.Attività;
import it.dstech.repository.AttivitàRepository;




public class AttivitàService {

	@Autowired
	private AttivitàRepository attivitàRepository;
	


	
	public void save(Attività attività) {
		attivitàRepository.save(attività);
	}
	public Attività get(Long id) {
		return attivitàRepository.findById(id).get();
	}
	public void delete(Long id) {
		attivitàRepository.deleteById(id);
	}
	public List<Attività> listaAttività() {
		return (List<Attività>) attivitàRepository.findAll();
	}
	
	public String reversedDateFormat(Attività attività) {
		String anno = attività.getDataStringa().substring(0, 4);
		String mese = attività.getDataStringa().substring(5, 7);
		String giorno = attività.getDataStringa().substring(8, 10);
		String data = giorno + "-" + mese + "-" + anno;
		return data;
	}
}
