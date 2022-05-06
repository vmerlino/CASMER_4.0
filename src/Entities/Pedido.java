package Entities;

import java.util.*;

public class Pedido {
	public int idPedido;
	public String estado;
	public Date fechaPedido;
	public Date fechaEntrega;
	public Date fechaCancelacion;
	public Usuario usu;
	public double monto;
	
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	private LinkedList<LineaDePedido> productos;
	
	public LinkedList<LineaDePedido> getProductos() {
		return productos;
	}
	public void setProductos(LinkedList<LineaDePedido> productosPedidos) {
		this.productos = productosPedidos;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Usuario getUsu() {
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date localDate) {
		this.fechaPedido = localDate;
	}
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", estado=" + estado + ", fechaPedido=" + fechaPedido
				+ ", fechaCancelacion=" + fechaCancelacion + "]";
	}
	
	
	
}
