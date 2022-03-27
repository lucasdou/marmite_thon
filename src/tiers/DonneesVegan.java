package tiers;

import java.util.List;

import interfaces.IDonnees;
import mainpackage.Recette;
import mainpackage.RecetteVeganSingleton;

/**
 * Plugin permettant de g�n�rer des recettes Vegan
 * 
 */
public class DonneesVegan implements IDonnees {

	@Override
	public List<Recette> getDonnees() {		
		return RecetteVeganSingleton.getInstance().getListRecette();
	}
}
