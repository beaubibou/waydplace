<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="text.pageweb.EcranPrincipal"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>



<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
</head>
<body>

<%@include file="menuMembre.jsp"%>

	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-8 col-md-offset-2 col-sm-8 margedebut">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><%=EcranPrincipal.TITRE_PAGE%></div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div class="row">
						<div class="col-sm-4 col-sm-offset-4">

							<a
								href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_PROPOSER_ACTIVITE_MEMBRE%>"
								class="btn btn-info" role="button">Propose</a>

						</div>

					</div>
					<br>

					<div class="row">
						<div  class="col-sm-4 col-sm-offset-4 ">
							<a
								href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE%>"
								class="btn btn-info" role="button">Rechercher</a>
						</div>

					</div>

					<br>

					<div class="row">
						<div class="col-sm-4  col-sm-offset-4 ">
							<a
								href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_MES_ACTIVITES_MEMBRE%>"
								class="btn btn-info" role="button">Mes activites</a>
						</div>

					</div>
					
						<div class="row">
					
					
						<div  class="col-sm-4 col-sm-offset-4">
							<a
								href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_COMPTE_MEMBRE%>"
								class="btn btn-info" role="button">Mon profil</a>
						</div>
				
					</div>

				</div>


			</div>
		</div>
	</div>


</body>
</html>