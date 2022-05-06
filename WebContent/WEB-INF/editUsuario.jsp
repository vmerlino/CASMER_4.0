<%@page import="Entities.*" %>
<%@page import="Logic.*" %>
<%@page import ="java.sql.Date" %>
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
	LogicRol ctrlR = new LogicRol();
		Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		String dni=request.getParameter("dni");
		Usuario u = new Usuario();
		 u.setDni(dni);
		u= ctrlU.getByDni(u);
		String nombre=u.getNombre();
		String apellido=u.getApellido();
		String direccion=u.getDireccion();
		String email=u.getEmail();
		String contrasenia =u.getContrasenia();
		String telefono = u.getTelefono();
		LinkedList<Rol> roles = ctrlR.getRolesUsuario(usu.getDni());
		String administrador ="off";
		String cliente ="off";
		for(Rol rol:roles){
			if(rol.getId()==1) administrador="on";
			if(rol.getId()==2) cliente ="on";	
		}
		
		// String img=request.getParameter("img");
	%>
	
	
	<br>
	
	<div class="card bg-dark text-white" style=" box-shadow: 4px 4px 6px 6px #BBB0B0; margin-left:42mm; margin-bottom:50mm; width:80%">
	 	 <h5 style="margin-top:10mm; margin-left:100mm">EDITAR USUARIO</h5>
	  <div class ="card-content" style="width:90%; margin:10mm">
	 
	  <form class="row g-3" action="abmc" method="get">
  <div class="col-12">
    <label for="inputAddress" class="form-label">DNI</label>
    <input type="text" style=" box-shadow: 1px 1px 1px 1px #BBB0B0"  pattern="[0-9]" title="Debe ingresar su numero de  DNI" class="form-control" id="dni" name="dni" readonly value="<%=dni%>" placeholder="Ingrese IdProducto" required>
  </div>
  <div class="col-md-12">
    <label for="inputEmail4" class="form-label">Nombre</label>
    <input type="text" class="form-control" id="nombre" name="nombre" value="<%=nombre%>" pattern="[a-zA-Z ]{2,254}" title="Debe ingresar su nombre" placeholder="Ingrese idCategoria" required>
  </div>
  <div class="col-md-12">
    <label for="inputPassword4" class="form-label">Apellido</label>
    <input type="text" class="form-control" id="apellido" pattern="[a-zA-Z ]{2,254}" title="Debe ingresar su apellido" name="apellido" value="<%=apellido%>" placeholder="Ingrese descripcion" required>
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Email</label>
    <input type="email" class="form-control" id="email" name="email" value="<%=email%>" placeholder="Ingrese precio" required>
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Direccion</label>
    <input type="text" class="form-control" id="direccion" name="direccion" value="<%=direccion%>" placeholder="Ingrese stock" required>
  </div>
  
   <div class="col-12">
    <label for="inputAddress2" class="form-label">Telefono</label>
    <input type="text" class="form-control" id="telefono" name="telefono" pattern="[0-9]{5,15}" title="Debe ingresar su numero telefonico" value="<%=telefono%>" placeholder="Ingrese stock" required>
  </div>
  
    <div class="col-12">
    <label for="inputAddress2" class="form-label">Contraseña</label>
    <input type="text" class="form-control" id="contrasenia" name="contrasenia" value="<%=contrasenia%>" pattern="[a-zA-Z_1-9]{5,30}" title="Debe ingresar su contraseña con 5 caracteres como minimo. Tiene permitido usar:  simbolo _ $, letras mayusculas y minusculas y numeros" placeholder="Ingrese stock" required>
  </div>

  <div class="col-12">
    <input style="margin-right:200mm"type="submit" class="btn btn-primary" name="accion" value="actualizar"> 
  		<a href="javascript:history.back()"><button type="button" class="btn btn-danger">Cancelar</button></a>
	</div>
</form>
</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>