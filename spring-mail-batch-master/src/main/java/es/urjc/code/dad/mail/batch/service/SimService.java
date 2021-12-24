package es.urjc.code.dad.mail.batch.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.code.dad.mail.batch.model.Sim;
import es.urjc.code.dad.mail.batch.repository.SimRepository;

@Service
public class SimService {
@Autowired
SimRepository repository;

public Sim saveSim(Sim sim) {
	// TODO Auto-generated method stub
	return repository.save(sim);
}
public List<Sim> getAllSims() {
	// TODO Auto-generated method stub
	return repository.findAll();
}





}
