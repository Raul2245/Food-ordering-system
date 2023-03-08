package forms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.UtilizatorBLL;
import connection.ConnectionFactory;
import model.Produs;
import model.ProdusComanda;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<ProdusComanda> order = new ArrayList<ProdusComanda>();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE HTML>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Produse</title>");
			out.println("</head>");
			out.println("<body>");
			
			ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
			Connection con = conFac.makeConnection();
    		
    		int resNo = 0;
			Statement st = con.createStatement();
			ResultSet count = st.executeQuery("SELECT COUNT(*) FROM produs WHERE restaurantID = " + request.getParameter("restaurantID"));
			count.next();
			resNo = count.getInt("COUNT(*)");
    		
    		int produsID = 0;
    		int actualID = 0;
			for (int i = 1; i <= resNo; i++)
				if (request.getParameter("product" + i) != null) {
					produsID = i;
					actualID = Integer.parseInt(request.getParameter("produsID" + i));
					break;
				}
			
    		if (produsID != 0) {
			System.out.println("Am selectat produsul " + produsID);
			int cantitate = 1;
			if(request.getParameter("cantitate" + produsID) != "")
				cantitate = Integer.parseInt(request.getParameter("cantitate" + produsID));
			
			if (request.getSession().getAttribute("order") == null) {
				ArrayList<ProdusComanda> list = new ArrayList<ProdusComanda>();
				list.add(new ProdusComanda(actualID, cantitate));
				
				request.getSession().setAttribute("order", list);
			} else {
				ArrayList<ProdusComanda> list = (ArrayList<ProdusComanda>)request.getSession().getAttribute("order");
				list.add(new ProdusComanda(actualID, cantitate));
				request.getSession().setAttribute("order", list);
			}
			System.out.println("Comanda este:  " + ((ArrayList<ProdusComanda>)request.getSession().getAttribute("order")).toString());
    		}
		    
    		
    		response.sendRedirect("http://localhost:8080/SE1/ProductServlet");
    		
			out.println("<p> Produs adaugat cu succes comenzii! ");
			out.println("<form action=\"product.jsp\">");
			out.println("<input type = submit value = Back> </form>");
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	

		
}
