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

			<img style="margin-top: 10px; margin-right: 10px;"
				src="/wayd/img/waydLogoLR.png" class="img-rounded"
				alt="Cinque Terre" width="30" height="30">

		</div>


		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">




				<%=MenuMembre.get_LI_MON_COMPTE((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_GERER((Profil) session
					.getAttribute("profil"))%>


				<li><a
					href="/waydplace/Frontal?action=<%=ActionPage.REDIRECTION_PROPOSER_ACTIVITE_MEMBRE%>">Proposer
				</a></li>
				<li><a
					href="/waydplace/Frontal?action=<%=ActionPage.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE%>">Rechercher
				</a></li>
				<li><a
					href="/waydplace/FrontalGestionnaire?action=<%=ActionPage.REDIRECTION_PLANING_GESTIONNAIRE%>">Planning
				</a></li>

				<%=MenuMembre.get_LI_ENVELOPPE((Profil) session
					.getAttribute("profil"))%>




			</ul>


			<ul class="nav navbar-nav navbar-right">

				<%=MenuMembre.get_LI_CONNEXION((Profil) session
					.getAttribute("profil"))%>


			</ul>
		</div>
	</div>
</nav>
