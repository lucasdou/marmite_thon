package IHM;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class ItemDetails extends JFrame {
	
	public ItemDetails(String detail) {
		super(detail);
				
		setSize(600,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
