package tiers;

import interfaces.IMakeItVegan;
import mainpackage.Ingredient;

public class MakeItVegan implements IMakeItVegan, Runnable {

	@Override
	public void changeToVegan() {
		RecetteSingleton.getInstance().getListRecette().forEach(recette -> {
			for(Ingredient i : recette.getIngredients()) {
				if (i.getNom().contains("Poulet")) {
					i.setNom("Falafel");
				}
			}
		});
	}

	@Override
	public void run() {
		this.changeToVegan();
	}
}
