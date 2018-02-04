
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

	<%
		String pseudo = Outils.convertRequeteToString(request
				.getParameter("nom"));

		String email = Outils.convertRequeteToString(request
				.getParameter("email"));
		String pwd1 = Outils.convertRequeteToString(request
				.getParameter("pwd1"));
		String pwd = Outils.convertRequeteToString(request
				.getParameter("pwd"));

		String messageAlert = (String)request.getAttribute("messageAlert");	
		
		
		%>

		<%=AlertDialog.getAlertDialog(messageAlert) %>

	<div class="container margedebut">
		<div id="loginbox"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><%=CreationCompteMembre.TITRE_PANEL%></div>

				</div>

				<div style="padding-top: 30px" class="panel-body">

					<form action="/waydplace/ConnexionMembre" method="post"
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
									name="email" required value="<%=email%>"
									placeholder="<%=CreationCompteMembre.HINT_EMAIL%>">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password" type="password" class="form-control"
									name="pwd" value="<%=pwd%>"
									placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE%>">
							</div>

							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input
									id="login-password-bis" type="password" class="form-control"
									name="pwd1" value="<%=pwd1%>"
									placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE_BIS%>">
							</div>
							<div class="row">
								<div class='col-md-4 col-md-offset-5'>
									<h4>Renseignements</h4>
								</div>
							</div>

							<div class="form-group">
								<label for="nom"><%=CreationCompteMembre.LABEL_PSEUDO_GESTIONNAIRE%></label>
								<input type="text"
									maxlength="<%=CreationCompteProText.TAILLE_PSEUDO_MAX%>"
									value="<%=pseudo%>" class="form-control" id="nom"
									placeholder="<%=CreationCompteMembre.HINT_PSEUDO%>" name="nom"
									required>
							</div>


							<br>

							<div class="form-group">
								<div class="g-recaptcha"
									data-sitekey="6Ld6TzgUAAAAAMx76Q_NXm3xEJ1vPa799RLMeYLn"></div>
							</div>

							<br>
							<button type="submit" class="btn btnwayd">Soumettre</button>
							<a href='/waydplace/index.jsp' class="btn btnwayd" role="button"><span
								class=" glyphicon glyphicon-home"></span> Accueil</a>



						</div>

					</form>


				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function valideFormulaire() {

			var pwd1 = document.getElementById("login-password").value;
			var pwd2 = document.getElementById("login-password-bis").value;

			if (pwd1 != pwd2) {
				BootstrapDialog.alert("Mot de passe différents");
				return false;
			}
			
			
			

			return true;
		}
		
		
	</script>


</body>
</html>