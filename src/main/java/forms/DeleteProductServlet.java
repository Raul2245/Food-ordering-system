package forms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionFactory;
import model.ProdusComanda;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
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
		
		String query = "DELETE FROM produs WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(request.getParameter("productID")));
			
			ps.execute();
			
			query = "SELECT * FROM produs WHERE restaurantID = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, (int)request.getSession().getAttribute("restaurantID-login"));
			
			ResultSet rs = ps.executeQuery();
			request.getSession().setAttribute("produseAngajat", rs);
			
    		response.sendRedirect("http://localhost:8080/SE1/produse-angajat.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
