<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="parametre.Parametres"%>
<%@page import="bean.Profil"%>
<%@page import="bean.RefTypeGenre"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@page import="outils.Outils"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>
<%@page import="text.pageweb.ProposeActiviteMembre"%>


<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>><%=CompteMembre.TITRE_ONGLET%></title>

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

			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 col-md-2 col-md-offset-5 ">

					<img src='<%=profil.getMembre().getURLPhoto()%>'
						class="<%=Parametres.STYLE_IMAGE%>">
				</div>
			</div>

			<form action="<%=Frontal.ACTION_CHARGE_PHOTO_MEMBRE%>" method="post"
				enctype="multipart/form-data" onsubmit="return valideFichier()">

				<div align="center"
					class="col-xs-12 col-xs-offset-0 col-md-4 col-md-offset-4 ">

					<br>

					<div class="btn-group">
						<label class="btn btnwayd btn-file btn-primary btn-md"> ..
							<input name="file" size="50" type="file" id="file"
							style="display: none;">
						</label> <input type="submit" value="Envoyer la photo"
							class="btn btnwayd btn-md  " /> <a
							href='<%=Frontal.ACTION_REDIRECTION_CHANGE_MOT_DE_PASSE_MEMBRE%>'
							class="btn btnwayd btn-md"> <span
							class="glyphicon glyphicon-lock"></span></a> <a
							href='/waydplace/Frontal?action=<%=Frontal.SUPPRIMER_PHOTO_MEMBRE%>'
							class='btn btn-danger btn-md'> <span
							class="glyphicon glyphicon-remove"></span></a>

					</div>

				</div>
			</form>

			<form action="/waydplace/Frontal" method="post" id='formulaire'
				onsubmit="return valideFichier()">

				<input name="action" type="hidden"
					value=<%=Frontal.MODIFIER_COMPTE_MEMBRE%>>


				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">

						<label for="nom"><%=CompteMembre.LABEL_NOM%></label> <input
							type="text" class="form-control" id="nom"
							placeholder="<%=CompteMembre.getHintNomSociete()%>"
							maxlength="<%=CompteMembre.TAILLE_PSEUDO_MAX%>" name="pseudo"
							required value="<%=profil.getPseudo()%>">

					</div>
				</div>
				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">
						<label for="datenaissance"><%=CompteMembre.DATE_NAISSANCE%></label>
						<div class='input-group date' id='datenaissance'>
							<input readonly style="background-color:white;"  type='text' class="form-control" id="datenaissance"
								name="datenaissance" required /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset- col-md-6 col-md-offset-3 ">
						<label for="typeUser">Genre</label> <select
							data-style="btn-primary" class="form-control" id="typeGenre"
							name="typeGenre">

							<%
								for (RefTypeGenre critereGenre:CacheDAO.getListGenre()) {
							%>
							<option value="<%=critereGenre.getId()%>"
								<%=Outils.jspAdapterListSelected(critereGenre.getId(), profil.getIdGenre())%>>
								<%=critereGenre.getLibelle()%></option>
							<%
								}
							%>
						</select>
					</div>

				</div>

				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">
						<label for="description"><%=CompteMembre.LABEL_DESCRIPTION_PROFIL%></label>
						<textarea class="form-control" rows="5" id="description"
							name="commentaire"
							placeholder="<%=CompteMembre.getHintDescriptionProfil()%>"
							maxlength="<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>"><%=Outils.convertRequeteToString(profil.getDescription())%></textarea>
					</div>
				</div>




			</form>

			<div class="form-group row">

				<div class="col-xs-4 col-xs-offset-8 col-md-2 col-md-offset-8 ">

					<h5 class="nbrcaracteremax" id="nbr">
						0 Caractére
						<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
					</h5>

				</div>
			</div>

			<div class="form-group row">

				<div class="col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3 ">
					<button onclick="modifieCompte()" class=" btn-lg btnwayd">Sauvegarder</button>

				</div>
			</div>

		</div>

	</div>


</body>

<script>
	
			$('#datenaissance').datetimepicker({
				defaultDate : new Date(<%=profil.getDateNaissance().getYear()%>,<%=profil.getDateNaissance().getMonthOfYear() - 1%>,<%=profil.getDateNaissance().getDayOfMonth()%>),
				format : 'DD/MM/YYYY',
				focusOnShow: false,
				  ignoreReadonly: true

			});

			
	function valideFichier(){
		
		monfichier= document.getElementById("file").value;
		
	if (monfichier==''){
		BootstrapDialog
		.alert("<%=CompteMembre.AUCUN_FICHIER_SELECTIONNE%>");
			return false;
		}

	}
</script>

<script>
	$(document)
			.ready(
					function(e) {

						$('#description')
								.keyup(
										function() {

											var nombreCaractere = $(this).val().length;
											//alert(nombreCaractere);

											var msg = nombreCaractere
													+ '/'
													+<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
	;
											$('#nbr').text(msg);
											// Le script qui devra calculer et afficher le nombre de mots et de caractères

										})

					});

	// Init le nombre de caraterces	
	var nombreCaractere = $('#description').val().length;
	var msg = nombreCaractere + "/"	+<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
	;
	$('#nbr').text(msg);
</script>

<script type="text/javascript">

function modifieCompte(){
	
	
	$.get("/waydplace/Frontal?"+$("#formulaire").serialize() ,
			function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
				if (responseText == 'ok')
				{
					BootstrapDialog.show({
			            title: 'Confirmation',
			            closable: false,
			            message: 'Votre compte a été modfiée',
			            buttons: [{
			                label: 'Ok',
			                action: function(dialog) {
			               	dialog.close();
			                location.href='<%=Frontal.ACTION_REDIRECTION_MES_ACTIVITE_MEMBRE%>'
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
