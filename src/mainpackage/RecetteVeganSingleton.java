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
	
	private RecetteVeganSingleton() {
		this.ingredientsSalade = new ArrayList<>();
		this.ingredientsBurger = new ArrayList<>();
		this.ingredientsLasagne = new ArrayList<>();
		this.ingredientsGateauYaourtSojaPomme = new ArrayList<>();
		this.listRecette =  new ArrayList<>();
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
		this.listRecette.add(new Recette("Salade de falafel",ingredientsSalade,"Entrée",Arrays.asList("Salade","Plat froid")));
		this.listRecette.add(new Recette("Burger steak carottes", ingredientsBurger,"Plat",Arrays.asList("Burger","Plat chaud")));
		this.listRecette.add(new Recette("Lasagnes de courgettes", ingredientsLasagne,"Plat",Arrays.asList("Plat chaud")));
		this.listRecette.add(new Recette("Gateau au yaourt de soja et pommes", ingredientsGateauYaourtSojaPomme,"Dessert", Arrays.asList("Plat froid","Gateau")));
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
