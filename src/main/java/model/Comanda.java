package model;

public class Comanda {
    private int id;
    private int curierID;
    private int restaurantID;
    private int clientID;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCurierID() {
		return curierID;
	}
	public void setCurierID(int curierID) {
		this.curierID = curierID;
	}
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	@Override
	public String toString() {
		return "Comanda [id=" + id + ", curierID=" + curierID + ", restaurantID=" + restaurantID + ", clientID="
				+ clientID + "]";
	}
    
    
    
}
