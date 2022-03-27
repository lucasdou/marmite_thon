package mainpackage;

import java.util.List;

/**
 * Classe représentant les recettes de l'application
 *
 */
public class Recette {

	String nom;
	List<Ingredient> ingredients;
	String type;
	List<String> keywords;
	
	public Recette(String nom, List<Ingredient> ingredients, String type, List<String> keywords) {
		super();
		this.nom = nom;
		this.ingredients = ingredients;
		this.type = type;
		this.keywords = keywords;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	
	
}
