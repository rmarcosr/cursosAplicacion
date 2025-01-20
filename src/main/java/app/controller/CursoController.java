package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.model.Categoria;
import app.model.Curso;
import app.services.CategoriaService;
import app.services.CursoService;

@Controller
public class CursoController {

	@Autowired
	private CursoService cursoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/curso/{id}")
	public String cargarCurso(@PathVariable Integer id, Model model) {

		Curso curso = cursoService.read(id);

		model.addAttribute("curso", curso);

		return "curso";
	}

	@GetMapping("/crearCurso")
	public String crearCurso(Model model) {

		List<Categoria> categorias = categoriaService.getAll();
		model.addAttribute("nuevoCurso", new Curso());

		model.addAttribute("categorias", categorias);

		return "crearCurso";
	}

	@PostMapping("/guardar")
	public String guardarCurso(@ModelAttribute Curso nuevoCurso) {

		cursoService.create(nuevoCurso);

		return "redirect:/";
	}

	@GetMapping("/eliminarCurso/{id}")
	public String eliminarCurso(@PathVariable Integer id, Model model) {

		if (id != 0) {
			cursoService.delete(id);
		}

		return "redirect:/";
	}
	
	
	
	
	@GetMapping("/actualizarCurso/{id}")
	public String actualizarCurso(@PathVariable Integer id , Model model) {

		Curso curso = cursoService.read(id);
		model.addAttribute("curso", curso);
		
		List<Categoria> categorias = categoriaService.getAll();
		model.addAttribute("categorias", categorias);

		return "actualizarCurso";
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(@ModelAttribute Curso curso, @PathVariable Integer id) {

		cursoService.update(curso, id);

		return "redirect:/";
	}
	
	

}
