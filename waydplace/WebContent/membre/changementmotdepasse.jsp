
<%@page import="text.pageweb.ChangemetMotDePasse"%>
<%@page import="text.pageweb.Erreur_HTML"%>
<%@page import="bean.Profil"%>
<%@page import="bean.RefTypeGenre"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@page import="outils.Outils"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>
<%@page import="text.pageweb.ProposeActiviteMembre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<title>><%=ProposeActiviteMembre.TITRE_ONGLET%></title>
">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
<link href="/waydplace/css/slide.css" rel="stylesheet" type="text/css">
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">

<script src="/waydplace/js/valideform.js"></script>
<script src="/waydplace/js/slide.js"></script>

</head>
<body>

	<%
	Profil profil = (Profil) request.getSession()
	.getAttribute("profil");
String mail = profil.getMembre().getMail();
	%>
	<div class="row">
		<!-- uncomment code for absolute positioning tweek see top comment in css -->
		<!-- <div class="absolute-wrapper"> </div> -->
		<!-- Menu -->
		<div class="side-menu">

			<nav class="navbar navbar-default" role="navigation">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<div class="brand-wrapper">
						<!-- Hamburger -->
						<button type="button" class="navbar-toggle ">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>



						<div class="brand-name-wrapper">
							<a class="navbar-brand" href="#"> Mon Compte </a>
						</div>


						<a href="<%=Frontal.ACTION_REDIRECTION_ACCEUIL%>"
							class="btn btn-default" id="search-trigger"> <span
							class="glyphicon glyphicon-home"></span>
						</a>


						<!-- Search body -->

					</div>

				</div>

				<!-- Main Menu -->
				<div class="side-menu-container">
					<ul class="nav navbar-nav">

						<%@ include file="menuMembre.jsp"%>


					</ul>
				</div>

			</nav>

		</div>
	</div>
	<!-- Main Content -->

	<div style="padding-top: 30px;" class="container-fluid well well-sm">
		<div class="side-body">
	<form method="post" action="" id="loginform" class="form-horizontal"
				role="form">

				<div class="row">

					<div class="col-xs-12 col-xs-offset-0 col-md-4 col-md-offset-4 ">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="ancienMdp"
								type="password" class="form-control" name="ancienMdp"
								placeholder="<%=ChangemetMotDePasse.HINT_ANCIEN_MOT_DE_PASSE%>">
						</div>
					</div>
				</div>

				<div class="col-xs-12 col-xs-offset-0 col-md-4 col-md-offset-4 ">

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <input id="motDePasse"
							type="password" class="form-control" name="motDePasse"
							placeholder="<%=ChangemetMotDePasse.HINT_MOT_DE_PASSE%>">
					</div>
				</div>


				<div class="col-xs-12 col-xs-offset-0 col-md-4 col-md-offset-4 ">

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <input id="motDePasseBis"
							type="password" class="form-control" name="motDePasseBis"
							placeholder="<%=ChangemetMotDePasse.HINT_MOT_DE_PASSE_BIS%>">
					</div>

				</div>
				<div style="margin-top: 10px" class="form-group">
					<!-- Button -->

					<div class="col-xs-12 col-xs-offset-0 col-md-4 col-md-offset-4 ">

						<a id="btn-password" onclick="envoiDemande()" class="btn btnwayd ">Envoyer
							la demande</a> <a href='<%=Frontal.ACTION_REDIRECTION_MON_COMPTE%>'
							class="btn btnwayd"><span
							class="glyphicon glyphicon-arrow-left"></span> </a>
					</div>


				</div>

			</form>
			
		</div>

	</div>


<script>


function envoiDemande(){

	
	var email= "<%=mail%>";
	var ancienmdp= document.getElementById("ancienMdp").value;
	var mdp= document.getElementById("motDePasse").value;
	var mdpBis= document.getElementById("motDePasseBis").value;

	
	if ( mdp!=mdpBis){
		BootstrapDialog.alert('Les mots de passe ne correspondent pas');
	return false;
	}




firebase.auth().signInWithEmailAndPassword(email, ancienmdp).then(function(firebaseUser) {

	 
	 firebase.auth().currentUser.updatePassword(mdp).then(function() {


			BootstrapDialog.alert("<%=Erreur_HTML.MOT_DE_PASSE_CHANGE%>", function(){
				document.location.href="/waydplace/membre/compteMembre.jsp";   
			});
			
		  // Update successful.
		}).catch(function(error) {
		  // An error happened.
			var errorMessage = error.message;
			 
			BootstrapDialog.alert(errorMessage);
		});

	
 
	  
}).catch(function(error) {
	  // Handle Errors here.
	  var errorCode = error.code;
		var errorMessage = error.message;

	if (errorCode=='auth/wrong-password')	
		BootstrapDialog.alert(errorMessage);


			

	  // ...
	});


}


	
	

</script>



</body>



</html>
