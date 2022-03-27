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
	
	public static void updateMonitor(String plugin, String status) {
		plugins.put(plugin, status);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM : hh:mm:ss");
		LocalDateTime time = LocalDateTime.now();
		addMonitorLine(time.format(formatter),plugin, status);
		refreshMonitor();	
		mainContainer.validate();
		mainContainer.repaint();
	}

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

	private static void addMonitorLine(String time, String name, String status) {
		String newLine = time + " : Le plugin : "+ name + " est " + status;
		dlm.addElement(newLine);	
	}
}
