<%@page import="parametre.ActionPage"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
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
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>Stacked form</h2>
		<form id="formmasque" action="/waydplace/ConnexionMembre" method="post">

			<input name='action' value='<%=ActionPage.ACT_CONNEXION_SITE%>'>
			
			<div class="form-group">
				<label for="codesite">Code site:</label> <input type="text"
					class="form-control" id="codesite"
					placeholder="Tapez le code du site" name="jetonSite">
			</div>


			<input id="token" type="hidden" class="form-control" name="tokenFireBase">
		<!-- 	<button type="submit" class="btn btn-primary">Submit</button> -->
		</form>

		<a id="btn_googl" e" onclick="signInGoogle()" class="btn btn-primary">Login
			with Google</a>
			
			<a id="btn_googl" e" onclick="signInTest()" class="btn btn-primary">Test
			</a>

	</div>
</body>

<script>

function signInTest(){
	
	  document.getElementById("formmasque").submit();
}
function signInGoogle(){
	
	var provider = new firebase.auth.GoogleAuthProvider();
	firebase.auth().signInWithPopup(provider).then(function(result) {
		  // This gives you a Google Access Token. You can use it to access the Google API.
		  var token = result.credential.accessToken;
		  // The signed-in user info.
		  var user = result.user;
		  // ...
		  firebase.auth().currentUser.getToken(/* forceRefresh */ true).then(function(idToken) {
			  // Send token to your backend via HTTPS
			  // ...
			//  document.getElementById("demo").innerHTML ="opo";
			//   document.location.href="/wayd/Connexion?token="+idToken;
			  document.getElementById("token").value =idToken;
			  document.getElementById("formmasque").submit();
			
		 
		  }).catch(function(error) {
			  var errorMessage = error.message;

			  var errorCode = error.code;
			  
			  BootstrapDialog.alert(errorCode);
			  // Handle error
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


</script>
</html>