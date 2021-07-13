package BDUNGS;
import java.util.*;

public class Estante {
	private String categoria;
	private Integer ancho, espacioLibre, numeroDeOrden;
	private ArrayList<Libro> libros;
	
	Estante(String categoria,Integer numeroDeOrden, Integer ancho){
		setCategoria(categoria);
		setAncho(ancho);
		setNumeroDeOrden(numeroDeOrden);
		setLibros();
		setEspacioLibre();
	}
	public String getCategoria() {
		return categoria;
	}
	
	

	public Integer getNumeroDeOrden() {
		return numeroDeOrden;
	}
	public void setNumeroDeOrden(Integer numeroDeOrden) {
		this.numeroDeOrden = numeroDeOrden;
	}
	public void setCategoria(String categoria) {
//		if (categoria.trim().length()==0) throw new RuntimeException("La categoría no puede estar vacía"); 
		this.categoria = categoria;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Integer getEspacioLibre() {
		return espacioLibre;
	}

	public void setEspacioLibre() {
		this.espacioLibre = this.ancho;
	}

	public ArrayList<Libro> getLibros() {
		return libros;
	}

	public void setLibros() {
		this.libros=new ArrayList<Libro>();
	}
	
	public void ingresarLibro(Libro l) {
		if (this.espacioLibre<l.getAncho()) throw new RuntimeException("No hay espacio suficiente para ingresar el libro");
		if (!this.categoria.equals(l.getCategoria())) throw new RuntimeException("No se puede ingresar el libro a esta categoria");
		else {
			this.libros.add(l);
			this.espacioLibre-=l.getAncho();
		}
	}
	
	public void quitarLibro(String isbn) {
		Iterator<Libro> it=this.libros.iterator();
		while(it.hasNext()) {
			Libro l=it.next();
			if(l.getIsbn().equals(isbn)) {
				it.remove();
				this.espacioLibre+=l.getAncho();
			}
		}
	}

	public boolean estaRotulado() {
		return this.categoria.length()!=0;
	}
	
	public boolean lleno() {
		return this.espacioLibre==0;
	}
	
	public boolean vacio() {
		return this.espacioLibre.equals(this.ancho);
	}
	
	@Override
	public String toString() {
		StringBuilder str=new StringBuilder();
		str.append(this.numeroDeOrden);
		str.append(" ");
		str.append(this.categoria);
		str.append(": [");
		for (int i=0;i<this.libros.size();i++) {
			str.append(this.libros.get(i));
		}
		str.append("]");
		return str.toString();
	}

	
}
