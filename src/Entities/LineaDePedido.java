package Entities;

public class LineaDePedido {
	private int nroldp;
	private Producto prod;
	private int cant;
	private double subTot;
	private double tot;
	
	public double getTot() {
		return tot;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}
	public int getNroldp() {
		return nroldp;
	}
	public void setNroldp(int nroldp) {
		this.nroldp = nroldp;
	}
	
	public Producto getProd() {
		return prod;
	}
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public double getSubTot() {
		return subTot;
	}
	public void setSubTot(double subTot) {
		this.subTot = subTot;
	}
	
	public String toString() {
		return "LineaDePedido [nroldp=" + nroldp + ", prod=" + prod + ", cant=" + cant + ", subTot=" + subTot + "]";
	}
	
	
	
	
}
