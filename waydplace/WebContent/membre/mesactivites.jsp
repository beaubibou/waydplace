
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

</head>
<body>

	<%
		Profil profil = (Profil) request.getSession().getAttribute("profil");
		FiltreRecherche filtre=profil.getFiltre();
		//ArrayList<Activite> listMesActivite=ActiviteDAO.getMesActivite(profil.getUID(), filtre.getCritereRechercheEtatMesActivite());

		PagerMesActivites pager=(PagerMesActivites) request.getAttribute("pager");
		ArrayList<Activite> listMesActivite = pager.getListActivite();
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
							<a class="navbar-brand" href="#"> Brand </a>
						</div>

						<!-- Search -->
						<a data-toggle="collapse" href="#search" class="btn btn-default"
							id="search-trigger"> <span class="glyphicon glyphicon-search"></span>
						</a>

						<!-- Search body -->
						<div id="search" class="panel-collapse collapse">
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

										<a href='<%=Frontal.ACTION_REDIRECTION_PROPOSER%>'
											class='btn btn-info btn-md btnwayd'> <span
											class="glyphicon glyphicon-plus"> </span>
										</a> <input type="hidden" name='action'
											value='<%=Frontal.REFRESH_MES_ACTIVITE_MEMBRES%>'>
									</form>
								</div>

							</div>
						</div>
					</div>

				</div>

				<!-- Main Menu -->
				<div class="side-menu-container">
					<ul class="nav navbar-nav">

						<%@ include file="menuMembreTest.jsp"%>



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
				if (listMesActivite!=null && !listMesActivite.isEmpty()){
			%>

			<table class="table">
				<%
					if (listMesActivite!=null)
																		
															for (Activite activite : listMesActivite)
																	{String lienDetailActivite =  "/waydplace/FrontalCommun?action="+FrontalCommun.REDIRECTION_DETAIL_ACTIVITE+"&idactivite="
																											+activite.getId()+"&idmembre=" +activite.getUid_membre()+"&from="+FrontalCommun.FROM_MES_ACTIVITES_MEMBRES;
														
																	String lienEffaceActivite = activite.getLienSupprimerMembre(profil);
																	String lienModifierActivite =activite.getLienModifierMembre(profil);
				%>

			    <tbody>
   
        <td>
    <div class="row">
      <div class="col-xs-3 col-md-2 col-lg-1">
        <img 
        class="img-responsive" src='<%=activite.getURLPhoto() %>' alt="Chania" alt="" />
        <p align="center" >date</p>
      </div>
      <div class="col-xs-7 col-md-8 col-lg-9">
       <p>sdfmsldfk</p>
    <p class="group inner list-group-item-text">
                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
      </div>
      <div class="col-xs-2 col-md-2 col-lg-2">
        <button>lqsd</button>
      </div>
    </div>
 </td>
       
    </tbody>
			</table>


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
			<%
				} else
						{
			%>
			<div class="jumbotron jumbotron-style">

				<h1>Pas de résultats</h1>
				<h3>
					Aucune de vos activités ne corresponde à vos critéres. N'hésitez
					pas à en <a href='<%=Frontal.ACTION_REDIRECTION_PROPOSER%>'><strong>proposer.</strong></a>
				</h3>
			</div>

			<%
				}
			%>


			<script type="text/javascript">
				$('select').on('change', function() {

					document.getElementById("formulaire").submit();
				});
			</script>



			<script type="text/javascript">
				function confirmEfface(liensupprime) {

					BootstrapDialog.show({
						title : 'Confirmation',
						closable : false,
						message : 'Vous allez effacer votre activité',
						buttons : [ {
							label : 'Non',
							action : function(dialog) {
								dialog.close();
							}
						}, {
							label : 'Oui',
							action : function(dialog) {
								dialog.close();
								document.location = liensupprime;
							}
						} ]
					});

				}
			</script>
		</div>
	</div>
</body>
</html>
