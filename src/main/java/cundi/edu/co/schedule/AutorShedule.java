package cundi.edu.co.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cundi.edu.co.service.IAutorService;

@Component
public class AutorShedule {
	
	@Autowired
	private IAutorService service;

	
	@Scheduled(fixedRate = 500000)
	public void scheduleTaskWithFixedRate() {
	    System.out.println("Tarea programada scheduleTaskWithFixedRate");
	}	

}