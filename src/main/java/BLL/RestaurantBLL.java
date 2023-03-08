package BLL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;

public class RestaurantBLL {

	public void addRestaurant(String nume, String descriere) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet users = null;
        
        String[] splittedString = nume.split(" ");
        String logo = "";
        
        for(String s: splittedString) {
        	logo += s.toLowerCase() + "-";
        }
        
        logo += "logo.png";

        String query = "INSERT INTO restaurant(nume, descriere, logo) VALUES('" + nume + "', '" + descriere
                + "', '" + logo + ");";

        try {
            Statement sta = connection.createStatement();
            sta.execute(query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
	}
	
	public String builtLogoName(String nume) {
		String[] splittedString = nume.split(" ");
        String logo = "";
        
        for(String s: splittedString) {
        	logo += s.toLowerCase() + "-";
        }
        
        logo += "logo.png";
        
        return logo;
	}
	
	public String getRestaurantName(int id) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet rs = null;

        String query = "SELECT nume FROM restaurant WHERE id = " + id + ";";

        try {
            Statement sta = connection.createStatement();
            rs = sta.executeQuery(query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        try {
			rs.next();
			return rs.getString("nume");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
	}
	
	public String getRestaurantAdress(int id) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet rs = null;

        String query = "SELECT descriere FROM restaurant WHERE id = " + id + ";";

        try {
            Statement sta = connection.createStatement();
            rs = sta.executeQuery(query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        try {
			rs.next();
			return rs.getString("descriere");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
	}
	
	public int getRestaurantNbOfProducts(int id) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet rs = null;

        String query = "SELECT COUNT(*) FROM produs WHERE restaurantID = " + id + ";";

        try {
            Statement sta = connection.createStatement();
            rs = sta.executeQuery(query);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        
        try {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return 0;
	}
}
