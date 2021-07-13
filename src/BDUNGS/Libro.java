package BDUNGS;

public class Libro {
	private String nombre, categoria, isbn;
	private Integer ancho;
	
	Libro (String isbn, String nombre, String categoria, Integer ancho){
		setIsbn(isbn);
		setNombre(nombre);
		setCategoria(categoria);
		setAncho(ancho);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}
	
	@Override
	public String toString() {
		return "ISBN: "+this.isbn + ", Nombre: "+ this.nombre+ ", Categoría "+ this.categoria+ ", Ancho "+ this.ancho+". ";
	}
	
	
}
