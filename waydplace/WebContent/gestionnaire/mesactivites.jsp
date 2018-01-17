

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
		Profil profil = (Profil) request.getSession().getAttribute("profil");
			FiltreRecherche filtre=profil.getFiltre();
			ArrayList<Activite> listMesActivite=ActiviteDAO.getMesActiviteBySite(profil.getIdSite(), filtre.getCritereRechercheEtatMesActivite());
	%>


	<script type="text/javascript">
		
	</script>

	<div class="container margedebut ">

		<div class="panel barrerecherche">
			<div class="panel-heading">
				<div class="row">
					<div class="col-sm-12">
						<p class="text-tuto"><%=MesActivites.TUTO_LIGNE1%></p>
						<br>
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
								value='<%=ActionPage.REFRESH_MES_ACTIVITE_MEMBRES%>'>
						</form>
					</div>

				</div>


			</div>

		</div>
		<table class="table table-responsive " id="matable">
			<thead class="entetetable">
				<tr>
					<th style="width: 10%;" class="text-center">Etat</th>
					<th class="text-center">Titre</th>
					<th class="text-center">Détail</th>
					<th style="width: 20%;" class="text-center">Date</th>

				</tr>
			</thead>
			<tbody
				style="background-color: #FFFFFF; text-align: center; vertical-align: middle;">

				<%
					if (listMesActivite!=null)
					for (Activite activite : listMesActivite)
					{
					String lienEffaceActivite = "/waydplace/FrontalGestionnaire?action="+ActionPage.EFFACE_ACTIVITE_GESTIONNAIRE+"&idactivite=" + activite.getId();
					String lienDetail = "/wayd/DetailActiviteSite?idactivite=" + activite.getId()+"&from=listActivite.jsp";
					String lienModifierActivite = "/waydplace/FrontalGestionnaire?action="+ActionPage.REDIRECTION_MODIFIER_ACTIVITE_GESTIONNAIRE+"&idactivite=" + activite.getId();
				%>


				<tr>
					<td><%=activite.getEtatHtml()%></td>
					<td class="idActivite" id=<%=activite.getId()%>
						style="vertical-align: middle;"><%=activite.getTitre()%></td>
					<td style="vertical-align: middle;"><%=activite.getHoraireLeA()%></td>
					<td style="vertical-align: middle;"><%=activite.getHoraireLeA()%></td>
					<td style="vertical-align: middle;"><a title="Détail"
						href="<%=lienDetail%>">
							<button type="button" class="btnwayd btn-sm">
								<span class="glyphicon glyphicon-search"></span>
							</button>
					</a> <%
 	if (!activite.isTerminee()){
 %> <a title="Modifier" href="<%=lienModifierActivite%>">
							<button title="Modifier" type="button" class="btnwayd btn-sm">
								<span class="glyphicon glyphicon-edit"></span>
							</button>
					</a> <a title="Supprimer" href="<%=lienEffaceActivite%>">
							<button title="Supprimer" name="supprimer" type="button"
								class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
					</a> <%
 	}
 %></td>


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






</body>
</html>
