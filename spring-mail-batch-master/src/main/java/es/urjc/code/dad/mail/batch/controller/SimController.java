package es.urjc.code.dad.mail.batch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.dad.mail.batch.model.Sim;
import es.urjc.code.dad.mail.batch.service.SimService;
@RestController
@RequestMapping("/sims")
public class SimController {
	@Autowired
	SimService service;

	@PostMapping("/createsim")
	public Sim createSim (Sim sim) {
		return service.saveSim(sim);
		
	}
		
		@GetMapping("/allsims")
		public List<Sim> getAllSims(){
			return service.getAllSims();
			
		}
	}



