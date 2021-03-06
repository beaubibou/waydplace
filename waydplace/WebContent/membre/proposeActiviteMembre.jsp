<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<title><%=ProposeActiviteMembre.TITRE_ONGLET%></title>
<meta charset="utf-8">
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
		ArrayList<RefTypeActivite> listTypeActivite = CacheDAO
			.getListRefTypeActivite();
			// Defini le li a rendre actif

			String titre = Outils.convertRequeteToString(request
			.getParameter("titre"));

			String description = Outils.convertRequeteToString(request
			.getParameter("description"));

			String messageAlert = (String) request.getAttribute("messageAlert");
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

						<div class="brand-name-wrapper">
							<a class="navbar-brand" href="#">Proposer </a>
							<a  href="<%=Frontal.ACTION_REDIRECTION_ACCEUIL %>" class="btn btn-default"
							id="search-trigger"> <span class="glyphicon glyphicon-home"></span>
						</a>
							
						</div>
						
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
	<div style="padding-top: 30px;" class="container-fluid">
		<div class="side-body">
			<form action="/waydplace/Frontal" 
				onsubmit="return valideFormulaire()" method="post" id='formulaire'>

				<input type='hidden' name='action'
					value='<%=Frontal.AJOUTER_ACTIVITE_MEMBRE%>'>

				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">

						<label for="titre"><%=ProposeActiviteMembre.LABEL_TITRE%></label>
						<input type="text" class="form-control" id="titre" required
							placeholder="<%=ProposeActiviteMembre.getHintTitreActivite()%>"
							maxLength="<%=ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX%>"
							name="titre" required value='<%=titre%>'>

					</div>
				</div>
				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-3 col-md-offset-3 ">
						<label for="iddatedebut"><%=ProposeActiviteMembre.LABEL_DATE_DEBUT%></label>
						<div class='input-group date' id='datedebut'>
							<input style="background-color:white;" readonly type='text' class="form-control " id="iddatedebut" required
								name="debut" /> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>

					</div>

					<div class="col-xs-12 col-xs-offset-0 col-md-3 col-md-offset-0 ">

						<label for="iddatefin"><%=ProposeActiviteMembre.LABEL_DATE_FIN%></label>
						<div class='input-group date' id="datefin">
							<input type='text' style="background-color:white;" readonly class="form-control" id="iddatefin" required
								name="fin" /> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>

				</div>


				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-3 col-md-offset-3 ">
						<label for="typeactivite"><%=ProposeActiviteMembre.LABEL_TYPE_ACTIVITE%></label>
						<select class="form-control" id="type" name="typeactivite">
							<%
								for (RefTypeActivite typeactivite : listTypeActivite) {
							%>
							<option value="<%=typeactivite.getId()%>"><%=typeactivite.getLibelle()%></option>
							<%
								}
								%>
						</select>

					</div>
				</div>


				<div class="form-group row">

					<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">
						<label for="description"><%=ProposeActiviteMembre.LABEL_DESCRIPTION_ACTIVITE%></label>
						<textarea
							placeholder="<%=ProposeActiviteMembre.getHintDescriptionActivite()%>"
							maxlength="<%=ProposeActiviteMembre.TAILLE_DESCRIPTION_ACTIVITE_MAX%>"
							class="form-control" rows="5" id="description" name="description"></textarea>

					</div>
				</div>


			</form>
	
			<div class="col-xs-4 col-xs-offset-8 col-md-2 col-md-offset-8 ">

				<h5 class="nbrcaracteremax" id="nbr">
					0 Caractére
					<%=CompteMembre.TAILLE_DESCRIPTION_PROFIL_MAX%>
				</h5>

			</div>

			<div class="form-group row">

				<div class="col-xs-4 col-xs-offset-0 col-md-6 col-md-offset-3 ">
					<button onclick="ajouteActivite()" class="btnwayd btn-lg">Proposer</button>
				</div>
			</div>
		</div>

	</div>


</body>

<script>
	
	function ajouteActivite(){
		
		if (valideFormulaire()==false)
			return;
		
		$.post($("#formulaire").attr("action"),$("#formulaire").serialize() ,
				function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
					if (responseText == 'ok')
					{
						BootstrapDialog.show({
				            title: 'Confirmation',
				            closable: false,
				            message: 'Votre activité a été ajoutée',
				            buttons: [{
				                label: 'Ok',
				                action: function(dialog) {
				                	
				               	dialog.close();
				               	document.getElementById("formulaire").reset(); 
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
	
		$(function() {

			$('#datedebut').datetimepicker({
				defaultDate : new Date,
				format : 'DD/MM/YYYY HH:mm',
				focusOnShow: false,
				  ignoreReadonly: true

			});

			var heure = new Date().getHours() + 3;

			$('#datefin').datetimepicker(
					{
						defaultDate : moment(new Date()).hours(heure)
								.minutes(0).seconds(0).milliseconds(0),
						format : 'DD/MM/YYYY HH:mm',
						focusOnShow: false,
						  ignoreReadonly: true

					});

		});
	
	</script>
<script>
	

		function valideFormulaire() {
			
			var description=document.getElementById("description").value;
			var titre=document.getElementById("titre").value;
			var datedebut = $('#datedebut').data('DateTimePicker').date();
			var datefin = $('#datefin').data('DateTimePicker').date();
	
			
			var isOk=valideActivite (description,titre,datedebut,datefin);
			
			if (isOk!='ok'){
				BootstrapDialog.alert(isOk);
				return false;
			}
			
		}

	
	</script>

<script>
		$(document).ready(function(e) {

			$('#description').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere + '<%=ProposeActiviteMembre.getNbrCarateresDescription()%>';

				$('#nbr').text(msg);
				// Le script qui devra calculer et afficher le nombre de mots et de caractères

			})

		});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg = nombreCaractere +'<%=ProposeActiviteMembre.getNbrCarateresDescription()%>';
	$('#nbr').text(msg);
</script>

</html>
