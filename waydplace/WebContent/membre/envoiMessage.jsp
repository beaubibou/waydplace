
<%@page import="servlet.membre.Frontal"%>
<%@page import="text.pageweb.EnvoiMessage"%>
<%@page import="outils.Outils"%>
<%@page import="bean.Activite"%>
<%@page import="parametre.ActionPage"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<title>><%=EnvoiMessage.TITRE_ONGLET%></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<meta charset="utf-8">
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
<link href="/wayd/css/styleWaydAdmin.css" rel="stylesheet"
	type="text/css">
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
		
	String  uidEmetteur=(String) request.getAttribute("uid_emetteur");
	String uidDestinataire =(String) request.getAttribute("uid_destinataire");
	Integer  idactivite=(Integer) request.getAttribute("idactivite");
	
	
	
	%>


	<%@ include file="menuMembre.jsp"%>

	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><a href='<%=Frontal.ACTION_REDIRECTION_RECHERCHE_ACTIVITE_MEMBRE%>'
											class='btn btnwayd btn-sm'> <span
											class="glyphicon glyphicon-arrow-left"></span></a> <%=EnvoiMessage.TITRE_PANEL %></div>
				</div>

				<div style="padding-top: 30px" class="panel-body">


					<form action="/waydplace/Frontal"
						onsubmit="return valideFormulaire()" method="post">

						<input name="action" type="hidden" Value=<%=Frontal.ENVOI_MESSAGE_MEMBRE%>> 
						
						<input name="uid_destinataire" type="hidden"	value=<%=uidDestinataire%>> 
						<input name="uid_emetteur" type="hidden"value=<%=uidEmetteur%>>
						<input name="idactivite" type="hidden"	value=<%=idactivite.toString()%>>
							

						<div class="form-group" style="border-bottom: 1px solid #888;">

							<p class="text-tuto"><%=EnvoiMessage.MESSAGE_JUMBO_LIGNE1%></p>
							

						</div>
						<br>




						<div class="form-group">
							<label for="message"><%=EnvoiMessage.LABEL_DESCRIPTION_MESSAGE%></label>
							<textarea 
								placeholder="<%=EnvoiMessage.HINT_DESCRIPTION_MESSAGE%>"
								maxlength="<%=EnvoiMessage.TAILLE_DESCRIPTION_MESSAGE%>"
								class="form-control" rows="5" id="message" name="message"></textarea>
						</div>
						<h5 class="nbrcaracteremax" id="nbr">
							<%=EnvoiMessage.initNbrCaracteres()%></h5>
						<button type="submit" class="btnwayd btn-lg">Envoyer</button>


					</form>


				</div>
			</div>
		</div>


	</div>





	<script>
		
	
	

	function valideFormulaire() {
		
		return true;
	
	}
	
		$(document).ready(function(e) {

			$('#message').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere + '<%=EnvoiMessage.getNbrCarateresDescription()%>';

				$('#nbr').text(msg);
				// Le script qui devra calculer et afficher le nombre de mots et de caractères

			})

		});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg = nombreCaractere +   '<%=EnvoiMessage.getNbrCarateresDescription()%>';
		$('#nbr').text(msg);
	</script>
</body>
</html>
