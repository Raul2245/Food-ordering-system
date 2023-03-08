package model;

public class Produs {
    private int id;
    private String nume;
    private float pret;
    private int restaurantID;
    private String logo;
    private String descriere;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public float getPret() {
		return pret;
	}
	public void setPret(float pret) {
		this.pret = pret;
	}
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	@Override
	public String toString() {
		return "Produs [id=" + id + ", nume=" + nume + ", pret=" + pret + ", restaurantID=" + restaurantID + ", logo="
				+ logo + ", descriere=" + descriere + "]";
	}

    
}
