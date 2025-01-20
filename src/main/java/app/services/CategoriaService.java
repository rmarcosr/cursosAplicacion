package app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Categoria;
import app.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria create(Categoria categoria) {

		Categoria.formatearCategoria(categoria);

		return categoriaRepository.save(categoria);
	}

	public Categoria read(Integer id) {
		Categoria categoria = new Categoria(0, "Categoria no encontrada", null);

		Optional<Categoria> buscarCategoria = categoriaRepository.findById(id);

		if (!buscarCategoria.isEmpty()) {
			categoria = buscarCategoria.get();
		}

		return categoria;
	}

	public void update(Categoria categoria, Integer id) {

		if (id != 0) {
			Categoria categoriaBD = read(id);

			categoriaBD = categoria;

			Categoria.formatearCategoria(categoriaBD);

			categoriaRepository.save(categoriaBD);
		}
	}

	public void delete(Integer id) {

		if (id != 0)
			categoriaRepository.deleteById(id);

	}

	public List<Categoria> getAll() {
		return categoriaRepository.findAll();
	}

	public void deleteAll() {
		categoriaRepository.deleteAll();
	}

}
