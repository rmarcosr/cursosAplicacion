package app.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Getter
	@Setter
	private String titulo;

	@Getter
	@Setter
	private String descripcion;
	
	@Column(name = "url_imagen")
	@Getter
	@Setter
	private String urlImagen;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "id_categoria")  
	 @Getter @Setter private Categoria categoria;

	public Curso() {

	}
	
	public Curso(String titulo, String descripcion, Categoria categoria, String urlImagen) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.urlImagen = urlImagen;
	}

	public Curso(Integer id, String titulo, String descripcion, Categoria categoria, String urlImagen) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.urlImagen = urlImagen;
	}
	
	public static void formatearCurso(Curso curso) {
		
		if (curso.getTitulo().isBlank()) curso.setTitulo("Curso sin nombre");
		
		if (curso.getDescripcion().isBlank()) curso.setDescripcion("Curso sin descripcion");

		if (curso.getUrlImagen().isBlank()) curso.setUrlImagen("https://www.nic.do/wp-content/uploads/2016/10/logo-placeholder-3.jpg");

	}
	
	
	
}
