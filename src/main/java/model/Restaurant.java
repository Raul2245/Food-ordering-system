package model;

public class Restaurant {
    private int id;
    private String nume;
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
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", nume=" + nume + ", descriere=" + descriere + "]";
	}
    
}
