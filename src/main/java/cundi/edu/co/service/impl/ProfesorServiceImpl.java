package cundi.edu.co.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cundi.edu.co.dto.Materias;
import cundi.edu.co.dto.ProfesorDto;
import cundi.edu.co.service.IMaterias;
import cundi.edu.co.service.IProfesorService;

@Service
@Qualifier("profesor")
public class ProfesorServiceImpl implements IProfesorService, IMaterias {
	
	List<Materias> materias = new ArrayList<Materias>();
	
	@Override
	public ProfesorDto retornarProfesor(int number, String emocion, String email) {
		materias.add(new Materias ("Español"));
		ProfesorDto profesor = new ProfesorDto("Sandra","461217","3107553158", (short) (21 + number),   emocion + email, materias);
		return profesor;
	}

	public ProfesorDto crearProfesor(ProfesorDto profesor) {
		System.out.println("Entre metodo crear");
		return profesor;
	}

	public ProfesorDto actualizarProfesor(ProfesorDto profesor) {
		System.out.println("Entre metodo actualizar");
		return profesor;
		
	}

	@Override
	public void eliminarProfesor(int i) {
		System.out.println("Entre metodo eliminar");
	}

	@Override
	public int numeroMaterias() {
		return materias.size();
	}


}
