<%@page import="servlet.membre.Frontal"%>
<%@page import="tag.MenuMembre"%>
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


				<%=MenuMembre.get_LI_MON_COMPTE((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_GERER((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_PROPOSE((Profil) session.getAttribute("profil"))%>
				
				<li><a
					href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE%>">Rechercher
				</a></li>
				<li><a
					href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_PLANING_MEMBRE%>">Planning
				</a></li>

				<%=MenuMembre.get_LI_ENVELOPPE((Profil) session
					.getAttribute("profil"))%>
		<%=MenuMembre.get_LI_BADGE((Profil) session
					.getAttribute("profil"))%>


		
			</ul>


			<ul class="nav navbar-nav navbar-right">

				<%=MenuMembre.get_LI_CONNEXION((Profil) session
					.getAttribute("profil"))%>


			</ul>
		</div>
	</div>
</nav>
