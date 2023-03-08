package BLL;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import connection.ConnectionFactory;
import model.ProdusComanda;

public class ComandaBLL {
	public void plasareComanda(int restaurantID, int clientID, ArrayList<ProdusComanda> produse) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet produs = null;

        try {
            statement = connection.createStatement();
            String query = "INSERT INTO comanda(restaurantID, clientID, data_plasarii, status_comanda) VALUES (" + restaurantID + ", " + clientID + ", NOW(), \"Plasata\");";
            statement.execute(query);
            
            ResultSet comanda = statement.executeQuery("SELECT id FROM comanda WHERE restaurantID = " + restaurantID + " and clientID = " + clientID + " and data_plasarii = NOW();");
            comanda.next();
            int comandaID = comanda.getInt("id");
            
            for (ProdusComanda p: produse) {
            	query = "INSERT INTO produseComanda(comandaID, produsID, cantitate) VALUES (" + comandaID + ", " + p.getProdusID() + ", " + p.getCantitate() + ");";
            	statement.execute(query);
            	
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}

	public ResultSet getDeliverableOrders() {
		 Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     Statement statement = null;
	     ResultSet orders = null;

	     try {
	         statement = connection.createStatement();
	         orders = statement.executeQuery("SELECT id, restaurantID, clientID, status_comanda FROM comanda WHERE curierID IS NULL and (status_comanda = 'Preparata' or status_comanda = 'Acceptata de restaurant');");

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       return orders;
	}
	
	public int getNbDeliverableOrders() {
		 Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     Statement statement = null;
	     ResultSet orders = null;

	     try {
	         statement = connection.createStatement();
	         orders = statement.executeQuery("SELECT COUNT(*) FROM comanda WHERE curierID IS NULL;");

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	      try {
			orders.next();
			return orders.getInt("COUNT(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return 0;
	}

	public ResultSet getDeliveredOrders(int id) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     PreparedStatement statement = null;
	     ResultSet orders = null;

	     try {
	    	 String query = "SELECT id, restaurantID, clientID, status_comanda FROM comanda WHERE curierID = ? and status_comanda = 'Acceptata de curier';";
	         statement = connection.prepareStatement(query);
	         
	         statement.setInt(1, id);
	         
	         orders = statement.executeQuery();

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       return orders;
	}
	
	public int getNbDeliveredOrders(int id) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     PreparedStatement statement = null;
	     ResultSet orders = null;

	     try {
	    	 String query = "SELECT COUNT(*) FROM comanda WHERE curierID = ? and status_comanda = 'Acceptata de curier';";
	         statement = connection.prepareStatement(query);
	         
	         statement.setInt(1, id);
	         
	         orders = statement.executeQuery();

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       try {
			orders.next();
			return orders.getInt("COUNT(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return 0;
	}
	
	public ResultSet getPreparableOrders(int comandaID) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     PreparedStatement statement = null;
	     ResultSet orders = null;

	     try {
	         statement = connection.prepareStatement("SELECT p.nume, cantitate FROM produsecomanda AS ps, produs AS p WHERE ps.comandaID = ? and ps.produsID = p.id;");
	         statement.setInt(1, comandaID);
	         orders = statement.executeQuery();

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       return orders;
	}
	
	public int getNbPreparableOrders(int id) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     PreparedStatement statement = null;
	     ResultSet orders = null;

	     try {
	    	 String query = "SELECT COUNT(*) FROM produsecomanda AS ps, produs AS p WHERE ps.comandaID = ? and ps.produsID = p.id;\"";
	         statement = connection.prepareStatement(query);
	         
	         statement.setInt(1, id);
	         
	         orders = statement.executeQuery();

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       try {
			orders.next();
			return orders.getInt("COUNT(*)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return 0;
	}
	
	public String preparableOrdersToString(int comandaID) {
		ResultSet rs = getPreparableOrders(comandaID);
		
		String result = "";
		try {
			while (rs.next()) {
				result += rs.getString("nume") + " X " + rs.getInt("cantitate") + "\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ResultSet getOrdersAngajat(int restaurantID) {
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id FROM comanda WHERE restaurantID = ? and status_comanda = 'Plasata';");
				ps.setInt(1, restaurantID);
		
				rs = ps.executeQuery();
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public int getNbOrdersAngajat(int restaurantID) {
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		ResultSet rs = null;
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM comanda WHERE restaurantID = ? and status_comanda = 'Plasata';");
			ps.setInt(1, restaurantID);
		
			rs = ps.executeQuery();
				
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public ResultSet getPreparedOrders(int restaurantID) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     PreparedStatement statement = null;
	     ResultSet orders = null;

	     try {
	         statement = connection.prepareStatement("SELECT id FROM comanda WHERE restaurantID = ? and status_comanda = 'Acceptata de restaurant';");
	         statement.setInt(1, restaurantID);
	         orders = statement.executeQuery();

	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       return orders;
	}
	
	public int getNbPreparedAngajat(int restaurantID) {
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		ResultSet rs = null;
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM comanda WHERE restaurantID = ? and status_comanda = 'Acceptata de restaurant';");
			ps.setInt(1, restaurantID);
		
			rs = ps.executeQuery();
				
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public String getStatusAsString(int userID) {
		ConnectionFactory conFac = ConnectionFactory.getSingleInstance();
		Connection con = conFac.makeConnection();
	
		ResultSet rs = null;
		String result = "";
		try {
			PreparedStatement ps = con.prepareStatement("SELECT status_comanda FROM comanda WHERE clientID = ? and status_comanda != 'Livrata';");
			ps.setInt(1, userID);
		
			rs = ps.executeQuery();
				
			rs.next();
			result = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int getUserID(int comandaID) {
		 Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

	     PreparedStatement statement = null;
	     ResultSet orders = null;
	     int result = 0;
	     
	     try {
	         statement = connection.prepareStatement("SELECT clientID FROM comanda WHERE id = ?;");
	         statement.setInt(1, comandaID);
	         orders = statement.executeQuery();
	         orders.next();
	         result = orders.getInt("clientID");
	         
	        } catch (SQLException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }

	       return result;
	}
}
