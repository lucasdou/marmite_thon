package tiers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import IHM.ItemDetails;

import interfaces.IAfficheur;
import interfaces.IMakeItVegan;
import mainpackage.Ingredient;
import mainpackage.Recette;
import pluginloader.Plugin;
import pluginloader.PluginLoader;

public class Afficheur extends JFrame implements IAfficheur, Runnable {
	
	private List<Plugin> availablePlugInList;
	private HashMap<String, Plugin> pluginNoAutoRun;
	private List<Recette> recettes;
	
	private HashMap<String, JButton> JButtonPlugInList;

	JPanel leftPanel;
	JPanel rightPanel;
	JPanel buttonPluginListPanel;
	JPanel ingredientPanel;
	
	Recette recetteDetaillee;

	@Override
	public void run() {
		initIHM();
		setVisible(true);
	}
	
	/**
	 * Méthode servant à mettre en place l'IHM
	 */
	private void initIHM() {
		
		setData();
		
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(8,6));
		
		// Liste des Plugins			
		JButtonPlugInList = new HashMap<String, JButton>();
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(4,4,4));
		
		buttonPluginListPanel = new JPanel();
		buttonPluginListPanel.setLayout(new GridLayout(0,1,2,2));

		// On génere les bouttons à partir d'une liste de plugIns
		availablePlugInList.forEach((plugin) -> {
				JButton button = new JButton(plugin.getName());
				button.setBackground(Color.RED); //set à désactivé
				button.addActionListener(event -> {
					if(button.getBackground().equals(Color.GREEN)) {
						JButtonPlugInList.get(plugin.getName()).setBackground(Color.RED);
					} else {
						Thread t = new Thread ((Runnable) PluginLoader.loadPlugin(plugin));
						t.start();
						mainContainer.repaint();
						JButtonPlugInList.get(plugin.getName()).setBackground(Color.GREEN);//set à activé
					}
				});
				JButtonPlugInList.put(plugin.getName(), button);
				buttonPluginListPanel.add(button);
				}
			);
		
		leftPanel.add(buttonPluginListPanel);
		mainContainer.add(leftPanel, BorderLayout.WEST);

		
		//Affichage de la liste des recettes
		
		rightPanel = new JPanel();

		rightPanel.setLayout(new BorderLayout());
        		
        JPanel list2 = new JPanel();
        list2.setLayout(new GridLayout(recettes.size(),1));
			
		recettes.forEach((recetteItem) -> {
			JPanel recettePanel = new JPanel();
			recettePanel.setLayout(new GridLayout(3,1));
			
			JLabel name = new JLabel();
			name.setText(recetteItem.getNom());
			recettePanel.add(name);
			
			JButton button = new JButton("Detail");
			button.addActionListener(event -> {
				recetteDetaillee = recetteItem;
				ingredientPanel.removeAll();
				ingredientPanel.add(setDetails());
				revalidate();
				});
			recettePanel.add(button);
			
			list2.add(recettePanel);
		});
		
        JScrollPane scroll2 = new JScrollPane(list2);
        
        rightPanel.add(scroll2);
        mainContainer.add(rightPanel);
        
        
       // Affiche détails d'une recette
        ingredientPanel = setDetails();
        mainContainer.add(ingredientPanel, BorderLayout.EAST);
		
	}
	
	/**
	 * Méthode servant à initialiser les données affichées sur l'interface
	 * 
	 */
	private void setData() {
		availablePlugInList = new ArrayList<>();
		recettes = new ArrayList<>();
		pluginNoAutoRun = PluginLoader.getPluginsNoAutoRun();
		
		for(Plugin p : pluginNoAutoRun.values()) {
			availablePlugInList.add(p);
		}
		
		recettes = RecetteSingleton.getInstance().getListRecette();
	}
	
	private JPanel setDetails() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));

        if(recetteDetaillee != null) {
        	
    		JLabel nomRecette = new JLabel(recetteDetaillee.getNom());
    		panel.add(nomRecette);
    		
			JPanel itemList = new JPanel();
			itemList.setLayout(new GridLayout(recetteDetaillee.getIngredients().size(),1));
			
			for(Ingredient ingredient : recetteDetaillee.getIngredients()) {
				
				JPanel ingredientItemPanel = new JPanel();
				ingredientItemPanel.setLayout(new GridLayout(3,1));
				
				JLabel nom = new JLabel(ingredient.getNom());
				ingredientItemPanel.add(nom);
				
				JLabel calories = new JLabel("nombre de calorie dans une quantité de " 
									+ ingredient.getNom() + " : " + ingredient.getCalorie());
				ingredientItemPanel.add(calories);
				
				itemList.add(ingredientItemPanel);
				}
			JScrollPane scroll = new JScrollPane(itemList);
			panel.add(scroll);
        }
        return panel;
	}
	
}
