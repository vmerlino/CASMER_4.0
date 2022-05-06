package Entities;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.http.Part;


public class Producto {
	private int idProducto;
	private String descripcion;
	private float precio;
	private int stock;
	private String img;
	private Categoria cat;
	
	public Categoria getCat() {
		return cat;
	}
	public void setCat(Categoria cat) {
		this.cat = cat;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idPoducto) {
		this.idProducto = idPoducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String toString() {
		return "Producto [idPoducto=" + idProducto + ", descripcion=" + descripcion + ", precio=" + precio + ", stock="
				+ stock +"]";
	}
	
	
}
