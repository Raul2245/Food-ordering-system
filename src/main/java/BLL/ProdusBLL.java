package BLL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;
import forms.LoginServlet;

public class ProdusBLL {
	
	public ResultSet getProdus(int id) {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet produs = null;

        try {
            statement = connection.createStatement();
            produs = statement.executeQuery("SELECT * FROM produs WHERE id = " + id);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       return produs;
    }
	
	public ResultSet getProduse(int restaurantID) {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet produs = null;

        try {
            statement = connection.createStatement();
            produs = statement.executeQuery("SELECT * FROM produs WHERE id = " + restaurantID);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       return produs;
    }
	
	public String builtLogoName(int restaurantID) {
		RestaurantBLL rbll = new RestaurantBLL();
		
		String[] splittedString = rbll.getRestaurantName(restaurantID).split(" ");
        String logo = "";
        
        for(String s: splittedString) {
        	logo += s.toLowerCase() + "-";
        }
        
        logo += "product-" + (rbll.getRestaurantNbOfProducts(restaurantID) + 1) + ".png";
        
        return logo;
	}
}
