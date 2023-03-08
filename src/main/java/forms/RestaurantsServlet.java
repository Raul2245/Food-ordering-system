package forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionFactory;
import model.Restaurant;

/**
 * Servlet implementation class RestaurantsServlet
 */
@WebServlet("/RestaurantsServlet")
public class RestaurantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE HTML");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Restaurante</title>");
			out.println("</head>");
			out.println("<body>");
			
			ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
			Connection con = conFac.makeConnection();
			
			Statement st = con.createStatement();
			String query = "SELECT * FROM restaurant";
			ResultSet rs = st.executeQuery(query);
			request.setAttribute("restaurante", rs);

			RequestDispatcher rd = request.getRequestDispatcher("restaurants.jsp");
			rd.forward(request, response);
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);
	}
}
