
<%@page import="servlet.membre.Frontal"%>
<%@page import="outils.AlertDialog"%>
<%@page import="outils.Outils"%>
<%@page import="bean.RefTypeGenre"%>
<%@page import="dao.CacheDAO"%>
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
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>

<script src="js/alertdialog.js"></script>

</head>
<body>
	<%
		Profil profil = (Profil) request.getSession()
		.getAttribute("profil");
	%>
	<%=AlertDialog.getAlertDialog(profil)%>
	<%@include file="menuGestionnaire.jsp"%>
	
<div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">		<div class="panel panel-default">
			<div class="panel-heading panel-heading-custom">
				<div class="panel-title"><a
							href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE %>'
							class='btn btnwayd btn-sm'> <span
							class="glyphicon glyphicon-home"></span></a> Mon compte</div>
			</div>
			<div style="padding-top: 30px" class="panel-body">

				<div style="border-bottom: 1px solid #888;">

					<p class="text-tuto"><%=CompteMembre.MESSAGE_JUMBO_L1%></p>

				</div>
				<br> <br>


				<div class="form-group">
			
						<%=profil.getMembre().getDetailEnteteMembreHtml()%>

							<form
								action="/waydplace/Frontal?action=<%=Frontal.CHARGE_PHOTO_PROFIL_MEMBRE%>"
								method="post" enctype="multipart/form-data"
								onsubmit="return valideFichier()">

								<br>
						
									<div class="btn-group">
										<label class="btn btn-default btn-file btn-primary btn-sm">
											.. <input name="file" size="50" type="file"
											style="display: none;">
										</label> <input type="submit" value="Envoyer la photo"
											class="btn btn-primary btn-sm  " /> <a
											href='/waydplace/Frontal?action=<%=Frontal.SUPPRIMER_PHOTO_MEMBRE%>'
											class='btn btn-danger btn-sm'> <span
											class="glyphicon glyphicon-remove"></span></a> <a
											href='/waydplace/Frontal?action=<%=Frontal.REDIRECTION_CHANGE_MOT_DE_PASSE_MEMBRE%>'
											class="btn btn-info btn-sm"> <span
											class="glyphicon glyphicon-lock"></span></a>

									</div>
							
							</form>


					
					

				</div>


				<form action="/waydplace/Frontal" method="post"
					onsubmit="return valideFormulaire()">

					<input name="action" type="hidden"
						value=<%=Frontal.MODIFIER_COMPTE_MEMBRE%>>

					<div class="form-group">
						<div class="row">
							<div class="col-sm-4">

								<label for="nom"><%=CompteMembre.LABEL_NOM%></label> <input
									type="text" class="form-control" id="nom"
									placeholder="<%=CompteMembre.getHintNomSociete()%>"
									maxlength="<%=CompteMembre.TAILLE_PSEUDO_MAX%>" name="pseudo"
									required value="<%=profil.getPseudo()%>">
							</div>
							<div class="col-sm-4">

								<label for="datenaissance"><%=CompteMembre.DATE_NAISSANCE%></label>
								<div class='input-group date' id='datenaissance'>
									<input type='text' class="form-control" id="datenaissance"
										name="datenaissance" required /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>

							</div>
							<div class="col-sm-4">
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
					</div>


					<div class="form-group"></div>

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
	</div>
</body>

	<script>
				$('#datenaissance').datetimepicker({
				defaultDate : new Date(<%=profil.getDateNaissance().getYear()%>,<%=profil.getDateNaissance().getMonthOfYear()-1%>,<%=profil.getDateNaissance().getDayOfMonth()%>),
				format : 'DD/MM/YYYY'

			});

			
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
			defaultDate : new Date(2010, 10, 10),
			format : 'DD/MM/YYYY'

		}).on('dp.change', function(e) {
			document.getElementById("formulaire").submit();
		});

		$(document)
				.ready(
						function(e) {

							$('#description')
									.keyup(
											function() {

												var nombreCaractere = $(this)
														.val().length;
												//alert(nombreCaractere);

												var msg = nombreCaractere
														+ '/'
														+
	<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
		;
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

</html>
