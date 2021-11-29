package cundi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cundi.edu.co.entity.Usuario;

@Repository
public interface IUsuarioRepo  extends JpaRepository<Usuario, Integer> {

	Usuario findOneByNick(String nick);
}