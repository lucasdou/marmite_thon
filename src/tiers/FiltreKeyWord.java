package tiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.IFiltre;
import mainpackage.Recette;

/** 
 * Plugin permettant de filtrer les recettes par mot clés
 * 
 */
public class FiltreKeyWord implements IFiltre {


	@Override
	public List<String> listFiltres() {
		List<String> listeFiltreKeyWords =  new ArrayList<String>();
		
		listeFiltreKeyWords.add("Burger");
		listeFiltreKeyWords.add("Plat chaud");
		listeFiltreKeyWords.add("Plat froid");
		listeFiltreKeyWords.add("Pizza");
		
		return listeFiltreKeyWords;
	}

	@Override
	public List<Recette> filterRecettes(List<Recette> recettes, String filter) {
		List<Recette> recettesFilter = new ArrayList<>();
		recettesFilter = recettes.stream().filter(r -> r.getKeywords().contains(filter)).collect(Collectors.toList());
		return recettesFilter;
	}
}
