
<%@page import="text.pageweb.MotPasseOublie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://www.gstatic.com/firebasejs/4.8.2/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCiTVROhzVToGPW-h16amlnckDvFEse9Hg",
    authDomain: "waydplace.firebaseapp.com",
    databaseURL: "https://waydplace.firebaseio.com",
    projectId: "waydplace",
    storageBucket: "",
    messagingSenderId: "165326675460"
  };
  firebase.initializeApp(config);
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mot de passe</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>


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

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
<link href="/waydplace/css/boutton.css" rel="stylesheet" media="all"
	type="text/css">

</head>
<body>
	<div class="container">
		<div class="page-header">

			<h1>
				<img src="/waydplace/img/waydLogoHD.png" style="margin-right: 50px;"
					class="img-rounded" alt="Cinque Terre" width="100" height="100"><%=MotPasseOublie.JUMBO_TITRE%>
			</h1>
		</div>



	</div>
	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">
					<div class="panel-title"><%=MotPasseOublie.TITRE_PANEL%></div>

				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<form id="loginform" class="form-horizontal">

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="email"
								placeholder="<%=MotPasseOublie.HINT_EMAIL%>">
						</div>



						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">

								<a id="btn-password" onclick="valideFormulaire()"
									class="btn btnwayd">Envoyer la demande </a> <a
									href="/waydplace/index.jsp" class="btn btnwayd"><span
									class="glyphicon glyphicon-home"></span> Accueil</a>

							</div>

						</div>
				</div>


				</form>

			</div>
		</div>
	</div>


	<script>


function sendEmailVerification(){

	var email= document.getElementById("login-username").value;
	var auth = firebase.auth();


auth.sendPasswordResetEmail(email).then(function() {
  // Email sent.
    
 BootstrapDialog.show({
            title: 'Confirmation',
            closable: false,
            message: 'Un mail de confirmation vous a été envoyé',
            buttons: [{
                label: 'Ok',
                action: function(dialog) {
                dialog.close();
                location.href="/waydplace/index.jsp"
                  //  dialog.setMessage('Message 1');
                }
            
            }]
        }); 
      

}).catch(function(error) {
  // An error happened.
   var errorMessage = error.message;
   
  BootstrapDialog.alert(errorMessage);
});


	
}
</script>

	<script>
	function valideFormulaire(){
			
		sendEmailVerification();
	}

</script>

</body>
</html>