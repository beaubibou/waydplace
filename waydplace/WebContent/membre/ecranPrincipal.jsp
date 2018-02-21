
<%@page import="bean.Profil"%>
<%@page import="text.pageweb.EcranPrincipal"%>
<%@page import="servlet.membre.ConnexionMembre"%>
<%@page import="dao.MessageDAO"%>
<%@page import="parametre.Parametres"%>
<%@page import="bean.New"%>
<%@page import="pager.PagerNew"%>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<title>><%=ProposeActiviteMembre.TITRE_ONGLET%></title>


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

</head>
<body>


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
							<a class="navbar-brand" href="#"> Menu </a>
						</div>

						<!-- Search -->
						

						<!-- Search body -->
						<div id="search" class="panel-collapse collapse"></div>
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
				Profil profilTmp = (Profil) request.getSession().getAttribute(
						"profil");
			%>
			<div style="padding-top: 30px" class="panel-body">

				<div class="row">

					<%
						if (profilTmp.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_MEMBRE) {
					%>
					<div class="col-sm-4 col-sm-offset-4 ">

						<a
							href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_PROPOSER_ACTIVITE_MEMBRE%>"
							class="btn btn-info btn-block btn-lg btnwayd">Propose</a>
					</div>

				</div>

				<br>
				<%
					}
				%>

				<div class="row">
					<div class="col-sm-4 col-sm-offset-4 ">
						<a
							href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE%>"
							class="btn btn-info  btn-block  btn-lg btnwayd">Rechercher</a>
					</div>

				</div>


				<br>
				<%
					if (profilTmp.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_MEMBRE) {
				%>

				<div class="row">
					<div class="col-sm-4 col-sm-offset-4 ">
						<a
							href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_MES_ACTIVITES_MEMBRE%>"
							class="btn btn-info  btn-block btn-lg btnwayd">Mes activités</a>
					</div>

				</div>
				<br>
				<%
					}
				%>
				<%
					if (profilTmp.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_MEMBRE) {
				%>

				<div class="row">
					<div class="col-sm-4 col-sm-offset-4 ">

						<a
							href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_DISCUSSION_MEMBRE%>"
							class="btn btn-info  btn-block btn-lg btnwayd">Mes messages
							(<%=MessageDAO.getNbrMessageNonLu(profilTmp.getUID())%>)
						</a>
					</div>

				</div>


			</div>
			<br>
			<%
				}
			%>

			<%
				if (profilTmp.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_VISITEUR) {
			%>

			<div class="row">
				<div class="col-sm-4 col-sm-offset-4 ">

					<a
						href="<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_COMPTE_MEMBRE%>"
						class="btn btn-info  btn-block btn-lg btnwayd">S'inscrire</a>
				</div>

			</div>


		</div>
		<br>
		<%
			}
		%>

	</div>

</body>
</html>
