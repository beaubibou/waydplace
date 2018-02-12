<%@page import="servlet.membre.Frontal"%>
<%@page import="tag.MenuMembre"%>
<%@page import="bean.Profil"%>
<%@page import="parametre.ActionPage"%>
<%@page import="text.pageweb.MenuGestionnaireText"%>



	

				<%=MenuMembre.get_LI_ACCEUIL((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_SITE((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_MON_COMPTE((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_GERER((Profil) session
					.getAttribute("profil"))%>

				<%=MenuMembre.get_LI_PROPOSE((Profil) session
					.getAttribute("profil"))%>
					
				
			

				<li><a
					href="/waydplace/Frontal?action=<%=Frontal.REDIRECTION_RECHERCHER_ACTIVITE_MEMBRE%>">Rechercher
				</a></li>
				
						<li><a
					href="<%=Frontal.ACTION_REDIRECTION_NEWS%>">News
				</a></li>
				
					<%=MenuMembre.get_LI_NEWS((Profil) session
					.getAttribute("profil"))%>
			


				<%=MenuMembre.get_LI_ENVELOPPE((Profil) session
					.getAttribute("profil"))%>
				<%=MenuMembre.get_LI_BADGE((Profil) session
					.getAttribute("profil"))%>

		

	
				<%=MenuMembre.get_LI_CONNEXION((Profil) session
					.getAttribute("profil"))%>


		
	
