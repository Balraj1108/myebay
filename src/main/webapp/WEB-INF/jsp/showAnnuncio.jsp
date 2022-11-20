<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="./header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="./navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Descrizione Annuncio:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Prezzo:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Aperto:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.aperto}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Pubblicazione:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome Venditore:</dt>
					  <dd class="col-sm-9">${show_annuncio_attr.utenteInserimento.nome}</dd>
			    	</dl>
			    	
			    	
					
			    	
			    <!-- end card body -->
			    </div>
			    
			    
			    
			    <div class='card-footer'>
			        <form action="${pageContext.request.contextPath}/confermaAcquisto" method="post">
					    		<input type="hidden" name="idAnnuncio" value="${show_annuncio_attr.id}">
					    		<input type="hidden" name="utenteId" id="utenteId" value="${userInfo.id}">
						    	<button type="submit" name="idAnnuncio" id="idAnnuncio" class="btn btn-primary">Conferma Acquisto</button>
						        <a href="${pageContext.request.contextPath}/film/" class='btn btn-outline-secondary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Back
						        </a>
					</form>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>