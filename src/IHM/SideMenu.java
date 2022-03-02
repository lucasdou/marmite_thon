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
		
		JButtonPlugInList = new HashMap<String, JButton>();

		availablePlugInList = plugIns;
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(4,4,4));
		
		JPanel buttonListPanel = new JPanel();
		buttonListPanel.setLayout(new GridLayout(0,1,2,2));

		// On génere les bouttons à partir d'une liste de plugIns
		availablePlugInList.forEach((plugIn) -> {
				JButton button = new JButton(plugIn);
				button.setBackground(Color.RED); //set à désactivé
				button.addActionListener(event -> {
					if(button.getBackground().equals(Color.GREEN)) {
						JButtonPlugInList.get(plugIn).setBackground(Color.RED);
					} else {
						JButtonPlugInList.get(plugIn).setBackground(Color.GREEN);//set à activé
					}
				});
				JButtonPlugInList.put(plugIn, button);
				buttonListPanel.add(button);
				}
			);
		
		leftPanel.add(buttonListPanel);
	}

}

