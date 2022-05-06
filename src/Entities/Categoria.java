package Entities;

public class Categoria {
	public int idCategoria;
	public String descripcion;
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Categorias \n[idCategoria=" + idCategoria + ", descripcion=" + descripcion + "]]";
	}
	
	
}
