
<%@page import="outils.AlertDialog"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="text.pageweb.CreationCompteMembre"%>
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




	<div class="container margedebut">
		<div id="loginbox"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><%=CreationCompteMembre.TITRE_PANEL%></div>

				</div>

				<div style="padding-top: 30px" class="panel-body">

					<form id='formulaire' action="/waydplace/ConnexionMembre" method="post"
						onsubmit="return valideFormulaire()">
						<input name="action" type="hidden"
							value=<%=ConnexionMembre.CREER_COMPTE_MEMBRE%>>

						<div class="form-group">
							<div class="row">
								<div class='col-md-4 col-md-offset-5'>
									<h4>Authentification</h4>
								</div>
							</div>
							<br>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input
									id="login-username" type="email" class="form-control"
									name="email" required 
									placeholder="<%=CreationCompteMembre.HINT_EMAIL%>">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password" type="password" class="form-control"
									name="pwd"
									placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE%>">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password-bis" type="password" class="form-control"
									name="pwd1"
									placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE_BIS%>">
							</div>
							

							<div class="form-group">
								<label for="nom"><%=CreationCompteMembre.LABEL_PSEUDO_GESTIONNAIRE%></label>
								<input type="text"
									maxlength="<%=CreationCompteProText.TAILLE_PSEUDO_MAX%>"
									 class="form-control" id="nom"
									placeholder="<%=CreationCompteMembre.HINT_PSEUDO%>" name="nom"
									required>
							</div>


							<br>

							<div class="form-group">
								<div class="g-recaptcha"
									data-sitekey="6Ld6TzgUAAAAAMx76Q_NXm3xEJ1vPa799RLMeYLn"></div>
							</div>

							<br>
			

						</div>

					</form>

		<button onclick="creerCompte()" class="btnwayd btn-lg">Soumettre</button>
					<a href="/wayd/Home" class="btnwayd btn-lg" role="button"><span
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
			            message: 'Votre compte a été crée un mail vous a été envoyé.',
			            buttons: [{
			                label: 'Ok',
			                action: function(dialog) {
			                location.href='waydplace/index.jsp'
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