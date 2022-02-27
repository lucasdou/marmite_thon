package mainpackage;

import java.util.HashMap;

public class Recette {

	String nom;
	HashMap<Ingredient, Integer> ingredients;
	public Recette(String nom, HashMap<Ingredient, Integer> ingredients) {
		super();
		this.nom = nom;
		this.ingredients = ingredients;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public HashMap<Ingredient, Integer> getIngredients() {
		return ingredients;
	}
	public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
