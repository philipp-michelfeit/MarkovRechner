//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.controller;

import mvc.model.MarkovketteModel;
import mvc.view.MarkovketteView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Der Controller muss sowohl View als auch Model kennen, da dieser für die
 * Kommunikation zwischen beiden sorgt
 *
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class MarkovketteController {

    // ************************************************************************
    // ** Instanzfelder der Klasse MarkovketteController
    // ************************************************************************
    private MarkovketteModel model;

    private MarkovketteView view;

    // ************************************************************************
    // ** Konstruktor der Klasse MarkovketteController
    // ************************************************************************
    public MarkovketteController(int zustaende) {

        this.model = new MarkovketteModel(zustaende);
        this.view = new MarkovketteView(zustaende);

        this.view
                .setStartverteilungLoeschenListener(new StartverteilungLoeschenListener());
        this.view.setAllesLoeschenListener(new AllesLoeschenListener());
        this.view
                .setFolgeverteilungKopierenListener(new FolgeverteilungKopierenListener());
        this.view
                .setFolgeverteilungBerechnenListener(new FolgeverteilungBerechnenListener());
        this.view
                .setStationaereVerteilungBerechnenListener(new StationaereVerteilungBerechnenListener());

        this.view.setVisible(true);
    }

    // Innere Klassen
    class StartverteilungLoeschenListener implements ActionListener {

        // TODO: Funktioniert
        public void actionPerformed(ActionEvent event) {
            // TODO Auto-generated method stub

            try {

//				TODO: updateModel funktioniert noch nicht richtig !
//				model.updateModel(null, view
//						.getFolgeverteilungValues(), view
//						.getUebergangsmatrixValues());
                view.updateView(null, model.getFolgeverteilung(), model
                        .getUebergangsmatrix());

            } catch (Exception exception) {
                // TODO: handle exception
            }

        }
    }

    class AllesLoeschenListener implements ActionListener {

        // TODO: Funktioniert
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            try {

//				model.updateModel(view.getStartverteilungValues(), view
//						.getFolgeverteilungValues(), view
//						.getUebergangsmatrixValues());
                view.updateView(null, null, null);

            } catch (Exception exception) {
                // TODO: handle exception

            }
        }
    }

    class FolgeverteilungKopierenListener implements ActionListener {

        // Funktioniert noch nicht !
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            try {
                // TODO: FUNKTIONIERT noch nicht !!!
                // Folgeverteilung aus dem model in den
                // Startverteilungstextfeldern setzen

            } catch (Exception exception) {
                // TODO: handle exception
            }
        }
    }

    class FolgeverteilungBerechnenListener implements ActionListener {

        // Funktioniert noch nicht !
        public void actionPerformed(ActionEvent e) {

            try {
                // TODO: Prüfen, ob Eingaben in textfeldern korrekt,
                // Übergangsmatrix aus textfeldern ins model aufnehmen,
                // Startverteilung aus textfeldern ins model aufnehmen,
                // Folgeverteilung aus textfeldern ins model aufnehmen,
                // Folgeverteilung im View setzen

                model.setFolgeverteilung(model.multipliziere(model
                        .getStartverteilung(), model.getUebergangsmatrix()));

            } catch (Exception exception) {
                // TODO: handle exception
            }
        }
    }

    class StationaereVerteilungBerechnenListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            try {
                // TODO: Methode ausführen

            } catch (Exception exception) {
                // TODO: handle exception
            }
        }
    }

}
