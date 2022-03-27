package tiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.IFiltre;
import mainpackage.Recette;

/**
 * Plugin permettant de filtrer les recettes par leurs types
 * 
 */
public class FiltreTypeRecette implements IFiltre{
	
	@Override
	public List<String> listFiltres() {
		List<String> listeFiltreTypeRecette = new ArrayList<String>();
		
		listeFiltreTypeRecette.add("Entrée");
		listeFiltreTypeRecette.add("Plat");
		listeFiltreTypeRecette.add("Dessert");
		
		return listeFiltreTypeRecette;
	}

	@Override
	public List<Recette> filterRecettes(List<Recette> recettes, String filter) {
		List<Recette> recettesFilter = new ArrayList<>();
		recettesFilter = recettes.stream().filter(r -> r.getType().contentEquals(filter)).collect(Collectors.toList());
		return recettesFilter;
	}
}
