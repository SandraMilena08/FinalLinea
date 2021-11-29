package cundi.edu.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cundi.edu.co.entity.Editorial;

@Repository
public interface IEditorialRepo extends JpaRepository<Editorial, Integer>{

}
