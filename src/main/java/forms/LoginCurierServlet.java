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
 * Servlet implementation class LoginCurierServlet
 */
@WebServlet("/LoginCurierServlet")
public class LoginCurierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCurierServlet() {
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
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id FROM curier WHERE numeUtilizator = ? and parola = ?;");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				request.getSession().setAttribute("userID", rs.getInt("id"));
				request.getSession().setAttribute("curier", 1);
				
					ComandaBLL cbll = new ComandaBLL();
					
					request.getSession().setAttribute("delivered", cbll.getDeliveredOrders(rs.getInt("id")));
					request.getSession().setAttribute("delivery", cbll.getDeliverableOrders());
					RequestDispatcher rd = request.getRequestDispatcher("curier.jsp");
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
