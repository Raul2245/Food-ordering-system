package forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
import connection.ConnectionFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id, mod_cont FROM utilizator WHERE numeUtilizator = ? and parola = ?;");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				request.getSession().setAttribute("userID", rs.getInt("id"));
				if (((String)rs.getString("mod_cont")).equals("Administrator")) {
					AdminNotificationsBLL anbll = new AdminNotificationsBLL();
					
					request.getSession().setAttribute("notifs", anbll.getNotifs());
					RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
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
