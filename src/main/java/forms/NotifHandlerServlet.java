package forms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.AdminNotificationsBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class NotifHandlerServlet
 */
@WebServlet("/NotifHandlerServlet")
public class NotifHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotifHandlerServlet() {
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
		AdminNotificationsBLL anbll = new AdminNotificationsBLL();
		
		int acceptNotif = 0;
		int refuseNotif = 0;
		for (int i = 1; i <= anbll.getNoNotifs(anbll.getNotifs()); i++) {
			if(request.getParameter("accepta-notif" + i) != null) {
				acceptNotif = i;
				break;
			}
			else if(request.getParameter("refuza-notif" + i) != null) {
				refuseNotif = i;
				break;
			}
		}
		
			System.out.println("S-a acceptat cererea : " + acceptNotif);
			System.out.println("S-a refuzat cererea : " + refuseNotif);
			System.out.println("Cereri : " + anbll.getNoNotifs(anbll.getNotifs()));
		
			if(acceptNotif != 0) {
				ConnectionFactory con = ConnectionFactory.getSingleInstance();
				Connection c = con.makeConnection();
	
				try {
					Statement st = c.createStatement();
					st.execute((String)request.getParameter("actiune" + acceptNotif));
					anbll.updateNotif(Integer.parseInt((String)request.getParameter("notif" + acceptNotif)), Boolean.TRUE, Boolean.TRUE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(refuseNotif != 0) {
				anbll.updateNotif(Integer.parseInt((String)request.getParameter("notif" + refuseNotif)), Boolean.TRUE, Boolean.FALSE);
			}
		
		request.getSession().setAttribute("notifs", anbll.getNotifs());
		response.sendRedirect("http://localhost:8080/SE1/admin.jsp");
	}

}
