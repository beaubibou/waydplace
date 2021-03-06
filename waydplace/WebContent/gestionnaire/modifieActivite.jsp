
<%@page import="outils.Outils"%>
<%@page import="bean.Activite"%>
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

<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
	
	
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
	<script src="/waydplace/js/valideform.js"></script>
</head>
<body>
	<%
		Activite activite=(Activite) request.getAttribute("activite");
		ArrayList<RefTypeActivite> listTypeActivite=CacheDAO.getListRefTypeActivite();
			// Defini le li a rendre actif
	%>

<%@ include file="menuGestionnaire.jsp"%>

	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">
			<div class="panel panel-default">
		
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><a href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE%>'
											class='btn btnwayd btn-sm'> <span
											class="glyphicon glyphicon-arrow-left"></span></a> Modifier</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">


					<form action="/waydplace/FrontalGestionnaire"
						onsubmit="return valideFormulaire()"  id="formulaire">
		
				<input  name="action" type="hidden"  value=<%=FrontalGestionnaire.MODIFIER_ACTIVITE_GESTIONNAIRE %> >
				<input  name="idactivite" type="hidden"  value=<%=activite.getId() %> >
		

						<div class="form-group" style="border-bottom: 1px solid #888;">

							<p class="text-tuto"><%=ProposeActiviteMembre.MESSAGE_JUMBO_LIGNE1%></p>
							<p class="text-tuto"><%=ProposeActiviteMembre.MESSAGE_JUMBO_LIGNE2%></p>
							<p class="text-tuto"><%=ProposeActiviteMembre.MESSAGE_JUMBO_LIGNE3%></p>

						</div>
						<br>

						<div class="form-group">

							<label for="titre"><%=ProposeActiviteMembre.LABEL_TITRE%></label>
							<input type="text" class="form-control" id="titre" required
								placeholder="<%=ProposeActiviteMembre.getHintTitreActivite()%>"
								maxLength="<%=ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX%>"
								name="titre" required value="<%=activite.getTitre()%>">
						</div>


						<div class="form-group">
							<div class="row">

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="iddatedebut"><%=ProposeActiviteMembre.LABEL_DATE_DEBUT%></label>
										<div class='input-group date' id='datedebut'>
											<input readonly style="background-color:white;" type='text' class="form-control" id="iddatedebut"
												name="debut" /> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="iddatefin"><%=ProposeActiviteMembre.LABEL_DATE_FIN%></label>
										<div  class='input-group date' id="datefin">
											<input readonly style="background-color:white;" type='text' class="form-control" id="iddatefin"
												name="fin" /> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
								<div class='col-sm-4'>
									<label for="typeactivite"><%=ProposeActiviteMembre.LABEL_TYPE_ACTIVITE%></label>
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
							<label for="description"><%=ProposeActiviteMembre.LABEL_DESCRIPTION_ACTIVITE%></label>
							<textarea row='12'
								placeholder="<%=ProposeActiviteMembre.getHintDescriptionActivite()%>"
								maxlength="<%=ProposeActiviteMembre.TAILLE_DESCRIPTION_ACTIVITE_MAX%>"
								class="form-control" rows="5" id="description"
								name="description"><%=activite.getLibelle()%></textarea>
						</div>
						<h5 class="nbrcaracteremax" id="nbr">

							<%=ProposeActiviteMembre.initNbrCaracteres()%></h5>

					</form>

		<button  onclick="modifieActivite()" class="btnwayd btn-lg">Modifier</button>
			

				</div>
			</div>
		</div>
	</div>
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
	
	<script type="text/javascript">
	
	function modifieActivite(){
		
		if (valideFormulaire()==false)
			return;
		
		
		$.post($("#formulaire").attr("action"),$("#formulaire").serialize() ,
				function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...

					if (responseText == 'ok')
					{
						BootstrapDialog.show({
				            title: 'Confirmation',
				            closable: false,
				            message: 'Votre activité a été modfiée',
				            buttons: [{
				                label: 'Ok',
				                action: function(dialog) {
				                dialog.close();
				                location.href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE%>'
				                  //  dialog.setMessage('Message 1');
				                }
				            
				            }]
				        }); 
						
						
					}
					else{
						
						BootstrapDialog.alert(responseText);
					}

					

				});	
		
		
		
	}
	
	
	</script>

	<script>
	

	$(function() {

		var dateDebut =<%=Outils.getDateHtml(activite.getDatedebut())%>;
		var dateFin =<%=Outils.getDateHtml(activite.getDatefin())%>;
		$('#datedebut').datetimepicker({
			defaultDate : dateDebut,
			format : 'DD/MM/YYYY HH:mm'

		});

		$('#datefin').datetimepicker({
			defaultDate : dateFin,
			format : 'DD/MM/YYYY HH:mm'

		});

	});
	
	
	
	
	
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
		var msg = nombreCaractere +   '<%=ProposeActiviteMembre.getNbrCarateresDescription()%>';
		$('#nbr').text(msg);
	</script>
</body>
</html>
