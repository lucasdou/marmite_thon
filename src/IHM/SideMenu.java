package IHM;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SideMenu extends JFrame{
	
	private List<String> availablePlugInList;
	private HashMap<String, JButton> JButtonPlugInList;

	JPanel leftPanel;

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public SideMenu(List<String> plugIns) {
		

	}
}

