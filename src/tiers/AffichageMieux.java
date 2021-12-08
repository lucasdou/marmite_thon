package tiers;


import interfaces.IAffichage;
import mainpackage.Person;

public class AffichageMieux implements IAffichage {
	@Override
	public void affichage(Person p) {
		System.out.print(p);
	}
}
