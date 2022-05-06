<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Pedido"%>
<%@page import="Entities.Usuario"%>
<%@page import="Logic.Login"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="style/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>UTNWEB</title>
<%
Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
Login ctrlU = new Login();
LinkedList<Pedido> lp = (LinkedList<Pedido>) request.getAttribute("listaPedidos");
if( (String)request.getAttribute("buscarprod") == null){request.setAttribute("buscarprod", "");}
String buscar = (String)request.getAttribute("buscarprod");
Boolean error ;
if(request.getAttribute("error") != null){
	error=(Boolean) request.getAttribute("error");
}else{error= false;}
%>
</head>
<body>
			<section >
	<nav   class="navbar navbar-expand-lg navbar-dark bg-dark">
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
					<li class="nav-item"><a class="nav-link"
						href="abmc?accion=inicio">SEGUIR COMPRANDO <svg
								xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
  <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
</svg>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="abmcLdp?accion=Carrito">MI CARRITO <svg
								xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16">
  <path	d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
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
					<li style="margin-left:170mm" class="nav-item dropdown"><a
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
<h3 style="margin-left:140mm">Pedidos</h3>
<br>
		<div class="col-14" class="col-10" style="margin-left:60mm"> 
		<%if(error){ %>	
   <div class="alert alert-success d-flex align-items-center" style="margin-left:-50mm" role="alert">
    <div>
      Se cancelo el pedido correctamente.
    </div>
  </div>
							<%} %>
		 <form class="d-flex" action="abmcPedido" method="get">
		 <div class="5" style="margin-right: 200px">
		 
			</div>

			<div class="col-3" style="margin-right: 10px; margin-left:70mm">
			<input type="hidden" class="form-control" id="accion" name="accion" value="buscar" >
        <input type="text" value="<%= buscar%>" class="form-control" id="buscar" name="buscar"  placeholder="Ingrese ID" aria-label="Search">
        </div>
        <div class="col-2" >
      <button class="btn btn-outline-dark" type="submit">Buscar</button>
        </div>

      </form>
        </div>	
       
	<br>
	<div class ="row">
	
        
        <div class ="col-10">
	<div class=" card" style="width: 115%; margin-left:10mm">
	<div class="card-content">
	<table class="table">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Estado</th>
				<th scope="col">Fecha</th>
				<th scope="col">Fecha Entrega</th>
				<th scope="col">Fecha Cancelacion</th>
				<th scope="col">Monto</th>
				<th scope="col">DNI cliente</th>
				<th scope="col">Editar</th>
				<th scope="col">Eliminar</th>
				<th scope="col">Detalles</th>
				<th scope="col">Entregado</th>
				<th scope="col"> Cancelar</th>
		</thead>
		<tbody>
			<%
			for (Pedido Prod : lp) {
				if(!Prod.getEstado().equals("En proceso")){

			%>
			<tr >
				<td><%=Prod.getIdPedido()%></td>
				<td><%=Prod.getEstado() %></td>
				<td><%=Prod.getFechaPedido() %></td>
				<%if(Prod.getFechaEntrega() == null){ %>
				<td>          - </td>
				<%}else{ %>
				<td><%=Prod.getFechaEntrega()%></td>
				<%} %>
				<%if(Prod.getFechaCancelacion() == null){ %>
				<td> - </td>
				<%}else{ %>
				<td><%=Prod.getFechaCancelacion()%></td>
				<%} %>
				<td><%=Prod.getMonto() %></td>
				<td><%=Prod.getUsu().getDni()%></td>
				<td><a
					href="abmcPedido?accion=editar
						&idPedido=<%=Prod.getIdPedido()%>
						">
						<button type="button" class="btn btn-warning"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
</svg></button>
				</a></td>
				<td><a
					href="abmcPedido?accion=borrar&idPedido=<%=Prod.getIdPedido()%>"><button
							type="button" class="btn btn-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
</svg></button></a></td>
				 <td>
                                    <a href="abmcPedido?accion=verDetalle&idcompra=<%=Prod.getIdPedido() %>">Ver Detalle</a>
                                </td>  
				
				<%if(Prod.getEstado().equals("Entregado")||Prod.getEstado().equals("Cancelado")){ %>
				<td><button type="button" disabled="disabled" class="btn btn-dark">Marcar Entregado</button>
					</td>	
				<%}else{ %>
				<td>
				<a
					href="abmcPedido?accion=entregado&idPedido=<%=Prod.getIdPedido()%>"><button
							type="button" class="btn btn-dark">Marcar Entregado</button></a>
							<%} %>
							</td>
				<td><%if(Prod.getFechaCancelacion() != null || Prod.getEstado().equals("Entregado")){ %>
							<button disabled = "disabled" class="btn btn-danger">Cancelar</button>
							<%} else { %>
							<a class="btn btn-danger" href="abmcPedido?accion=cancelacionAdmin&idPedido=<%=Prod.getIdPedido()%>">Cancelar</a>
							<%} %></td>
			</tr>
			<%
			}}
			%>
		</tbody>
	</table>
</div>	
	</div>
	</div>
	
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>