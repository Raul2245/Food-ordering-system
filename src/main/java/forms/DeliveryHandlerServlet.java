package forms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ComandaBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class DeliveryHandlerServlet
 */
@WebServlet("/DeliveryHandlerServlet")
public class DeliveryHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryHandlerServlet() {
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
        
        for (int i = 1; i <= cbll.getNbDeliverableOrders(); i++) {
        	System.out.println(i + request.getParameter("accepta" + i));
        	if (request.getParameter("accepta" + i) != null) {
        		comandaID = Integer.parseInt((String)request.getParameter("comanda" + i));
        		break;
        	}
        }
        System.out.println("comanda: " + comandaID);
        try {
        	String query = "UPDATE comanda SET curierID = ?, status_comanda = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setInt(1, (int)request.getSession().getAttribute("userID"));
            ps.setString(2, "Acceptata de curier");
            ps.setInt(3, comandaID);
            
            ps.execute();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        request.getSession().setAttribute("delivery", cbll.getDeliverableOrders());
        response.sendRedirect("http://localhost:8080/SE1/curier.jsp");
	}

}
