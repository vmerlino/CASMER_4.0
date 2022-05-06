package servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import Entities.Categoria;
import Entities.Producto;
import Logic.LogicCategoria;
import Logic.LogicProducto;


/**
 * Servlet implementation class abmcCategorias
 */

@WebServlet("/abmcProductos")
@MultipartConfig
public class abmcProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public static final String UPLOAD_DIR = "images";
    public String dbFileName = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcProductos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String add="WEB-INF/NewProducto.jsp";
    String list="WEB-INF/listarProductos.jsp";
    String editar="WEB-INF/EditProducto.jsp";
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogicProducto ctrl = new LogicProducto();
		Producto prod = new Producto();
		String acceso="";
		String action=request.getParameter("accion");
		switch(action) {
		 case "add": request.getRequestDispatcher("WEB-INF/NewProducto.jsp").forward(request, response);
		 			break;
		 case "listar": {	LinkedList<Producto> Producto = ctrl.getAll();
		 					request.setAttribute("listaProductos", Producto);
		 	
		 					request.getRequestDispatcher(list).forward(request, response);}
		 			break;
		 case "inicio":{
			 			LinkedList<Producto> Producto = ctrl.getAll();
			 			request.setAttribute("listaProductos", Producto);
			 			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		 				}
		 			break;
		 case "agregar": {
		 				  Categoria cat = new Categoria();
						LogicProducto ctrlLogin = new LogicProducto();
						String idCategoria = request.getParameter("idCategoria");
						String descripcion = request.getParameter("descripcion");
						String precio = request.getParameter("precio");
						String stock = request.getParameter("stock");
						Part part = request.getPart("imagen");//
				        String fileName = extractFileName(part);//file name
				        System.out.println("filename" + fileName);
				    

				        String applicationPath = getServletContext().getRealPath("");
				        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
				        //				        String uploadPath = "C:\\Users\\Usuario\\eclipse-workspace\\TP-Merlino_Casesi\\WebContent" + File.separator + UPLOAD_DIR;
				        File fileUploadDirectory = new File(uploadPath);
				        if (!fileUploadDirectory.exists()) {
				            fileUploadDirectory.mkdirs();
				        }
				        String savePath = uploadPath + File.separator + fileName;
				        System.out.println("savePath: " + savePath);
				        String sRootPath = new File(savePath).getAbsolutePath();
				        System.out.println("sRootPath: " + sRootPath);
				        part.write(savePath);
				        dbFileName = UPLOAD_DIR + File.separator + fileName;
						prod.setDescripcion(descripcion);
						prod.setPrecio(Float.parseFloat(precio));
						prod.setStock(Integer.parseInt(stock));
						cat.setIdCategoria(Integer.parseInt(idCategoria));
						prod.setCat(cat);
						prod.setImg(dbFileName);
						ctrlLogin.add(prod);
						LinkedList<Producto> Producto = ctrl.getAll();
						request.setAttribute("listaProductos", Producto);
						request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
		 		break;}
		 case "borrar":{
						String id = request.getParameter("idProducto");
						prod.setIdProducto(Integer.parseInt(id));
						ctrl.delete(prod);
						LinkedList<Producto> Producto = ctrl.getAll();
						request.setAttribute("error", true);
						request.setAttribute("listaProductos", Producto);
						request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
				break;}
		case "editar":{
			
				LogicCategoria ctrlC = new LogicCategoria();
				LinkedList<Categoria> ldc = ctrlC.getAll();
				request.setAttribute("listaCategorias", ldc);
				request.getRequestDispatcher(editar).forward(request, response);
				break;}
		
		case "filtrar":{
						int idCat = Integer.parseInt(request.getParameter("idCat"));
						LogicCategoria ctrlCat = new LogicCategoria();
						LinkedList<Producto> Producto= new LinkedList<>();
						if(idCat != -1) {
						 Producto = ctrl.getPorCategoria(idCat);
						}else { Producto = ctrl.getAll();}
						 LinkedList<Categoria> Categoria = ctrlCat.getAll();
						 request.setAttribute("listaCategorias", Categoria);
						request.setAttribute("listaProductos", Producto);
			 			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		break;}
		
		 case "actualizar":{
			 try {
			 Categoria cat = new Categoria();
			 String id = request.getParameter("idProducto");
			 String idCategoria = request.getParameter("idCategoria");
			 String descripcion = request.getParameter("descripcion");
			 String precio = request.getParameter("precio");
			 String stock = request.getParameter("stock");
			 Part img = request.getPart("imagen");
			 System.out.print(img);
			 if(!img.toString().contains("org.apache.catalina.core.ApplicationPart")) {
				 System.out.print("entro if");

			 String fileName = extractFileName(img);//file name

		        String applicationPath = getServletContext().getRealPath("");
		        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
		       // System.out.println("applicationPath:" + applicationPath);
		        File fileUploadDirectory = new File(uploadPath);
		        if (!fileUploadDirectory.exists()) {
		            fileUploadDirectory.mkdirs();
		        }
		        String savePath = uploadPath + File.separator + fileName;
		        System.out.println("savePath: " + savePath);
		        String sRootPath = new File(savePath).getAbsolutePath();
		        System.out.println("sRootPath: " + sRootPath);
		        img.write(savePath);
		        dbFileName = UPLOAD_DIR + File.separator + fileName;
		        prod.setImg(dbFileName);
			 }
			 prod.setIdProducto(Integer.parseInt(id));
			 prod.setDescripcion(descripcion);
			 prod.setPrecio(Float.parseFloat(precio));
			 prod.setStock(Integer.parseInt(stock));
			 cat.setIdCategoria(Integer.parseInt(idCategoria));
			 prod.setCat(cat);
			 
			 ctrl.edit(prod);
			 LinkedList<Producto> Producto = ctrl.getAll();
			 request.setAttribute("listaProductos", Producto);
			 request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
			 break;}  catch(Exception e) {
				 System.out.println("Entro al eror");
				request.setAttribute("errorForm",e.getMessage());
				request.getRequestDispatcher("WEB-INF/EditProducto.jsp").forward(request, response);
				 break;
			 }
		 }
		 case "buscar": {
			 String descripcion = request.getParameter("buscar");
			 if(descripcion.equals("")) {
				 request.setAttribute("buscarprod", "");
				 LinkedList<Producto> Productos = ctrl.getAll();
				 request.setAttribute("listaProductos", Productos);

			 }else{
				 prod.setDescripcion(descripcion);
			 LinkedList<Producto> Producto = ctrl.getByDescripcion(prod);
			 request.setAttribute("buscarprod", descripcion);
			 request.setAttribute("listaProductos", Producto);

			 }
			 request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
			 break;
		 }
		 case "filtrarStock": {
			 String filtro = request.getParameter("filtro");
			 if(filtro.equals("sinStock")) {
				 LinkedList<Producto> Producto = ctrl.getSinStock();
				 request.setAttribute("listaProductos", Producto);
				 request.getRequestDispatcher("WEB-INF/listarProductos.jsp").forward(request, response);
			}
			 else{
				 
			 request.getRequestDispatcher("abmcProductos?accion=listar").forward(request, response);}
		 break;}
		 case "buscarIndex": {
			 String descripcion = request.getParameter("buscar");
			 LinkedList<Producto> Producto = new LinkedList<Producto>();
			 LogicCategoria ctrlCat = new LogicCategoria();
			 if(descripcion.equals("")) {
				 request.setAttribute("buscarprod", "");
				Producto = ctrl.getAll();
			 }else{
				 request.setAttribute("buscarprod", descripcion);
				 prod.setDescripcion(descripcion);
			 Producto = ctrl.getByDescripcion(prod);
			}
			 LinkedList<Categoria> Categoria = ctrlCat.getAll();
			 request.setAttribute("listaCategorias", Categoria);
			 request.setAttribute("listaProductos", Producto);
			 request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
			 break;
		 }
		 case "salir":
			 break;
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}