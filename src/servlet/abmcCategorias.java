package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Categoria;
import Entities.Producto;
import Logic.LogicCategoria;


/**
 * Servlet implementation class abmcCategorias
 */
@WebServlet("/abmcCategorias")
public class abmcCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcCategorias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String add="WEB-INF/NewCategoria.jsp";
    String list="WEB-INF/listarCategorias.jsp";
    String editar="WEB-INF/EditCategoria.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicCategoria ctrl = new LogicCategoria();
		 Categoria cat = new Categoria();
		String acceso="";
		String action=request.getParameter("accion");
		switch(action) {
		 case "add": request.getRequestDispatcher("WEB-INF/NewCategoria.jsp").forward(request, response);
		 			break;
		 case "listar": {	LinkedList<Categoria> categorias = ctrl.getAll();
		 					request.setAttribute("listaCategorias", categorias);
		 					request.getRequestDispatcher(list).forward(request, response);}
		 			break;
		 case "agregar": {
						String descripcion = request.getParameter("descripcion");
						cat.setDescripcion(descripcion);
						ctrl.add(cat);
						LinkedList<Categoria> categorias = ctrl.getAll();
						request.setAttribute("listaCategorias", categorias);
						request.getRequestDispatcher("WEB-INF/listarCategorias.jsp").forward(request, response);
		 		break;}
		 case "borrar":{
						String id = request.getParameter("idCategoria");
						cat.setIdCategoria(Integer.parseInt(id));
						String error = ctrl.delete(cat);
						if(!error.equals("")) {
							request.setAttribute("errorSQL", error);
						}
						LinkedList<Categoria> categorias = ctrl.getAll();
						request.setAttribute("listaCategorias", categorias);
						request.getRequestDispatcher("WEB-INF/listarCategorias.jsp").forward(request, response);
				break;}
		case "editar":{
						request.getRequestDispatcher(editar).forward(request, response);
				break;}
		 case "actualizar":{
			 String id = request.getParameter("idCategoria");
			 String descripcion = request.getParameter("Descripcion");
			 cat.setIdCategoria(Integer.parseInt(id));
			 cat.setDescripcion(descripcion);
			 ctrl.edit(cat);
			 LinkedList<Categoria> categorias = ctrl.getAll();
			 request.setAttribute("listaCategorias", categorias);
			 request.getRequestDispatcher("WEB-INF/listarCategorias.jsp").forward(request, response);
		 }
		 case "home":{
			 LinkedList<Categoria> categorias = ctrl.getAll();
			 request.setAttribute("listaCategorias", categorias);
			 request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			 
		 }
		 case "buscar": {
			 String descripcion = request.getParameter("buscar");
			 if(descripcion.equals("")) {
				 request.setAttribute("buscarcat", "");
				 request.getRequestDispatcher("abmcCategorias?accion=listar").forward(request, response);
			 }else{
				 cat.setDescripcion(descripcion);
			 LinkedList<Categoria> Categoria = ctrl.getByDescripcion(cat);
			 request.setAttribute("listaCategorias", Categoria);
			 request.setAttribute("buscarcat", descripcion);
			 request.getRequestDispatcher("WEB-INF/listarCategorias.jsp").forward(request, response);}
			 break;
		 }
		 case "salir":
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
