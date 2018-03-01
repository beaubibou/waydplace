<%@page import="text.pageweb.DetailActiviteSite"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="bean.Activite"%>
<%@page import="bean.Profil"%>
<%@page import="parametre.Parametres"%>
<%@page import="bean.Site"%>
<%@page import="bean.RefTypeGenre"%>
<%@page import="text.pageweb.CompteMembre"%>
<%@page import="outils.Outils"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>
<%@page import="text.pageweb.ProposeActiviteMembre"%>

<%@page import="java.util.ArrayList"%>
 
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>><%=DetailActiviteSite.TITRE_ONGLET%></title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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

<script src="/waydplace/js/valideform.js"></script>
<script src="/waydplace/js/slide.js"></script>

</head>
<body>

	<%
		Profil profil = (Profil) request.getSession()
				.getAttribute("profil");

		Activite activite = (Activite) request.getAttribute("activite");
		ArrayList<RefTypeActivite> listTypeActivite = CacheDAO
				.getListRefTypeActivite();

		String back = (String) request.getAttribute("back");
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
							<a class="navbar-brand" href="#"> Activité site </a>
						</div>

						<a href="javascript:history.back()" class="btn btn-default"
							id="search-trigger"> <span
							class="glyphicon glyphicon-arrow-left"></span>
						</a>


					</div>

				</div>

				<!-- Main Menu -->
				<div class="side-menu-container">
					<ul class="nav navbar-nav">



						<%@ include file="/membre/menuMembre.jsp"%>

					</ul>
				</div>

			</nav>

		</div>
	</div>
	<!-- Main Content -->
	<div style="padding-top: 30px;" class="container-fluid well well-sm">
		<div class="side-body">


			<div class="row">
				<div align="center" 
					class="col-xs-8 col-xs-offset-2 col-md-2 col-md-offset-5 ">

					<img src='<%=activite.getURLPhoto()%>' 
						class="img-circle img-thumbnail img-responsive">
				</div>

			</div>


			<div class="row">
				<div class="form-group">
					<div class="form-group">

						<div class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 ">

							<h3 align='center'><%=activite.getTitre().toUpperCase()%></h3>

						</div>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="form-group">
					<div class="form-group">
						<div
							class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4 ">
							<label for="tel">Tèl:</label> <input
								style='background-color: white;' disabled type="text"
								class="form-control" id="tel"
								value="<%=activite.getHoraireLigne()%>">

						</div>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="form-group">
					<div class="form-group">
						<div
							class="col-xs-12 col-xs-offset-0 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4 ">
								<label for="description">Description</label>
							<textarea id='description'  disabled
								style='background-color: white;' class="form-control" rows="5"><%=activite.getLibelle()%></textarea>


						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="form-group">

						<div 
							class="col-xs-12  col-md-3 col-md-offset-3 col-lg-3 col-lg-offset-4 ">

						<%=activite.getInteretHTML(profil) %>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript">

function likeActivite(){
	

	$.post("<%=activite.getLienLike(profil)%>" ,
			function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
				if (responseText == 'ok')
				{
					BootstrapDialog.show({
			            title: 'Confirmation',
			            closable: false,
			            message: 'Votre intèret a été pris en compte',
			            buttons: [{
			                label: 'Ok',
			                action: function(dialog) {
			                	
			               	dialog.close();
			               	document.getElementById("formulaire").reset(); 
			                location.href='<%=Frontal.ACTION_REDIRECTION_MES_ACTIVITE_MEMBRE%>'
			                  //  dialog.setMessage('Message 1');
			                }
			            
			            }]
			        }); 
					
					
				}
				else
				{
					
					BootstrapDialog.alert(responseText);
				}

				

			});	
	
	
	
}
</script>



</body>
</html>
