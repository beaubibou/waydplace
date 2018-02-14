
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
<title>><%=ProposeActiviteMembre.TITRE_ONGLET%></title>

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
						<button type="button" class="navbar-toggle">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>

						<!-- Brand -->


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

						<%@ include file="menuMembreTest.jsp"%>


					</ul>
				</div>

			</nav>

		</div>
	</div>
	<!-- Main Content -->
	<div class="container-fluid">
		<div class="side-body">


			<div class="jumbotron vertical-center">
				<div class="container ">


					<form action="/waydplace/Frontal" method="post" id='formulaire'
						onsubmit="return valideFichier()">

						<input name="action" type="hidden"
							value=<%=Frontal.MODIFIER_COMPTE_MEMBRE%>>

						<div class="form-group row">
							<div align="center"
								class="col-xs-6 col-xs-offset-3 col-md-6 col-md-offset-3 ">

								<img width='50%' src='<%=profil.getMembre().getUrlPhoto()%>'
									class="img-rounded img-responsive">
							</div>
						</div>

						<div class="form-group row">

							<div class="col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3 ">

								<label for="nom"><%=CompteMembre.LABEL_NOM%></label> <input
									type="text" class="form-control" id="nom"
									placeholder="<%=CompteMembre.getHintNomSociete()%>"
									maxlength="<%=CompteMembre.TAILLE_PSEUDO_MAX%>" name="pseudo"
									required value="<%=profil.getPseudo()%>">

							</div>
						</div>
						<div class="form-group row">

							<div class="col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3 ">
								<label for="datenaissance"><%=CompteMembre.DATE_NAISSANCE%></label>
								<div class='input-group date' id='datenaissance'>
									<input type='text' class="form-control" id="datenaissance"
										name="datenaissance" required /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="form-group row">

							<div class="col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3 ">
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

							<div class="col-xs-10 col-xs-offset-1 col-md-6 col-md-offset-3 ">
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
									<button onclick="modifieCompte()" class="btnwayd btn-lg">Sauvegarder</button>
							
							</div>
						</div>

			



				</div>

			</div>



		</div>
	</div>

</body>

<script>
	
			$('#datenaissance').datetimepicker({
				defaultDate : new Date(<%=profil.getDateNaissance().getYear()%>,<%=profil.getDateNaissance().getMonthOfYear() - 1%>,<%=profil.getDateNaissance().getDayOfMonth()%>),
				format : 'DD/MM/YYYY'

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
			            
			            }]
			        }); 
					
					
				}
				else
				{
					
					BootstrapDialog.alert(responseText);
				}

				

			});	
	
	
	
}

</script>


</body>
</html>
