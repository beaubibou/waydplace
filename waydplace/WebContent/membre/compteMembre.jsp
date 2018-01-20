
<%@page import="parametre.ActionPage"%>
<%@page import="bean.Profil"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><%=CompteMembre.TITRE_ONGLET%></title>

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

<link href="/waydplace/css/styleWayd.css" rel="stylesheet" type="text/css" />

<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet" media="all"
	type="text/css" />





</head>
<body>
<%

	Profil profil = (Profil) request.getSession().getAttribute("profil");
 %>

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
							<div class="col-sm-4">
								<img height="300" width="200"
									src=<%=profil.getPhotostr()%>
									class="img-thumbnail" />
							</div>
							<div class="col-sm-8">
								<form action="/wayd/ChargePhotoPro" method="post"
									enctype="multipart/form-data" onsubmit="return valideFichier()">
									<input type="file" name="file" size="50" id="file" /> <br>

									<input type="submit" value="Envoyer la photo" class="btn btnwayd btn-sm" />


								</form>
								<br>
								<a title="Supprimer photo"
										href="/wayd/ComptePro?action=supprimerPhoto">
										<button type="button" class="btn btn-danger btn-sm">
											<span class="glyphicon glyphicon-trash"> Supprimer la photo</span>
										</button>
									</a>
									<br>
									<br>
										<a title="Mot de passe"
										href="/wayd/auth/changementmotdepasse.jsp">
										<button type="button" class="btn btnwayd  btn-sm">
											<span class="glyphicon glyphicon-lock">Changer mot de passe</span>
										</button>
									</a>
							</div>

						</div>

					</div>


					<form action="/waydplace/Frontal" method="post"
						onsubmit="return valideFormulaire()">

					<input  name="action" type="text"  value=<%=ActionPage.MODIFIER_COMPTE_MEMBRE %> >
			
						<div class="form-group">

							<div class="row">
								
								<div class="col-sm-1 ">
								

								</div>

								<div class="col-sm-1">
								
								</div>



							</div>
							<br>
							<div class="row">
								<div class="col-sm-8">
									<div class="form-group">
										<label for="nom"><%=CompteMembre.LABEL_NOM%></label> <input
											type="text" class="form-control" id="nom"
											placeholder="<%=CompteMembre.getHintNomSociete()%>"
											maxlength="<%=CompteMembre.TAILLE_PSEUDO_MAX%>" name="pseudo"
											required value="<%=profil.getPseudo()%>">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="typro"><%=CompteMembre.TYPE_COMPTE%></label> <input
											type="text" class="form-control" disabled id="typepro"
											value="Membre">
									</div>
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
							0 Caractére "<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>"
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

				var msg = nombreCaractere + '/ <%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>';

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
