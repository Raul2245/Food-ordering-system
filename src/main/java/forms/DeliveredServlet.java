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
 * Servlet implementation class DeliveredServlet
 */
@WebServlet("/DeliveredServlet")
public class DeliveredServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveredServlet() {
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
        
        for (int i = 1; i <= cbll.getNbDeliveredOrders((int)request.getSession().getAttribute("userID")); i++) {
        	System.out.println(i + request.getParameter("livrat" + i));
        	if (request.getParameter("livrat" + i) != null) {
        		comandaID = Integer.parseInt((String)request.getParameter("comanda" + i));
        		break;
        	}
        }

        try {
        	String query = "UPDATE comanda SET status_comanda = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setString(1, "Livrata");
            ps.setInt(2, comandaID);
            
            ps.execute();

            
            query = "UPDATE utilizator SET comanda_curenta = ? WHERE id = ?;";
            ps = connection.prepareStatement(query);
            
            ps.setBoolean(1, Boolean.FALSE);
            ps.setInt(2, Integer.parseInt((String) request.getParameter("clientID")));
            
            ps.execute();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        request.getSession().setAttribute("order", null);
        request.getSession().setAttribute("restaurantID", null);
        request.getSession().setAttribute("delivered", cbll.getDeliveredOrders((int)request.getSession().getAttribute("userID")));
        response.sendRedirect("http://localhost:8080/SE1/view-current-delivery.jsp");
	}

}
