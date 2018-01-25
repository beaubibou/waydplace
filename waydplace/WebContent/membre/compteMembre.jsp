
<%@page import="parametre.ActionPage"%>
<%@page import="bean.Profil"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><%=CompteMembre.TITRE_ONGLET%></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="/wayd/css/styleWaydAdmin.css" rel="stylesheet"
	type="text/css">
<script src="/waydplace/js/moment.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">

</head>
<body>
	<%
		Profil profil = (Profil) request.getSession()
				.getAttribute("profil");
	%>
	<%@include file="menuMembre.jsp"%>
	<div class="container margedebut">
		<div id="loginbox" class="mainbox col-md-8 col-md-offset-2 col-sm-8">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title">Mon compte</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">

					<div style="border-bottom: 1px solid #888;">

						<p class="text-tuto"><%=CompteMembre.MESSAGE_JUMBO_L1%></p>
						<p class="text-tuto"><%=CompteMembre.MESSAGE_JUMBO_L2%></p>

					</div>
					<br> <br>


					<div class="form-group">
						<div class="row">
							<div class="col-sm-8">
								<a href="#" class="btn btn-danger btn-sm"> <span
									class="glyphicon glyphicon-remove"></span>
								</a>
								<%=profil.getMembre().getDetailEnteteMembreHtml()%>

								<form action="/waydplace/Frontal?action=<%=ActionPage.CHARGE_PHOTO_PROFIL_MEMBRE %>" method="post"
									enctype="multipart/form-data" onsubmit="return valideFichier()">

									<input type="file" name="file" size="50" id="file" />

									<div class="container">
										<div class="btn-group">
											<input type="submit" value="Envoyer la photo"
												class="btn btn-primary " /> <a href="#"
												class="btn btn-primary">Mot de passe </a>

										</div>
									</div>

								</form>


							</div>
						</div>

					</div>


					<form action="/waydplace/Frontal" method="post"
						onsubmit="return valideFormulaire()">

						<input name="action" type="hidden"
							value=<%=ActionPage.MODIFIER_COMPTE_MEMBRE%>>

						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">

									<label for="nom"><%=CompteMembre.LABEL_NOM%></label> <input
										type="text" class="form-control" id="nom"
										placeholder="<%=CompteMembre.getHintNomSociete()%>"
										maxlength="<%=CompteMembre.TAILLE_PSEUDO_MAX%>" name="pseudo"
										required value="<%=profil.getPseudo()%>">
								</div>
								<div class="col-sm-6">

									<label for="datenaissance"><%=CompteMembre.DATE_NAISSANCE%></label>
									<div class='input-group date' id='datenaissance'>
										<input type='text' class="form-control" id="datenaissance"
											name="datenaissance" /> <span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>

								</div>


							</div>
						</div>


						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">

									<label for="nom"><%=CompteMembre.GENRE%></label> <input
										type="text" class="form-control" id="nom"
										placeholder="<%=CompteMembre.getHintNomSociete()%>"
										maxlength="<%=CompteMembre.TAILLE_PSEUDO_MAX%>" name="pseudo"
										required value="<%=profil.getPseudo()%>">
								</div>


							</div>
						</div>

						<div class="form-group">
							<label for="description"><%=CompteMembre.LABEL_DESCRIPTION_PROFIL%></label>
							<textarea class="form-control" rows="5" id="description"
								name="commentaire"
								placeholder="<%=CompteMembre.getHintDescriptionProfil()%>"
								maxlength="<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>"><%=profil.getDescription()%></textarea>
						</div>

						<h5 class="nbrcaracteremax" id="nbr">
							0 Caractére
							<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
						</h5>

						<button type="submit" class="btnwayd btn-lg">Sauvegarder</button>

					</form>

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
	
		
		$('#datenaissance').datetimepicker({
			defaultDate : new Date(2010,10,10),
			format : 'DD/MM/YYYY'

		}).on('dp.change', function (e) {document.getElementById("formulaire").submit(); });


		$(document).ready(function(e) {

			$('#description').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere +
				'/ <%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
			';
													$('#nbr').text(msg);
													// Le script qui devra calculer et afficher le nombre de mots et de caractères

												})

							});

			// Init le nombre de caraterces	
			var nombreCaractere = $('#description').val().length;
			var msg = nombreCaractere + "/"
					+
		<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
			;
			$('#nbr').text(msg);
		</script>
</body>
</html>
