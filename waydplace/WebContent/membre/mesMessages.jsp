
<%@page import="bean.ListMessage"%>
<%@page import="bean.MessageActivite"%>
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
		Profil profil = (Profil) request.getSession()
		.getAttribute("profil");

			ListMessage listMessage = (ListMessage) request
		.getAttribute("listMessage");
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
							<a class="navbar-brand" href="#"> Messages </a>

						</div>


						<a data-toggle="collapse"
							href='/waydplace/Frontal?action=<%=Frontal.REDIRECTION_DISCUSSION_MEMBRE%>'
							class="btn btn-default" id="search-trigger"> <span
							class="glyphicon glyphicon-arrow-left"></span>
						</a>
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
	<div style="padding-top: 30px;" class="container-fluid">
		<div class="side-body">

			<form
				action='<%=listMessage.getDiscussion().getLienReponseHTML(profil)%>'
				method='post'>

				<div class="row">
					<div class="col-xs-10 col-xs-offset-0 col-md-6 col-md-offset-2">

						<input placeholder="Tapez votre message" name="message"
							type="text" class="form-control" id="usr">
					</div>

					<div class="col-xs-2  col-md-1"></div>
					<button type="submit" class="btn btnwayd">
						<span class="glyphicon glyphicon-ok"></span>
					</button>

				</div>

			</form>
			<br>
			<%
				for (MessageActivite messageActivite : listMessage
												.getListMessages()) {
			%>


			<div class="row">
				<div
					
					class="col-xs-12 col-xs-offset-0 col-md-8 col-md-offset-2 items">
				
					<div style='padding-top:8px' class="comments-list">
						<div class="media">
							<p class="pull-right">
								<small><%=messageActivite.getDateCreationStr()%></small>
							</p>
							<a class="media-left "> <img style="width: 60px"
								src="<%=listMessage.getPhotoURL(messageActivite.getUid_avec())%>">
							</a>
							<div class="media-body">

								<h4 class="media-heading user_name"><%=listMessage.getEmetteur(messageActivite)%></h4>
								<%=messageActivite.getMessage()%>

							</div>
							<p align='right'>
								<button
									onclick="confirmEfface('<%=messageActivite.getLienSupprime(profil)%>')"
									class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-remove"></span>
								</button>
							</p>

						</div>
					</div>
				</div>
			</div>


			<%
				}
			
			%>
			
		
		</div>





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
				closable : false,
				message : 'Vous allez effacer votre message',
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
</body>
</html>
