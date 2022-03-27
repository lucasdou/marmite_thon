package tiers;

import java.util.List;

import interfaces.IDonnees;
import mainpackage.Recette;
import mainpackage.RecetteSingleton;

/**
 * Plugin qui g�n�re les recettes utilis�es par l'application
 *
 */
public class DonneesDefaut implements IDonnees {
	public static List<Recette> recettes;
	
	@Override
	public List<Recette> getDonnees() {		
		return RecetteSingleton.getInstance().getListRecette();
	}

}
