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

import BLL.ComandaBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class PreparedOrdersServlet
 */
@WebServlet("/PreparedOrdersServlet")
public class PreparedOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreparedOrdersServlet() {
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
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        ComandaBLL cbll = new ComandaBLL();
        int comandaID = 0;
        
        for (int i = 1; i <= cbll.getNbPreparedAngajat((int)request.getSession().getAttribute("restaurantID")); i++) {
        	if (request.getParameter("livreaza-order" + i) != null) {
        		comandaID = Integer.parseInt((String)request.getParameter("comanda" + i));
        		break;
        	}
        }

        try {
        	String query = "UPDATE comanda SET status_comanda = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setString(1, "Preparata");
            ps.setInt(2, comandaID);
            
            ps.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        request.getSession().setAttribute("prepared", cbll.getPreparedOrders((int)request.getSession().getAttribute("restaurantID")));
        response.sendRedirect("http://localhost:8080/SE1/view-current-order-angajat.jsp");
	}

}
