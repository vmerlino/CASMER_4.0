package Entities;

public class Rol {
	private int idrol;
	private String descripcion;
	
	public int getId() {
		return idrol;
	}
	public void setId(int id) {
		this.idrol = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Rol [id=" + idrol + ", descripcion=" + descripcion + "]";
	}

}