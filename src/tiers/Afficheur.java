package tiers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import IHM.ItemDetails;
import IHM.ItemsList;
import IHM.SideMenu;

import interfaces.IAfficheur;
import mainpackage.Ingredient;
import mainpackage.Recette;
import pluginloader.Plugin;
import pluginloader.PluginLoader;

public class Afficheur extends JFrame implements IAfficheur, Runnable {
	
	private List<String> availablePlugInList;
	private HashMap<String, Plugin> pluginNoAutoRun;
	private List<Recette> recettes;
		
	private List<String> ingredientList;
	private List<Ingredient> ingredientsSalade;
	
	private HashMap<String, JButton> JButtonPlugInList;

	JPanel leftPanel;
	JPanel rightPanel;
	JPanel buttonPluginListPanel;

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

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
		availablePlugInList.forEach((plugIn) -> {
				JButton button = new JButton(plugIn);
				button.setBackground(Color.RED); //set à désactivé
				button.addActionListener(event -> {
					if(button.getBackground().equals(Color.GREEN)) {
						JButtonPlugInList.get(plugIn).setBackground(Color.RED);
					} else {
						JButtonPlugInList.get(plugIn).setBackground(Color.GREEN);//set à activé
					}
				});
				JButtonPlugInList.put(plugIn, button);
				buttonPluginListPanel.add(button);
				}
			);
		
		leftPanel.add(buttonPluginListPanel);
		mainContainer.add(leftPanel, BorderLayout.WEST);

		
		// Liste des ingrédients
		
		
		rightPanel = new JPanel();

		rightPanel.setLayout(new BorderLayout());
        		
        JPanel list2 = new JPanel();
        list2.setLayout(new GridLayout(ingredientList.size(),1));

				
		ingredientList.forEach((ingredient) -> {
		JPanel ingredientPanel = new JPanel();
		ingredientPanel.setLayout(new GridLayout(3,1));
		
		JLabel name = new JLabel();
		name.setText(ingredient);
		ingredientPanel.add(name);
		
		JButton button = new JButton("Detail");
		button.addActionListener(event -> {
			new ItemDetails(ingredient).setVisible(true);
		});
		ingredientPanel.add(button);
		list2.add(ingredientPanel);
		}
		);
			
		recettes.forEach((recette) -> {
			JPanel recettePanel = new JPanel();
			recettePanel.setLayout(new GridLayout(3,1));
			
			JLabel name = new JLabel();
			name.setText(recette.getNom());
			recettePanel.add(name);
			
			JButton button = new JButton("Detail");
			button.addActionListener(event -> {
				for(Ingredient i : recette.getIngredients()) {
					new ItemDetails(i.getNom()).setVisible(true);
				}				
			});
			recettePanel.add(button);
			
			list2.add(recettePanel);
		});
		
        JScrollPane scroll2 = new JScrollPane(list2);
        
        rightPanel.add(scroll2);
        mainContainer.add(rightPanel);
		
	}
	
	/**
	 * Méthode servant à initialiser les données affichées sur l'interface
	 * 
	 */
	private void setData() {
		availablePlugInList = new ArrayList<String>();
		recettes = new ArrayList<>();
		pluginNoAutoRun = PluginLoader.getPluginsNoAutoRun();
		ingredientsSalade = new ArrayList<>();
		ingredientsSalade.add(new Ingredient("Poulet",100,null, null));
		
		
		for(Plugin p : pluginNoAutoRun.values()) {
			availablePlugInList.add(p.getName());
		}
			
		ingredientList = new ArrayList<String>();
		ingredientList.add("Tomate");
		ingredientList.add("Patate");
		ingredientList.add("Poulet");
		
		recettes.add(new Recette("Salade de poulet",ingredientsSalade));
	}
	
	
	private void clickOnPlugin(ActionEvent e) {
		
	}
}
