package interfaces;

import javax.swing.JPanel;

import mainpackage.Recette;

public interface IDetails {

	/**
	 * M�thode qui renvoie un JPanel avec les d�tails d'une recette
	 * @param r
	 * @return
	 */
	public JPanel detailPanel(Recette r);
}
