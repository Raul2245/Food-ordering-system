package BLL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;

public class AngajatBLL {
	
	  public ResultSet getUser(int id) {
	        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	        Statement statement = null;
	        ResultSet users = null;

	        try {
	            statement = connection.createStatement();
	            users = statement.executeQuery("SELECT * FROM angajat WHERE id = " + id);

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       return users;
	    }
}
