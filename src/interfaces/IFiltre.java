package interfaces;

import java.util.List;

import javax.swing.JPanel;

import mainpackage.Recette;

public interface IFiltre {
	
	/**
	 * M�thode qui renvoie une liste de filtre
	 * @return
	 */
	public List<String> listFiltres();
	
	/**
	 * M�thode qui filtre les recettes en entr�es via le filtre pass� en param�tre
	 * @param recettes
	 * @param filter
	 * @return une liste de recettes
	 */
	public List<Recette> filterRecettes(final List<Recette> recettes, final String filter);
}
