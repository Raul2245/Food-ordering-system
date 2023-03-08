package model;

public class ProdusComanda {
    private int id;
    private int comandaID;
    private int produsID;
    private int cantitate;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComandaID() {
		return comandaID;
	}
	public void setComandaID(int comandaID) {
		this.comandaID = comandaID;
	}
	public int getProdusID() {
		return produsID;
	}
	public void setProdusID(int produsID) {
		this.produsID = produsID;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	@Override
	public String toString() {
		return "ProdusComanda [id=" + id + ", comandaID=" + comandaID + ", produsID=" + produsID + ", cantitate="
				+ cantitate + "]";
	}
	public ProdusComanda(int produsID, int cantitate) {
		super();
		this.produsID = produsID;
		this.cantitate = cantitate;
	}

    
}
