package forms;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import BLL.ProdusBLL;
import BLL.RestaurantBLL;
import connection.ConnectionFactory;

/**
 * Servlet implementation class PictureUploadServlet
 */
@MultipartConfig
@WebServlet("/ProductUploadServlet")
public class ProductUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUploadServlet() {
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
		    ProdusBLL pbll = new ProdusBLL();
		    String newName = pbll.builtLogoName((int)request.getSession().getAttribute("restaurantID-login"));
		    
		    // Rename the file
		    File newFile = new File("C:\\Users\\pop_r\\new-workspace\\SE1\\src\\main\\webapp\\assets\\imgs\\" + newName);
		    file.renameTo(newFile);
		    
		    System.out.println(newName);
		    
		    ConnectionFactory con = ConnectionFactory.getSingleInstance();
		    Connection c = con.makeConnection();
		    
		    String query = "INSERT INTO produs(nume, pret, restaurantID, logo, descriere) VALUES(?, ?, ?, ?, ?)"; 
		   
			try {
				PreparedStatement st = c.prepareStatement(query);
				
				st.setString(1, (String)request.getParameter("nume"));
				st.setFloat(2, Float.parseFloat((String)request.getParameter("pret")));
				st.setInt(3, (int)request.getSession().getAttribute("restaurantID-login"));
				st.setString(4, newName);
				st.setString(5, (String)request.getParameter("descriere"));
				st.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    //response.sendRedirect("angajat.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("angajat.jsp");
			rd.forward(request, response);
		  }

		  private String getFileName(Part part) {
		    // Get the header value and extract the file name
		    String header = part.getHeader("Content-Disposition");
		    String fileName = header.substring(header.indexOf("filename=") + 10, header.length() - 1);
		    return fileName;
		  }

}
