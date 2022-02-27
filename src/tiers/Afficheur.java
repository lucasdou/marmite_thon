package tiers;

import interfaces.IAfficheur;

public class Afficheur implements IAfficheur, Runnable {

	@Override
	public void run() {
		System.out.print("test");
	}
}
