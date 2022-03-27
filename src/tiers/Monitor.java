package tiers;

import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Classe représentant le moniteur de plugins de l'application
 *
 */
public class Monitor extends JFrame implements Runnable {
	private static HashMap<String,String> plugins;
	private JLabel labelList;	
	private JList <String> historic;
	private static DefaultListModel <String> dlm;
	private static DefaultTableModel dtm;
	private JTable table;
	private static Container mainContainer;
	
	@Override
	public void run() {
		setVisible(true);
	}
	
	public Monitor() {
		setTitle("Monitor");
		setBounds(900, 0, 400, 500);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		mainContainer = this.getContentPane();
		
		plugins = new HashMap<>();
		labelList = new JLabel("Liste des plugins charges : ",SwingConstants.LEFT);
		
		dlm = new DefaultListModel<String>();
		historic = new JList<>(dlm);
		String[] entetes = {"Plugin","Status"};
		dtm = new DefaultTableModel(entetes,0);
		table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setMinimumSize(new Dimension(200,150));
        
		add(labelList);
		add(scrollPane);
		add(historic);
	}
	
	/**
	 * Methode qui met à jour les informations du moniteur
	 * @param plugin
	 * @param status
	 */
	public static void updateMonitor(String plugin, String status) {
		plugins.put(plugin, status);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM : hh:mm:ss");
		LocalDateTime time = LocalDateTime.now();
		
		addMonitorLine(time.format(formatter),plugin, status);
		refreshMonitor();	
		mainContainer.validate();
		mainContainer.repaint();
	}

	/**
	 * Méthode qui actualise l'affichage du moniteur
	 */
	private static void refreshMonitor() {
		Object[][] arr = new Object[plugins.size()][2];
		Set<?> entries = plugins.entrySet();
		Iterator<?> entriesIterator = entries.iterator();
		
		int i = 0;
		while(entriesIterator.hasNext()){
		    Map.Entry mapping = (Map.Entry) entriesIterator.next();

		    arr[i][0] = mapping.getKey();
		    arr[i][1] = mapping.getValue();
		    i++;
		}
		
		String[] entetes = {"Plugin","Status"};
		dtm.setDataVector(arr, entetes);
		
	}

	/**
	 * Méthode qui ajoute une ligne d'informations au moniteur
	 * @param time
	 * @param name
	 * @param status
	 */
	private static void addMonitorLine(String time, String name, String status) {
		String newLine = time + " : Le plugin " + name + " est " + status;
		dlm.addElement(newLine);	
	}
	
	/**
	 * Méthode qui ajoute une ligne d'informations au moniteur lorsqu'un plugin dont dépend un plugin qui veut être chargé ne l'est pas
	 * @param plugin
	 * @param pluginDependant
	 */
	public static void warningMonitor(String plugin, String pluginDependant) {
		String newLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM : hh:mm:ss")) + " : Le plugin " + plugin +" doit d abord etre charge pour pouvoir charger le plugin " + pluginDependant;
		dlm.addElement(newLine);
	}
}
