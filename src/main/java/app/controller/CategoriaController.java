package app.controller;

import java.util.ArrayList;
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
public class CategoriaController {
	
	@Autowired
    private CategoriaService categoriaService;
	
	// Pagina principal de la aplicación
	// Carga y muestra todas las categorias y en consecuencia sus cursos
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Categoria> categorias = categoriaService.getAll();

		model.addAttribute("categorias", categorias);

		return "index";
	}
	
	// Carga todas las categorias, usada en la tabla para gestionar las mismas.
	
	@GetMapping("/categorias")
	public String cargarCategorias(Model model) {
		
		List<Categoria> categorias = categoriaService.getAll();
		
		model.addAttribute("categorias", categorias);
		
		return "categorias";
	}
	
	
	// Pagina y método para guardar nuevas categorias
	
	@GetMapping("/crearCategoria")
	public String crearCategoria(Model model) {
		
		model.addAttribute("nuevaCategoria", new Categoria());
		
		return "crearCategoria";
	}
	
	
	
	@PostMapping("/guardarCategoria")
	public String guardarCategoria(@ModelAttribute Categoria nuevaCategoria) {

		categoriaService.create(nuevaCategoria);

		return "redirect:/categorias";
	}
	
	
	
	// Pagina para mostrar una única categoria
	
	@GetMapping("/categoria/{id}")
	public String mostrarCategoria(@PathVariable Integer id, Model model) {
		
		Categoria categoria = categoriaService.read(id);
		
		model.addAttribute("categoria", categoria);
		
		
		return "categoria";
	}
	
	// Método para eliminar una categoria
	
	@GetMapping("/eliminarCategoria/{id}")
	public String eliminarCategoria(@PathVariable Integer id) {
		
		categoriaService.delete(id);
		
		return "redirect:/categorias";
	}
	
	// Pagina y método para actualizar una categoria
	

	@GetMapping("/formularioActualizarCategoria/{id}")
	public String actualizarCategoria(Model model, @PathVariable Integer id) {
		
		Categoria categoria = categoriaService.read(id);
		
		model.addAttribute("categoria", categoria);
		
		return "actualizarCategoria";
	}
	
	
	@PostMapping("/actualizarCategoria/{id}")
	public String actualizar(@PathVariable Integer id, Categoria categoria) {
		
		categoriaService.update(categoria, id);
		
		return "redirect:/categorias";
	}
	
	// Generar datos de prueba (para crear el esquema en BD)
	
	
	@GetMapping("/generarDatos")
	public String generarDatos() {
		
		Categoria cat1 = new Categoria("Java");
		
		Categoria cat2 = new Categoria("C++");
		
		Curso curso1 = new Curso("Java básico", "Aprende lo esencial sobre java", cat1, 
				"https://formaciononline.eu/wp-content/uploads/2013/05/curso-java.jpg");
		
		Curso curso2 = new Curso("Java Medio", "Aprende lo esencial sobre java", cat1, 
				"https://formaciononline.eu/wp-content/uploads/2013/05/curso-java.jpg");
		
		Curso curso3 = new Curso("Java Avanzado", "Aprende lo esencial sobre java", cat1, 
				"https://formaciononline.eu/wp-content/uploads/2013/05/curso-java.jpg");
		
		Curso curso4 = new Curso("Java UI", "Interfaz grafica en Java", cat1,
				"https://i.ytimg.com/vi/JjA8PWWFDJk/maxresdefault.jpg");
		
		Curso curso8 = new Curso("Java con MySQL", "Java con persistencia en Base de datos", cat1,
				"https://darvishdarab.github.io/cs421_f20/assets/images/jdbc-b56f22932c17065dd130df67bee45bb0.png");
		
		Curso curso5 = new Curso("C++ Básico", "Aprende lo básico de C++", cat2,
				"https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2019/05/hipertextual-cursos-online-gratis-aprender-programar-c-2019879376.jpg?fit=1500%2C1000&quality=50&strip=all&ssl=1");
		
		Curso curso6 = new Curso("C++ Intermedio", "Aprende más sobre de C++", cat2,
				"https://i0.wp.com/imgs.hipertextual.com/wp-content/uploads/2019/05/hipertextual-cursos-online-gratis-aprender-programar-c-2019879376.jpg?fit=1500%2C1000&quality=50&strip=all&ssl=1");
		
		Curso curso7 = new Curso("C++ POO", "Programación orientada a objetos en C++", cat2,
				"https://impulso06.com/wp-content/uploads/2024/01/Por-que-estudiar-gratis-Programacion-en-Visual-C.png");
		
		
		
		ArrayList<Curso> cursosCat1 = new ArrayList<>();
		
		cursosCat1.add(curso1); cursosCat1.add(curso2); 
		cursosCat1.add(curso3); cursosCat1.add(curso4);
		
		ArrayList<Curso> cursosCat2 = new ArrayList<>();
		
		cursosCat2.add(curso5); cursosCat2.add(curso6); 
		cursosCat2.add(curso7); cursosCat1.add(curso8);
		
		
		cat1.setCursos(cursosCat1);
		
		cat2.setCursos(cursosCat2);
		
		categoriaService.create(cat1); categoriaService.create(cat2);
				
		return "redirect:/";
	}
	
	
	@GetMapping("/eliminarTodo")
	public String eliminarTodo() {

		categoriaService.deleteAll();
		
		
		return "redirect:/";
		
	}
	
	
	
}
