<!DOCTYPE html>

<%@page import="servlet.membre.Frontal"%>
<%@page import="servlet.membre.ConnexionMembre"%>
<%@page import="text.pageweb.RedemandeConfirmationMail"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html lang="en">
<head>
<script src="https://www.gstatic.com/firebasejs/4.8.2/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCiTVROhzVToGPW-h16amlnckDvFEse9Hg",
    authDomain: "waydplace.firebaseapp.com",
    databaseURL: "https://waydplace.firebaseio.com",
    projectId: "waydplace",
    storageBucket: "",
    messagingSenderId: "165326675460"
  };
  firebase.initializeApp(config);
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mot de passe</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>


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

<script src="/waydplace/js/moment.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
<link href="/waydplace/css/boutton.css" rel="stylesheet" media="all"
	type="text/css">


</head>
<body>

<div class="container" >
	<div class="page-header">
			
			<h1 ><img src="/waydplace/img/waydLogoHD.png" style="margin-right:50px;" class="img-rounded"
				alt="Cinque Terre" width="100" height="100"><%=RedemandeConfirmationMail.JUMBO_TITRE %></h1>
		</div>
		<p><%=RedemandeConfirmationMail.JUMBO_LIGNE1 %></p>
		<p> <%=RedemandeConfirmationMail.JUMBO_LIGNE2 %> </p>
		
	<br>
	</div>
	
		
	<form id="formmasque" action="/wayd/Connexion" method="post">
		<input id="token" type="hidden" class="form-control" name="token">
		<input id="pwd" type="hidden" class="form-control" name="pwd">
	</form>

	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default" >
				<div class="panel-heading panel-heading-custom">

					<div class="panel-title"><%=RedemandeConfirmationMail.TITRE_PANEL %></div>
			
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<form id="loginform" class="form-horizontal" role="form">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="email" class="form-control" name="email"
								 placeholder="<%=RedemandeConfirmationMail.HINT_EMAIL %>">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="pwd" 
								placeholder="<%=RedemandeConfirmationMail.HINT_MOT_DE_PASSE %>">
						</div>

						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

					 	<div class="col-sm-12 controls">
				
						
									<a id="btn-password" onclick="envoiDemande()"
									class="btn btnwayd ">Envoyer la demande</a>
								 <a href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN %>"  class="btn btnwayd"><span
								  class="glyphicon glyphicon-home" ></span> Accueil</a>
									</div>

						
						</div>

					
						
						
					</form>



				</div>
			</div>
		</div>
	</div>

	<script>


function envoiDemande(){

	var email= document.getElementById("login-username").value;
	var password= document.getElementById("login-password").value;

	firebase.auth().signInWithEmailAndPassword(email, password).then(function(firebaseUser) {
		 
		firebase.auth().currentUser.sendEmailVerification().then(function() {
			  // Email sent.
 
			BootstrapDialog.alert('Un email de confirmation vous a été envoyé', function(){
				document.location.href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN%>";
			});
			
		}).catch(function(error) {
			  // An error happened.
		  var errorMessage = error.message;
			 
			BootstrapDialog.alert(errorMessage, function(){
				document.location.href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN%>";   
			});

				});
	  
		  
	}).catch(function(error) {
		  // Handle Errors here.
		  var errorCode = error.code;
		  var errorMessage = error.message;
		  // The email of the user's account used.
		  var email = error.email;
		  // The firebase.auth.AuthCredential type that was used.
		  var credential = error.credential;
		  BootstrapDialog.alert(errorMessage, function(){
				document.location.href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN%>";
			});

		  // ...
		});
	

	

	
}
</script>

</body>
</html>
