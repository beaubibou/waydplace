

<%@page import="servlet.membre.Frontal"%>
<%@page import="servlet.membre.FrontalCommun"%>
<%@page import="com.sun.accessibility.internal.resources.accessibility"%>
<%@page import="pager.PagerActivite"%>
<%@page import="critere.CritereTypeOrganisateur"%>
<%@page import="critere.CritereTypeActivite"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="dao.ActiviteDAO"%>
<%@page import="parametre.ActionPage"%>
<%@page import="critere.FiltreRecherche"%>
<%@page import="bean.Profil"%>
<%@page import="bean.Activite"%>
<%@page import="outils.Outils"%>
<%@page import="dao.CacheDAO"%>
<%@page import="critere.CritereEtatActivite"%>
<%@page import="text.pageweb.RechercheActiviteMembre"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Liste activités</title>
<meta charset="utf-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

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
</head>

<body>

	<%
		Profil profil = (Profil) request.getSession().getAttribute("profil");
			FiltreRecherche filtre=profil.getFiltre();
		
			ArrayList<CritereTypeActivite> listCritereTypeActivite=CacheDAO.getListCrtitereTypeActivite();
			ArrayList<CritereEtatActivite> listCritereEtatActivite=CacheDAO.getListCritereEtatActivite();
			ArrayList<CritereTypeOrganisateur> listCritereTypeOrganisateur=CacheDAO.getListCritereTypeOrganisateurs();
		
			PagerActivite pager=(PagerActivite) request.getAttribute("pager");
			ArrayList<Activite> listActivite = pager.getListActivite();
	%>

	<%@ include file="menuMembre.jsp"%>

	<div class="container margedebut ">
		<div class="panel barrerecherche">
			<div class="panel-heading">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-1">
							<a
								href='/waydplace/Frontal?action=<%=Frontal.REDIRECTION_ACCUEIL_MEMBRE%>'
								class='btn btnwayd btn-md'> <span
								class="glyphicon glyphicon-home"></span></a>

						</div>
						<div class="col-sm-11">
							<p class="text-tuto"><%=RechercheActiviteMembre.TUTO_LIGNE1%></p>


						</div>

					</div>
				</div>


				<form class="form-inline" id="formulaire" method="post"
					action="/waydplace/Frontal">

					<input type="hidden" name='action'
						value='<%=Frontal.REFRESH_RECHERCHE_ACTIVITE_MEMBRES%>'>



					<div class="form-group">
						<label for="typeUser">Type</label> <select
							data-style="btn-primary" class="form-control" id="typeUser"
							name="typeUser">

							<%
								for (CritereTypeOrganisateur critereOrganisteur:listCritereTypeOrganisateur) {
							%>
							<option value="<%=critereOrganisteur.getId()%>"
								<%=Outils.jspAdapterListSelected(critereOrganisteur.getId(), filtre.getCritereTypeorganisateur())%>>
								<%=critereOrganisteur.getLibelle()%></option>
							<%
								}
							%>

						</select>
					</div>


					<div class="form-group">
						<label for="typeactivite">Cat.</label> <select
							data-style="btn-primary" class="form-control" id="typeactivite"
							name="typeactivite">

							<%
								for (CritereTypeActivite critereTypeActivite:listCritereTypeActivite) {
							%>
							<option value="<%=critereTypeActivite.getId()%>"
								<%=Outils.jspAdapterListSelected(critereTypeActivite.getId(), filtre.getCritereTypeActivite())%>>
								<%=critereTypeActivite.getLibelle()%></option>
							<%
								}
							%>

						</select>
					</div>




					<div class="form-group">
						<label for="etatActivite">Etat</label> <select
							data-style="btn-primary" class="form-control" id="etatActivite"
							name="etatActivite">

							<%
								for (CritereEtatActivite critereEtatActivite:listCritereEtatActivite) {
							%>
							<option value="<%=critereEtatActivite.getId()%>"
								<%=Outils.jspAdapterListSelected(critereEtatActivite.getId(), filtre.getCritereRechercheEtatActivite())%>>
								<%=critereEtatActivite.getLibelle()%></option>
							<%
								}
							%>

						</select>
					</div>

					<div class="form-group">
						<label for="iddatedebut">Début</label>
						<div class='input-group date' id='datedebut'>
							<input type='text' class="form-control" id="iddatedebut"
								name="debut" /> <span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>



					<div class="form-group">
						<label for="iddatefin">Fin</label>
						<div class='input-group date' id="datefin">
							<input type='text' class="form-control" id="iddatefin" name="fin" />
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>

					<div class="form-group">

						<button id="go" type="submit" class="btn btn-info"
							name="rechercheactivite">
							<span class="glyphicon glyphicon-search"></span>
						</button>

					</div>
				</form>

			</div>

		</div>
<%if (listActivite!=null && !listActivite.isEmpty()){ %>
		

		<table class="table table-striped  table-responsive  "
			style='border: 5px solid #fff;' id="matable">

			<thead>
				<tr>
					<th style="width: 80%;" class="text-center"></th>
					<th style="width: 20%;" class="text-center"></th>

				</tr>
				
			</thead>
		
			<tbody style="vertical-align: middle;">
				<%
					if (listActivite!=null )
									for (Activite activite : listActivite) {
													String lienDetailActivite =  "/waydplace/FrontalCommun?action="+FrontalCommun.REDIRECTION_DETAIL_ACTIVITE+"&idactivite=" +activite.getId()+"&idmembre=" +activite.getUid_membre();
													String lienDetailParticipant = "/waydplace/FrontalCommun?action="+FrontalCommun.REDIRECTION_DETAIL_PARTICIPANT+"&idmembre=" +activite.getUid_membre();
				%>

				<tr onclick="document.location='<%=lienDetailActivite%>'">
					<td><%=activite.getAdpaterListHtml()%></td>
					<td><%=activite.getPanelActionParticipationHtml(profil,activite.getUid_membre())%>

					</td>


				</tr>

			<%} %>

			</tbody>
		</table>
		<%} else{ %>
				<div class="jumbotron">
  <h1>Pas de résultats</h1>      
  <h3>Aucune de vos activités ne corresponde à vos critéres. N'hésitez pas à en <a href='<%=Frontal.ACTION_REDIRECTION_PROPOSER %>'>proposer.</a></h3>
</div>




		<%} %>
		
	</div>



	<ul class="pager">

		<li <%=pager.isPreviousHtml()%>><a
			href="<%=pager.getLienPrevioustHtml(profil)%>">Previous</a></li>
		<li>Page N° <%=pager.getPageEnCours()%></li>
		<li <%=pager.isNextHtml()%>><a
			href="<%=pager.getLienNextHtml(profil)%>">Next</a></li>


	</ul>


	<script>
		$(function() {

			$('select').change(function() {

				document.getElementById("formulaire").submit();
			});

			$('#datedebut')
					.datetimepicker(
							{
								defaultDate : new Date(
	<%=filtre.getCritereDateDebutCreation().getYear()%>
		,
	<%=filtre.getCritereDateDebutCreation().getMonthOfYear()-1%>
		,
	<%=filtre.getCritereDateDebutCreation().getDayOfMonth()%>
		),
								format : 'DD/MM/YYYY'

							}).on('dp.change', function(e) {
						document.getElementById("formulaire").submit();
					});

			$('#datefin')
					.datetimepicker(
							{
								defaultDate : new Date(
	<%=filtre.getCritereDateFinCreation().getYear()%>
		,
	<%=filtre.getCritereDateFinCreation().getMonthOfYear()-1%>
		,
	<%=filtre.getCritereDateFinCreation().getDayOfMonth()%>
		),
								format : 'DD/MM/YYYY'

							}).on('dp.change', function(e) {
						document.getElementById("formulaire").submit();
					});

		});
	</script>
</body>

</html>