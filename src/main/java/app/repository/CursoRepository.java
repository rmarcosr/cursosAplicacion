package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
	
	 
    
	
}
