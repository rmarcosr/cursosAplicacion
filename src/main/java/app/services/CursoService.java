package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Curso;
import app.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	public Curso create(Curso curso) {

		Curso.formatearCurso(curso);

		return cursoRepository.save(curso);
	}

	public Curso read(Integer id) {
		Curso curso = new Curso(0, "Curso no encontrado", "Este curso no existe o fue eliminado.", null, 
				"https://www.nic.do/wp-content/uploads/2016/10/logo-placeholder-3.jpg");

		Optional<Curso> buscarCurso = cursoRepository.findById(id);

		if (!buscarCurso.isEmpty()) curso = buscarCurso.get();
		

		return curso;
	}

	public void update(Curso curso, Integer id) {

		if (id != 0) {
			Curso cursoBD = read(id);

			cursoBD = curso;

			Curso.formatearCurso(cursoBD);

			cursoRepository.save(cursoBD);
		}
	}

	public void delete(Integer id) {
		if (id != 0)
			cursoRepository.deleteById(id);

	}

	public List<Curso> getAll() {
		return cursoRepository.findAll();
	}

}
