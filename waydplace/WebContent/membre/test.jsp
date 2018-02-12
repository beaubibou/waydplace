
<%@page import="outils.Outils"%>
<%@page import="servlet.membre.Frontal"%>
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

<meta charset="utf-8">
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
	<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/slide.css" rel="stylesheet" type="text/css">

	
	<script src="/waydplace/js/slide.js"></script>
	
</head>
<body>

<%

Profil profil = (Profil) request.getSession().getAttribute("profil");
ArrayList<RefTypeActivite> listTypeActivite=CacheDAO.getListRefTypeActivite();
// Defini le li a rendre actif

String titre = Outils.convertRequeteToString(request
	.getParameter("titre"));

String description = Outils.convertRequeteToString(request
	.getParameter("description"));


String messageAlert = (String)request.getAttribute("messageAlert");	



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
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Brand -->
            <div class="brand-name-wrapper">
                <a class="navbar-brand" href="#">
                    Brand
                </a>
            </div>

            <!-- Search -->
            <a data-toggle="collapse" href="#search" class="btn btn-default" id="search-trigger">
                <span class="glyphicon glyphicon-search"></span>
            </a>

            <!-- Search body -->
            <div id="search" class="panel-collapse collapse">
                <div class="panel-body">
                    <form class="navbar-form" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-ok"></span></button>
                    </form>
                </div>
            </div>
        </div>

    </div>

    <!-- Main Menu -->
    <div class="side-menu-container">
        <ul class="nav navbar-nav">

<%@ include file="menuMembreTest.jsp"%>

         

        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
    
    </div>

    <!-- Main Content -->
    <div class="container-fluid">
        <div class="side-body">
       
       <div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">

			<div class="panel panel-default">
						<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><a href='/waydplace/Frontal?action=<%=Frontal.REDIRECTION_ACCUEIL_MEMBRE%>'
											class='btn btnwayd btn-sm'> <span
											class="glyphicon glyphicon-home"></span></a> <%=ProposeActiviteMembre.TITRE_PANEL%></div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
				
				
					<form action="/waydplace/Frontal"
						onsubmit="return valideFormulaire()" method="post" id='formulaire'>

						<div class="form-group"   style="border-bottom: 1px solid #888;">

							<p class="text-tuto"><%=ProposeActiviteMembre.MESSAGE_JUMBO_LIGNE1%></p>
						
						</div>
					<br>	

						<div class="form-group">
							<label for="titre"><%=ProposeActiviteMembre.LABEL_TITRE%></label>
							<input type="text" class="form-control" id="titre" required
								placeholder="<%=ProposeActiviteMembre.getHintTitreActivite()%>"
								maxLength="<%=ProposeActiviteMembre.TAILLE_TITRE_ACTIVITE_MAX%>"
								name="titre" required value='<%=titre%>'>
						</div>


						<div class="form-group">
							<div class="row">

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="iddatedebut"><%=ProposeActiviteMembre.LABEL_DATE_DEBUT%></label>
										<div class='input-group date' id='datedebut'>
											<input type='text' class="form-control" id="iddatedebut" required
												name="debut" /> <span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>

								<div class='col-sm-4'>
									<div class="form-group">
										<label for="iddatefin"><%=ProposeActiviteMembre.LABEL_DATE_FIN%></label>
										<div class='input-group date' id="datefin">
											<input type='text' class="form-control" id="iddatefin" required
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
							<textarea
								placeholder="<%=ProposeActiviteMembre.getHintDescriptionActivite()%>"
								maxlength="<%=ProposeActiviteMembre.TAILLE_DESCRIPTION_ACTIVITE_MAX%>"
								class="form-control" rows="5" id="description"
								name="description" value='<%=description%>'></textarea>
						</div>
						<h5 class="nbrcaracteremax" id="nbr">

							<%=ProposeActiviteMembre.initNbrCaracteres()%></h5>
							
						<input type='hidden' name='action' value='<%=Frontal.AJOUTER_ACTIVITE_MEMBRE%>'>

					
						
						
						
					</form>
					
					
					<button  onclick="ajouteActivite()" class="btnwayd btn-lg">Proposer</button>
					
						<a href='/waydplace/Frontal?action=<%=Frontal.REDIRECTION_ACCUEIL_MEMBRE%>'
											class='btn btnwayd btn-lg'> <span
											class="glyphicon glyphicon-home"></span></a> 
						
				</div>
			</div>
		</div>


	</div>

	

	
	<script>
	
	function ajouteActivite(){
		
		if (valideFormulaire()==false)
			return;
		
		$.get("/waydplace/Frontal?"+$("#formulaire").serialize() ,
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
				format : 'DD/MM/YYYY HH:mm'

			});

			var heure = new Date().getHours() + 3;

			$('#datefin').datetimepicker(
					{
						defaultDate : moment(new Date()).hours(heure)
								.minutes(0).seconds(0).milliseconds(0),
						format : 'DD/MM/YYYY HH:mm'

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
		var msg = nombreCaractere +   '<%=ProposeActiviteMembre.getNbrCarateresDescription()%>';
		$('#nbr').text(msg);
	</script>         
        </div>
    </div>
</div>
</body>
</html>
