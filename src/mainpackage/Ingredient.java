package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

	String nom;
	Integer calorie;
	List<String> listeType;
	List<String> listeGeo;
	
	public Ingredient(String nom, Integer calorie, List<String> listeType, List<String> listeGeo) {
		super();
		this.nom = nom;
		this.calorie = calorie;
		this.listeType = listeType;
		this.listeGeo = listeGeo;
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
	public List<String> getListeType() {
		return listeType;
	}
	public void setListeType(List<String> listeType) {
		this.listeType = listeType;
	}
	public List<String> getListeGeo() {
		return listeGeo;
	}
	public void setListeGeo(List<String> listeGeo) {
		this.listeGeo = listeGeo;
	}
	
	
	
}
