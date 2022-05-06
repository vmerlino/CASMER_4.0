<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Entities.Producto"%>
<%@page import="Entities.Categoria"%>
<%@page import="Entities.Usuario"%>
<%@page import="Entities.Rol"%>
<%@page import="Logic.Login"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>UTNWEB</title>
<link rel="stylesheet" href="style/estilo.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Azeret+Mono:ital,wght@1,200&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Azeret+Mono:ital,wght@1,200&family=Oswald:wght@200&display=swap"
	rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css'
	rel='stylesheet'>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<%
Usuario usu = (Usuario) request.getSession().getAttribute("usuario");
Login ctrlU = new Login();
LinkedList<Producto> lp = (LinkedList<Producto>) request.getAttribute("listaProductos");
LinkedList<Categoria> lc = (LinkedList<Categoria>) request.getAttribute("listaCategorias");
if ((String) request.getAttribute("buscarprod") == null) {
	request.setAttribute("buscarprod", "");
}
String buscar = (String) request.getAttribute("buscarprod");
// int contador = (int)request.getAttribute("contador");
Boolean error ;
if(request.getAttribute("error") != null){
	error=(Boolean) request.getAttribute("error");
}else{error= false;}%>
</head>
<body>

	<section>
		<nav
			style="background-color: #5a471b; position: fixed; width: 100%; z-index: 1000;"
			class="navbar navbar-expand-lg navbar-dark bg-primary">
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
							aria-current="page" href="#">INICIO</a></li>
						<li class="nav-item"><a class="nav-link" href="#footer">CONTACTANOS</a>
						</li>
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
							data-bs-toggle="dropdown" aria-expanded="false"> ADMINISTRAR
						</a>
							<ul class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<li><a class="dropdown-item" href="abmc?accion=listar">Usuarios</a></li>
								<li><a class="dropdown-item"
									href="abmcProductos?accion=listar">Productos</a></li>
								<li><a class="dropdown-item"
									href="abmcCategorias?accion=listar">Categorias</a></li>
								<li><a class="dropdown-item"
									href="abmcPedido?accion=listar">Pedidos</a></li>
							</ul> <%
 }
 %>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">CERRAR SESION</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"><img src="img/usu.png"
										height="80" width="80"></a></li>
								<li><a class="dropdown-item"><%=usu.getNombre()%> <%=usu.getApellido()%></a></li>
								<li><a class="dropdown-item"><%=usu.getEmail()%></a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="Signin?salida=salir">Salir</a></li>
							</ul></li>
					</ul>
					<form class="d-flex" action="abmcProductos" method="get">
						<input type="hidden" class="form-control" id="accion"
							name="accion" value="buscarIndex"> <input type="text"
							value="<%=buscar%>" class="form-control" id="buscar"
							name="buscar" placeholder="Ingrese descripcion del producto" aria-label="Search">
						<button class="btn btn-light" type="submit">Buscar</button>
					</form>
				</div>
			</div>
		</nav>
	</section>
	<br>
	<br>
	
	<section>
	<%if(error){ %>	
   <div class="alert alert-success d-flex align-items-center" role="alert">
    <div>
      Se añadió su producto con éxito.
    </div>
  </div>
							<%} %>
		<br>
		<div class="row">
			<div class="col-2">
				<ul
					style="margin-top: 5mm; margin-left: 5mm; margin-bottom: 10mm; box-shadow: 2px 2px 2px 2px #BBB0B0"
					class="list-group">
					<li class="list-group-item active">Categorias</li>
					<a href="abmcProductos?accion=filtrar&idCat=-1"
						class="list-group-item list-group-item-action">Todas</a>
					<%
					for (Categoria cat : lc) {
					%>
					<a
						href="abmcProductos?accion=filtrar&idCat=<%=cat.getIdCategoria()%>"
						class="list-group-item list-group-item-action"><%=cat.getDescripcion()%></a>

					<%
					}
					%>
				</ul>
			</div>
			<br>

			<div class="col-9">
				<div class="row">
					<%
					if (ctrlU.escliente(usu)) {
					%>
					<%
					for (Producto Prod : lp) {
						if (Prod.getStock() > 0) {
					%>
					<div class="col-4">
						<div class="card" style="width: 18rem;"
							data-category="<%=Prod.getCat().getDescripcion()%>">
							<div class="card-header">
								<img class="card-img" src="<%=Prod.getImg()%>">
							</div>
							<div class="card-body">
								<h3 class="card-title"><%=Prod.getDescripcion()%></h3>
								<h5 class="precio">
									$<%=Prod.getPrecio()%></h5>

								<form class="d-flex" action="abmcLdp" method="get">
									<div class="row">
										<input
											style="width: 20%; margin-left: 27mm; margin-bottom: 5mm"
											type="number" id="cantidad" name="cantidad" min="1"
											max="<%=Prod.getStock()%>" value="1"
											class=" form-control text-center">
											 <input type=hidden
											id="accion" name="accion" value="AgregarCarrito"> <input
											type=hidden id="id" name="id"
											value="<%=Prod.getIdProducto()%>">

										<button type="submit" style="width: 80%; margin-left: 8mm"
											class="btn btn-primary">AÑADIR AL CARRITO</button>
											
									</div>
								</form>
							</div>
						</div>
					</div>

					<%
					} else {
					%>
					<div class="col-4">

						<div class="card" style="width: 18rem;"
							data-category="<%=Prod.getCat().getDescripcion()%>">
							<div class="card-header">
								<img src="<%=Prod.getImg()%>" class="card-img">
							</div>
							<div class="card-body">
								<h3 class="card-title"><%=Prod.getDescripcion()%></h3>
								<h5 class="precio">
									$<%=Prod.getPrecio()%></h5>
								<input style="width: 20%; margin-left: 27mm; margin-bottom: 5mm"
									type="number" id="cantidad" min="1" max="<%=Prod.getStock()%>"
									value="1" id="Cantidad" class=" form-control text-center">
								<button disabled="disabled" class="btn btn-primary">
									SIN STOCK</button>
							</div>
						</div>
					</div>

					<%
					}
					}
					%>
					<%
					} else {
					%>
					<h1>Usted no es cliente... No tiene acceso a las compras</h1>
					<%
					}
					%>
					
				</div>
			</div>

		</div>

	</section>


	<footer>
		<div class="fot">
			<div class="fot-cont">
				<a name="footer">QUIENES SOMOS</a>
				<ul class="list">
					<li><a href="#">CONTACTANOS</a></li>
					<li class="lg"><i class='bx bxl-facebook-circle'></i></li>
					<li class="lg"><i class='bx bxl-whatsapp'></i></li>
					<li class="lg"><i class='bx bxl-instagram'></i></li>
				</ul>
				<a href="#">SERVICIOS AL CLIENTE</a> <a href="#">AYUDA</a>
				<p>CASMER 2021 ©. Todos los derechos reservados.</p>
			</div>
		</div>
	</footer>
	<script src="https://unpkg.com/boxicons@2.0.9/dist/boxicons.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>