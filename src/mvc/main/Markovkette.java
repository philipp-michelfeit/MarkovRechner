//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.main;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import mvc.controller.MarkovketteController;

/**
 * Diese Klasse wird nur dazu benutzt ein MarkovketteController-Objekt zu
 * initialisieren, dem der Wert aus dem JOptionPane mitgegeben wird
 * 
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class Markovkette {

	// ************************************************************************
	// ** Main-Methode
	// ************************************************************************
	public static void main(String[] args) {

		Markovkette.setNimbusLAF();

		int zustaende = 0;

		while (zustaende < 2 || zustaende > 20) {

			String text = JOptionPane
					.showInputDialog("Anzahl Zustände ?    (2 - 20)");

			if (text == null || !text.matches("[0-9]+")) {
				text = null;
			} else {
				zustaende = Integer.parseInt(text);
				if (zustaende >= 2 && zustaende <= 20) {
					new MarkovketteController(zustaende);
				} else {
					text = null;
				}
			}
		}
	}

	// Standard-LAF festlegen
	private static void setNimbusLAF() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}
}
