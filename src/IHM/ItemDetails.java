package IHM;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mainpackage.Recette;
import mainpackage.Ingredient;

public class ItemDetails extends JFrame {
	
	public ItemDetails(Recette recette) {
		super(recette.getNom());
		Container mainContainer = this.getContentPane();
		JPanel ingredientPanel = new JPanel();
		for(Ingredient i : recette.getIngredients()) {
			
			JLabel name = new JLabel();
			name.setText(i.getNom());
			ingredientPanel.add(name);
			
		}
		mainContainer.add(ingredientPanel);
		setSize(100,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
