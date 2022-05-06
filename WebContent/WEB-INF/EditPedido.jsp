<%@page import="Entities.*" %>
<%@page import="Logic.*" %>

<%@page import ="java.util.Date" %>
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
	Login ctrlU = new Login();
		Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
		String idPedido=request.getParameter("idPedido");
		LogicPedido ctrlP = new LogicPedido();
		Pedido p = new Pedido();
		p.setIdPedido(Integer.parseInt(idPedido));
		p=ctrlP.getOne(p);
		Date fechaEntrega=p.getFechaEntrega();
		Date fechaCancelacion= p.getFechaCancelacion();
		Double monto=p.getMonto();
		String estado=p.getEstado();
		String dni =p.getUsu().getDni();
		Date fechaPedido=p.getFechaPedido();
		// String img=request.getParameter("img");
	%>
	
	<br>
	
	<div class="card bg-dark text-white" style=" box-shadow: 4px 4px 6px 6px #BBB0B0; margin-left:42mm; margin-bottom:50mm; width:80%">
	 	 <h5 style="margin-top:10mm; margin-left:100mm">EDITAR PEDIDO</h5>
	  <div class ="card-content" style="width:90%; margin:10mm">
	 
	  <form class="row g-3" action="abmcPedido" method="get">
  <div class="col-12">
    <label for="inputAddress" class="form-label">ID Pedido</label>
    <input type="text" style=" box-shadow: 1px 1px 1px 1px #BBB0B0" class="form-control" id="idPedido" name="idPedido" readonly value="<%=idPedido%>" placeholder="Ingrese IdProducto" required>
  </div>
  <div class="col-md-12">
    <label for="inputEmail4" class="form-label">Estado</label>
     <select class="form-select form-select-sm"  id="estado" name="estado"  aria-label=".form-select-sm example">
  <option hidden value="<%=estado %>" selected><%=estado %></option>
  <option value="a entregar">a entregar</option>
  <option value="Entregado">Entregado</option>
  <option value="En proceso">En proceso</option>
    <option value="En proceso">Cancelado</option>
  
</select>
  </div>
  
 
  <div class="col-md-12">
    <label for="inputPassword4" class="form-label">Fecha Pedido</label>
    <input type="date" class="form-control" id="fechaPedido" name="fechaPedido" value="<%=fechaPedido%>" placeholder="Ingrese descripcion" required>
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Fecha Entregado</label>
    <input type="date" class="form-control" id="fechaEntrega" name="fechaEntrega" value="<%=fechaEntrega%>" placeholder="Ingrese precio" >
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Fecha Cancelacion</label>
    <input type="date" class="form-control" id="fechaCancelacion" name="fechaCancelacion" value="<%=fechaCancelacion%>" placeholder="Ingrese stock" >
  </div>
  
   <div class="col-12">
    <label for="inputAddress2" class="form-label">Monto</label>
    <input type="text" class="form-control" id="monto" name="monto" value="<%=monto%>" pattern="[0-9]+([,\.][0-9]+)?" title="Debe ingresar solo numero" placeholder="Ingrese stock" required>
  </div>
  
    <div class="col-12">
    <label for="inputAddress2" class="form-label">DNI del usuario</label>
    <input type="text" class="form-control" id="dni" name="dni"  value="<%=dni%>" placeholder="Ingrese stock" required>
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