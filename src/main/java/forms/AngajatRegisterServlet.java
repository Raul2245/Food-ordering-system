package forms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.RestaurantBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class AngajatRegisterServlet
 */
@WebServlet("/AngajatRegisterServlet")
public class AngajatRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AngajatRegisterServlet() {
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
	    
	    String builtQuery = "INSERT INTO angajat(restaurantID, parola) "
	    		+ "VALUES('" 
	    		+ Integer.parseInt((String)request.getParameter("restaurantID")) + "', '"
	    		+ (String)request.getParameter("parola") + "');";
	    
	    String query = "INSERT INTO notificari_administrator(tip, statut, descriere, actiune) "
	    		+ "VALUES(?, ?, ?, ?);";
	    
		try {
			RestaurantBLL rbll = new RestaurantBLL();
			String notifType = "angajat";
			Boolean stat = Boolean.FALSE;
			String descriere = "Nume restaurant: " + rbll.getRestaurantName(Integer.parseInt((String)request.getParameter("restaurantID")));
			
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
