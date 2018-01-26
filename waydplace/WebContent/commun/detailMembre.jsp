
<%@page import="parametre.Parametres"%>
<%@page import="bean.Membre"%>
<%@page import="bean.Profil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>>Détail Membre</title>


<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<!-- <script src="src/bootstrap-rating-input.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-rating-input/0.4.0/bootstrap-rating-input.js"></script>

<script src="js/moment.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript">
	var lastIndex = 0;
</script>
<style>
.vcenter {
	display: inline-block;
	vertical-align: middle;
	float: none;
}

.vertical-align {
	display: flex;
	align-items: center;
}
</style>
</head>
<body>


	<%
	
	Profil profil = (Profil) request.getSession().getAttribute("profil");
	
			Membre membre = (Membre) request.getAttribute("membre");
		
	%>

	<%if (profil.getTypeOrganisteur()==Parametres.TYPE_ORGANISATEUR_MEMBRE){ %>
	
	<%@ include file="/membre/menuMembre.jsp"%>
	
	<%} %>
	
	<%if (profil.getTypeOrganisteur()==Parametres.TYPE_ORGANISATEUR_VISITEUR){ %>
	
	<%@ include file="/membre/menuMembre.jsp"%>
	
	<%} %>
	
	
	<%if (profil.getTypeOrganisteur()==Parametres.TYPE_ORGANISATEUR_SITE){ %>
	<%@ include file="/membre/menuMembre.jsp"%>

	<%} %>

	<div class="container margedebut">
			<div class="panel panel-default">
	
				<div class="panel-heading panel-heading-custom">

					<div class="panel-title">Profil</div>

				</div>

				<div style="padding-top: 30px" class="panel-body">


					<div class="form-group">

						<div class="row">
							<div class='col-sm-12'>

								<%=membre.getDetailEnteteMembreHtml() %>



							</div>



						</div>

					</div>
					<div class="form-group">
						<label for="description">Description:</label>
						<textarea disabled class="form-control" rows="10" id="description"
							name="description"><%=membre.getDescription()%></textarea>
					</div>


				</div>
			</div>
		</div>

	</div>

</body>
</html>