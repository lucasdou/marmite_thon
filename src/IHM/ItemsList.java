package IHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mainpackage.Ingredient;
import mainpackage.Recette;

public class ItemsList {
	
	private JPanel rightPanel;
	
	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
	

	public ItemsList(List<String> ingredients, List<Recette> recettes) {
		
	}
}
