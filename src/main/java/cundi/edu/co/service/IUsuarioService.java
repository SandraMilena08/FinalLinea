package cundi.edu.co.service;

import cundi.edu.co.entity.Usuario;

public interface IUsuarioService extends ICrud<Usuario, Integer> {
	
	public int sumar(int... num);
	

}