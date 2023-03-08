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
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE HTML");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Produse</title>");
			out.println("</head>");
			out.println("<body>");
			
			ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
			Connection con = conFac.makeConnection();
			
			int resNo = 0;
			Statement st = con.createStatement();
			ResultSet count = st.executeQuery("SELECT COUNT(*) FROM restaurant");
			count.next();
			resNo = count.getInt("COUNT(*)");
			
			
			int restaurantID = 0;
			
			if (request.getSession().getAttribute("restaurantID") != null)
				restaurantID = (int)request.getSession().getAttribute("restaurantID");
			else {
			for (int i = 1; i <= resNo; i++) {
				if (request.getParameter("buton" + i) != null) {
					if (request.getParameter("restaurant" + i) != null)
						restaurantID = Integer.parseInt(request.getParameter("restaurant" + i));
					
					break;
				}
			}
			}
			String query = "SELECT * FROM produs WHERE restaurantID = ?;";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, restaurantID);
			request.setAttribute("restaurantID", restaurantID);
			
			ResultSet rs = ps.executeQuery();
			request.setAttribute("produse", rs);
			request.getSession().setAttribute("produse", rs);
			
			RequestDispatcher rd = null;
			
			if (request.getSession().getAttribute("angajat") != null) {
				String query1 = "SELECT * FROM produs WHERE restaurantID = ?;";
				PreparedStatement ps1 = con.prepareStatement(query);
				
				ps1.setInt(1, (int)request.getSession().getAttribute("restaurantID-login"));
				
				
				ResultSet rs1 = ps1.executeQuery();
				request.getSession().setAttribute("produseAngajat", rs1);
				
				 rd = request.getRequestDispatcher("produse-angajat.jsp");
			} else {
				 //response.sendRedirect("http://localhost:8080/SE1/product.jsp");
				 rd = request.getRequestDispatcher("product.jsp");
			}
			
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
