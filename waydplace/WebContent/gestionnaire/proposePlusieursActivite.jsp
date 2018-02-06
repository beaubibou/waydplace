
<!DOCTYPE html>

<%@page import="critere.CritereDuree"%>
<%@page import="text.pageweb.ProposePlusieursActivite"%>
<%@page import="text.pageweb.ProposeActiviteMembre"%>
<html lang="fr">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>

<head>
<title><%=ProposePlusieursActivite.TITRE_ONGLET%></title>

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

<link href="/waydplace/css/styleWaydGestionnaire.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet" media="all"
	type="text/css">
</head>



<body>


<% 	ArrayList<RefTypeActivite> listTypeActivite=CacheDAO.getListRefTypeActivite(); %>
	
	<%@ include file="menuGestionnaire.jsp"%>


	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><a
							href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE %>'
							class='btn btnwayd btn-sm'> <span
							class="glyphicon glyphicon-home"></span></a> <%=ProposePlusieursActivite.TITRE_PANEL%></div>
				</div>

				<div style="padding-top: 30px" class="panel-body">


					<form action="/waydplace/FrontalGestionnaire"
						onsubmit="return valideFormulaire()" method="post" id='formulaire'>

			
						<div class="form-group" style="border-bottom: 1px solid #888;">

							<p class="text-tuto"><%=ProposePlusieursActivite.MESSAGE_JUMBO_LIGNE1%></p>
							<p class="text-tuto"><%=ProposePlusieursActivite.MESSAGE_JUMBO_LIGNE2%></p>

						</div>
						<br>
						<div class="form-group">
							<label for="titre"><%=ProposePlusieursActivite.LABEL_TITRE%></label>
							<input type="text" class="form-control" id="titre" required
								placeholder="<%=ProposePlusieursActivite.getHintTitreActivite()%>"
								maxLength="<%=ProposePlusieursActivite.TAILLE_TITRE_ACTIVITE_MAX%>"
								name="titre" required>
						</div>
						<div class="form-	group">
							<div class="row">

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="iddatedebut"><%=ProposePlusieursActivite.LABEL_DATE_DEBUT%></label>
										<div class='input-group date' id='datedebut'>
											<input type='text' class="form-control" id="iddatedebut"
												name="debut" /> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="iddatefin"><%=ProposePlusieursActivite.LABEL_DATE_FIN%></label>
										<div class='input-group date' id="datefin">
											<input type='text' class="form-control" id="iddatefin"
												name="fin" /> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class='col-sm-4'>
									<label for="typeactivite"><%=ProposePlusieursActivite.LABEL_TYPE_ACTIVITE%></label>
									<select class="form-control" id="type" name="typeactivite">
										<%
											for (RefTypeActivite typeactivite:listTypeActivite) {
										%>
										<option value="<%=typeactivite.getId()%>"><%=typeactivite.getLibelle()%></option>
										<%
											}
										%>
									</select>

								</div>
							</div>

						</div>
						<div class="form-group">
							<div class="row">

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="idheuredebut"><%=ProposePlusieursActivite.LABEL_HEURE_DEBUT%></label>
										<div class='input-group date' id='heuredebut'>
											<input type='text' class="form-control" id="idheuredebut"
												name="heuredebut" /> <span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>

								<div class='col-sm-4'>
									<div class="form-group">

										<label for="duree"><%=ProposePlusieursActivite.LABEL_DUREE%></label>
										<select class="form-control" id="typeactivite" name="duree">
											<%
												for (CritereDuree criteeDuree:CacheDAO.getListCritereDuree()) {
											%>
											<option value="<%=criteeDuree.getDureeMinutes()%>"><%=criteeDuree.getLibelle()%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>

							</div>


						</div>
						<div class="form-group">
							<div class="container" id="mescheck">

								<h4><%=ProposePlusieursActivite.MESSAGE_CHECKBOX%></h4>
								<br> <label class="radio-inline"> <input
									type="checkbox" name="lundi">Lundi
								</label> <label class="radio-inline"> <input type="checkbox"
									name="mardi">Mardi
								</label> <label class="radio-inline"> <input type="checkbox"
									name="mercredi">Mercredi
								</label> <label class="radio-inline"> <input type="checkbox"
									name="jeudi">Jeudi
								</label> <label class="radio-inline"> <input type="checkbox"
									name="vendredi">Vendredi
								</label> <label class="radio-inline"> <input type="checkbox"
									name="samedi">Samedi
								</label> <label class="radio-inline"> <input type="checkbox"
									name="dimanche">Dimanche
								</label>


							</div>
						</div>
					
						<div class="form-group">
							<label for="description"><%=ProposePlusieursActivite.LABEL_DESCRIPTION_ACTIVITE%></label>
							<textarea
								placeholder="<%=ProposePlusieursActivite.getHintDescriptionActivite()%>"
								maxlength="<%=ProposePlusieursActivite.TAILLE_DESCRIPTION_ACTIVITE_MAX%>"
								class="form-control" rows="5" id="description"
								name="description"></textarea>
						</div>
						<h5 class="nbrcaracteremax" id="nbr">

							<%=ProposePlusieursActivite.initNbrCaracteres()%></h5>
							<input type='hidden' name='action' value='<%=FrontalGestionnaire.AJOUTER_PLUSIEURS_ACTIVITE_GESTIONNAIRE%>'>
							
					
					</form>

			<button  onclick="ajouteActivite()" class="btnwayd btn-lg">Proposer</button>
			
				</div>
			</div>
		</div>


	</div>
	

	
	<script>
		$(function() {

			$('#datedebut').datetimepicker(
					{
						defaultDate : moment(new Date()).hours(0).minutes(0)
								.seconds(0).milliseconds(0),
						format : 'DD/MM/YYYY'

					});

			$('#datefin').datetimepicker(
					{
						defaultDate : moment(new Date()).hours(0).minutes(0)
								.seconds(0).milliseconds(0),
						format : 'DD/MM/YYYY'

					});

			$('#heuredebut').datetimepicker({
				defaultDate : new Date,
				format : 'HH:mm'

			});

			$('#heurefin').datetimepicker({
				defaultDate : new Date,
				format : 'HH:mm'

			});

		});
	</script>
	<script>
		
		function valideFormulaire() {

		
		var datedebut = $('#datedebut').data('DateTimePicker').date();
		var datefin = $('#datefin').data('DateTimePicker').date();

		
		// Verifie les positions
		latitude = document.getElementById("latitude").value;
		longitude = document.getElementById("longitude").value;

		

	

		if (datedebut > datefin) {
		

 			BootstrapDialog.show({
<%-- 				message:"<%=Erreur_HTML.DATEDEBUT_SUP_DATEFIN%>" --%>
 						});

			
			return false;
		}
		var aujourdui=new Date();
		aujourdui.setHours(0, 0, 0, 0);
// 		if (datefin < aujourdui) {
			
// 			BootstrapDialog.show({
<%-- 				message:"<%=Erreur_HTML.DATEFIN_INF_NOW%>" --%>
// 						});
// 			return false;
// 		}

		

		var nbrCheck = getNbrJourCheck();

// 		if (nbrCheck == 0) {
// 			BootstrapDialog.show({
<%-- 	//	message:"<%=Erreur_HTML.AU_MOINS_UN_JOUR_SELECTIONNE%>" --%>
// 				});
// 			return false;
// 		}
		
		
		
	
		return true;
		
		}

		function getNbrJourCheck() {

			var nbrLigne = 0;
			$('#mescheck label ').each(function() {
				var checkBox = $(this).find('input:checkbox');
				if (checkBox.is(":checked")) {

					nbrLigne++;

				}

			});

			return nbrLigne;
		}

		function initPosition() {

			latitude = 0;
			longitude = 0;
			document.getElementById("latitude").value = 0;
			longitude = document.getElementById("longitude").value = 0;

		}
	</script>

	<script>
		$(document).ready(function(e) {

			$('#description').keyup(function() {

				var nombreCaractere = $(this).val().length;
				//alert(nombreCaractere);

				var msg = nombreCaractere + '<%=ProposePlusieursActivite.getNbrCarateresDescription()%>';

				$('#nbr').text(msg);
				// Le script qui devra calculer et afficher le nombre de mots et de caractères

			})

		});

		// Init le nombre de caraterces	
		var nombreCaractere = $('#description').val().length;
		var msg =nombreCaractere + "<%=ProposePlusieursActivite.getNbrCarateresDescription()%>";

		$('#nbr').text(msg);

		</script>
	<script type="text/javascript">
		
	function ajouteActivite(){
		
		//if (valideFormulaire()==false)
		//	return;
		
		$.get("/waydplace/FrontalGestionnaire?"+$("#formulaire").serialize() ,
				function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
					if (responseText == 'ok')
					{
						BootstrapDialog.show({
				            title: 'Confirmation',
				            message: 'Votre activité a été ajoutée',
				            buttons: [{
				                label: 'Ok',
				                action: function(dialog) {
				                location.href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE%>'
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