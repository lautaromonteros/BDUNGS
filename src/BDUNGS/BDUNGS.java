package BDUNGS;
import java.util.*;

public class BDUNGS {
	private Integer cantidadEstantes, ancho;
	private HashMap<Integer, Estante> estantes;
	private HashMap<String, Integer> libros;
	private HashSet<String> categorias;

	BDUNGS (Integer cantidadEstantes, Integer ancho){
		setCantidadEstantes(cantidadEstantes);
		setAncho(ancho);
		setEstantes(cantidadEstantes, ancho);
		setLibros();
		setCategorias();
	}
	
	public HashSet<String> getCategorias() {
		return categorias;
	}

	public void setCategorias() {
		this.categorias = new HashSet<String>();
	}	

	public HashMap<String, Integer> getLibros() {
		return libros;
	}

	public void setLibros() {
		this.libros = new HashMap<String,Integer>();
	}

	public Integer getCantidadEstantes() {
		return cantidadEstantes;
	}

	public void setCantidadEstantes(Integer cantidadEstantes) {
		this.cantidadEstantes = cantidadEstantes;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public HashMap<Integer,Estante> getEstantes() {
		return estantes;
	}
	
	public void setEstantes(Integer cantidadEstantes, Integer ancho) {
		this.estantes = new HashMap<Integer,Estante>();
		for(int i = 0; i < cantidadEstantes; i++) {
			this.estantes.put(i,new Estante("",i,ancho));
		}
	}
	
	public void rotularEstante(String categoria, int posicion) {
		if (posicion>=getEstantes().size()) throw new RuntimeException("No hay mas espacio");
		for (int i=0;i<this.estantes.size();i++) {
			if (i==posicion) {
				if (this.estantes.get(i).estaRotulado() && !this.estantes.get(i).vacio()) throw new RuntimeException("El estante "+i+" ya está rotulado, pruebe con otra posición");
				else {
					this.categorias.add(categoria);
					this.getEstantes().get(posicion).setCategoria(categoria);
				}
			}			
		}
	}
	
	public boolean ingresarLibro(String isbn, String categoria, String nombre, Integer ancho) {
		if (!this.categorias.contains(categoria)) throw new RuntimeException("No se puede ingresar el libro");
		Libro l=new Libro(isbn, nombre,categoria, ancho);
		HashSet<Estante> estantesPorCategorias=new HashSet<Estante>();
		for (int i = 0; i < this.estantes.size(); i++) {
			if(this.estantes.get(i).getCategoria().equals(categoria)) {
				estantesPorCategorias.add(this.estantes.get(i));
			}
		}
		Iterator<Estante> it=estantesPorCategorias.iterator();
		while(it.hasNext()) {
			Estante est=it.next();
			if (est.getEspacioLibre()>ancho) {
				this.estantes.get(est.getNumeroDeOrden()).ingresarLibro(l);
				agregarLibros(l);
				return true;
			}
			else {
				System.out.println("El estante " +est.getNumeroDeOrden()+ " está lleno");
			}
		}
		return false;
	}
	
	public void agregarLibros(Libro l) {
		if(this.libros.containsKey(l.getIsbn())) {
			int cantidad=this.libros.get(l.getIsbn());
			this.libros.put(l.getIsbn(), cantidad+=1);
		}
		else {
			this.libros.put(l.getIsbn(), 1);
		}
	}
	
	public int espacioLibre(Integer posicion) {
		if (!this.estantes.containsKey(posicion)) {
			throw new RuntimeException("La posición es mas grande que la cantidad de estantes");
		}
		if (this.estantes.get(posicion).getCategoria().isEmpty()) {
			throw new RuntimeException("No está rotulado");
		}
		return this.estantes.get(posicion).getEspacioLibre();
	}
	
	public void eliminarLibro(String isbn) {
		if (!this.libros.containsKey(isbn)) throw new RuntimeException("Este libro no esta archivado");
		ArrayList<Estante> estantes=new ArrayList<>();
		for (int i = 0; i < this.estantes.size(); i++) {
			estantes.add(this.estantes.get(i));
		}
		for(Estante e:estantes) {
			this.estantes.get(e.getNumeroDeOrden()).quitarLibro(isbn);
		}
		this.libros.remove(isbn);
	}
	
	public HashMap<String, Integer> verLibrosCategoria(String categoria){
		if (!this.categorias.contains(categoria)) throw new RuntimeException("Esta categoria no esta ingresada");
		HashMap<String, Integer> aux = new HashMap<>();
		for (int i=0;i<this.estantes.size();i++) {
			if (this.estantes.get(i).getCategoria().equals(categoria)) {
				ArrayList<Libro> libros= this.estantes.get(i).getLibros();
				for (Libro l:libros) {
					if (aux.containsKey(l.getIsbn())) {
						int cantidad=aux.get(l.getIsbn());
						aux.put(l.getIsbn(), cantidad+=1);
					}else {
						aux.put(l.getIsbn(), 1);
					}
				}
			}
		}
		return aux;
	}
	
	public int reacomodarCategoria(String categoria) {
		if (!this.categorias.contains(categoria)) throw new RuntimeException("No está definida la categoría");
		ArrayList<Integer> ordenes=new ArrayList<Integer>();
		for (int i=0;i<this.estantes.size();i++) {
			if (this.estantes.get(i).getCategoria().equals(categoria)) {
				ordenes.add(this.estantes.get(i).getNumeroDeOrden());
			}
		} 
		int estantesLiberados=0, ultimoEstante=ordenes.size()-1, primerEstantedeOrden=0;
		while (ultimoEstante>primerEstantedeOrden) {
			if(this.estantes.get(ordenes.get(ultimoEstante)).vacio()) ultimoEstante--;
			else if(this.estantes.get(ordenes.get(primerEstantedeOrden)).lleno()) primerEstantedeOrden++;
			else {
				ArrayList<Libro> libros=this.estantes.get(ordenes.get(ultimoEstante)).getLibros();
				for (int i = 0; i < libros.size(); i++) {
					Libro aux=libros.get(i);
					if(ultimoEstante>primerEstantedeOrden && aux.getAncho()<this.estantes.get(ordenes.get(primerEstantedeOrden)).getEspacioLibre()){
						this.estantes.get(ordenes.get(primerEstantedeOrden)).ingresarLibro(aux);
						this.estantes.get(ordenes.get(ultimoEstante)).quitarLibro(aux.getIsbn());
					}
					else {
						ultimoEstante--;
					}
					
				}if (this.estantes.get(ordenes.get(ultimoEstante)).vacio()) estantesLiberados++;
			}
		}
		return estantesLiberados;
	}
	
	@Override
	public String toString() {
		StringBuilder s=new StringBuilder();
		for (int i=0;i<this.estantes.size();i++) {
			s.append(this.estantes.get(i)+ "\n");
		}
		s.append("\nLibros: [");
		for (Map.Entry<String, Integer> aux : this.libros.entrySet()) {
            String isbn = aux.getKey();
            Integer stock = aux.getValue();
            s.append("ISBN: " + isbn + " Cantidad: " + stock+". ");
        }
		s.append("]\n");
		
		return s.toString();
	}
	

}
