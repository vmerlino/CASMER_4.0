package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Logic.LogicLdp;
import Logic.LogicProducto;
import Data.DataProducto;
import Entities.LineaDePedido;
import Entities.Pedido;
import Entities.Producto;

/**
 * Servlet implementation class abmcLdp
 */
@WebServlet("/abmcLdp")
public class abmcLdp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public abmcLdp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	LogicProducto ctrl = new LogicProducto();
	LogicLdp ctrlldp = new LogicLdp();
	Producto Prod = new Producto();
	// LinkedList<Producto> Productos = new LinkedList<>();

	LinkedList<LineaDePedido> listaLdp = new LinkedList<>();
	LinkedList<LineaDePedido> listaLdp1 = new LinkedList<>();
	int item = 0;
	double subtotal = 0.0;
	double totalPagar = 0.0;
	int cant;
	int k = 0;
	
	LinkedList<Producto> productosUsados = new LinkedList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		Pedido pedido = (Pedido) request.getSession().getAttribute("Pedido");
		switch (accion) {

		case "AgregarCarrito": {
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			int idProd = Integer.parseInt(request.getParameter("id"));
			Prod.setIdProducto(idProd);
			Prod = ctrl.getByIdProducto(Prod);
			ctrlldp.agregarCarrito(Prod, pedido, cantidad);
			listaLdp = ctrlldp.getLineasDelPedido(pedido);
			request.setAttribute("carrito", listaLdp);
			request.setAttribute("error", true);
			request.getRequestDispatcher("abmc?accion=inicio").forward(request, response);

			break;
		}

		case "updateCantidad": {
			int idProd = Integer.parseInt(request.getParameter("id"));
			int cant = Integer.parseInt(request.getParameter("cant"));
			String operacion = (String)request.getParameter("operacion");
			LineaDePedido linea = ctrlldp.getOne(idProd, pedido.getIdPedido());
			if(cant >0) {
				linea.setCant(cant);
				linea.setSubTot(cant * linea.getProd().getPrecio());
				if(operacion.equals("suma")) {
					
				linea.getProd().setStock(linea.getProd().getStock()-1);
				totalPagar=totalPagar + linea.getProd().getPrecio();
				if(linea.getProd().getStock()==0 ) {
					request.setAttribute("error", true);
				}
				}else {	
					totalPagar=totalPagar - linea.getProd().getPrecio();
					linea.getProd().setStock(linea.getProd().getStock()+1);
						}
				ctrl.edit(linea.getProd());
				ctrlldp.update(linea, pedido);}
				listaLdp = ctrlldp.getLineasDelPedido(pedido);
				request.setAttribute("totalPagar", totalPagar);
				request.setAttribute("carrito", listaLdp);
			request.getRequestDispatcher("WEB-INF/Carrito.jsp").forward(request, response);

			break;
			
		}
		case "Carrito": {
			totalPagar = 0.0;
			LogicLdp ctrlldp = new LogicLdp();
			Pedido ped = (Pedido) request.getSession().getAttribute("Pedido");
			LinkedList<LineaDePedido> ldp =ctrlldp.getLineasDelPedido(ped);
			request.setAttribute("carrito", ldp);
			for (int i = 0; i < ldp.size(); i++) {
				totalPagar = totalPagar + ldp.get(i).getSubTot();
			}
			request.setAttribute("totalPagar", totalPagar);
			request.getRequestDispatcher("WEB-INF/Carrito.jsp").forward(request, response);
			break;
		}
		case "borrar": {
			LinkedList<LineaDePedido> listaldp = ctrlldp.getLineasDelPedido(pedido);
			LogicProducto  ctrlProd = new LogicProducto();
			
			for(LineaDePedido linea: listaldp) {
				linea.getProd().setStock(linea.getProd().getStock() + linea.getCant());
				ctrlProd.edit(linea.getProd());
				ctrlldp.update(linea, pedido);
				ctrlldp.delete(linea.getProd().getIdProducto(), pedido.getIdPedido());
			}
			LinkedList<LineaDePedido> lista = ctrlldp.getLineasDelPedido(pedido);
			request.setAttribute("totalPagar", 0.00);
			request.setAttribute("carrito", lista);
			request.getRequestDispatcher("WEB-INF/Carrito.jsp").forward(request, response);
			break;
		}
		case "delete": {
			int idProducto = Integer.parseInt(request.getParameter("id"));
			LineaDePedido ldp = new LineaDePedido();
			ldp = ctrlldp.getOne(idProducto, pedido.getIdPedido());
			totalPagar= totalPagar - ldp.getSubTot();
			ldp.getProd().setStock(ldp.getProd().getStock() + ldp.getCant());
			LogicProducto  ctrlProd = new LogicProducto();
			ctrlProd.edit(ldp.getProd());
			ctrlldp.delete(idProducto, pedido.getIdPedido());
			listaLdp = ctrlldp.getLineasDelPedido(pedido);
			request.setAttribute("totalPagar", totalPagar);
			request.setAttribute("carrito", listaLdp);
			request.getRequestDispatcher("WEB-INF/Carrito.jsp").forward(request, response);
		}
		case "listar": {
			LogicLdp ctrlLdp = new LogicLdp();
			LinkedList<LineaDePedido> listaldp = ctrlLdp.getAll();
			request.setAttribute("carrito", listaldp);
			request.getRequestDispatcher("WEB-INF/Carrito.jsp").forward(request, response);

			break;
		}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
