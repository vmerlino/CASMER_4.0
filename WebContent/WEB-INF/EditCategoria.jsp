<%@page import="Entities.*" %>
<%@page import="Logic.*" %>

<%@page import ="java.sql.Date" %>
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
		Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		String idCategoria=request.getParameter("idCategoria");
		String Descripcion=request.getParameter("descripcion");
		Login ctrlU =  new Login();
	%>
	
	<br>
	
	<div class="card bg-dark text-white" style=" box-shadow: 4px 4px 6px 6px #BBB0B0; margin-left:42mm; margin-bottom:50mm; width:80%">
	 	 <h5 style="margin-top:10mm; margin-left:100mm">EDITAR CATEGORIA</h5>
	  <div class ="card-content" style="width:90%; margin:10mm">
	 
	  <form class="row g-3" action="abmcCategorias" method="get">
  <div class="col-12">
    <label for="inputAddress" class="form-label">ID Categoria</label>
    <input type="text" style=" box-shadow: 1px 1px 1px 1px #BBB0B0" class="form-control" id="idCategoria" name="idCategoria" readonly value="<%=idCategoria%>" placeholder="Ingrese IdProducto" required>
  </div>
  <div class="col-md-12">
    <label for="inputEmail4" class="form-label">Descripcion</label>
    <input type="text" class="form-control" id="Descripcion" name="Descripcion" pattern="[a-zA-Z ]{1,300}"  title="Debe ingresar solo letras" value="<%=Descripcion%>" placeholder="Ingrese idCategoria" required>
  </div>
  
  <div class="col-12">
    <input style="margin-right:200mm"type="submit" class="btn btn-primary" name="accion" value="actualizar"> 
  		<a href="abmc?accion=listar"><button type="button" class="btn btn-danger">Cancelar</button></a>
	</div>
</form>
</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>