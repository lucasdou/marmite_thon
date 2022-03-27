package interfaces;

import java.util.List;

import javax.swing.JPanel;

import mainpackage.Recette;

public interface IFiltre {
	
	/**
	 * Méthode qui renvoie une liste de filtre
	 * @return
	 */
	public List<String> listFiltres();
	
	/**
	 * Méthode qui filtre les recettes en entrées via le filtre passé en paramètre
	 * @param recettes
	 * @param filter
	 * @return une liste de recettes
	 */
	public List<Recette> filterRecettes(final List<Recette> recettes, final String filter);
}
