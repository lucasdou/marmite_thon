package IHM;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mainpackage.Recette;
import mainpackage.Ingredient;

public class ItemDetails extends JFrame {
	
	private JPanel detailPanel;
	
	public JPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(JPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
	private Recette recipe;

	public ItemDetails(Recette recette) {
		super(recette.getNom());
		recipe = recette;
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(8,6));
		
		JPanel ingredientPanel = new JPanel();

		JPanel itemList = new JPanel();
		itemList.setLayout(new GridLayout(recipe.getIngredients().size(),1));
		for(Ingredient ingredient : recette.getIngredients()) {
			
			JPanel ingredientItemPanel = new JPanel();
			ingredientItemPanel.setLayout(new GridLayout(3,1));
			
			JLabel name = new JLabel();
			name.setText(ingredient.getNom());
			ingredientItemPanel.add(name);
			
			JLabel calories = new JLabel();
			calories.setText("nombre de calorie dans une quantité de " + ingredient.getNom() + " : " + ingredient.getCalorie());
			ingredientItemPanel.add(calories);
			
			itemList.add(ingredientItemPanel);
		}
        
		JScrollPane scroll = new JScrollPane(itemList);
        
        ingredientPanel.add(scroll);
        
		mainContainer.add(ingredientPanel);

		setSize(100,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
