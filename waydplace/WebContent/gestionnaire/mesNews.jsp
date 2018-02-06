

<%@page import="pager.PagerNew"%>
<%@page import="bean.New"%>
<%@page import="pager.PagerActivite"%>
<%@page import="text.pageweb.MesNewsText"%>
<%@page import="servlet.membre.FrontalCommun"%>
<%@page import="servlet.membre.Frontal"%>
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

<link href="/waydplace/css/styleWaydGestionnaire.css" rel="stylesheet"
	type="text/css">

</head>

<body>

	<%
		Profil profil = (Profil) request.getSession().getAttribute("profil");
			FiltreRecherche filtre=profil.getFiltre();
			PagerNew pager=(PagerNew) request.getAttribute("pager");
			ArrayList<New> listNews = pager.getListNew();
	%>

	<%@ include file="menuGestionnaire.jsp"%>


	<div class="container margedebut ">

		<div class="panel barrerecherche">
			<div class="panel-heading">
				<div class="row">
					<div class="col-sm-12">
						<p class="text-tuto"><%=MesNewsText.TUTO_LIGNE1%></p>
						<br>
					</div>
				</div>

				<div class="row"></div>


			</div>

		</div>
		<table class="table table-responsive " id="matable">
			<thead class="entetetable" align="center">
				<tr>

	<th style="width: 85%;" class="text-center">News</th>
					<th style="width: 15%;" class="text-center">Action</th>

				

				</tr>
			</thead>
			<tbody style="background-color: #FFFFFF; vertical-align: middle;">

				<%
					if (listNews !=null)
						for (New news : listNews)
				{
			String lienEffaceNew = "/waydplace/FrontalGestionnaire?action="
									+ FrontalGestionnaire.EFFACE_NEWS_GESTIONNAIRE + "&idNew=" + news.getId();
 			String lienModifierNew = "/waydplace/FrontalGestionnaire?action="
 									+ FrontalGestionnaire.REDIRECTION_MODIFIER_NEWS_GESTIONNAIRE
									+ "&idNew=" + news.getId();			
				%>


				<tr>
					<td>
						<div class="jumbotron" style="margin-bottom: 0px;">
							<h2 style="margin-top: 0px;"><%=news.getTitre()%></h2>
							<p><%=news.getMessage()%></p>

							<h6><%=news.getDateCreationStr()%></h6>
						</div>

					</td>
					<td style="vertical-align: middle; text-align: center;">
						<p>
							<a href='<%=lienModifierNew%>' class='btn btn-info btn-sm'>
								<span class='glyphicon glyphicon-edit'></span>
							</a>
							<button onclick="confirmEfface('<%=lienEffaceNew%>')"
								class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</p>
					</td>
							</tr>
				<%
					}
				%>
			</tbody>
		</table>

	</div>


	<script type="text/javascript">
		$('select').on('change', function() {

			document.getElementById("formulaire").submit();
		});
	</script>


<script type="text/javascript">
		function confirmEfface(liensupprime) {

			BootstrapDialog.show({
				title : 'Confirmation',
				message : 'Vous allez effacer votre news',
				buttons : [ {
					label : 'Oui',
					action : function(dialog) {
						document.location = liensupprime;
					}
				}, {
					label : 'Non',
					action : function(dialog) {
						dialog.close();
					}
				} ]
			});

		}
	</script>





</body>
</html>
