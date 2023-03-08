package forms;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import BLL.RestaurantBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class PictureUploadServlet
 */
@MultipartConfig
@WebServlet("/PictureUploadServlet")
public class PictureUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PictureUploadServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		    // Get the file part from the request
		    Part filePart = request.getPart("logo");

		    // Get the file name from the header
		    String fileName = getFileName(filePart);

		    // Create a File object representing the uploaded file
		    File file = new File("C:\\Users\\pop_r\\new-workspace\\SE1\\src\\main\\webapp\\assets\\imgs\\" + fileName);

		    // Write the uploaded file to the destination file
		    filePart.write(file.getAbsolutePath());

		    RestaurantBLL rbll = new RestaurantBLL();
		    String newName = rbll.builtLogoName((String)request.getParameter("nume"));
		    
		    // Rename the file
		    File newFile = new File("C:\\Users\\pop_r\\new-workspace\\SE1\\src\\main\\webapp\\assets\\imgs\\" + newName);
		    file.renameTo(newFile);
		    
		    System.out.println(newName);
		    
		    ConnectionFactory con = ConnectionFactory.getSingleInstance();
		    Connection c = con.makeConnection();
		    
		    String builtQuery = "INSERT INTO restaurant(nume, descriere, logo) "
		    		+ "VALUES('" + (String)request.getParameter("nume") + "', '" + 
		    		(String)request.getParameter("descriere") + "', '" + rbll.builtLogoName((String)request.getParameter("nume")) + "');";
		    
		    String query = "INSERT INTO notificari_administrator(tip, statut, descriere, actiune) "
		    		+ "VALUES(?, ?, ?, ?);";		    
		   
		    System.out.println("Query: " + builtQuery);
		    System.out.println("Query1: " + query);
			try {
				String notifType = "restaurant";
				Boolean stat = Boolean.FALSE;
				String descriere = "Nume restaurant: " + (String)request.getParameter("nume") + " Adresa restaurant: " + (String)request.getParameter("descriere");
				
				PreparedStatement st = c.prepareStatement(query);
				
				st.setString(1, notifType);
				st.setBoolean(2, stat);
				st.setString(3, descriere);
				st.setString(4, builtQuery);
				st.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    response.sendRedirect("index.jsp");
		  }

		  private String getFileName(Part part) {
		    // Get the header value and extract the file name
		    String header = part.getHeader("Content-Disposition");
		    String fileName = header.substring(header.indexOf("filename=") + 10, header.length() - 1);
		    return fileName;
		  }

}
