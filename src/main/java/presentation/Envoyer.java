package presentation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Ivote;
import model.InfoVote;

import java.io.IOException;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.DataBase;
import dao.VoteDao;

/**
 * Servlet implementation class Envoyer
 */
public class Envoyer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Envoyer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int id =Integer.parseInt(request.getParameter("id"));
		//String iden = request.getParameter("iden");
		//System.out.println(iden);
		Ivote service = new VoteDao();
		InfoVote infoVote;
		InfoVote infoVote1;
		/*InfoVote infoVote2;
		try {
			infoVote1=service.findOne(iden,0);
			infoVote2=service.findOne(iden,1);
			System.out.println(service.findOne(iden,0));
			System.out.println(infoVote2.getNom());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		try {
			//infoVote=service.findOne(iden);
			Connection conn = (Connection) DataBase.getInstance().getConnection();
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM infovotec1 WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				String date = result.getString("date");
				String bulletinvote = result.getString("bulletinvote");
				String iden=result.getString("iden");
				infoVote = new InfoVote(id, iden, nom, prenom,date, bulletinvote);
				infoVote1 = new InfoVote(id, iden, nom, prenom,date, bulletinvote);
				if(!service.rech2(infoVote1))
					service.envoyerC2(infoVote);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
