package tiers;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		recettesFilter = recettes.stream().filter(r -> 
		r.getKeywords().contains(filter)).collect(Collectors.toList());
		return recettesFilter;
	}
}
