<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Logic.*" %>
    
<%@page import="Entities.Pedido"%>
<%@page import="Entities.LineaDePedido"%>
<%@page import="Entities.Usuario"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Azeret+Mono:ital,wght@1,200&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Azeret+Mono:ital,wght@1,200&family=Oswald:wght@200&display=swap" rel="stylesheet">
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">	

<title>UTNWEB</title>


<%
Login ctrlU =  new Login();
Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
Pedido ped = (Pedido)request.getAttribute("Pedido");
LinkedList<LineaDePedido> listaLdp = (LinkedList<LineaDePedido>) request.getAttribute("lineasDelPedidoComprado");
%>
</head>
<body>       
  <section >
	<nav  style="width: 100%; 
  z-index: 1000;" class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="abmc?accion=inicio">INICIO</a></li>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="abmc?accion=inicio">SEGUIR COMPRANDO <svg
								xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
  <path
									d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
</svg>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="abmcLdp?accion=Carrito">MI CARRITO <svg
								xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
  <path
									d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
</svg>
					</a></li>
					<li class="nav-item"><a class="nav-link"
							href="abmcPedido?accion=misPedidos">MIS PEDIDOS </a></li>
					<%
					if (ctrlU.esAdmin(usu)) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> ADMINISTRAR </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item" href="abmc?accion=listar">Usuarios</a></li>
							<li><a class="dropdown-item"
								href="abmcProductos?accion=listar">Productos</a></li>
							<li><a class="dropdown-item"
								href="abmcCategorias?accion=listar">Categorias</a></li>
							<li><a class="dropdown-item" href="abmcPedido?accion=listar">Pedidos</a></li>
						</ul> <%
 }
 %>
					<li style="margin-left:190mm" class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-expanded="false">CERRAR SESION</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"><img src="img/usu.png"
									height="80" width="80"></a></li>
							<li><a class="dropdown-item"><%=usu.getNombre()%> <%=usu.getApellido() %></a></li>
							<li><a class="dropdown-item"><%=usu.getEmail() %></a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="Signin?salida=salir">Salir</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	</section>
	<br>
	
		<div class=" card" style="margin-left:20mm; width: 85%">
		<h3 style="margin-left:35%; margin-top:5%">
		DETALLES DE LA COMPRA
		</h3>
		<div class="card-content" style="margin:8%; margin-top:-1%">
		<p><h6>ID:</h6>	<%=ped.getIdPedido() %></p>
		<P><h6>Fecha realizado:</h6><%=ped.getFechaPedido() %></P>
	<h6>Fecha Entregado:</h6>
		<%if(ped.getFechaEntrega() == null){ %>
				<td>          - </td>
				<%}else{ %>
				<td><%=ped.getFechaEntrega()%></td>
				<%} %>
				<h6>Fecha Cancelado:</h6>
				<%if(ped.getFechaCancelacion() == null){ %>
				<td> - </td>
				<%}else{ %>
				<td><%=ped.getFechaCancelacion()%></td>
				<%} %>
		<p><h6>Estado del pedido:</h6> 	<%=ped.getEstado() %></p>
		<h6>Monto:</h6> 	<%=ped.getMonto() %>
		
	<table class="table">
  <thead>
    <tr>
      <th scope="col">Producto</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Subtotal</th>
   
    </tr>
  </thead>
  <tbody>
<%for(LineaDePedido ldp: listaLdp){ %>
 <tr>
      <th ><%=ldp.getProd().getDescripcion()%></th>
      <td ><%=ldp.getCant() %></td>
      <td><%=ldp.getSubTot() %></td>
    </tr>
    <%} %>
  </tbody>
</table>
		
<div class="col-12">
  		<a href="javascript:history.back()"><button type="button" class="btn btn-danger">Volver</button></a>
	</div>
	</div>
	</div>