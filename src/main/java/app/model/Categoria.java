package app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_categoria")
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Getter
	@Setter
	private String nombre;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Getter
	@Setter
	private List<Curso> cursos = new ArrayList<>();

	public Categoria() {

	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public Categoria(Integer id, String nombre, List<Curso> cursos) {
		this.id = id;
		this.nombre = nombre;
		this.cursos = cursos;
	}
	
	

	public static void formatearCategoria(Categoria categoria) {

		if (categoria.nombre.isBlank())
			categoria.setNombre("Categoria sin nombre");

	}
}
