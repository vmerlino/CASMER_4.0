<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="Entities.LineaDePedido"%>
  <%@page import="Entities.Usuario"%>
    <%@page import="Logic.Login"%>
  
 <%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>UTNWEB</title>
	<link rel="stylesheet" href="style/estiloCarrito.css">
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Azeret+Mono:ital,wght@1,200&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Azeret+Mono:ital,wght@1,200&family=Oswald:wght@200&display=swap" rel="stylesheet">
	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">	

<%
Login ctrlU = new Login();
Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
LinkedList<LineaDePedido> ldp = (LinkedList<LineaDePedido>) request.getAttribute("carrito");
Double tp = (Double) request.getAttribute("totalPagar");
Boolean error ;
if(request.getAttribute("error") != null){
	error=(Boolean) request.getAttribute("error");
}else{error= false;}
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
	
	
<%if(ctrlU.escliente(usu)){ %>
<div class="container mt-12">
<%if(error){ %>	
   <div class="alert alert-warning d-flex align-items-center" role="alert">
    <div>
      No se encuentra más stock disponible del producto.
    </div>
  </div>
							<%} %>
	<h3 style="margin-left:90mm">Carrito</h3>
	<br>
	<br>
	<div class="row">
			<div class="col-sm-8">
			
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ITEM</th>
							<th>DESCRIPCION</th>
							<th>PRECIO</th>
							<th ></th>
							<th >CANTIDAD</th>
							<th ></th>
							<th>SUBTOTAL</th>
							<th>ACCION</th>
						</tr>
					</thead>
					<tbody>
					<%
						for (LineaDePedido car : ldp) {
					%>
						<tr>
							<td><%= car.getNroldp() %></td>
							<td><%= car.getProd().getDescripcion() %></td>
							<td><%= car.getProd().getPrecio() %></td>
							<td>
							<%if(car.getCant() ==1){ %>
							<button disabled = "disabled" class="btn btn-success"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-lg" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M2 8a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11A.5.5 0 0 1 2 8Z"/>
</svg> </button>
							<%} else { %>
<a href="abmcLdp?accion=updateCantidad&cant=<%=car.getCant() - 1%>&id=<%=car.getProd().getIdProducto()%>&operacion=<%="resta"%>"  style="width: 30%; margin: 0 auto"><button class="btn btn-success"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-lg" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M2 8a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11A.5.5 0 0 1 2 8Z"/>
</svg></button> </a>							<%} %>
							</td>
							<td >
							<input  readonly type="number" style="width: 70%; margin: 0 auto"  min="1"  max="<%=car.getProd().getStock() %>" id="Cantidad" class=" form-control text-center" value="<%= car.getCant()%>">
							</td>
							<td>
							<%if(0 >= car.getProd().getStock()){ %>
							<button disabled = "disabled" class="btn btn-success"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
</svg></button>
							<%} else { %>
							<a href="abmcLdp?accion=updateCantidad&cant=<%=car.getCant() + 1%>&id=<%=car.getProd().getIdProducto()%>&operacion=<%="suma"%>"  style="width: 30%; margin: 0 auto"><button class="btn btn-success"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
</svg></button> </a>
							<%} %>
							</td>
							<td><%=car.getSubTot() %></td>
							<td><a id="btnDelete"
					href="abmcLdp?accion=delete&id=<%= car.getProd().getIdProducto() %>"><button
							type="button" class="btn btn-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
</svg></button></a></td>
						</tr>
					<% } %>
					
					</tbody>
				
				
				</table>
			</div>
			<div style="margin-top:18mm" class="col-sm-4">
				<div class="card bg-outline-dark text-dark">
					<div class="card-header">
					 <h3>Generar Compra</h3>
					</div>
					<div class="card-body">
						<label>Total Pagar:</label>
						<br>
						<input type="text" value="<%=tp %>" readonly class="from-control">
						
					</div>
					<div class="card-footer">
					<%if(ldp.size() >0){ %>
					
						<a href="abmcPedido?accion=generarCompra&totalPagar=<%=tp %>" class="btn btn-danger btn-block">Generar Compra</a>
						<%}else{ %><button style="margin-left:10mm" class="btn btn-danger btn-block" disabled="disabled"> Generar Compra </button> <%} %>
					<% if(ldp.size() != 0){ %>
			<a href="abmcLdp?accion=borrar" class="btn btn-warning">Limpiar carrito</a>
			<%	}else{%>
			<button style="margin-left:10mm" class="btn btn-warning" disabled="disabled">Limpiar carrito</button>
			
			<%} %>
					</div>
				</div>
			</div>
	</div>

</div>
<%}else{ %>
<h1>Usted no es cliente, no tiene acceso a esta seccion</h1>
<%} %>
<script src="js/funciones.js" type="text/javascript"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="https://unpkg.com/boxicons@2.0.9/dist/boxicons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>