

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
<%@page import="text.pageweb.MesActivites"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Liste activités</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="/wayd/css/styleWaydAdmin.css" rel="stylesheet"
	type="text/css">
	<script src="/waydplace/js/moment.js"></script>
	<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<link href="/waydplace/css/styleWayd.css" rel="stylesheet" type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet" media="all"
	type="text/css">
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

	<div class="container" style="width: 90%;margin-top: 120px">
		<div class="panel panel-primary">
			<div class="panel-body" style="background: #99ccff;">

				<form class="form-inline" id="formulaire" method="post"
					action="/waydplace/Frontal">

		<input type="hidden" name='action' value='<%=ActionPage.REFRESH_RECHERCHE_ACTIVITE_MEMBRES%>'>
	


					<div class="form-group">
						<label for="typeUser">Type</label>
						 <select
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
				<label for="iddatedebut">Date debut</label>
				<div class='input-group date' id='datedebut'>
					<input type='text' class="form-control" id="iddatedebut"
						name="debut" /> <span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>



			<div class="form-group">
				<label for="iddatefin">Date fin</label>
				<div class='input-group date' id="datefin">
					<input type='text' class="form-control" id="iddatefin" name="fin" />
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
			<button id="go" type="submit" class="btn btn-info"
				name="rechercheactivite">Rechercher</button>
		

		</form>
	
	</div>

</div>
</div>


	<div class="container" style="width: 90%;">

		<table class="table table-striped">
			<thead>
				<tr>
					<th style="width: 10%;" class="text-center">Etat</th>
					<th style="width: 5%;" class="text-center">Titre</th>
					<th style="width: 30%;" class="text-center">User</th>
					<th style="width: 10%;" class="text-center">Le</th>
				
				</tr>
			</thead>
			<tbody
				style="background-color: #FFFFFF; text-align: center; vertical-align: middle;">
				<%
					if (listActivite!=null)
																		for (Activite activite : listActivite) {
							String lien = "DetailActivite?idactivite=" + activite.getId()+"&from=listActivite.jsp";
					
							String lienDetailParticipant = "/waydplace/Frontal?action="+ActionPage.REDIRECTION_DETAIL_PARTICIPANT_MEMBRE+"&idmembre=" +activite.getUid_membre();	
							%>

				<tr>
					<td>

						<div class="clearfix">
							<img height="100" width="100" src=<%=activite.getUrlPhoto()%>
								class="img-thumbnail pull-left ">

							<p>
							<%=activite.getTypeUserLienHTML(lienDetailParticipant)%></p>
							<p><%=activite.getEtatHtml()%></p>
							

						</div>
					</td>

					<td><a href=<%=lien%>> <%=activite.getTitre()%></a></td>
					<td><a href=<%=lienDetailParticipant%>><%=activite.getPseudoOrganisateur()%></a></td>
					<td><%=activite.getHoraireLeA()%></td>
					<td><%=activite.getLibelle()%></td>
					<%=activite.getLienMessage(profil)%>
						
				</tr>

				<%
					}
				%>

			</tbody>
		</table>
	</div>



	<ul class="pager">

		<li <%=pager.isPreviousHtml()%>><a
			href="<%=pager.getLienPrevioustHtml()%>">Previous</a></li>
		<li>Page N° <%=pager.getPageEnCours()%></li>
		<li <%=pager.isNextHtml()%>><a
			href="<%=pager.getLienNextHtml()%>">Next</a></li>


	</ul>


	<script>
		$(function() {

			$('select').change(function() {

				document.getElementById("formulaire").submit();
			});

			
			$('#datedebut').datetimepicker({
				defaultDate : new Date(<%=filtre.getCritereDateDebutCreation().getYear()%>,<%=filtre.getCritereDateDebutCreation().getMonthOfYear()-1%>,<%=filtre.getCritereDateDebutCreation().getDayOfMonth()%>),
				format : 'DD/MM/YYYY'

			}).on('dp.change', function (e) {document.getElementById("formulaire").submit(); });

	
			$('#datefin').datetimepicker(
					{
						defaultDate : new Date(<%=filtre.getCritereDateFinCreation().getYear()%>,<%=filtre.getCritereDateFinCreation().getMonthOfYear()-1%>,<%=filtre.getCritereDateFinCreation().getDayOfMonth()%>,	),
			format : 'DD/MM/YYYY'

							}).on('dp.change', function (e) {document.getElementById("formulaire").submit(); });
		
		});
		
		
	
	</script>
</body>

</html>