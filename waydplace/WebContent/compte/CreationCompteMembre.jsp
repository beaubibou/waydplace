<%@page import="text.pageweb.CreationCompteMembre"%>
<%@page import="text.pageweb.LoginTxt"%>
<%@page import="servlet.membre.FrontalGestionnaire"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="servlet.membre.ConnexionMembre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link href="/waydplace/css/indexcss.css" rel="stylesheet"
	type="text/css">

<script src="https://www.gstatic.com/firebasejs/4.8.2/firebase.js"></script>
<script>
	// Initialize Firebase
	var config = {
		apiKey : "AIzaSyCiTVROhzVToGPW-h16amlnckDvFEse9Hg",
		authDomain : "waydplace.firebaseapp.com",
		databaseURL : "https://waydplace.firebaseio.com",
		projectId : "waydplace",
		storageBucket : "",
		messagingSenderId : "165326675460"
	};
	firebase.initializeApp(config);
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Création compte</title>
<meta charset="utf-8">
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
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
<link href="/waydplace/css/boutton.css" rel="stylesheet" media="all"
	type="text/css">

<script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body>





	<div class="container">


		<div class="omb_login">
			<div class="row">
				<div class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5 ">
					<a href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN%>">
						<img src='/waydplace/img/waydLogoHD.png'
						class="img-rounded img-responsive">
					</a>
				</div>
			</div>
			<h3 class="omb_authTitle">
				Particulier ou <a
					href="<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_COMPTE_PRO%>">Professionel</a>
			</h3>


			<div class="row omb_row-sm-offset-3 omb_loginOr">
				<div class="col-xs-12 col-sm-6">
					<hr class="omb_hrOr">

				</div>
			</div>

			<div class="row omb_row-sm-offset-3">
				<div class="col-xs-12 col-sm-6">
					<form id="formulaire" class="omb_loginForm" action=""
						autocomplete="off" method="POST" onsubmit="return creerCompte()">
						<div class="input-group">

							<span class="input-group-addon"><i class="fa fa-user"></i></span>

							<input id="login-username" type="email" class="form-control"
								name="email" required
								placeholder="<%=CreationCompteMembre.HINT_EMAIL%>">
						</div>
						<input name="action" type="hidden"
							value=<%=ConnexionMembre.CREER_COMPTE_MEMBRE%>> <span
							class="help-block"></span>

						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="nom"
								type="text" class="form-control" name='nom'
								placeholder="<%=CreationCompteMembre.HINT_PSEUDO%>">

						</div>

						<span class="help-block"></span>
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input id="login-password" type="password" class="form-control"
								name="pwd"
								placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE%>">
						</div>
						<span class="help-block"></span>

						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-lock"></i></span>
							<input id="login-password-bis" type="password"
								class="form-control" name="pwd1"
								placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE_BIS%>">
						</div>
						<span class="help-block"></span>


						<div class="form-group">
							<div class="g-recaptcha"
								data-sitekey="6Ld6TzgUAAAAAMx76Q_NXm3xEJ1vPa799RLMeYLn"></div>
						</div>

						<span class="help-block"></span>

						<div class="btn-group btn-group-justified">

							<div class="btn-group ">
								<button type="submit" class="btn btn-lg btnwayd ">Créer</button>
							</div>

							<div class="btn-group ">
								<a href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN%>"
									class="btn btn-lg btnwayd"> <span
									class="glyphicon glyphicon-arrow-left"></span> Accueil
								</a>
							</div>


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

			if (pwd1 == '') {
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
		function creerCompte() {

			var validation = valideFormulaire();

			$
					.get(
							"/waydplace/ConnexionMembre?"
									+ $("#formulaire").serialize(),
							function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
								if (responseText == 'ok') {
									BootstrapDialog
											.show({
												title : 'Confirmation',
												closable : false,
												message : 'Votre compte a été crée un mail vous a été envoyé.',
												buttons : [ {
													label : 'Ok',
													action : function(dialog) {
														dialog.close();
														location.href = '/waydplace/index.jsp'
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