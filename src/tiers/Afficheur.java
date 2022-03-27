package tiers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaces.IAfficheur;
import interfaces.IDetails;
import interfaces.IDonnees;
import interfaces.IFiltre;
import mainpackage.Ingredient;
import mainpackage.Recette;
import pluginloader.Plugin;
import pluginloader.PluginLoader;

/**
 * Plugin permettant d'afficher le menu de l'application
 *
 */
public class Afficheur extends JFrame implements IAfficheur, Runnable {
	
	private List<Plugin> availablePlugInList; //Liste des plugins utilisables
	private List<Recette> recettes;//Liste contenant les données des recettes de l'application
	private HashMap<String, JButton> JButtonPlugInList; //Map des boutons permettant d'interagir avec les plugins
	
	// L'ensemble des variables permettant de construire l'IHM
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel buttonPluginListPanel;
	private JPanel ingredientPanel;
	private JPanel detailPanel;
	private JPanel topPanel;
	private Container mainContainer;
	
	// Variables permettant de récupérer les plugins
	private IDonnees donnees;
	private IFiltre filtre;
	private IDetails detail;
	
	@Override
	public void run() {
		setIHM();		
	}

	/**
	 * Méthode qui instancie l'IHM
	 */
	private void setIHM() {
		mainContainer = this.getContentPane();
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Marmite_thon");
		mainContainer.setLayout(new BorderLayout(8,6));		
		setData();
		setLeftPanel();
		setRightPanel();
		setDetailPanel();
		setFiltrePanel();
		setVisible(true);
	}
	
	/**
	 * Méthode servant à mettre en place la partie plugins de l'interface
	 */
	private void setLeftPanel() {		
		// Liste des Plugins			
		JButtonPlugInList = new HashMap<String, JButton>();
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(4,4,4));
		
		buttonPluginListPanel = new JPanel();
		buttonPluginListPanel.setLayout(new GridLayout(0,1,2,2));

		// On génere les bouttons à partir d'une liste de plugIns
		availablePlugInList.forEach((plugin) -> {
				JButton button = new JButton(plugin.getName());
				// Chaque bouton correspondant à un plugin permet de charger le plugin correspondant
				button.addActionListener(event -> {
					if(plugin.getInterf().contentEquals("interfaces.IDonnees")) {
						this.donnees = (IDonnees) PluginLoader.loadPlugin(plugin);
						if(donnees != null) {
							recettes = this.donnees.getDonnees();
							setRightPanel();
							setDetailPanel();
						}						
					}					
					if(plugin.getInterf().contentEquals("interfaces.IFiltre")) {
						filtre = (IFiltre) PluginLoader.loadPlugin(plugin);
						if(filtre != null) {
							setFiltrePanel();
							setDetailPanel();
						}
					}
					if(plugin.getInterf().contentEquals("interfaces.IDetails")) {
						this.detail = (IDetails) PluginLoader.loadPlugin(plugin);
						if(detail != null) {
							setRightPanel();
							setDetailPanel();
						}						
					}
				});
				JButtonPlugInList.put(plugin.getName(), button);
				buttonPluginListPanel.add(button);
				}
			);
		leftPanel.add(buttonPluginListPanel);
		mainContainer.add(leftPanel, BorderLayout.WEST);   	
	}

	/**
	 * Construit la partie "détails de la recette" de l'interface
	 */
	private void setDetailPanel() {
		if(ingredientPanel != null) {
			mainContainer.remove(ingredientPanel);
			mainContainer.validate();
		}
		ingredientPanel = new JPanel();
        mainContainer.add(ingredientPanel, BorderLayout.EAST);
        mainContainer.validate();	
	}

	/**
	 * Définit la partie affichage des recettes de l'interface
	 */
	private void setRightPanel() {
		if(rightPanel != null) {
			mainContainer.remove(rightPanel);
			mainContainer.validate();
		}
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
				//setDetails(recetteItem);
				if(this.detail == null) {
					this.detail = (IDetails) PluginLoader.loadPlugin(PluginLoader.getInstance().getPlugins().get("Detail d'une recette par défaut"));
				}
				ingredientPanel.removeAll();
				ingredientPanel.add(this.detail.detailPanel(recetteItem));
				ingredientPanel.revalidate();
				});
			recettePanel.add(button);
			
			list2.add(recettePanel);
		});
		
        JScrollPane scroll2 = new JScrollPane(list2);
        
        rightPanel.add(scroll2);
        mainContainer.add(rightPanel);
        mainContainer.revalidate();        
	}
	
	/**
	 * Méthode servant à initialiser les données de l'application
	 * 
	 */
	private void setData() {
		availablePlugInList = new ArrayList<>();
		recettes = new ArrayList<>();
		
		for(Plugin p : PluginLoader.getPluginsForUser().values()) {
			if(!p.getName().contains("Monitor")) {
				availablePlugInList.add(p); 
			}			
			if(p.isLoaded() && p.getName().contentEquals("Recettes par defaut")) {
				donnees = (IDonnees) PluginLoader.getPluginLoaded(p);
			}
			if(p.isLoaded() && p.getName().contentEquals("Filtre par type de recette")) {
				filtre = (IFiltre) PluginLoader.getPluginLoaded(p);
			}
		}
		recettes = donnees.getDonnees();	
	}
	
	/**
	 * Méthode permettant d'afficher les données détaillées des recettes
	 */
	private void setDetails(Recette recette) {
		detailPanel = new JPanel();
		detailPanel.setLayout(new GridLayout(2,1));

        if(recette != null) {
        	
    		JLabel nomRecette = new JLabel(recette.getNom());
    		detailPanel.add(nomRecette);
    		
			JPanel itemList = new JPanel();
			itemList.setLayout(new GridLayout(recette.getIngredients().size(),1));
			
			for(Ingredient ingredient : recette.getIngredients()) {
				
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
        }
	}
	
	/**
	 * Méthode qui initialise la partie filtre de l'IHM
	 */
	private void setFiltrePanel() {
		if(topPanel !=null) {
			mainContainer.remove(topPanel);
			mainContainer.validate();
		}
		topPanel = new JPanel();
	
		DefaultComboBoxModel modelFiltresAppliquables = new DefaultComboBoxModel();

		filtre.listFiltres().forEach(f -> modelFiltresAppliquables.addElement(f));
        
        final JComboBox FiltresAppliquables = new JComboBox(modelFiltresAppliquables);    
        FiltresAppliquables.setSelectedIndex(0);

        JScrollPane filterListScrollPane = new JScrollPane(FiltresAppliquables);    
        JButton filterButton = new JButton("Filtrer");
      
        filterButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) { 
              String résulat = "";
              if (FiltresAppliquables.getSelectedIndex() != -1) {                     
            	  résulat = "Filtre appliqué: " 
                    + FiltresAppliquables.getItemAt
                    (FiltresAppliquables.getSelectedIndex());             
              }
              System.out.println(résulat);
              recettes = filtre.filterRecettes(recettes, FiltresAppliquables.getItemAt(FiltresAppliquables.getSelectedIndex()).toString());
              setRightPanel();
              setDetailPanel();
              recettes = donnees.getDonnees();
           }
        }); 
        topPanel.add(filterListScrollPane);          
        topPanel.add(filterButton);
        mainContainer.add(topPanel, BorderLayout.NORTH);
	}
}
