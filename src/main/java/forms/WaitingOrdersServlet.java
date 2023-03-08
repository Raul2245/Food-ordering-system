package forms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ComandaBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class WaitingOrdersServlet
 */
@WebServlet("/WaitingOrdersServlet")
public class WaitingOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaitingOrdersServlet() {
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
        
        ResultSet rs = (ResultSet)request.getSession().getAttribute("ordersAngajat");
        int nbOrders = cbll.getNbOrdersAngajat((int)request.getSession().getAttribute("restaurantID"));
        
        
        System.out.println("Avem atatea comenzi: %d" + nbOrders);
        
        int acceptOrder = 0;
        int refuzOrder = 0;
        for (int i = 1; i <= nbOrders; i++) {
        	System.out.println(i + request.getParameter("accepta" + i));
        	if (request.getParameter("accepta-order" + i) != null) {
        		acceptOrder = Integer.parseInt((String)request.getParameter("comanda" + i));
        		break;
        	}
        	else if (request.getParameter("refuza-order" + i) != null) {
        		refuzOrder = Integer.parseInt((String)request.getParameter("comanda" + i));
        		break;
        	}
        }

        try {
        	String query = "UPDATE comanda SET status_comanda = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            
            if (acceptOrder != 0) {
            	ps.setString(1, "Acceptata de restaurant");
            	ps.setInt(2, acceptOrder);
            }
            else if (refuzOrder != 0) {
            	ps.setString(1, "Refuzata de restaurant");
            	ps.setInt(2, refuzOrder);
            	ps.execute();
            	
            	query = "UPDATE utilizator SET comanda_curenta = ? WHERE id = ?;";
                ps = connection.prepareStatement(query);
                
                ps.setBoolean(1, Boolean.FALSE);
                ps.setInt(2, refuzOrder);
            }
          
            ps.execute();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        System.out.println("RestaurantID: waiting " + (int) request.getSession().getAttribute("restaurantID"));
        request.getSession().setAttribute("prepared", cbll.getPreparedOrders((int) request.getSession().getAttribute("restaurantID")));
        request.getSession().setAttribute("ordersAngajat", cbll.getOrdersAngajat((int) request.getSession().getAttribute("restaurantID")));
        response.sendRedirect("http://localhost:8080/SE1/angajat.jsp");
	}

}
