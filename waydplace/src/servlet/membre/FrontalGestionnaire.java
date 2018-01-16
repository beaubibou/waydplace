package servlet.membre;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pager.PagerActivite;
import parametre.ActionPage;
import bean.Profil;

/**
 * Servlet implementation class FrontalGestionnaire
 */
public class FrontalGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontalGestionnaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Profil profil = (Profil) session.getAttribute("profil");

		String action = request.getParameter("action");
		System.out.println(action);
		PagerActivite pager=null;
		// System.out.println("jeton"+tokenFireBase);
		if (action == null || action.isEmpty())
			return;

		switch (action) {

		case ActionPage.REDIRECTION_PROPOSER_ACTIVITE_GESTIONNAIRE:
			response.sendRedirect("membre/proposeActiviteMembre.jsp");
			break;

		case ActionPage.REDIRECTION_PROPOSER_PLUSIEURS_ACTIVITE_GESTIONNAIRE:

			int page = 0;
			pager = new PagerActivite(profil.getFiltre(), page);
			request.setAttribute("pager", pager);
			request.getRequestDispatcher("membre/rechercheActivite.jsp")
					.forward(request, response);

			break;
		}
	
	}

}
