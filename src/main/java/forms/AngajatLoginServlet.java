package forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.AdminNotificationsBLL;
import BLL.ComandaBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class AngajatLoginServle
 */
@WebServlet("/AngajatLoginServlet")
public class AngajatLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AngajatLoginServlet() {
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
	
		String username = request.getParameter("restaurantID");
		String password = request.getParameter("parola");
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id FROM angajat WHERE restaurantID = ? and parola = ?;");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				request.getSession().setAttribute("userID", rs.getInt("id"));
				request.getSession().setAttribute("angajat", 1);
				request.getSession().setAttribute("restaurantID", Integer.parseInt(username));
				request.getSession().setAttribute("restaurantID-login", Integer.parseInt(username));
				ps = con.prepareStatement("SELECT id FROM comanda WHERE restaurantID = ? and status_comanda = 'Plasata';");
				ps.setInt(1, Integer.parseInt(username));
		
				rs = ps.executeQuery();
				request.getSession().setAttribute("ordersAngajat", rs);
				RequestDispatcher rd = request.getRequestDispatcher("angajat.jsp");
				rd.forward(request, response);
				
			}
			else 
			{
				PrintWriter out = response.getWriter();
				out.println("<script>alert(\"Nume utilizator sau parola gresite!\")</script>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
