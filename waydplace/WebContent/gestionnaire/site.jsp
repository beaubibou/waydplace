
<%@page import="parametre.ActionPage"%>
<%@page import="text.pageweb.SiteText"%>
<%@page import="bean.Site"%>
<%@page import="bean.Profil"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><%=SiteText.TITRE_ONGLET%></title>

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
	type="text/css" />

<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css" />





</head>
<%@ include file="menuGestionnaire.jsp"%>
<body>
	<%
		Profil profil = (Profil) request.getSession()
				.getAttribute("profil");
		Site site = profil.getSite();
	%>

	<div class="container margedebut">
		<div id="loginbox" class="mainbox col-md-8 col-md-offset-2 col-sm-8">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title">Site</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">

					<div style="border-bottom: 1px solid #888;">

						<p class="text-tuto"><%=SiteText.MESSAGE_JUMBO_L1%></p>
						<p class="text-tuto"><%=SiteText.MESSAGE_JUMBO_L2%></p>

					</div>
					<br> <br>


					<div class="form-group">
						<div class="row">
							<div class="col-sm-4">
								<img height="300" width="200" src=<%=profil.getPhotostr()%>
									class="img-thumbnail" />
							</div>
							<div class="col-sm-8">
								<form action="/wayd/ChargePhotoPro" method="post"
									enctype="multipart/form-data" onsubmit="return valideFichier()">
									<input type="file" name="file" size="50" id="file" /> <br>

									<input type="submit" value="Envoyer la photo"
										class="btn btnwayd btn-sm" />


								</form>
								<br> <a title="Supprimer photo"
									href="/wayd/ComptePro?action=supprimerPhoto">
									<button type="button" class="btn btn-danger btn-sm">
										<span class="glyphicon glyphicon-trash"> Supprimer la
											photo</span>
									</button>
								</a> <br> <br> <a title="Mot de passe"
									href="/wayd/auth/changementmotdepasse.jsp">
									<button type="button" class="btn btnwayd  btn-sm">
										<span class="glyphicon glyphicon-lock">Changer mot de
											passe</span>
									</button>
								</a>
							</div>

						</div>

					</div>


					<form action="/waydplace/FrontalGestionnaire" method="post"
						onsubmit="return valideFormulaire()">

						<input name="action" type="text"
							value=<%=ActionPage.MODIFIER_SITE_GESTIONNAIRE%>>

						<div class="form-group">

							<div class="row">

								
								<div class="col-sm-8 ">

									<div class="form-group">
										<label for="nom"><%=SiteText.LABEL_NOM%></label> <input
											type="text" class="form-control" id="nom"
											placeholder="<%=SiteText.getHintNomSociete()%>"
											maxlength="<%=SiteText.TAILLE_NOM_SITE_MAX%>" name="nom"
											required value="<%=site.getNom()%>">
									</div>
								

							</div>
								
						</div>
						<div class="form-group">
					
							<div class="row">
								<div class="col-sm-8">
									<div class="form-group">
										<label for="jeton"><%=SiteText.LABEL_JETON%></label> <input
											type="text" class="form-control" id="jeton"
											placeholder="<%=SiteText.HINT_DESCRIPTION_JETON%>"
											maxlength="<%=SiteText.TAILLE_JETON_MAX%>" name="jetonSite"
											required value="<%=site.getJeton()%>">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="typro"><%=SiteText.TYPE_COMPTE%></label> <input
											type="text" class="form-control" disabled id="typepro"
											value="Site">
									</div>
								</div>

							</div>
						</div>
		</div>
							<label for="description"><%=SiteText.LABEL_DESCRIPTION_PROFIL%></label>
							<textarea class="form-control" rows="5" id="description"
								name="description"
								placeholder="<%=SiteText.getHintDescriptionProfil()%>"
								maxlength="<%=SiteText.TAILLE_DESCRIPTION_PROFIL_MAX%>"><%=site.getDescription()%></textarea>
					
						<h5 class="nbrcaracteremax" id="nbr">
							0 Caractére
							<%=SiteText.TAILLE_DESCRIPTION_PROFIL_MAX%>
						</h5>
				<button type="submit" class="btnwayd btn-lg">Sauvegarder</button>




					</form>


				</div>
			</div>
		</div>
	</div>


	<script>
	
	function valideFichier(){
		
		var monfichier;
		monfichier=latitude = document.getElementById("file").value;
		
	if (monfichier==''){
		BootstrapDialog
		.alert("<%=CompteMembre.AUCUN_FICHIER_SELECTIONNE%>");
	return false;
	}
			
	
	}
	
	</script>

	<script>
	
	$(document).ready(function(e) {

		$('#description').keyup(function() {

			var nombreCaractere = $(this).val().length;
			//alert(nombreCaractere);

			var msg = nombreCaractere + '/ <%=SiteText.TAILLE_DESCRIPTION_PROFIL_MAX%>
		';

												$('#nbr').text(msg);
												// Le script qui devra calculer et afficher le nombre de mots et de caractères

											})

						});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg = nombreCaractere + "/"
				+
	<%=SiteText.TAILLE_DESCRIPTION_PROFIL_MAX%>
		;
		$('#nbr').text(msg);
	</script>
</body>
</html>
