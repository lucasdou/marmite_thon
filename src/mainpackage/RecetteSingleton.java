package mainpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Singleton utilisé pour les recettes de l'application
 *
 */
public class RecetteSingleton {
	
	static RecetteSingleton recetteSingleton;
	
	private List<Recette> listRecette;
	private List<Ingredient> ingredientsSalade;
	private List<Ingredient> ingredientsBurger;
	private List<Ingredient> ingredientsLasagne;
	private List<Ingredient> ingredientsTartiflette;
	private List<Ingredient> ingredientsPizzaRegina;
	private List<Ingredient> ingredientsBurgerSavoyard;
	private List<Ingredient> ingredientsTiramisu;
	
	private RecetteSingleton() {
		
		this.ingredientsSalade = new ArrayList<>();
		this.ingredientsBurger = new ArrayList<>();
		this.ingredientsLasagne = new ArrayList<>();
		this.ingredientsTartiflette = new ArrayList<>();
		this.ingredientsPizzaRegina = new ArrayList<>();
		this.ingredientsBurgerSavoyard = new ArrayList<>();
		this.ingredientsTiramisu = new ArrayList<>();
		this.listRecette = new ArrayList<>();
		
		ingredientsSalade.add(new Ingredient("Poulet",239));
		ingredientsSalade.add(new Ingredient("Salade",15));
		
		ingredientsBurger.add(new Ingredient("Pain hamburger",150));
		ingredientsBurger.add(new Ingredient("Steack haché",241));
		ingredientsBurger.add(new Ingredient("Salade",15));
		ingredientsBurger.add(new Ingredient("Tomate",20));
		
		ingredientsLasagne.add(new Ingredient("Pate à Lasagne",349));
		ingredientsLasagne.add(new Ingredient("Sauce tomate",29));
		ingredientsLasagne.add(new Ingredient("Viande hachée",241));
		ingredientsLasagne.add(new Ingredient("Béchamel",105));
		
		ingredientsTartiflette.add(new Ingredient("Pommes de terre",73));
		ingredientsTartiflette.add(new Ingredient("Reblochon",330));
		ingredientsTartiflette.add(new Ingredient("Lardons",258));
		ingredientsTartiflette.add(new Ingredient("Oignons",40));
		
		ingredientsPizzaRegina.add(new Ingredient("Pate à pizza",267));
		ingredientsPizzaRegina.add(new Ingredient("Jambon",267));
		ingredientsPizzaRegina.add(new Ingredient("Champignons",22));
		ingredientsPizzaRegina.add(new Ingredient("Sauce tomate",29));
		
		ingredientsBurgerSavoyard.add(new Ingredient("Pain hamburger",150));
		ingredientsBurgerSavoyard.add(new Ingredient("Steack haché",241));
		ingredientsBurgerSavoyard.add(new Ingredient("Pommes de terre",73));
		ingredientsBurgerSavoyard.add(new Ingredient("Reblochon",330));
		
		ingredientsTiramisu.add(new Ingredient("Oeufs",150));
		ingredientsTiramisu.add(new Ingredient("Mascarpone",500));
		ingredientsTiramisu.add(new Ingredient("Cacao amer",350));
		ingredientsTiramisu.add(new Ingredient("Sucre vanillé",396));
		
		this.listRecette.add(new Recette("Salade de poulet", ingredientsSalade, "Entrée", Arrays.asList("Salade","Plat froid")));
		this.listRecette.add(new Recette("Burger classique", ingredientsBurger, "Plat", Arrays.asList("Burger","Plat chaud")));
		this.listRecette.add(new Recette("Lasagnes", ingredientsLasagne, "Plat", Arrays.asList("Plat chaud")));
		this.listRecette.add(new Recette("Tartiflette", ingredientsTartiflette, "Plat", Arrays.asList("Plat chaud")));
		this.listRecette.add(new Recette("Pizza Regina", ingredientsPizzaRegina, "Plat", Arrays.asList("Pizza","Plat chaud")));
		this.listRecette.add(new Recette("Burger Savoyard", ingredientsBurgerSavoyard, "Plat", Arrays.asList("Burger","Plat chaud")));
		this.listRecette.add(new Recette("Tiramisu", ingredientsTiramisu, "Dessert", Arrays.asList("Plat froid")));
	}

	public static RecetteSingleton getInstance() {
		if(recetteSingleton == null) {
			recetteSingleton = new RecetteSingleton();
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
