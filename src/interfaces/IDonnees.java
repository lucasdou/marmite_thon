package interfaces;

import java.util.List;

import mainpackage.Recette;

public interface IDonnees {
	/**
	 * M�thode qui renvoie les donn�es des recettes de l'application
	 */
	public List<Recette> getDonnees();
}
