
<%@page import="parametre.Parametres"%>
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
		Profil profil = (Profil) request.getSession()
				.getAttribute("profil");

		Activite activite = (Activite) request.getAttribute("activite");
		ArrayList<RefTypeActivite> listTypeActivite = CacheDAO
				.getListRefTypeActivite();
	%>

	<%
		if (profil.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_MEMBRE) {
	%>

	<%@ include file="/membre/menuMembre.jsp"%>

	<%
		}
	%>

	<%
		if (profil.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_VISITEUR) {
	%>

	<%@ include file="/membre/menuMembre.jsp"%>

	<%
		}
	%>


	<%
		if (profil.getTypeOrganisteur() == Parametres.TYPE_ORGANISATEUR_SITE) {
	%>
	<%@ include file="/membre/menuMembre.jsp"%>

	<%
		}
	%>


	<div class="container margedebut ">

		<div id="loginbox"
			"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">

					<div class="panel-title">Détail Activite</div>

				</div>

				<div style="padding-top: 30px" class="panel-body">


					<div class="form-group">

						<div class="row">
							<div class='col-sm-12'>

								<%=activite.getDetailEnteteMembreHtml()%>



							</div>



						</div>

					</div>
					<div class="form-group">
						<label for="description">Description:</label>
						<textarea disabled class="form-control" rows="10" id="description"
							name="description"><%=activite.getLibelle()%></textarea>
					</div>


				</div>
			</div>
		</div>

	</div>







</body>
</html>
