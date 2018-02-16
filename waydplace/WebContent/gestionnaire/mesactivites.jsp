

<%@page import="pager.PagerMesActivites"%>
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

<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">



</head>

<body>

	<%
		Profil profil = (Profil) request.getSession().getAttribute("profil");
		FiltreRecherche filtre=profil.getFiltre();
		PagerMesActivites pager=(PagerMesActivites) request.getAttribute("pager");
		ArrayList<Activite> listMesActivite = pager.getListActivite();
		
		%>

	<%@ include file="menuGestionnaire.jsp"%>


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
						<form method="post" action="/waydplace/FrontalGestionnaire"
							id="formulaire" class="form-inline">
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
							<a	 href='<%=FrontalGestionnaire.ACTION_REDIRECTION_PROPOSER_UNE_ACTIVITE %>'
								class='btn btn-info btn-md btnwayd'> <span
								class="glyphicon glyphicon-plus"> </span>
								</a>
							<input type="hidden" name='action'
								value='<%=FrontalGestionnaire.REFRESH_MES_ACTIVITE_GESTIONNAIRE%>'>
						</form>
					</div>

				</div>


			</div>

		</div>
		<table class="table table-responsive " id="matable">
			<thead class="entetetable" align="center">
				<tr>
					<th style="width: 10%;" class="text-center">Etat</th>
					<th class="text-center">Titre</th>
					<th class="text-center">Détail</th>
					<th style="width: 15%;" class="text-center">Date</th>
					<th style="width: 15%;" class="text-center">Action</th>

				</tr>
			</thead>
			<tbody
				style="background-color: #FFFFFF; text-align: center; vertical-align: middle;">

				<%
					if (listMesActivite!=null)
									for (Activite activite : listMesActivite)
									{
						String lienDetailActivite =  "/waydplace/FrontalGestionnaire?action="+FrontalGestionnaire.REDIRECTION_DETAIL_ACTIVITE
										+"&idactivite=" +activite.getId()+"&idmembre=" +activite.getUid_membre()+"&from="+FrontalCommun.FROM_MES_ACTIVITES_GESTIONNAIRE;;
										String lienEffaceActivite = activite.getLienSupprimerGestionnaire(profil);
										String lienModifierActivite =activite.getLienModifierGestionnaire(profil);
				%>


				<tr>
					<td style="vertical-align: middle;" onclick="document.location='<%=lienDetailActivite%>'"><%=activite.getEtatHtml()%></td>
					<td class="idActivite" id=<%=activite.getId()%>
						style="vertical-align: middle;"><strong><%=activite.getTitre()%></strong></td>
					<td style="vertical-align: middle;"><%=Outils.ellipsis(activite.getLibelle(), 50)%></td>
					<td style="vertical-align: middle;"><%=activite.getHoraireLeA()%></td>
					<td style="vertical-align: middle; text-align: center;">
						<p>
						<%
								if (lienModifierActivite!=null) {
							%>
							<a href='<%=lienModifierActivite%>' class='btn btnwayd'>
								<span class='glyphicon glyphicon-edit'></span>
							</a>
							<%} %>
							
								<%
								if (lienEffaceActivite!=null) {
							%>
							<button onclick="confirmEfface('<%=lienEffaceActivite%>')"
								class="btn btn-danger btn">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
							<%} %>
						</p>
					</td>
			

				</tr>
				<%
					}
				%>
			</tbody>
		</table>
				<ul class="pager">

			<li <%=pager.isPreviousHtml()%>><a
				href="<%=pager.getLienPrevioustHtml(profil)%>">Previous</a></li>
			<li>Page N° <%=pager.getPageEnCours()%></li>
			<li <%=pager.isNextHtml()%>><a
				href="<%=pager.getLienNextHtml(profil)%>">Next</a></li>

		</ul>

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
				closable: false,
				message : 'Vous allez effacer votre activité',
				buttons : [{
					label : 'Non',
					action : function(dialog) {
						dialog.close();
					}
				} , {
					label : 'Oui',
					action : function(dialog) {
						dialog.close();
						document.location = liensupprime;
					}
				}]
			});

		}
	</script>




</body>
</html>
