

<%@page import="bean.BoiteReception"%>
<%@page import="java.util.HashMap"%>
<%@page import="bean.Discussion"%>
<%@page import="dao.MessageDAO"%>
<%@page import="bean.MessageActivite"%>
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

<script src="js/alertdialog.js"></script>

<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">

</head>
<body>

	<%
		Profil profil = (Profil) request.getSession()
				.getAttribute("profil");
		ArrayList<Discussion> listMesDiscussion = MessageDAO
				.getDiscussions(profil.getUID());
		BoiteReception boiteReception = new BoiteReception(profil);
	%>

	<%@ include file="menuMembre.jsp"%>



	<div class="container" style='margin-top: 120px;'>
		<h2>Messages</h2>
		<table class="table">

			<tbody>

				<%
					for (Discussion discussion : boiteReception.getMesDiscussion()) {
				%>
				<tr>
					<td><div class="container">

							<img src='<%=boiteReception.getPhotoInterlocteur(discussion)%>'>
							<button type="button" class="btn btn-info" data-toggle="collapse"
								data-target="#<%=discussion.getIdActivite()%>"><%=discussion.getTitreActivite()%></button>
							<div id="<%=discussion.getIdActivite()%>" class="collapse">
								<div class="container">
									<table class="table">
										<%
											for (MessageActivite messageActivite : boiteReception
														.getMessageDiscussionEntre2ByActivite(discussion)) {
										%>
										<tbody>
											<tr>

												<%=messageActivite.getMessageHtlm()%>
												<td><%=MessageActivite.getLienReponse(profil,
							messageActivite.getIdActivite(),
							messageActivite.getUidEmetteur())%></td>
											</tr>
											<%
												}
											%>

										</tbody>
									</table>

								</div>

							</div>

						</div></td>
				</tr>


				<%
					}
				%>

			</tbody>
		</table>
	</div>


</body>
</html>
