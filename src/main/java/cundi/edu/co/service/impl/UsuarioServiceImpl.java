package cundi.edu.co.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cundi.edu.co.entity.Usuario;
import cundi.edu.co.exception.ArgumentRequiredException;
import cundi.edu.co.exception.ConflicException;
import cundi.edu.co.exception.ModelNotFoundException;
import cundi.edu.co.repository.IUsuarioRepo;
import cundi.edu.co.service.IUsuarioService;
import org.springframework.security.core.GrantedAuthority;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {
	
	@Autowired
	private IUsuarioRepo repo;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario usuario = repo.findOneByNick(username);		
		if(usuario == null)
			new UsernameNotFoundException("----Usuario no encontrado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails ud = new User(usuario.getNick(), usuario.getClave(), roles);
		return ud;
	}



	@Override
	public Page<Usuario> retornarPaginado(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Page<Usuario> retornarPaginado(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario retonarPorId(Integer idObj) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public void editar(Usuario obj) throws ArgumentRequiredException, ModelNotFoundException, ConflicException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void eliminar(int obj) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int sumar(int... num) {
		int resultado = 0;
		for (int i : num) 
			resultado += i;
		return resultado;
	}



	@Override
	public List<Usuario> obtener() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void crear(Usuario entity) throws ConflicException {
		// TODO Auto-generated method stub
		
	}

}
