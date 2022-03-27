package mainpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Singleton utilisé pour les recettes vegan de l'application
 *
 */
public class RecetteVeganSingleton {
	
	static RecetteVeganSingleton recetteSingleton;
	
	private List<Recette> listRecette;
	private List<Ingredient> ingredientsSalade;
	private List<Ingredient> ingredientsBurger;
	private List<Ingredient> ingredientsLasagne;
	private List<Ingredient> ingredientsGateauYaourtSojaPomme;
	private List<Ingredient> ingredientsPizzaVeganLegumesDHiver;
	private List<Ingredient> ingredientsGaspacho;
	
	private RecetteVeganSingleton() {
		
		this.ingredientsSalade = new ArrayList<>();
		this.ingredientsBurger = new ArrayList<>();
		this.ingredientsLasagne = new ArrayList<>();
		this.ingredientsGateauYaourtSojaPomme = new ArrayList<>();
		this.ingredientsPizzaVeganLegumesDHiver = new ArrayList<>();
		this.ingredientsGaspacho = new ArrayList<>();
		
		this.listRecette = new ArrayList<>();
		
		ingredientsSalade.add(new Ingredient("Falafel",333));
		ingredientsSalade.add(new Ingredient("Salade",15));
		
		ingredientsBurger.add(new Ingredient("Pain hamburger",150));
		ingredientsBurger.add(new Ingredient("Steak de carottes",41));
		ingredientsBurger.add(new Ingredient("Salade",15));
		ingredientsBurger.add(new Ingredient("Tomate",20));
		
		ingredientsLasagne.add(new Ingredient("Pate à Lasagne",349));
		ingredientsLasagne.add(new Ingredient("Sauce tomate",29));
		ingredientsLasagne.add(new Ingredient("Courgette",17));
		ingredientsLasagne.add(new Ingredient("Béchamel",105));
		
		ingredientsGateauYaourtSojaPomme.add(new Ingredient("Pommes golden",55));
		ingredientsGateauYaourtSojaPomme.add(new Ingredient("Farine",360));
		ingredientsGateauYaourtSojaPomme.add(new Ingredient("Yaourt soja",46));
		ingredientsGateauYaourtSojaPomme.add(new Ingredient("Sucre roux",380));
		
		ingredientsPizzaVeganLegumesDHiver.add(new Ingredient("Pate à pizza vegan",150));
		ingredientsPizzaVeganLegumesDHiver.add(new Ingredient("Champignons",22));
		ingredientsPizzaVeganLegumesDHiver.add(new Ingredient("Epinards frais",23));
		ingredientsPizzaVeganLegumesDHiver.add(new Ingredient("Persil",43));
		
		ingredientsGaspacho.add(new Ingredient("Tomates moyennes", 20));
		ingredientsGaspacho.add(new Ingredient("Poivrons rouges", 23));
		ingredientsGaspacho.add(new Ingredient("Concombre", 15));
		ingredientsGaspacho.add(new Ingredient("Oignon rouge", 36));
		ingredientsGaspacho.add(new Ingredient("Basilic", 23));
		ingredientsGaspacho.add(new Ingredient("Persil", 22));
		
		this.listRecette.add(new Recette("Salade de falafel", ingredientsSalade, "Entrée", Arrays.asList("Salade","Plat froid")));
		this.listRecette.add(new Recette("Burger steak carottes", ingredientsBurger, "Plat", Arrays.asList("Burger","Plat chaud")));
		this.listRecette.add(new Recette("Lasagnes de courgettes", ingredientsLasagne, "Plat", Arrays.asList("Plat chaud")));
		this.listRecette.add(new Recette("Gateau au yaourt de soja et pommes", ingredientsGateauYaourtSojaPomme, "Dessert", Arrays.asList("Plat chaud","Gateau")));
		this.listRecette.add(new Recette("Pizza vegan aux legumes d hiver", ingredientsPizzaVeganLegumesDHiver, "Plat", Arrays.asList("Plat chaud","Pizza")));
		this.listRecette.add(new Recette("Gaspacho", ingredientsGaspacho, "Plat", Arrays.asList("Plat froid")));
	}

	public static RecetteVeganSingleton getInstance() {
		if(recetteSingleton == null) {
			recetteSingleton = new RecetteVeganSingleton();
		}
		return recetteSingleton;
	}

	public List<Recette> getListRecette() {
		return listRecette;
	}

	public void setListRecette(List<Recette> listRecette) {
		this.listRecette = listRecette;
	}
}
