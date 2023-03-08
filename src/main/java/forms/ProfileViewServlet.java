package forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionFactory;

/**
 * Servlet implementation class ProfileViewServlet
 */
@WebServlet("/ProfileViewServlet")
public class ProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileViewServlet() {
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
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nume = request.getParameter("nume");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String adresa = request.getParameter("adresa");
		
		String nrInmatriculare = request.getParameter("nrInmatriculare");
		String tipMasina = request.getParameter("tipMasina");
		
		int mode = 0;
		if (request.getParameter("update") != null) {
		try {
			Statement updateStatement = con.createStatement();
			String updateQuery = "UPDATE utilizator SET numeUtilizator = \"" + username + "\", parola = \"" + password + "\", email = \"" + email + "\", nume = \"" + nume + "\", adresa = \"" + adresa + "\", telefon = \"" + telefon + "\" WHERE id = " + request.getSession().getAttribute("userID") + ";";
			
			String query = "SELECT COUNT(*) FROM utilizator WHERE (numeUtilizator = \"" + username + "\" or email = \"" + email + "\") and id != " + request.getSession().getAttribute("userID") + ";";
			Statement st = con.createStatement();
			
			if (request.getSession().getAttribute("curier") != null) {
				updateQuery = "UPDATE curier SET numeUtilizator = \"" + username + "\", parola = \"" + password + "\", email = \"" + email + "\", nume = \"" + nume + "\", adresa = \"" + adresa + "\", telefon = \"" + telefon + "\", numarInmatriculare = \"" + nrInmatriculare + "\", tipMasina = \"" + tipMasina + "\" WHERE id = " + request.getSession().getAttribute("userID") + ";";
				query = "SELECT COUNT(*) FROM curier WHERE (numeUtilizator = \"" + username + "\" or email = \"" + email + "\") and id != " + request.getSession().getAttribute("userID") + ";";
				st = con.createStatement();
				mode = 1;
			}
			
			if (request.getSession().getAttribute("angajat") != null) {
				updateQuery = "UPDATE angajat SET parola = \"" + password + "\" WHERE id = " + request.getSession().getAttribute("userID") + ";";
				updateStatement.execute(updateQuery);
				
				RequestDispatcher rd = request.getRequestDispatcher("angajat.jsp");
				rd.forward(request, response);
				
				mode = 2;
			}
			
			ResultSet r = st.executeQuery(query);
			r.next();
			
			if (mode != 2) {
				if (r.getInt(1) == 0)
				{
					updateStatement.execute(updateQuery);
					if (mode == 0) {
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					} else if (mode == 1) {
						RequestDispatcher rd = request.getRequestDispatcher("curier.jsp");
						rd.forward(request, response);
					}
				}
				else 
				{
					PrintWriter out = response.getWriter();
					out.println("<script>alert(\"Nume utilizator sau email deja exitente!\")</script>");
				}
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		}else {
			request.getSession().setAttribute("userID", null);
			request.getSession().setAttribute("curier", null);
			request.getSession().setAttribute("angajat", null);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
