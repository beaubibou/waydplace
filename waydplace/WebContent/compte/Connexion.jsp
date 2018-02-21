<%@page import="text.pageweb.CreationCompteMembre"%>
<%@page import="text.pageweb.CompteMembre"%>
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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Acceuil</title>

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
	<a id="btn_googl" onclick="signInTestGestionnaire()"
			class="btn btn-primary">Gestionnaire </a> <a id="btn_googl"
			onclick="signInTestMembre1()" class="btn btn-primary">Membre 1 </a> <a
			id="btn_googl" onclick="signInTestMembre2()" class="btn btn-primary">Membre
			2 </a> <a id="btn_googl" onclick="signInTestAnonyme()"
			class="btn btn-primary">Anonyme </a>
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
    
    <div  class="row">
							<div
								class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5 ">

								<img src='/waydplace/img/waydLogoHD.png' 
									class="img-rounded img-responsive">
							</div>
						</div>
  	<h3 class="omb_authTitle">Login or <a href="<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_COMPTE_MEMBRE%>">Sign up</a></h3>
		<div class="row omb_row-sm-offset-3 omb_socialButtons">
    	    <div class="col-xs-3 col-sm-2">
		        <a href="#" class="btn btn-lg btn-block omb_btn-facebook">
			        <i class="fa fa-facebook visible-xs"></i>
			        <span class="hidden-xs">Facebook</span>
		        </a>
	        </div>
        	<div  align='center' class="col-xs-5 col-sm-2">
		        <button type="button" class="btn btn-lg btn-block omb_btn_wayd" onclick="signInTestAnonyme()">Visiteur</button>
	        </div>	
        	<div class="col-xs-3 col-sm-2">
		        <a onclick="signInGoogle()" class="btn btn-lg btn-block omb_btn-google">
			        <i class="fa fa-google-plus visible-xs"></i>
			        <span class="hidden-xs">Google+</span>
		        </a>
	        </div>	
		</div>

		<div class="row omb_row-sm-offset-3 omb_loginOr">
			<div class="col-xs-12 col-sm-6">
				<hr class="omb_hrOr">
				<span class="omb_spanOr">or</span>
			</div>
		</div>

		<div class="row omb_row-sm-offset-3">
			<div class="col-xs-12 col-sm-6">	
			    <form  class="omb_loginForm" onsubmit="return signPassword()"  method="POST">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input required type="email" class="form-control" name="email" placeholder="<%=CreationCompteMembre.HINT_EMAIL %>" id="login-username">
					</div>
					<span class="help-block"></span>
										
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input required id="login-password" type="password" class="form-control" name="password" placeholder="<%=CreationCompteMembre.HINT_MOT_DE_PASSE %>">
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon"><i
							required class="glyphicon glyphicon-home"></i></span> <input id="inputJetonSite"
							type="text" class="form-control" value="<%=request.getParameter("codeSite") %>"
							placeholder="<%=LoginTxt.CODE_SITE%>">
					</div>
                  <br>
                  	<button  type="submit"  class="btn btn-lg btnwayd btn-block" >Login</button>
				</form>
					
			
			</div> 
    	</div>
		<div class="row omb_row-sm-offset-3">
			<div  class="col-xs-12 col-sm-3">
				<label hidden  class="checkbox">
					<input hidden  type="checkbox" value="remember-me">Remember Me
				</label>
			</div>
			<div class="col-xs-12 col-sm-3">
				<p class="omb_forgotPwd">
					<a href='<%=ConnexionMembre.ACTION_REDIRECTION_CREATION_MDP_OUBLIE%>'>Mot de passe oublié?</a>
				</p>
			</div>
		</div>	    	
	</div>
	</div>
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
	return false;
	
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
	

</body>

</html>