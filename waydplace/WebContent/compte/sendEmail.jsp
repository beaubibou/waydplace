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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
	
<link href="/wayd/css/styleWayd.css" rel="stylesheet" type="text/css">


</head>
<body>

<%
String pwd=(String)request.getParameter("pwd");
String email=(String)request.getParameter("email");%>

<script>

signPassword("<%=email%>", "<%=pwd%>");


function signPassword(email,password){
	

	
	firebase.auth().signInWithEmailAndPassword(email, password).then(function(firebaseUser) {
		 
		firebase.auth().currentUser.sendEmailVerification().then(function() {
			  // Email sent.

			BootstrapDialog.alert('Bienvenue un email de confirmation vous a été envoyé', function(){
				document.location.href="index.jsp";
			});
			
		}).catch(function(error) {
			  // An error happened.
		  var errorMessage = error.message;
			 
			BootstrapDialog.alert(errorMessage, function(){
				document.location.href="index.jsp";   
			});

				});
	  
		  
	}).catch(function(error) {
		  // Handle Errors here.
		  var errorCode = error.code;
		  var errorMessage = error.message;
		  // The email of the user's account used.
		  var email = error.email;
		  // The firebase.auth.AuthCredential type that was used.
		  var credential = error.credential;
		  BootstrapDialog.alert(errorMessage, function(){
				document.location.href="index.jsp";
			});

		  // ...
		});
	
}
</script>
</body>
</html>