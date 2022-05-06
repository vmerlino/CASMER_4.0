package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Categoria;
import Entities.LineaDePedido;
import Entities.Pedido;
import Entities.Producto;
import Entities.Usuario;
import Logic.LogicLdp;
import Logic.LogicPedido;
import Logic.Login;

/**
 * Servlet implementation class abmcPedido
 */
@WebServlet("/abmcPedido")
public class abmcPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public abmcPedido() {
		super();
	}

	LogicPedido ctrlped = new LogicPedido();
	Usuario usu = new Usuario();
	LinkedList<Pedido> listaPed = new LinkedList<>();
	Double totalPagar;
	LogicPedido ctrl = new LogicPedido();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		switch (accion) {

		case "generarCompra": {
			Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
			Pedido p = (Pedido) request.getSession().getAttribute("Pedido");
			LinkedList<LineaDePedido> productosPedidos = (LinkedList<LineaDePedido>) request.getAttribute("carrito");
			totalPagar = Double.parseDouble(request.getParameter("totalPagar"));
			p.setMonto(totalPagar);
			p.setProductos(productosPedidos);
			p.setEstado("a entregar");
			ctrl.update(p);
			Pedido pedidoActual= new Pedido();
			pedidoActual.setEstado("En proceso");
			pedidoActual.setUsu(usu);
			pedidoActual.setFechaPedido(new java.sql.Date(System.currentTimeMillis()));
			ctrl.add(pedidoActual);
			request.getSession().setAttribute("Pedido", pedidoActual);
			request.setAttribute("Pedido", p);
			request.setAttribute("error", true);
			request.getRequestDispatcher("WEB-INF/pedidos.jsp").forward(request, response);
			break;
		}
		
		case "misPedidos":{
			Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
			LinkedList<Pedido> pedidoss = ctrl.getPedidosUsuario(usu);
			request.setAttribute("listaPedidos", pedidoss);
			request.getRequestDispatcher("WEB-INF/misPedidos.jsp").forward(request, response);
			break;
		}
		case "cancelacion":{
			Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
			String idPedido = request.getParameter("idPedido");
			Pedido ped= new Pedido();
			ped.setIdPedido(Integer.parseInt(idPedido));
			ped= ctrl.getOne(ped);
			ped.setEstado("Cancelado");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date date =java.sql.Date.valueOf(LocalDate.now()) ;
			var fechaCancel = formato.format(date);
			ped.setFechaCancelacion(java.sql.Date.valueOf(fechaCancel));
			ctrl.update(ped);
			LinkedList<Pedido> pedidoss = ctrl.getPedidosUsuario(usu);
			request.setAttribute("listaPedidos", pedidoss);
			request.setAttribute("error", true);
			request.getRequestDispatcher("WEB-INF/misPedidos.jsp").forward(request, response);
			break;
		}
		case "cancelacionAdmin":{
			Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
			String idPedido = request.getParameter("idPedido");
			Pedido ped= new Pedido();
			ped.setIdPedido(Integer.parseInt(idPedido));
			ped= ctrl.getOne(ped);
			ped.setEstado("Cancelado");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date date =java.sql.Date.valueOf(LocalDate.now()) ;
			var fechaCancel = formato.format(date);
			ped.setFechaCancelacion(java.sql.Date.valueOf(fechaCancel));
			ctrl.update(ped);
			LinkedList<Pedido> pedidoss = ctrl.getAll();
			request.setAttribute("listaPedidos", pedidoss);
			request.setAttribute("error", true);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
			break;
		}
		case "ini":{
			LinkedList<Pedido> Pedido = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedido);
			request.getRequestDispatcher("WEB-INF/pedidos.jsp").forward(request, response);
			break;}

		case "editar": {
			request.getRequestDispatcher("WEB-INF/EditPedido.jsp").forward(request, response);
			break;
		}
		
		case "verDetalle":{
			Pedido p = new Pedido();
			LogicLdp ctrlLdp = new LogicLdp();
			int idPedido =Integer.parseInt( request.getParameter("idcompra"));
			p.setIdPedido(idPedido);
			p = ctrl.getOne(p);
			LinkedList<LineaDePedido> lineasDelPedido = ctrlLdp.getLineasDelPedido(p);
			request.setAttribute("lineasDelPedidoComprado", lineasDelPedido);
			request.setAttribute("Pedido", p);
			request.getRequestDispatcher("WEB-INF/verDetallePedido.jsp").forward(request, response);
			break;
		}

		case "listar": {
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
		}
			break;

		case "borrar": {
			String id = request.getParameter("idPedido");
			Pedido ped = new Pedido();
			ped.setIdPedido(Integer.parseInt(id));
			ctrlped.delete(ped);
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
			break;
		}

		case "actualizar": {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaC = null;
			Date fechaE = null;
			Date fechaP = null;
			Usuario usu = new Usuario();
			Login logicU = new Login();
			Pedido ped = new Pedido();
			Categoria cat = new Categoria();
			String id = request.getParameter("idPedido");
			String fechaPedido = request.getParameter("fechaPedido");
			String fechaEntrega = request.getParameter("fechaEntrega");
			String fechaCancelacion = request.getParameter("fechaCancelacion");
			String monto = request.getParameter("monto");
			String dni = request.getParameter("dni");
			String estado = request.getParameter("estado");
			ped.setIdPedido(Integer.parseInt(id));
			try {
				fechaE = formato.parse(fechaEntrega);
				fechaEntrega = formato.format(fechaE);
				fechaC = formato.parse(fechaCancelacion);
				fechaCancelacion = formato.format(fechaC);
				fechaP = formato.parse(fechaPedido);
				fechaPedido = formato.format(fechaP);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ped.setFechaPedido(java.sql.Date.valueOf(fechaPedido));
			if(!fechaEntrega.equals("")) {
			ped.setFechaEntrega(java.sql.Date.valueOf(fechaEntrega));
			}else {ped.setFechaEntrega(null);}
			if(!fechaCancelacion.equals("")) {
			ped.setFechaCancelacion(java.sql.Date.valueOf(fechaCancelacion));
			}else {ped.setFechaCancelacion(null);}
			ped.setMonto(Double.parseDouble(monto));
			ped.setEstado(estado);
			usu.setDni(dni);
			logicU.getByDni(usu);
			ped.setUsu(usu);
			ctrlped.update(ped);
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
			break;
		}
		case "entregado":{
			String id = request.getParameter("idPedido");
			Pedido ped = new Pedido();
			ped.setIdPedido(Integer.parseInt(id));
			ped= ctrl.getOne(ped);
			ped.setEstado("Entregado");
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date date =java.sql.Date.valueOf(LocalDate.now()) ;
			var fechaEntrega = formato.format(date);
			ped.setFechaEntrega(java.sql.Date.valueOf(fechaEntrega));
			ctrl.update(ped);
			LinkedList<Pedido> Pedidos = ctrlped.getAll();
			request.setAttribute("listaPedidos", Pedidos);
			request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);
			break;
		}
		case "buscar":{
			Pedido ped = new Pedido();
			String descripcion = request.getParameter("buscar");
			 if(descripcion.equals("")) {
				
				 request.setAttribute("buscarprod", "");
				 request.getRequestDispatcher("abmcPedido?accion=listar").forward(request, response);
			 }else{
				 int id1 = Integer.parseInt(descripcion);
				 ped.setIdPedido(id1);
			 Pedido Pedido = ctrl.getOne(ped);
			 LinkedList<Pedido> pedidos = new LinkedList<>();
			 pedidos.add(Pedido);
			 request.setAttribute("listaPedidos", pedidos);
			 request.setAttribute("buscarprod", descripcion);
			 request.getRequestDispatcher("WEB-INF/listarPedidos.jsp").forward(request, response);}
			 break;
		}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
