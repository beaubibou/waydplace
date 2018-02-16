
<%@page import="text.pageweb.EnvoiMessage"%>
<%@page import="bean.RefTypeGenre"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@page import="outils.Outils"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>
<%@page import="text.pageweb.ProposeActiviteMembre"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<title>><%=EnvoiMessage.TITRE_ONGLET%></title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta charset="utf-8">
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
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/slide.css" rel="stylesheet" type="text/css">

<script src="/waydplace/js/valideform.js"></script>
<script src="/waydplace/js/slide.js"></script>

</head>
<body>

	<%
	String  uidEmetteur=(String) request.getAttribute("uid_emetteur");
	String uidDestinataire =(String) request.getAttribute("uid_destinataire");
	Integer  idactivite=(Integer) request.getAttribute("idactivite");
	
	

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
						<button type="button" class="navbar-toggle">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>

						<!-- Brand -->

						<div class="brand-name-wrapper">
							<a class="navbar-brand" href="#"> Proposer </a>
						</div>


						<!-- Search body -->
						<div id="search" class="panel-collapse collapse">
							<div class="panel-body">
								<form class="navbar-form" role="search">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Search">
									</div>
									<button type="submit" class="btn btn-default ">
										<span class="glyphicon glyphicon-ok"></span>
									</button>
								</form>
							</div>
						</div>
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
	<div style="padding-top: 30px" class="container-fluid">
		<div class="side-body">




			<form action="/waydplace/Frontal"
						onsubmit="return valideFormulaire()" method="post">

						<input name="action" type="hidden" Value=<%=Frontal.ENVOI_MESSAGE_MEMBRE%>> 
						
						<input name="uid_destinataire" type="hidden"	value=<%=uidDestinataire%>> 
						<input name="uid_emetteur" type="hidden"value=<%=uidEmetteur%>>
						<input name="idactivite" type="hidden"	value=<%=idactivite.toString()%>>
							


				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">

					<label for="message"><%=EnvoiMessage.LABEL_DESCRIPTION_MESSAGE%></label>
							<textarea 
								placeholder="<%=EnvoiMessage.HINT_DESCRIPTION_MESSAGE%>"
								maxlength="<%=EnvoiMessage.TAILLE_DESCRIPTION_MESSAGE%>"
								class="form-control" rows="5" id="message" name="message"></textarea>
					</div>
				</div>
				
				<div class="col-xs-4 col-xs-offset-10 col-md-2 col-md-offset-8 ">

				<h5 class="nbrcaracteremax" id="nbr">
					0 Caractére
					<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
				</h5>

			</div>
					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">
				
						<button type="submit" class="btnwayd btn-lg">Envoyer</button>
				</div>

			</form>


			

	
		</div>

	</div>



</body>

	<script>

	function valideFormulaire() {
		
		return true;
	
	}
	
		$(document).ready(function(e) {

			$('#message').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere + '<%=EnvoiMessage.getNbrCarateresDescription()%>';

				$('#nbr').text(msg);
				// Le script qui devra calculer et afficher le nombre de mots et de caractères

			})

		});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg = nombreCaractere +   '<%=EnvoiMessage.getNbrCarateresDescription()%>';
		$('#nbr').text(msg);
	</script>

</html>
