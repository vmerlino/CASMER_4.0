package servlet;

import java.io.IOException;

import java.util.LinkedList;

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
import Logic.LogicCategoria;
import Logic.LogicLdp;
import Logic.LogicPedido;
import Logic.LogicProducto;
import Logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Signin", "/SIGNIN", "/signin" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String salida = request.getParameter("salida");
			LogicLdp ctrlLdp = new LogicLdp();
			LogicPedido ctrlPed = new LogicPedido();
			Pedido ped = (Pedido) request.getSession().getAttribute("Pedido");
			LinkedList<LineaDePedido> ldps = ctrlLdp.getLineasDelPedido(ped);
			for (LineaDePedido ldp : ldps) {
				ctrlLdp.delete(ldp.getProd().getIdProducto(), ped.getIdPedido());
			}
			ctrlPed.eliminarPedido(ped);
			HttpSession cerrarSesion = request.getSession(false);
			if (cerrarSesion != null) {

				cerrarSesion.setAttribute("usuario", null);
				cerrarSesion.invalidate();
			}
			response.sendRedirect("login.html");
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usu = new Usuario();
		Login ctrl = new Login();
		Pedido pedidoActual = new Pedido();
		LogicProducto ctrlProd = new LogicProducto();
		LogicCategoria ctrlCat = new LogicCategoria();
		LogicPedido ctrlPedido = new LogicPedido();
		String email = request.getParameter("email");
		String contrasenia = request.getParameter("contrasenia");

		request.getSession().removeAttribute("usuario");
		usu.setEmail(email);
		usu.setContrasenia(contrasenia);
		usu = ctrl.validate(usu);
		if (usu == null) {
			request.setAttribute("Errormesaje", "Usuario y/o contraseña no encontrados");
			request.setAttribute("returnPage", "login.html");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} else if (usu != null) {
			request.getSession().setAttribute("usuario", usu);
			pedidoActual.setEstado("En proceso");
			pedidoActual.setUsu(usu);
			pedidoActual.setFechaPedido(new java.sql.Date(System.currentTimeMillis()));
			ctrlPedido.add(pedidoActual);
			request.getSession().setAttribute("Pedido", pedidoActual);

			LinkedList<Producto> Producto = ctrlProd.getAll();
			request.setAttribute("listaProductos", Producto);
			LinkedList<Categoria> Categoria = ctrlCat.getAll();
			request.setAttribute("listaCategorias", Categoria);
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}

	}

}
