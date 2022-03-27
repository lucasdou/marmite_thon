package interfaces;

import java.util.List;

import mainpackage.Recette;

public interface IDonnees {
	/**
	 * Méthode qui renvoie les données des recettes de l'application
	 */
	public List<Recette> getDonnees();
}
