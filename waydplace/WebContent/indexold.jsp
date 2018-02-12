<%@page import="servlet.membre.FrontalGestionnaire"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="servlet.membre.ConnexionMembre"%>
<%@page import="text.pageweb.LoginTxt"%>
<%@page import="parametre.ActionPage"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>Acceuil</title>
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
<link href="/waydplace/css/styleWayd.css" rel="stylesheet"
	type="text/css">
<link href="/waydplace/css/nbrcaractere.css" rel="stylesheet"
	media="all" type="text/css">
<link href="/waydplace/css/boutton.css" rel="stylesheet" media="all"
	type="text/css">
	
</head>
<body>

	<div class="container">

		<form id="formmasque" action="/waydplace/ConnexionMembre"
			method="post">

			<input id='action' type="hidden" name='action'> <input
				id="token" type="hidden" class="form-control" name="tokenFireBase">
			<!-- 	<button type="submit" class="btn btn-primary">Submit</button> -->
			<input id='action' type="hidden" name='action'> <input
				id="codeSiteMasque" type="hidden" class="form-control"
				name="jetonSite">

		</form>



		<a id="btn_googl" onclick="signInTestGestionnaire()"
			class="btn btn-primary">Gestionnaire </a> <a id="btn_googl"
			onclick="signInTestMembre1()" class="btn btn-primary">Membre 1 </a> <a
			id="btn_googl" onclick="signInTestMembre2()" class="btn btn-primary">Membre
			2 </a> <a id="btn_googl" onclick="signInTestAnonyme()"
			class="btn btn-primary">Anonyme </a>
	</div>


	<form id="formlogin" action="/waydplace/ConnexionMembre" method="post">
		<input id="tokenfb" type="hidden" class="form-control" name="token">
		<input id="pwd" type="hidden" class="form-control" name="pwd">
		<input type="hidden" name='action'
			value='<%=ConnexionMembre.CONNEXION_SITE_MEMBRE%>'> <input
			id="outputJetonSite" type="hidden" class="form-control"
			name="jetonSite">

		<div class="container">
			<div class="page-header">

				<h1>
					<img src="/waydplace/img/waydLogoHD.png"
						style="margin-right: 50px;" class="img-rounded" alt="Cinque Terre"
						width="100" height="100"><%=LoginTxt.JUMBO_TITRE%>
				</h1>
			</div>



		</div>
	</form>

	<div class="container">
		<div id="loginbox"
			class="mainbox col-md-10 col-md-offset-0 col-sm-10 col-sm-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading panel-heading-custom">

					<div class="panel-title"><%=LoginTxt.TITRE_PANEL%></div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a
							href='<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_MDP_OUBLIE%>'>Mot
							de passe oublié?</a>
					</div>
				</div>



				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>


					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <input id="login-username"
							type="email" class="form-control" name="email"
							
							placeholder="<%=LoginTxt.HINT_EMAIL%> required">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
							type="password" class="form-control" name="pwd" value='azerty'
							placeholder="<%=LoginTxt.HINT_MOT_DE_PASSE%>">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-home"></i></span> <input id="inputJetonSite"
							type="text" class="form-control" value="aaaa"
							placeholder="<%=LoginTxt.CODE_SITE%>">
					</div>
					
					<div style="margin-bottom: 25px" class="input-group">
						<a id="btn-password" onclick="signPassword()" class="btn  btnwayd">Se
							connecter</a> 
					</div>
		
					<div style="margin-bottom: 25px" class="input-group">
						<span>
							<button onclick="signInFB()" class="loginBtn loginBtn--facebook">Facebook</button>
							<button onclick="signInGoogle()"
								class="loginBtn loginBtn--google">Google+</button>

						</span>

					</div>





					<div class="form-group">
						<div style="padding-top: 15px;" class="col-md-12 control">

							<div
								style="border-top: 1px solid #888; padding-top: 15px; padding-left: 15px; font-size: 85%">
								Particulier pas de compte? <a
									href='<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_COMPTE_MEMBRE%>'>
									Inscrivez-vous. </a>
							</div>
							<div
								style="padding-top: 15px; padding-left: 15px; font-size: 85%">
								Professionnel pas de compte? <a
									href='<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_COMPTE_PRO%>'>
									Inscrivez-vous. </a>
							</div>

							<div style="padding-top: 15px; padding-left: 15px; font-size: 85%">
								<a id="btn-primary"		href="<%=ConnexionMembre.ACTION_REDIRECTION_DEMANDE_CONFIRMATION_MAIL%>">Mail de confirmation non reçu?</a>
							</div>

						</div>


					</div>


				</div>

			</div>
		</div>
	</div>


</body>


<script>

function signInTestMembre1(){
	
	var codeSite=document.getElementById("inputJetonSite").value;
  
	 document.getElementById("codeSiteMasque").value =codeSite;
	 document.getElementById("action").value ='<%=ConnexionMembre.CONNEXION_SITE_MEMBRE_TEST%>';
	 document.getElementById("token").value="membre1";
		connexionTest();
}

function signInTestAnonyme(){
	
	var codeSite=document.getElementById("inputJetonSite").value;
  
	document.getElementById("codeSiteMasque").value =codeSite;
	document.getElementById("action").value ='<%=ConnexionMembre.CONNEXION_SITE_MEMBRE_TEST%>';
	document.getElementById("token").value="anonyme";
	connexionTest();
}


function signInTestMembre2(){
	
	var codeSite=document.getElementById("inputJetonSite").value;
    document.getElementById("codeSiteMasque").value =codeSite;
	
	document.getElementById("action").value ='<%=ConnexionMembre.CONNEXION_SITE_MEMBRE_TEST%>';
	 document.getElementById("token").value="membre2";
	 connexionTest();
}


function signInTestGestionnaire(){
	var codeSite=document.getElementById("inputJetonSite").value;
    document.getElementById("codeSiteMasque").value =codeSite;

	 document.getElementById("action").value ='<%=ConnexionMembre.CONNEXION_SITE_MEMBRE_TEST%>';
	 document.getElementById("token").value="gestionnaire";
	 connexionTest();
}

function signInTest(){
	
	  document.getElementById("formmasque").submit();
}
function signInGoogle(){

	var codeSite=document.getElementById("inputJetonSite").value;
	var provider = new firebase.auth.GoogleAuthProvider();
		firebase.auth().signInWithPopup(provider).then(function(result) {
		var token = result.credential.accessToken;
		  var user = result.user;
			
		  firebase.auth().currentUser.getToken(/* forceRefresh */ true).then(function(idToken) {
			
			  document.getElementById("tokenfb").value =idToken;
			  document.getElementById("pwd").value ="0";
			  document.getElementById("outputJetonSite").value =codeSite;
				
				  connexionFireBase();
					
		  }).catch(function(error) {
			  var errorMessage = error.message;

			  var errorCode = error.code;
			  
			  BootstrapDialog.alert(errorCode);
			});
	  
		  
	}).catch(function(error) {
		  // Handle Errors here.
		  var errorCode = error.code;
		  var errorMessage = error.message;
		  // The email of the user's account used.
		  var email = error.email;
		  // The firebase.auth.AuthCredential type that was used.
		  var credential = error.credential;
		  BootstrapDialog.alert(errorCode);
		  // ...
		});
	
	
}

function signInFB(){
	var codeSite=document.getElementById("inputJetonSite").value;
	var provider = new firebase.auth.FacebookAuthProvider();
	
	firebase.auth().signInWithPopup(provider).then(function(result) {
		var token = result.credential.accessToken;
		  var user = result.user;
		  firebase.auth().currentUser.getToken(/* forceRefresh */ true).then(function(idToken) {
			   document.getElementById("tokenfb").value =idToken;
			    document.getElementById("pwd").value ="0";
			    document.getElementById("outputJetonSite").value =codeSite;
			    connexionFireBase();
		 
		  }).catch(function(error) {
			  var errorMessage = error.message;

			  var errorCode = error.code;
			  
			  BootstrapDialog.alert(errorCode);
			});
	  
		  
	}).catch(function(error) {
		  // Handle Errors here.
		  var errorCode = error.code;
		  var errorMessage = error.message;
		  // The email of the user's account used.
		  var email = error.email;
		  // The firebase.auth.AuthCredential type that was used.
		  var credential = error.credential;
		  BootstrapDialog.alert(errorCode);
		  // ...
		});
	
	
}

function signPassword(){

	var email= document.getElementById("login-username").value;
	var password= document.getElementById("login-password").value;
	var codeSite=document.getElementById("inputJetonSite").value;
	firebase.auth().signInWithEmailAndPassword(email, password).then(function(firebaseUser) {
		
		  firebase.auth().currentUser.getToken(/* forceRefresh */ true).then(function(idToken) {
			  // Send token to your backend via HTTPS
			//  document.getElementById("login-username").innerHTML ="opo";
			 //  document.location.href="/wayd/Connexion?token="+idToken+"&pwd=1";
			    document.getElementById("tokenfb").value =idToken;
			    document.getElementById("pwd").value ="1";
			    document.getElementById("outputJetonSite").value =codeSite;
				   
			    connexionFireBase();
		  
		  }).catch(function(error) {
			
			});
	  
		  
	}).catch(function(error) {
		  // Handle Errors here.
		  var errorCode = error.code;
		  var errorMessage = error.message;
		  // The email of the user's account used.
		  var email = error.email;
		  // The firebase.auth.AuthCredential type that was used.
		  var credential = error.credential;

		  if (errorCode=="auth/wrong-password")
			  BootstrapDialog.alert("Mauvais mot de passe ou login incorrect.");
		  else
		  if (errorCode=="auth/user-not-found")
			  BootstrapDialog.alert("Le compte n'existe pas.");

		  else
			  if (errorCode=="auth/user-disabled")
				  BootstrapDialog.alert("Le compte est désactivé.");
			  else
			  BootstrapDialog.alert(errorMessage);
		 
			 
		  // ...
		});
	
}
</script>
<script type="text/javascript">

function connexionTest(){
	
	
	$.get("/waydplace/ConnexionMembre?"+$("#formmasque").serialize() ,
			function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
			
		if (responseText == 'membre'){
			      location.href='<%=Frontal.ACTION_REDIRECTION_ACCEUIL%>';
					return;
				
		} 
					
		if (responseText == 'gestionnaire'){
				      location.href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE%>';
						return;
						} 
			
			BootstrapDialog.alert(responseText);
								

							});

		}
	</script>

<script type="text/javascript">

function connexionFireBase(){
	
	
	$.get("/waydplace/ConnexionMembre?"+$("#formlogin").serialize() ,function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
			
		if (responseText == 'membre'){
			      location.href='<%=Frontal.ACTION_REDIRECTION_ACCEUIL%>';
					return;
				
		} 
					
		if (responseText == 'gestionnaire'){
				      location.href='<%=FrontalGestionnaire.ACTION_REDIRECTION_MES_ACTIVITE_GESTIONNAIRE%>';
						return;
						} 
			
			BootstrapDialog.alert(responseText);
								

							});

		}
	</script>

</html>