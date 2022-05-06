<%@page import="Entities.*" %>
<%@page import="Logic.*" %>
 <%@page import="java.util.LinkedList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>UTNWEB</title>
</head>
<body>
	<%
	Login ctrlU =  new Login();
		Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		String idProducto=request.getParameter("idProducto");
		String idCategoria=request.getParameter("idCategoria");
		LogicCategoria ctrlC = new LogicCategoria();
		Categoria cat = ctrlC.getByIdCategoria(Integer.parseInt(idCategoria));
		String descripcion=request.getParameter("descripcion");
		String precio=request.getParameter("precio");
		String stock=request.getParameter("stock");
	    String img=request.getParameter("img");
	    String error ;
	    if(request.getAttribute("errorForm") != null){
	    	error=(String) request.getAttribute("errorForm");
	    }else{error= "";}
	    LinkedList<Categoria> ldc = (LinkedList<Categoria>) request.getAttribute("listaCategorias") ;
	    %>


	<br>
		 <%if(!error.equals("")){ %>	
   <div class="alert alert-danger d-flex align-items-center" style="width:50%; margin-left:50mm" role="alert">
    <div>
      <%=error %>
    </div>
  </div>
							<%} %>
	<div class="card bg-dark text-white" style=" box-shadow: 4px 4px 6px 6px #BBB0B0; margin-left:42mm; margin-bottom:50mm; width:80%">
	 	 <h5 style="margin-top:10mm; margin-left:100mm">EDITAR PRODUCTO</h5>
	  <div class ="card-content" style="width:90%; margin:10mm">
	  <form class="row g-3" action="abmcProductos" method="post" enctype="multipart/form-data">
  <div class="col-12">
    <label for="inputAddress" class="form-label">idProducto</label>
    <input type="text" style=" box-shadow: 1px 1px 1px 1px #BBB0B0" class="form-control" id="idProducto" name="idProducto" readonly value="<%=idProducto%>" placeholder="Ingrese IdProducto" required>
  </div>

  <div class="col-md-12">
      <label for="inputEmail4" class="form-label">Categoria</label>
  
<select name="idCategoria" id="idCategoria"  class="form-select" aria-label="Default select example">
  <option hidden value="<%=cat.getIdCategoria() %>" selected ><%=cat.getDescripcion() %></option>
   <%for(Categoria c: ldc){ %>
  <option value="<%=c.getIdCategoria()%>"><%=c.getDescripcion() %></option>
     <%} %>
</select>  </div>


  <div class="col-md-12">
    <label for="inputPassword4" class="form-label">Descripcion</label>
    <input type="text" class="form-control" id="descripcion" name="descripcion" pattern="[a-zA-Z ]{1,300}"  title="Debe ingresar solo letras" value="<%=descripcion%>" placeholder="Ingrese descripcion" required>
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Precio</label>
    <input type="text" class="form-control" id="precio" name="precio" value="<%=precio%>" pattern="[0-9]+([,\.][0-9]+)?" title="Debe ingresar solo numero"placeholder="Ingrese precio" required>
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Stock</label>
    <input type="text" class="form-control" id="stock" name="stock" value="<%=stock%>" placeholder="Ingrese stock" pattern="[0-9]{1,1000}" title="Debe ingresar solo numero" required>
  </div>
  
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Imagen</label>
    <input type="file" class="form-control" id="imagen" name="imagen" value="<%=img %>"  placeholder="Ingrese imagen" >
  </div>
  <div class="col-12">
    <input style="margin-right:200mm"type="submit" class="btn btn-primary" name="accion" value="actualizar"> 
  		<a href="abmcProductos?accion=listar"><button type="button" class="btn btn-danger">Cancelar</button></a>
	</div>
</form>

</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>