package tiers;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaces.IDetails;
import mainpackage.Ingredient;
import mainpackage.Recette;

public class DetailDefault implements IDetails {

	@Override
	public JPanel detailPanel(Recette r) {
		JPanel detailPanel = new JPanel();
		detailPanel.setLayout(new GridLayout(2,1));

    	JLabel nomRecette = new JLabel(r.getNom());
    	detailPanel.add(nomRecette);
    		
		JPanel itemList = new JPanel();
		itemList.setLayout(new GridLayout(r.getIngredients().size(),1));
			
		for(Ingredient ingredient : r.getIngredients()) {
			JPanel ingredientItemPanel = new JPanel();
			ingredientItemPanel.setLayout(new GridLayout(3,1));
				
			JLabel nom = new JLabel(ingredient.getNom());
			ingredientItemPanel.add(nom);
				
			JLabel calories = new JLabel("nombre de calorie dans 100g de " 
								+ ingredient.getNom() + " : " + ingredient.getCalorie());
			ingredientItemPanel.add(calories);
				
			itemList.add(ingredientItemPanel);
			}
		JScrollPane scroll = new JScrollPane(itemList);
		detailPanel.add(scroll);
		return detailPanel;
	}
}
