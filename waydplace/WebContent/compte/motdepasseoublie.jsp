<%@page import="text.pageweb.LoginTxt"%>
<%@page import="servlet.membre.FrontalGestionnaire"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="servlet.membre.ConnexionMembre"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link href="/waydplace/css/indexcss.css" rel="stylesheet"
	type="text/css">
	
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	
<form id="formmasque" action="/waydplace/ConnexionMembre"
			method="post">

			<input id='action' type="hidden" name='action'> <input
				id="token" type="hidden" class="form-control" name="tokenFireBase">
			<!-- 	<button type="submit" class="btn btn-primary">Submit</button> -->
			<input id='action' type="hidden" name='action'> <input
				id="codeSiteMasque" type="hidden" class="form-control"
				name="jetonSite">

		</form>

<form id="formlogin" action="/waydplace/ConnexionMembre" method="post">
		<input id="tokenfb" type="hidden" class="form-control" name="token">
		<input id="pwd" type="hidden" class="form-control" name="pwd">
		<input type="hidden" name='action'
			value='<%=ConnexionMembre.CONNEXION_SITE_MEMBRE%>'> <input
			id="outputJetonSite" type="hidden" class="form-control"
			name="jetonSite">
</form>

	
<div class="container">

    
    <div class="omb_login">
   		<div class="row">
				<div class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5 ">
					<a href="<%=ConnexionMembre.ACTION_REDIRECTION_LOGIN%>">
						<img src='/waydplace/img/waydLogoHD.png'
						class="img-rounded img-responsive">
					</a>
				</div>
			</div>
  	<h3 class="omb_authTitle">Mot de passse oublié</h3>
		

		

		<div class="row omb_row-sm-offset-3">
			<div class="col-xs-12 col-sm-6">	
			    <form  class="omb_loginForm" action="" autocomplete="off" method="POST">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text" class="form-control" name="email" placeholder="email address" id="login-username">
					</div>
					<span class="help-block"></span>
										
				
					<br>
					
                  <br>
				</form>
						<button   onclick="valideFormulaire()"  class="btn btn-lg btnwayd btn-block" >Envoyer</button>
			
			</div> 
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