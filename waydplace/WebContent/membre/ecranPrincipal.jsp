<%@page import="parametre.Parametres"%>
<%@page import="dao.MessageDAO"%>
<%@page import="servlet.membre.FrontalCommun"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="text.pageweb.EcranPrincipal"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
								class="btn btn-info  btn-block btn-lg btnwayd">Mes
								activités</a>
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
								class="btn btn-info  btn-block btn-lg btnwayd" >Mes
								messages (<%=MessageDAO.getNbrMessageNonLu(profilTmp.getUID())%>)
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
							href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_INSCRIPTION_MEMBRE%>"
							class="btn btn-info  btn-block btn-lg btnwayd"  >S'inscrire</a>
					</div>

				</div>


			</div>
			<br>
			<%
				}
			%>

		</div>
	</div>

	<script>

test();


function test() {
	
	$.get("/waydplace/FrontalCommun?action=<%=FrontalCommun.AJAX_GET_MESSAGE_DIALOG%>",
							function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...

								if (responseText == 'null')
									return;

								alert(responseText);

							});

		}
	</script>
</body>
</html>