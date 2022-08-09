package main;

import javax.swing.UIManager;
import frames.TreeFrame;

public class RunClass {
	public static void main(String[] args) {
		try {
		   UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) { }
		
		@SuppressWarnings("unused")
		TreeFrame tf = new TreeFrame();
	}
}
