package tiers;

import java.util.ArrayList;
import java.util.List;

import mainpackage.Ingredient;
import mainpackage.Recette;

public class RecetteSingleton {
	static RecetteSingleton recetteSingleton;
	private List<Recette> listRecette;
	private List<Ingredient> ingredientsSalade;
	private RecetteSingleton() {
		this.ingredientsSalade = new ArrayList<>();
		this.listRecette =  new ArrayList<>();
		ingredientsSalade.add(new Ingredient("Poulet",100,null, null));
		ingredientsSalade.add(new Ingredient("Salade",10,null, null));
		this.listRecette.add(new Recette("Salade",ingredientsSalade));
	}

	public static RecetteSingleton getInstance() {
		if(recetteSingleton == null) {
			recetteSingleton = new RecetteSingleton();
		}
		return recetteSingleton;
	}

	public List<Recette> getListRecette() {
		return listRecette;
	}

	public void setListRecette(List<Recette> listRecette) {
		this.listRecette = listRecette;
	}
	
	
}
