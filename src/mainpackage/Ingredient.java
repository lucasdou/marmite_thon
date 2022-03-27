package mainpackage;

/**
 * Classe représentant les ingrédients des recettes
 *
 */
public class Ingredient {

	String nom;
	Integer calorie;

	public Ingredient(String nom, Integer calorie) {
		super();
		this.nom = nom;
		this.calorie = calorie;

	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getCalorie() {
		return calorie;
	}
	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}	
}
