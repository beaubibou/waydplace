

<%@page import="parametre.ActionPage"%>
<%@page import="text.pageweb.MenuGestionnaireText"%>
<nav class="navbar navbar-inverse navbar-fixed-top" id="menupro">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<img style="margin-top: 10px; margin-right: 10px;"
				src="/wayd/img/waydLogoLR.png" class="img-rounded"
				alt="Cinque Terre" width="30" height="30">

		</div>


		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
		
					<li ><a
					href="/waydplace/FrontalGestionnaire?action=<%=ActionPage.REDIRECTION_COMPTE_GESTIONNAIRE%>"><%=MenuGestionnaireText.MON_COMPTE%></a></li>
				<li>
					<a
					href="/waydplace/FrontalGestionnaire?action=<%=ActionPage.REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.GERER%> </a></li>


				<li
										class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><%=MenuGestionnaireText.PROPOSER%> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a style='color: black; background-color: #FFFFFF;'
							href="/waydplace/FrontalGestionnaire?action=<%=ActionPage.REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.CREER_ACTIVITE%></a></li>
						<li><a style='color: black; background-color: #FFFFFF;'
							href="/waydplace/FrontalGestionnaire?action=<%=ActionPage.REDIRECTION_PROPOSER_PLUSIEURS_ACTIVITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.PLANIFIER_ACTIVITE%></a></li>

					</ul></li>

			
				
			</ul>


			<ul class="nav navbar-nav navbar-right">

			
				<li><a href="/wayd/Deconnexion"><span
						class="glyphicon glyphicon-log-in"></span> Connexion</a></li>
				
			


			</ul>
		</div>
	</div>
</nav>
