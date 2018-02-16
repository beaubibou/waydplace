
<%@page import="bean.Profil"%>
<%@page import="pager.PagerActivite"%>
<%@page import="critere.CritereTypeActivite"%>
<%@page import="critere.CritereTypeOrganisateur"%>
<%@page import="critere.CritereEtatActivite"%>
<%@page import="servlet.membre.FrontalCommun"%>
<%@page import="text.pageweb.MesActivites"%>
<%@page import="bean.Activite"%>
<%@page import="pager.PagerMesActivites"%>
<%@page import="critere.FiltreRecherche"%>
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
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/slide.css" rel="stylesheet" type="text/css">


<script src="/waydplace/js/slide.js"></script>
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">

</head>


<%
	Profil profil = (Profil) request.getSession().getAttribute("profil");
FiltreRecherche filtre=profil.getFiltre();

ArrayList<CritereTypeActivite> listCritereTypeActivite=CacheDAO.getListCrtitereTypeActivite();
ArrayList<CritereEtatActivite> listCritereEtatActivite=CacheDAO.getListCritereEtatActivite();
ArrayList<CritereTypeOrganisateur> listCritereTypeOrganisateur=CacheDAO.getListCritereTypeOrganisateurs();

PagerActivite pager=(PagerActivite) request.getAttribute("pager");
ArrayList<Activite> listActivite = pager.getListActivite();
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
						<a class="navbar-brand" href="#"> Recherche </a>
					</div>

					<!-- Search -->
					<a data-toggle="collapse" href="#search" class="btn btn-default"
						id="search-trigger"> <span class="glyphicon glyphicon-filter"></span>
					</a>

					<!-- Search body -->
					<div id="search" class="panel-collapse collapse">
						<form style='padding: 10px;' id="formulaire" method="post"
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
									<input type='text' class="form-control" id="iddatefin"
										name="fin" /> <span class="input-group-addon"> <span
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


			</div>

			<!-- Main Menu -->
			<div class="side-menu-container">
				<ul class="nav navbar-nav">

					<%@ include file="menuMembre.jsp"%>



				</ul>
			</div>
			<!-- /.navbar-collapse -->

		</nav>
	</div>
</div>

<!-- Main Content -->
<div class="container-fluid">
	<div class="side-body">


		<%
			if (listActivite!=null && !listActivite.isEmpty()){
		%>



		<%
			if (listActivite!=null )
							for (Activite activite : listActivite) {
											String lienDetailActivite =  "/waydplace/Frontal?action="
																+Frontal.REDIRECTION_DETAIL_ACTIVITE+"&idactivite="
																+activite.getId()+"&idmembre=" +activite.getUid_membre()+"&from="+FrontalCommun.FROM_MES_RECHERCHE_ACTIVITES_MEMBRES;
							String lienDetailParticipant = "/waydplace/FrontalCommun?action="+
								FrontalCommun.REDIRECTION_DETAIL_PARTICIPANT+"&idmembre=" +activite.getUid_membre()+"&from="+FrontalCommun.FROM_MES_RECHERCHE_ACTIVITES_MEMBRES;
		%>


		<div class="row" onclick="document.location='<%=lienDetailActivite%>'">
			<div class="col-xs-12 col-xs-offset-0 col-md-8 col-md-offset-2">
				<div class="page-header">
					<h1>
						<small class="pull-right"> <%=activite.getPanelActionParticipationHtml(profil, activite.getUid_membre())%></small>
						<%=activite.getTitre()%>
					</h1>
				</div>
				<div class="comments-list">
					<div class="media">
						<p class="pull-right">
							<small><%=activite.getHoraireLeA()%></small>
						</p>
						<a class="media-left "> <img style="width: 60px"
							src="<%=activite.getURLPhoto()%>">
						</a>
						<div class="media-body">

							<h4 class="media-heading user_name"><%=activite.getPseudoOrganisateur()%></h4>
							<%=activite.getLibelleEllipis()%>



						</div>
						<p></p>
					</div>

				</div>

			</div>
		</div>

		<%
			}
		%>
	</div>
	<ul class="pager">

		<li <%=pager.isPreviousHtml()%>><a
			href="<%=pager.getLienPrevioustHtml(profil)%>">Previous</a></li>
		<li>Page N° <%=pager.getPageEnCours()%></li>
		<li <%=pager.isNextHtml()%>><a
			href="<%=pager.getLienNextHtml(profil)%>">Next</a></li>

	</ul>
	<%
		}
				else
						{
	%>
	<div class="jumbotron jumbotron-style">
		<h1>Pas de résultats</h1>
		<h3>
			Aucune de vos activités ne corresponde à vos critéres. N'hésitez pas
			à en <a href='<%=Frontal.ACTION_REDIRECTION_PROPOSER%>'>proposer.</a>
		</h3>
	</div>

	<%
		}
	%>



</div>
</div>




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
