package mainpackage;

import java.util.ArrayList;

public class Ingredient {

	String nom;
	Integer calorie;
	ArrayList listeType;
	ArrayList listeGeo;
	
	public Ingredient(String nom, Integer calorie, ArrayList listeType, ArrayList listeGeo) {
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
	public ArrayList getListeType() {
		return listeType;
	}
	public void setListeType(ArrayList listeType) {
		this.listeType = listeType;
	}
	public ArrayList getListeGeo() {
		return listeGeo;
	}
	public void setListeGeo(ArrayList listeGeo) {
		this.listeGeo = listeGeo;
	}
	
	
	
}
