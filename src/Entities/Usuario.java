package Entities;

import java.util.LinkedList;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String direccion;
	private String email;
	private String telefono;
	private String contrasenia;
	private LinkedList<Rol> rol;
	
	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LinkedList<Rol> getRol() {
		return rol;
	}
	public void setRol(LinkedList<Rol> r) {
		this.rol = r;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", direccion=" + direccion
				+ ", email=" + email + ", telefono=" + telefono + ", contraseña=" + contrasenia +", rol=" + rol + "\n]";
	}
	
	
	
}
