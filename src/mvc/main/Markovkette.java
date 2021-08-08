//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.main;

import mvc.controller.MarkovketteController;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Diese Klasse wird nur dazu benutzt ein MarkovketteController-Objekt zu
 * initialisieren
 *
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class Markovkette {

    // ************************************************************************
    // ** Main-Methode
    // ************************************************************************
    public static void main(String[] args) {

        // Standard-LAF festlegen
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
        JOptionPane optionPane = new JOptionPane("Zustände");
        String text = optionPane.showInputDialog("Anzahl Zustände ?");
        int zustaende = Integer.parseInt(text);
        if (zustaende >= 2) {
            new MarkovketteController(zustaende);
        } else {

        }

    }

    // TODO: JOptionPane für die Eingabe der Zustände vor Erzeugen des Views !!!

    // Startverteilungstextfelder sind nicht gleich groß wie die folgeverteilungstextfelder !
    // Mögliche Ursache: Der gridx-Wert der Buttons
    // Mögliche Lösung: gridx-Wert der Buttons : JFrame.getSize() / 2
}
