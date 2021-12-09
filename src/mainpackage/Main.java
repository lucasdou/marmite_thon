package mainpackage;

import interfaces.IAffichage;
import pluginloader.PluginLoader;

public class Main{
	
	IAffichage afficheur;
		
	public Main() {
			
		afficheur = (IAffichage) PluginLoader.getLoader(IAffichage.class);
		//Chargement des données
		Person p = new Person("machin",22) ;
		//Traitement
		p.setAge(p.getAge()+1) ;
		//Affichage des données
		affichage(p);
			
	}
		
	public static void main(String[] args) {
		new Main();
	}
	
	public void affichage(Person p) {
		afficheur.affichage(p);
	}

}

