package BLL;

import java.util.ArrayList;
import java.util.HashMap;

import model.ProdusComanda;

public class ProdusComandaBLL {

	public static ArrayList<ProdusComanda> minimizeOrder(ArrayList<ProdusComanda> list) {
		ArrayList<ProdusComanda> copy = new ArrayList<>();
		int[] added = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++)
			added[i] = 0;
		
		int first = 0;
		int second = 0;
		
		for (ProdusComanda p: list) {
			if (added[first] != 1) {
				for (ProdusComanda c: list) {
					if (p.getProdusID() == c.getProdusID() && first != second && second < list.size() && added[first] != 1 && added[second] != 1) {
						copy.add(new ProdusComanda(p.getProdusID(), p.getCantitate() + c.getCantitate()));
						added[first] = 1;
						added[second] = 1;
						second++;
					} else {
						second++;
					}
				}
				if (added[first] == 0) {
					copy.add(new ProdusComanda(p.getProdusID(), p.getCantitate()));
					added[first] = 1;
				}
				first++;
			}
		}
		
		return copy;
	}
}
