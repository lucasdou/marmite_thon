package tiers;

import java.util.List;

import interfaces.IDonnees;
import mainpackage.Recette;
import mainpackage.RecetteSingleton;

/**
 * Plugin qui g�n�re les recettes utilis�es par l'application
 *
 */
public class DonneesDefaut implements IDonnees, Runnable {
	public static List<Recette> recettes;
	
	@Override
	public void run () {
		recettes = RecetteSingleton.getInstance().getListRecette();				
	}

	@Override
	public List<Recette> getDonnees() {		
		return recettes;
	}

}
