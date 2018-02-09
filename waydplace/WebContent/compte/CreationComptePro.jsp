
<%@page import="outils.AlertDialog"%>
<%@page import="servlet.membre.ConnexionMembre"%>
<%@page import="parametre.ActionPage"%>
<%@page import="outils.Outils"%>
<%@page import="text.pageweb.CreationCompteProText"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Renseignements</title>

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
<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">
<link href="/wayd/css/nbrcaractere.css" rel="stylesheet" media="all"
	type="text/css">

<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

<div class="container">
		<div class="page-header">

			<h1>
				<img src="/waydplace/img/waydLogoHD.png" style="margin-right: 50px;"
					class="img-rounded" alt="Cinque Terre" width="100" height="100"><%=CreationCompteProText.TITRE_JUMBO%>
			</h1>
		</div>



	</div>

	<div class="container ">
		<div id="loginbox"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><%=CreationCompteProText.TITRE_PANEL%></div>

				</div>

				<div style="padding-top: 30px" class="panel-body">


					<form id='formulaire' action="/waydplace/ConnexionMembre"
						method="post" onsubmit="return valideFormulaire()">
						<input name="action" type="hidden"
							value=<%=ConnexionMembre.CREER_COMPTE_PRO%>>


							<input name="action" type="hidden"
								value=<%=ConnexionMembre.CREER_COMPTE_PRO%>>

						<div class="form-group">
							<div class="row">
								<div class='col-md-4 col-md-offset-5'>
									<h4>Authentification</h4>
								</div>
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input
									id="login-username" type="email" class="form-control"
									name="email" required
									placeholder="<%=CreationCompteProText.HINT_EMAIL%>">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password" type="password" class="form-control"
									name="pwd"
									placeholder="<%=CreationCompteProText.HINT_MOT_DE_PASSE%>">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password-bis" type="password" class="form-control"
									name="pwd1"
									placeholder="<%=CreationCompteProText.HINT_MOT_DE_PASSE_BIS%>">
							</div>
							<div class="row">
								<div class='col-md-4 col-md-offset-5'>
									<h4>Renseignements</h4>
								</div>
							</div>

							<div class="form-group">
								<label for="nom"><%=CreationCompteProText.LABEL_NOM_GESTIONNAIRE%></label>
								<input type="text"
									maxlength="<%=CreationCompteProText.TAILLE_PSEUDO_MAX%>"
									class="form-control" id="nom"
									placeholder="<%=CreationCompteProText.HINT_PSEUDO%>" name="nom"
									required>
							</div>

							<div class="form-group">
								<label for="nomSite"><%=CreationCompteProText.LABEL_NOM_SITE%></label>
								<input type="text"
									maxlength="<%=CreationCompteProText.TAILLE_PSEUDO_MAX%>"
									class="form-control" id="nomSite"
									placeholder="<%=CreationCompteProText.getHintNomSociete()%>"
									name="nomSite" required>
							</div>

							<div class="form-group">
								<label for="nom"><%=CreationCompteProText.LABEL_NUMERO_SIRET%></label>
								<input type="text" class="form-control" id="nom"
									placeholder="<%=CreationCompteProText.HINT_SIRET%>"
									name="siret"
									pattern="[0-9]{<%=CreationCompteProText.TAILLE_SIRET_MAX%>}"
									maxlength="<%=CreationCompteProText.TAILLE_SIRET_MAX%>"
									required>
							</div>

							<div class="form-group">
								<label for="nom"><%=CreationCompteProText.LABEL_TELEPHONE%></label>
								<input type="text" class="form-control" id="nom"
									placeholder="<%=CreationCompteProText.HINT_TELEPHONE%>"
									pattern="[0-9]{10}" name="telephone" required
									maxlength="<%=CreationCompteProText.TAILLE_TELEPHONNE_MAX%>">
							</div>

							<input type="hidden" class="form-control" id="typeuser"
								placeholder="typeuser" name="typeuser" required value="1">

							<div class="form-group">
								<label for="adresse"><%=CreationCompteProText.LABEL_ADRESSE%></label>
								<input type="text" class="form-control" id="adresse"
									placeholder="Renseigner l'adresse" name="adresse"
									onkeypress="initPosition()" required
									maxlength="<%=CreationCompteProText.TAILLE_ADRESSE_MAX%>">
							</div>

							<div class="form-group">
								<label for="commentaire"><%=CreationCompteProText.LABEL_DESCRIPTION_PROFIL%></label>
								<textarea class="form-control" rows="5" id="description"
									placeholder="<%=CreationCompteProText.getHintDescriptionProfil()%>"
									maxlength="<%=CreationCompteProText.TAILLE_DESCRIPTION_PROFIL_MAX%>"
									name="commentaire"></textarea>
							</div>

							<h5 class="nbrcaracteremax" id="nbr">
								0 Caractére sur
								<%=CreationCompteProText.TAILLE_DESCRIPTION_PROFIL_MAX%>
							</h5>
							<div class="form-group">

								<input type="hidden" class="form-control" id="latitude"
									name="latitude" value=0>
							</div>
							<div class="form-group">

								<input type="hidden" class="form-control" id="longitude"
									name="longitude" value=0>
							</div>
							<div class="form-group">
								<div class="g-recaptcha"
									data-sitekey="6Ld6TzgUAAAAAMx76Q_NXm3xEJ1vPa799RLMeYLn"></div>
							</div>






						</div>

					</form>

					<button onclick="creerCompte()" class="btnwayd btn">Soumettre</button>
					<a href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN %>" class="btnwayd btn" role="button"><span
						class="glyphicon glyphicon-home"></span> Accueil</a>


				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
	
	function valideFormulaire() {

			var pwd1 = document.getElementById("login-password").value;
			var pwd2 = document.getElementById("login-password-bis").value;

			if (pwd1=='')
				{
					BootstrapDialog.alert("Mot de passe obligatoire");
					return false;
				}
			if (pwd1 != pwd2) {
				BootstrapDialog.alert("Mot de passe différents");
				return false;
			}
			
	
			return true;
		}
		
		
	</script>

	<script>
		$(document).ready(function(e) {

			$('#description').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere + ' Caractere(s) / <%=CreationCompteProText.TAILLE_DESCRIPTION_PROFIL_MAX%>';

												$('#nbr').text(msg);
												// Le script qui devra calculer et afficher le nombre de mots et de caractères

											})

						});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg = nombreCaractere + " / "
				+
	<%=CreationCompteProText.TAILLE_DESCRIPTION_PROFIL_MAX%>
		;
		$('#nbr').text(msg);
	</script>

	<script type="text/javascript">

function creerCompte(){
	
	var validation=valideFormulaire();
	
	if (validation==false)
		return false;
	
	
	$.get("/waydplace/ConnexionMembre?"+$("#formulaire").serialize() ,
			function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
				if (responseText == 'ok')
				{
					BootstrapDialog.show({
			            title: 'Confirmation',
			            closable: false,
			            message: 'Votre compte a été crée un mail vous a été envoyé.',
			            buttons: [{
			                label: 'Ok',
			                action: function(dialog) {
			                location.href='/waydplace/index.jsp'
														//  dialog.setMessage('Message 1');
													}

												} ]
											});

								} else {

									BootstrapDialog.alert(responseText);
								}

							});

		}
	</script>


</body>
</html>