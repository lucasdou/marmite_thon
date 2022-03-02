package IHM;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
public class UIContainer extends JFrame{
		
	private List<String> availablePlugInList;
		
	private List<String> ingredientList;	
		
	public void setData() {
		availablePlugInList = new ArrayList<String>();
		availablePlugInList.add("plugIn1");
		availablePlugInList.add("plugIn2");
		availablePlugInList.add("plugIn3");
			
		ingredientList = new ArrayList<String>();
		ingredientList.add("Tomate");
		ingredientList.add("Patate");
		ingredientList.add("Carrote");
		ingredientList.add("Tomate");
		ingredientList.add("Patate");
		ingredientList.add("Carrote");
		ingredientList.add("Tomate");
		ingredientList.add("Patate");
		ingredientList.add("Carrote");
		ingredientList.add("Tomate");
		ingredientList.add("Patate");
		ingredientList.add("Carrote");
		}

	public UIContainer() {
			super("Plugin loader APP");
			
			setData();
			
			setSize(600,600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			Container mainContainer = this.getContentPane();
			mainContainer.setLayout(new BorderLayout(8,6));
			
			// Liste des Plugins
			SideMenu plugInMenu = new SideMenu(availablePlugInList);
			mainContainer.add(plugInMenu.getLeftPanel(), BorderLayout.WEST);

			
			// Liste des ingrédients
			ItemsList ingredientItemList = new ItemsList(ingredientList);
			mainContainer.add(ingredientItemList.getRightPanel());
		}
		
		/*public static void main(String[] args) {
			new Main().setVisible(true);
		}*/
}
