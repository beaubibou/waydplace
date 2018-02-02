<%@page import="servlet.membre.FrontalGestionnaire"%>
<%@page import="bean.Profil"%>
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

			
		</div>


		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
		
			<li ><a
					href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_COMPTE_GESTIONNAIRE%>"><%=MenuGestionnaireText.COMPTE%></a></li>
				<li>
					<li ><a
					href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_SITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.SITE%></a></li>
				<li>
					<a
					href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_GERER_ACTIVITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.GERER%> </a></li>

<li>
					<a
					href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_RECHERCHER_ACTIVITE_GESTIONNAIRE%>">Rechercher </a></li>
<li>
	<a
					href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_PLANING_GESTIONNAIRE%>">Planning </a></li>


				<li
										class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><%=MenuGestionnaireText.PROPOSER%> <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a style='color: black; background-color: #FFFFFF;'
							href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.CREER_ACTIVITE%></a></li>
						<li><a style='color: black; background-color: #FFFFFF;'
							href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_PROPOSER_PLUSIEURS_ACTIVITE_GESTIONNAIRE%>"><%=MenuGestionnaireText.PLANIFIER_ACTIVITE%></a></li>
					
						<li><a style='color: black; background-color: #FFFFFF;'
							href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.REDIRECTION_PROPOSER_NOTIFICATION_GESTIONNAIRE%>"><%=MenuGestionnaireText.AJOUTER_NOTIFICATION%></a></li>
						

					</ul></li>

			
				
			</ul>


			<ul class="nav navbar-nav navbar-right">

<%if ((Profil) request.getSession().getAttribute("profil")==null){ %>
			
				<li><a href="/wayd/Deconnexion"><span
						class="glyphicon glyphicon-log-in"></span> Connexion</a></li>
		
		
		<%}else{ %>
		
		<li><a href="/waydplace/FrontalGestionnaire?action=<%=FrontalGestionnaire.DECONNEXION_GESTIONNAIRE %>"><span
						class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
	<%} %>	
			</ul>
		</div>
	</div>
</nav>
