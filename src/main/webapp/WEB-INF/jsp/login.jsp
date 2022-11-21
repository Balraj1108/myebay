<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="./header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body  class="text-center">
	<!-- Fixed navbar -->
	<jsp:include page="./navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="form-signin">
		<div class="container">
			<div class='card'>
			<form class="form-signin" name='login' action="login" method='POST' novalidate="novalidate">
		   	
			   	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
				  ${errorMessage}
				</div>
				
				<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			 		 ${successMessage}
			  		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				
				<div class="alert alert-info alert-dismissible fade show ${infoMessage==null?'d-none': ''}" role="alert">
				  ${infoMessage}
				</div>
				
				
			  	<img class="mb-4" src="./assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		    	
		    	
			  	<div class="form-floating">
			      <input type="text" name="username" class="form-control" id="inputUsername" placeholder="username">
			      <label for="inputUsername">Username</label>
			    </div>
			    <div style="margin-top: 4px" class="form-floating">
			      <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
			      <label for="inputPassword">Password</label>
			    </div>
			
			    <div class="checkbox mb-3">
			      <label>
			        <input type="checkbox" value="remember-me"> Remember me
			      </label>
			    </div>
			    <div style="margin-top: 4px">
			    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
			    </div>
			    </form>
			    
			    <div style="margin-top: 4px">
				<a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/registrazione">Sign Up</a>			    
				<p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
				</div>
			</div>
			</div>
		</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>