
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
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title">Site</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">



					<div class="form-group">
						<div class="row">

							<div class="col-sm-8">
							
								<%=profil.getSite().getDetailEnteteSiteHtml()%>

								<form
									action="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.CHARGE_PHOTO_SITE_GESTIONNAIRE%>"
									method="post" enctype="multipart/form-data"
									onsubmit="return valideFichier()">

									</br>
										<div class="container">
									<div class="btn-group">
										<label class="btn btn-default btn-file btn-primary btn-sm">
											.. <input name="file" size="50" type="file"
											style="display: none;">
										</label> <input type="submit" value="Envoyer la photo"
											class="btn btn-primary btn-sm  " /> <a
											href='/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.SUPPRIMER_PHOTO_SITE_GESTIONNAIRE%>'
											class='btn btn-danger btn-sm'> <span class="glyphicon glyphicon-remove"></span></a>
					 <a href='/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_CHANGE_MOT_DE_PASSE_GESTIONNAIRE%>' class="btn btn-info btn-sm"> <span class="glyphicon glyphicon-lock"></span></a> 

									</div>

								</form>


							</div>
						</div>

					</div>

					<form action="/waydplace/FrontalGestionnaire" method="post"
						onsubmit="return valideFormulaire()">

						<input name="action" type="hidden"
							value=<%=FrontalGestionnaire.MODIFIER_SITE_GESTIONNAIRE%>>

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


								</div>
							</div>
							<div class="form-group">

								<div class="row">
									<div class="col-sm-4">
										<div class="form-group">
											<label for="jeton"><%=SiteText.LABEL_TELEPHONE%></label> <input
												type="text" class="form-control" id="jeton"
												placeholder="<%=SiteText.HINT_TELEPHONE%>"
												maxlength="<%=SiteText.TAILLE_TELEPHONNE_MAX%>"
												name="jetonSite" required value="<%=site.getJeton()%>">
										</div>
									</div>


								</div>
							</div>
						</div>
						<div class="form-group">

							<label for="adresse"><%=SiteText.LABEL_ADRESSE%></label>
							<textarea class="form-control" rows="5" id="adresse"
								name="adresse" placeholder="<%=SiteText.HINT_ADRESSE%>"
								maxlength="<%=SiteText.TAILLE_ADRESSE_MAX%>"><%=site.getAdresse()%></textarea>
						</div>
						<div class="form-group">

							<label for="description"><%=SiteText.LABEL_DESCRIPTION_PROFIL%></label>
							<textarea class="form-control" rows="5" id="description"
								name="description"
								placeholder="<%=SiteText.getHintDescriptionProfil()%>"
								maxlength="<%=SiteText.TAILLE_DESCRIPTION_PROFIL_MAX%>"><%=site.getDescription()%></textarea>


						</div>

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

			var msg = nombreCaractere + '/'+<%=SiteText.TAILLE_DESCRIPTION_PROFIL_MAX%>;

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
