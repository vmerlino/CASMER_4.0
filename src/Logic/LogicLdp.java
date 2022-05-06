package Logic;

import Data.DataLdp;
import Data.DataProducto;
import Entities.LineaDePedido;
import Entities.Pedido;
import Entities.Producto;

import java.util.LinkedList;

public class LogicLdp {
	
	private DataLdp dldp;
	private int nro=0;
	
	public LinkedList<LineaDePedido> getAll(){
		dldp = new DataLdp();
		return dldp.getAll();
	}

	public void delete(int idProducto, int idPedido) {
		dldp=new DataLdp();
		dldp.delete(idProducto, idPedido);
	}
	
	public LineaDePedido getOne(int idProducto, int idPedido){
		dldp=new DataLdp();
		return dldp.getOne(idProducto, idPedido) ;
	}

	public void add(LineaDePedido ldp, Pedido pedido) {
		dldp=new DataLdp();
		dldp.add(ldp, pedido.getIdPedido());
		
	}
	public void agregarCarrito(Producto Prod, Pedido pedido, int cantidad) {
		LinkedList<LineaDePedido> listadelineas = new LinkedList<LineaDePedido>();
		LineaDePedido linea;
		linea= null;
		int cant = 1;
		dldp=new DataLdp();
		 listadelineas = dldp.getLineasDelPedido(pedido.getIdPedido());
		if(listadelineas.size() != 0) {
		for(LineaDePedido ldp: listadelineas) {
			if(ldp.getProd().getIdProducto() == Prod.getIdProducto()) {
				linea = ldp;
				break;
			}
		}}
		if(linea != null) {
			linea.setCant(linea.getCant() + cantidad);
			linea.setSubTot(linea.getCant() * linea.getProd().getPrecio());
			this.update(linea, pedido);
		}else {
			linea = new LineaDePedido();
			linea.setNroldp(nro+1);
			linea.setProd(Prod);
			linea.setCant(cantidad);
			linea.setSubTot(cantidad*Prod.getPrecio());
			this.add(linea, pedido);
		}
		Prod.setStock(Prod.getStock()-cantidad);
		LogicProducto ctrlProd = new LogicProducto();
		ctrlProd.edit(Prod);
		
	}

	public LinkedList<LineaDePedido> getLineasDelPedido(Pedido pedido) {
		dldp=new DataLdp();
		return dldp.getLineasDelPedido(pedido.getIdPedido());
	}
	
	public void update(LineaDePedido linea, Pedido pedido) {
		dldp=new DataLdp();
		 dldp.update(linea, pedido);
	}
}
