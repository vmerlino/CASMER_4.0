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
	Usuario Usuario ;
	if(request.getSession().getAttribute("usuario") != null){
		Usuario=(Usuario) request.getSession().getAttribute("usuario");
	}else{Usuario= null;}
		String dni=request.getParameter("dni");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String direccion=request.getParameter("direccion");
		String email=request.getParameter("email");
		String contrasenia =request.getParameter("contrasenia");
		String telefono = request.getParameter("telefono");
		Login ctrlU =  new Login();
	%>
	
	<br>
	<div class="card bg-dark text-white" style=" box-shadow: 4px 4px 6px 6px #BBB0B0; margin-left:42mm; margin-bottom:50mm; width:80%">
	 	 <h5 style="margin-top:10mm; margin-left:100mm">AGREGAR USUARIO</h5>
	  <div class ="card-content" style="width:90%; margin:10mm">
	 
	  <form class="row g-3" action="abmc" method="get">
  <div class="col-12">
    <label for="inputAddress" class="form-label">DNI</label>
    <input type="text" style=" pattern="[0-9]" title="Debe ingresar su numero de  DNI" box-shadow: 1px 1px 1px 1px #BBB0B0" class="form-control" id="dni" name="dni"  placeholder="Ingrese DNI" required>
  </div>
  <div class="col-md-12">
    <label for="inputEmail4" class="form-label">Nombre</label>
    <input type="text" class="form-control" id="nombre" pattern="[a-zA-Z ]{2,254}" title="Debe ingresar su nombre" name="nombre" placeholder="Ingrese nombre" required>
  </div>
  <div class="col-md-12">
    <label for="inputPassword4" class="form-label">Apellido</label>
    <input type="text" class="form-control" pattern="[a-zA-Z ]{2,254}" title="Debe ingresar su apellido" id="apellido" name="apellido"  placeholder="Ingrese apellido" required>
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Email</label>
    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese email" required>
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Direccion</label>
    <input type="text" class="form-control" id="direccion" name="direccion" placeholder="Ingrese direccion" required>
  </div>
  
   <div class="col-12">
    <label for="inputAddress2" class="form-label">Telefono</label>
    <input type="text" class="form-control" pattern="[0-9]{5,15}" title="Debe ingresar su numero telefonico" id="telefono" name="telefono" placeholder="Ingrese telefono" required>
  </div>
  
  

    <div class="col-12">
    <label for="inputAddress2" class="form-label">Contraseña</label>
    <input type="text" class="form-control" id="contrasenia"pattern="[a-zA-Z_1-9]{5,30}" title="Debe ingresar su contraseña con 5 caracteres como minimo. Tiene permitido usar:  simbolo _ $, letras mayusculas y minusculas y numeros" name="contrasenia"  placeholder="Ingrese contraseña" required>
  </div>
  <%if(Usuario!=null){ %> 
 <div class="form-check">
  <input class="form-check-input" type="checkbox" value="" id="administrador" name="administrador">
  <label class="form-check-label" for="flexCheckDefault">
Administrador
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="checkbox" value="" id="cliente" name="cliente">
  <label class="form-check-label" for="flexCheckChecked">
   Cliente
  </label>
</div>
<%}else{ %>
<input type="hidden" name="cliente" value="true" >
<%} %>
  <div class="col-12">
    <input style="margin-right:200mm"type="submit" class="btn btn-primary" name="accion" value="agregar"> 
  		<%if(Usuario != null){ %>
  		<a href="abmc?accion=listar"><button type="button" class="btn btn-danger">Cancelar</button></a>
  		
  	<%	}else{%>
  		  		<a href="login.html"><button type="button" class="btn btn-danger">Cancelar</button></a>
  		
  	<%}%>
	</div>
</form>
</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>