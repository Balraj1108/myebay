<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
			    <sec:authorize access="isAuthenticated()">
			    
			        <form action="${pageContext.request.contextPath}/confermaAcquisto" method="post">
			        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#confirmOperationModal">
					  Compra
					</button>
					    		
						        <a href="${pageContext.request.contextPath}/listAnnuncio" class='btn btn-outline-secondary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Back
						        </a>
					</form>
				</sec:authorize>	
					
				<c:if test="${show_annuncio_attr.aperto }">	
				<sec:authorize access="!isAuthenticated()">
							   		<a class="btn btn-success" href="${pageContext.request.contextPath}/loginAcquisto?idAnnuncioWithNoAuth=${show_annuncio_attr.id }">Acquist</a> 
				</sec:authorize>
				</c:if>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="./footer.jsp" />
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Aquisto</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Continuare con l'aquisto?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/confermaAcquisto" >
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary modal" data-bs-dismiss="modal">Chiudi</button>
		            	<input type="hidden" name="idAnnuncio" value="${show_annuncio_attr.id}">
					    <input type="hidden" name="utenteId" id="utenteId" value="${userInfo.id}">
		                <input type="submit" value="Continua"  class="btn btn-primary">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		
	</script>
	
</body>
</html>