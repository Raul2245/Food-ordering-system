package BLL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;

public class AdminNotificationsBLL {
	
	public ResultSet getNotifs() {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();

        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM notificari_administrator WHERE statut = " + Boolean.FALSE + ";");

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

       return rs;
	}
	
	public void updateNotif(int id, Boolean current, Boolean response) {
		Connection connection = ConnectionFactory.getSingleInstance().makeConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute("UPDATE notificari_administrator SET statut = " + current + ", raspuns = " + response + " WHERE id = " + id + ";");

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

	}
	
	public int getNoNotifs(ResultSet rs) {
		int nb = 0;
		
		try {
			while (rs.next()) 
				nb++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nb;
	}
}
