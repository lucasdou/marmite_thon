package mainpackage;

import java.util.HashMap;
import java.util.List;

public class Recette {

	String nom;
	List<Ingredient> ingredients;
	public Recette(String nom, List<Ingredient> ingredients) {
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
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
