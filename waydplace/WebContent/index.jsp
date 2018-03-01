
<%@page import="servlet.membre.ConnexionMembre"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
<link href="/waydplace/css/boutton.css" rel="stylesheet" media="all"
	type="text/css">


</head>
<body>

	<div class="row">
		<div class="col-xs-8 col-xs-offset-2 col-md-6 col-md-offset-3 ">
		<div class="carousel-inner">

			<div class="item active">
				<img src="/waydplace/img/waydLogoHD.png" alt="Los Angeles"
					class="img-responsive">
				<div class="carousel-caption">


					<form action='/waydplace/ConnexionMembre' id="formulaire" onsubmit="return connexionSite()">

						<input name="action" type="hidden"
							value=<%=ConnexionMembre.VALIDE_SITE%>>


						<div class="form-group">
							<div class="col-xs-8 col-xs-offset-2 col-md-4 col-md-offset-4 ">
								<input type="text" class="form-control" id="codeSite"
									name="codeSite" placeholder='Code site' value="aaaa">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-8 col-xs-offset-2  col-md-4 col-md-offset-4 ">

								<button style="margin-top: 10px;" type="submit"
									class="btn-lg btnwayd btn-block ">Entrez</button>

							</div>
						</div>

						<div class="form-group">

							<div style="margin-top: 10px;"
								class="col-xs-8 col-xs-offset-2  col-md-4 col-md-offset-4 ">

								<a href='<%=ConnexionMembre.ACTION_REDIRECTION_CONNEXION_PRO%>'>Professionels</a>

							</div>
						</div>


					</form>
				</div>
			</div>


		</div>

</div>

		<br>


	</div>

	<script type="text/javascript">
	
function connexionSite() {

	var codeSite=document.getElementById("codeSite").value;
		
			$.post($("#formulaire").attr("action"), $("#formulaire").serialize(),
							function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
								if (responseText == 'ok') {
									location.href = "<%=ConnexionMembre.ACTION_REDIRECTION_CONNEXION%>"
											+ codeSite;

								} else {

									BootstrapDialog.alert(responseText);
								}

							});
			return false;

		}
	</script>


</body>
</html>