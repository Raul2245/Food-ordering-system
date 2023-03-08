package BLL;

import connection.ConnectionFactory;
import forms.LoginServlet;
import model.Mode;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilizatorBLL {

    public String modeToString(Mode m) {
        switch (m) {
            case ADMINISTRATOR -> {
                return "Administrator";
            }
            case CLIENT -> {
                return "Client";
            }
        }

        return "";
    }

    public int addUser(String username, String password, String nume, String telefon, String email, String adresa, Mode mod) {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet users = null;

        try {
            statement = connection.createStatement();
            users = statement.executeQuery("SELECT * FROM utilizator");

            boolean flagUsername = false;
            boolean flagEmail = false;
            while (users.next()) {
                String tempUser = users.getString("numeUtilizator");
                String tempEmail = users.getString("email");

                System.out.println(tempUser + tempEmail);
                if (tempUser.indexOf(username) != -1)
                    flagUsername = true;
                if (tempEmail.indexOf(email) != -1)
                    flagEmail = true;

            }
            if (flagUsername == true) {
                //JOptionPane.showMessageDialog(null, "Username already exists.");

                return 1;
            }
            if (flagEmail == true) {
                //JOptionPane.showMessageDialog(null, "Email already exists.");
                return 2;
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String query = "INSERT INTO utilizator(numeUtilizator, parola, email, nume, adresa, telefon, mod_cont, comanda_curenta) VALUES('" + username + "', '" + password
                + "', '" + email + "', '" + nume + "', '" + adresa + "', '" + telefon + "', '" + modeToString(mod) + "', " + Boolean.FALSE +");";

        try {
            Statement sta = connection.createStatement();
            sta.execute(query);
            return 0;
            //JOptionPane.showMessageDialog(null, "User was succesfully created.");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error creating user.");
            return 3;
        }
    }
    
    public ResultSet getUsers() {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet users = null;

        try {
            statement = connection.createStatement();
            users = statement.executeQuery("SELECT * FROM utilizator");

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       return users;
    }
    
    public ResultSet getUser(int id) {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet users = null;

        try {
            statement = connection.createStatement();
            users = statement.executeQuery("SELECT * FROM utilizator WHERE id = " + id);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       return users;
    }

    public Boolean hasOnGoingOrder(int userID) {
    	 Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

         Statement statement = null;
         ResultSet users = null;

         try {
             statement = connection.createStatement();
             users = statement.executeQuery("SELECT comanda_curenta FROM utilizator WHERE id = " + userID);

         } catch (SQLException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
         }

        try {
			users.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			return users.getBoolean("comanda_curenta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
    
    public void updateHasOrder(int userID, Boolean current) {
    	Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute("UPDATE utilizator SET comanda_curenta = " + current + " WHERE id = " + userID);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
       
      
    }
    
    public String getUserName(int id) {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet users = null;

        try {
            statement = connection.createStatement();
            users = statement.executeQuery("SELECT nume FROM utilizator WHERE id = " + id);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       try {
		users.next();
	    return users.getString("nume");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return null;
    }
    
    public String getUserAdress(int id) {
        Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet users = null;

        try {
            statement = connection.createStatement();
            users = statement.executeQuery("SELECT adresa FROM utilizator WHERE id = " + id);

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       try {
		users.next();
		return users.getString("adresa");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return null;
    }
    
}
