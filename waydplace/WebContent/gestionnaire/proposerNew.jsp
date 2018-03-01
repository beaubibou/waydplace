
<%@page import="text.pageweb.ProposerNewText"%>
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


<meta name="viewport" content="width=device-width, initial-scale=1">
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

<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
</head>
<body>
	<%
		Profil profil = (Profil) request.getSession()
				.getAttribute("profil");
	%>


	<%@ include file="menuGestionnaire.jsp"%>

	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title">
						<a
							href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_NEWS_GESTIONNAIRE%>'
							class='btn btnwayd btn-sm'> <span
							class="glyphicon glyphicon-arrow-left"></span></a>
						<%=ProposerNewText.TITRE_PANEL%></div>
				</div>

				<div style="padding-top: 3" class="panel-body">


					<form action="/waydplace/FrontalGestionnaire"
						onsubmit="return valideFormulaire()" id='formulaire'>

						<div class="form-group" style="border-bottom: 1px solid #888;">

							<p class="text-tuto"><%=ProposerNewText.MESSAGE_JUMBO_LIGNE1%></p>
							<p class="text-tuto"><%=ProposerNewText.MESSAGE_JUMBO_LIGNE2%></p>
							<p class="text-tuto"><%=ProposerNewText.MESSAGE_JUMBO_LIGNE3%></p>

						</div>
						<br>

						<div class="form-group">
							<label for="titre"><%=ProposerNewText.LABEL_TITRE%></label>
							<input type="text" class="form-control" id="titre" required
								placeholder="<%=ProposeActiviteMembre.getHintTitreActivite()%>"
								maxLength="<%=ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX%>"
								name="titre" required>
						</div>


						<div class="form-group">
							<div class="row"></div>
						</div>



						<div class="form-group">
							<label for="description"><%=ProposerNewText.LABEL_DESCRIPTION_MESSAGE%></label>
							<textarea
								placeholder="<%=ProposerNewText.HINT_DESCRIPTION_MESSAGE%>"
								maxlength="<%=ProposerNewText.TAILLE_DESCRIPTION_NEWS_MAX%>"
								class="form-control" rows="5" id="description"
								name="message"></textarea>
						</div>
						<h5 class="nbrcaracteremax" id="nbr">

							<%=ProposerNewText.initNbrCaracteres()%></h5>

						<input type='hidden' name='action'
							value='<%=FrontalGestionnaire.AJOUTER_NEWS_GESTIONNAIRE%>'>



					</form>
					<button onclick="ajouteNew()" class="btnwayd btn-lg">Proposer</button>


				</div>
			</div>
		</div>


	</div>




	<script>
	
	
	
		function valideFormulaire() {
			
		
			return true;
		}

	
	</script>

	<script>
		$(document).ready(function(e) {

			$('#description').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere + '<%=ProposerNewText.getNbrCarateresDescription()%>';

				$('#nbr').text(msg);
				// Le script qui devra calculer et afficher le nombre de mots et de caractères

			})

		});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg = nombreCaractere +   '<%=ProposerNewText.getNbrCarateresDescription()%>';
		$('#nbr').text(msg);
	</script>
	<script type="text/javascript">
		
	function ajouteNew(){
		
		if (valideFormulaire()==false)
			return;
		
		$.post($("#formulaire").attr("action"),$("#formulaire").serialize() ,
				function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
					if (responseText == 'ok')
					{
						BootstrapDialog.show({
				            title: 'Confirmation',
				            closable: false,
				            message: 'Votre news a été ajoutée',
				            
				            buttons: [{
				                label: 'Ok',
				                action: function(dialog) {
				                	dialog.close();
				                location.href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE%>'
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
