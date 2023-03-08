package forms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionFactory;

/**
 * Servlet implementation class CurierRegisterServlet
 */
@WebServlet("/CurierRegisterServlet")
public class CurierRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurierRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionFactory con = ConnectionFactory.getSingleInstance();
	    Connection c = con.makeConnection();
	    
	    String builtQuery = "INSERT INTO curier(numeUtilizator, parola, email, nume, adresa, telefon, numarInmatriculare, tipMasina) "
	    		+ "VALUES('" 
	    		+ (String)request.getParameter("username") + "', '"
	    		+ (String)request.getParameter("parola") + "', '"
	    		+ (String)request.getParameter("email") + "', '"
	    		+ (String)request.getParameter("nume") + "', '"
	    		+ (String)request.getParameter("adresa") + "', '" 
	    		+ (String)request.getParameter("telefon") + "', '" 
	    		+ (String)request.getParameter("nrInmatriculare") + "', '" 
	    		+ (String)request.getParameter("tipMasina") + "');";
	    
	    String query = "INSERT INTO notificari_administrator(tip, statut, descriere, actiune) "
	    		+ "VALUES(?, ?, ?, ?);";
	    
		try {
			String notifType = "curier";
			Boolean stat = Boolean.FALSE;
			String descriere = "Nume curier: " + (String)request.getParameter("nume");
			
			PreparedStatement st = c.prepareStatement(query);
			
			st.setString(1, notifType);
			st.setBoolean(2, stat);
			st.setString(3, descriere);
			st.setString(4, builtQuery);
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    response.sendRedirect("index.jsp");
	  }

	}


