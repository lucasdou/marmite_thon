package IHM;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ItemsList {
	
	private JPanel rightPanel;
	
	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
	
	private List<String> ingredientList;

	public ItemsList(List<String> ingredients) {
		this.ingredientList = ingredients;
		rightPanel = new JPanel();

		rightPanel.setLayout(new BorderLayout());
        		
        JPanel list2 = new JPanel();
        list2.setLayout(new GridLayout(ingredientList.size(),1));

				
		this.ingredientList.forEach((ingredient) -> {
			JPanel ingredientPanel = new JPanel();
			ingredientPanel.setLayout(new GridLayout(3,1));
			
			JLabel name = new JLabel();
			name.setText(ingredient);
			ingredientPanel.add(name);
			
			JButton button = new JButton("Detail");
			button.addActionListener(event -> {
				new ItemDetails(ingredient).setVisible(true);
			});
			ingredientPanel.add(button);
			list2.add(ingredientPanel);
			}
		);
        JScrollPane scroll2 = new JScrollPane(list2);
        
        rightPanel.add(scroll2);
	}
}
