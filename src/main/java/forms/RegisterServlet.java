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

import BLL.UtilizatorBLL;
import connection.ConnectionFactory;
import model.Mode;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		UtilizatorBLL ub = new UtilizatorBLL();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nume = request.getParameter("nume");
		String telefon = request.getParameter("telefon");
		String email = request.getParameter("email");
		String adresa = request.getParameter("adresa");
		
		int rs = ub.addUser(username, password, nume, telefon, email, adresa, Mode.CLIENT);
		
		if (rs == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Cont inregistrat cu succes!\")</script> ");
		}
		else if (rs == 1) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Nume utilizator deja existent!\")</script> ");
		}
		else if (rs == 2) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Email deja existent!\")</script> ");
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert(\"Eroare la accesarea bazei de date!\")</script> ");
		}
		
	}

}
