

<%@page import="java.util.Date"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="servlet.membre.FrontalCommun"%>
<%@page import="outils.AlertDialog"%>
<%@page import="dao.ActiviteDAO"%>
<%@page import="parametre.ActionPage"%>
<%@page import="critere.FiltreRecherche"%>
<%@page import="bean.Profil"%>
<%@page import="bean.Activite"%>
<%@page import="outils.Outils"%>
<%@page import="dao.CacheDAO"%>
<%@page import="critere.CritereEtatActivite"%>
<%@page import="text.pageweb.MesActivites"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Mes activités</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


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
	type="text/css">


</head>

<body ">

	<%
		Profil profil = (Profil) request.getSession().getAttribute("profil");
			FiltreRecherche filtre=profil.getFiltre();
			ArrayList<Activite> listMesActivite=ActiviteDAO.getMesActivite(profil.getUID(), filtre.getCritereRechercheEtatMesActivite());
			String afficheMessage=(String)request.getAttribute("alertMessage");
	%>


	<%@ include file="menuMembre.jsp"%>


	<div class="container margedebut ">
		<div class="panel barrerecherche">
			<div class="panel-heading">
					<div class="form-group">
					
				<div class="row">
					<div class="col-sm-1">
								<a href='/waydplace/Frontal?action=<%=Frontal.REDIRECTION_ACCUEIL_MEMBRE%>'
											class='btn btn-info btn-md'> <span
											class="glyphicon glyphicon-home"></span></a> 
						
					</div>
					<div class="col-sm-11">
						<p class="text-tuto"><%=MesActivites.TUTO_LIGNE1%></p>
							
						
					</div>
				
				</div>
				</div>

				<div class="row">
					<div class="col-sm-3">
						<form method="post" action="/waydplace/Frontal" id="formulaire"
							class="form-inline">
							<div class="form-group">
								<label for="idEtatActivite">Status:</label> <select
									class="form-control" id="idEtatActivite"
									name="critereEtatMesActivite">

									<%
										for (CritereEtatActivite etatActivite:CacheDAO.getListCritereEtatActivite()) {
									%>
									<option value="<%=etatActivite.getId()%>"
										<%=Outils.jspAdapterListSelected(etatActivite.getId(), filtre.getCritereRechercheEtatMesActivite())%>>
										<%=etatActivite.getLibelle()%></option>
									<%
										}
									%>

								</select>

							</div>
							
					
							<input type="hidden" name='action'
								value='<%=Frontal.REFRESH_MES_ACTIVITE_MEMBRES%>'>
						</form>
					</div>

				</div>


			</div>

		</div>
			<%if (listMesActivite!=null && !listMesActivite.isEmpty()){ %>
		
		<table class="table table-striped table-responsive "
			style='border: 5px solid #fff;' id="matable">

			<thead>
				<tr>
					<th style="width: 80%;" class="text-center"></th>
					<th style="width: 20%;" class="text-center"></th>

				</tr>
				
			</thead>
		

			<tbody style="background-color: #FFFFFF; vertical-align: middle;">

				<%
					if (listMesActivite!=null)
													for (Activite activite : listMesActivite)
													{
										String lienDetailActivite =  "/waydplace/FrontalCommun?action="+FrontalCommun.REDIRECTION_DETAIL_ACTIVITE+"&idactivite=" +activite.getId()+"&idmembre=" +activite.getUid_membre();
				%>

				<tr onclick="document.location='<%=lienDetailActivite%>'">

					<td><%=activite.getAdpaterListHtml()%></td>
					<td style="vertical-align: middle; text-align: center;"><%=activite.getPanelActionGestionHtmlMembre()%>

					</td>

				</tr>
				<%
					}
				%>
			</tbody>
		</table>
			<%} else{ %>
				<div class="jumbotron">
  <h1>Pas de résultats</h1>      
  <h3>Aucune de vos activités ne corresponde à vos critéres. N'hésitez pas à en <a href='<%=Frontal.ACTION_REDIRECTION_PROPOSER %>'>proposer.</a></h3>
</div>




		<%} %>
	</div>


	<script type="text/javascript">
		$('select').on('change', function() {

			document.getElementById("formulaire").submit();
		});
	</script>

	<script type="text/javascript">
	<!-- Permet d afficher un message dialog -->
		
		
		
		function getMessageDialog() {
			
			$.get("/waydplace/FrontalCommun?action=AJAX_GET_MESSAGE_DIALOG",
					function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
						if (responseText == 'null')
							return;

						BootstrapDialog.alert(responseText);

					});
		}
		
	
		
		$( document ).ready(function() {
			getMessageDialog();
		});
	</script>





</body>
</html>
