package interfaces;

import javax.swing.JPanel;

import mainpackage.Recette;

public interface IDetails {

	/**
	 * Méthode qui renvoie un JPanel avec les détails d'une recette
	 * @param r
	 * @return
	 */
	public JPanel detailPanel(Recette r);
}
