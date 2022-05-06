package Logic;

import java.util.LinkedList;
import Data.DataPedido;
import Entities.Pedido;
import Entities.Usuario;

public class LogicPedido {
private DataPedido dp;
	
	public LinkedList<Pedido> getAll(){
		dp = new DataPedido();
		return dp.getAll();
	}
	
	public void add(Pedido ped) {
		dp=new DataPedido();
		dp.add(ped);
	}
	
	public void delete(Pedido ped) {
		dp=new DataPedido();
		dp.delete(ped);
	}

	public void eliminarPedido(Pedido pedido) {
		if(pedido.getEstado().equals("En proceso") ) {
			dp=new DataPedido();
			dp.delete(pedido);
		}
	}
	
	public Pedido getOne(Pedido pedido) {
		dp=new DataPedido();
		return dp.getOne(pedido);
	}
	

	public void update(Pedido p) {
		dp=new DataPedido();
		dp.update(p);
	}

	public LinkedList<Pedido> getPedidosUsuario(Usuario usu) {
		dp=new DataPedido();
		return dp.getPedidoUsuario(usu);		
	}
}
