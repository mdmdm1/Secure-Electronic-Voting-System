package presentation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Ivote;
import model.InfoVote;

import java.io.IOException;
import dao.VoteDao;

/**
 * Servlet implementation class Verifier
 */

public class Verifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verifier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String date = request.getParameter("datenaissance");
		String iden = request.getParameter("identification");
		String bulletinvote = request.getParameter("bulltinvote");
		
		InfoVote infoVote = new InfoVote(iden,nom,prenom,date,bulletinvote);
		
		Ivote service = new VoteDao();
		
		try {
			if(!service.rech1(iden)) {
			infoVote = service.chiffrer(infoVote);
			service.add(infoVote);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/LeVote.jsp");
			rd.forward(request, response);}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
