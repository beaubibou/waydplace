
<%@page import="servlet.membre.ConnexionMembre"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>

<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
<link href="/waydplace/css/boutton.css" rel="stylesheet" media="all"
	type="text/css">

<style>
body, html {
	height: 100%;
	margin: 0;
}

.hero-image {
	background-image: url("/waydplace/img/waydfond.jpg");
	height: 100%;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	position: relative;
}

.hero-text {
	text-align: center;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	color: white;
}



.hero-text button:hover {
	background-color: #555;
	color: white;
}

#jumbo {
	/* IE8 and below */
	background: rgb(255, 173, 64);
	/* all other browsers */
	background: rgba(255, 173, 64, 0.5);
}
</style>

</head>
<body>


	<div class="hero-image">

			<div class="hero-text">
				<div class="container">
					<div class="jumbotron" id="jumbo">

						<img src="/waydplace/img/waydLogoHD.png" alt="Cinque Terre"
							style="width: 30%">
					
						<form id="formulaire" onsubmit="return connexionSite()">
						
								<input name="action" type="hidden"
							value=<%=ConnexionMembre.VALIDE_SITE%>> 
					
							<div class="form-group">
								<div class="col-xs-8 col-md-4 col-md-offset-4 ">
									<input type="text" class="form-control" id="codeSite" name="codeSite"
										placeholder='Code site'>
								</div>
									<div class="col-xs-4 col-md-2  ">
								 	<button  type="submit"  class="btn btnwayd" >Ok</button>
							
							</div>
							</div>
							
							
							</form>
							
					</div>

					
				</div>
			</div>
		
	</div>

<script type="text/javascript">
	
function connexionSite() {

	var codeSite=document.getElementById("codeSite").value;
	 

			$.get(
							"/waydplace/ConnexionMembre?"
									+ $("#formulaire").serialize(),
							function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
								if (responseText == 'ok') {
									BootstrapDialog
											.show({
												title : 'Confirmation',
												closable : false,
												message : 'Bienvenue!! ',
												buttons : [ {
													label : 'Ok',
													action : function(dialog) {
														dialog.close();
														location.href = "<%=ConnexionMembre.ACTION_REDIRECTION_CONNEXION%>"+codeSite;
														//  dialog.setMessage('Message 1');
								 					}

												} ]
											});

								} else {

									 
									BootstrapDialog.alert(responseText);
								}

							});
			return false;

		}
	</script>


</body>
</html>