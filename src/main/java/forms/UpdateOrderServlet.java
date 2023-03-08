package forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLL.*;
import connection.ConnectionFactory;
import model.ProdusComanda;

/**
 * Servlet implementation class UpdateOrderServlet
 */
@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE HTML>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Update</title>");
			out.println("</head>");
			out.println("<body>");
			
			ArrayList<ProdusComanda> list = (ArrayList<ProdusComanda>)request.getSession().getAttribute("order");
			ArrayList<ProdusComanda> copy = (ArrayList<ProdusComanda>)list.clone();
		
			int removed = 0;
			for (int i = 1; i <= list.size(); i++)
				if (request.getParameter("sterge" + i) != null) {
					copy.remove(i - 1);
					removed = 1;
				}
			
			if (request.getParameter("update") != null) {
				int id = 1;
				for (ProdusComanda p: list) {
					copy.get(id - 1).setCantitate(Integer.parseInt(request.getParameter("cantitate" + id)));
					id++;
				}
				request.getSession().setAttribute("order", copy);	
	    		response.sendRedirect("http://localhost:8080/SE1/view-order.jsp");
			}
			
			if (removed == 1) {
				request.getSession().setAttribute("order", copy);	
	    		response.sendRedirect("http://localhost:8080/SE1/view-order.jsp");
			}
			
			HttpSession ses = request.getSession();
			if (request.getParameter("finish") != null) {
				ComandaBLL bcbll = new ComandaBLL();
				System.out.println("Comanda trimisa in db: " + copy.toString());
				bcbll.plasareComanda((int)ses.getAttribute("restaurantID"), (int)ses.getAttribute("userID"), copy);
				copy = null;
				UtilizatorBLL ubll = new UtilizatorBLL();
				ubll.updateHasOrder((int)request.getSession().getAttribute("userID"), true);
	    		response.sendRedirect("http://localhost:8080/SE1/follow_order.jsp");
			}
    		
			out.println("<p> Produs adaugat cu succes comenzii! ");
			out.println("<form action=\"product.jsp\">");
			out.println("<input type = submit value = Back> </form>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
