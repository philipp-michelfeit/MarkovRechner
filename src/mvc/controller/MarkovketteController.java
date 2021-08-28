//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.view.MarkovketteView;
import mvc.model.MarkovketteModel;

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
	private int zustaende;

	private MarkovketteModel model;

	private MarkovketteView view;

	// ************************************************************************
	// ** Konstruktor der Klasse MarkovketteController
	// ************************************************************************
	public MarkovketteController(int zustaende) {

		this.zustaende = zustaende;
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
	}

	// Innere Klassen
	class StartverteilungLoeschenListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			model.updateModel(null, view.getFolgeverteilungValues(),
					view.getUebergangsmatrixValues());
			view.updateView(model.getStartverteilung(),
					model.getFolgeverteilung(), model.getUebergangsmatrix());
		}
	}

	class AllesLoeschenListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			model.updateModel(null, null, null);
			view.updateView(model.getStartverteilung(),
					model.getFolgeverteilung(), model.getUebergangsmatrix());
		}
	}

	class FolgeverteilungKopierenListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Alle Textfelder aus dem View auslesen und im Modell speichern
			model.updateModel(view.getStartverteilungValues(),
					view.getFolgeverteilungValues(),
					view.getUebergangsmatrixValues());

			// Folgeverteilung im Modell löschen und Startverteilung auf
			// Folgeverteilung setzen
			model.updateModel(model.getFolgeverteilung(), null,
					model.getUebergangsmatrix());

			// View updaten
			view.updateView(model.getStartverteilung(),
					model.getFolgeverteilung(), model.getUebergangsmatrix());
		}
	}

	class FolgeverteilungBerechnenListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Start und Übergangsmatrix in Modell übernehmen
			model.updateModel(view.getStartverteilungValues(), null,
					view.getUebergangsmatrixValues());

			// Folgeverteilung berechnen
			model.setFolgeverteilung(model.multipliziere(
					model.getStartverteilung(), model.getUebergangsmatrix()));

			// Folgeverteilung in View eintragen
			view.updateView(model.getStartverteilung(),
					model.getFolgeverteilung(), model.getUebergangsmatrix());
		}
	}

	class StationaereVerteilungBerechnenListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int count = 0; // Schleifenzähler
			double epsilon = 0.0000001; // Schranke für Gleichheit
			double[] folgev_alt = new double[zustaende];
			double[] folgev_neu = new double[zustaende];
			int isStationaereVerteilung;

			// Start und Übergangsmatrix in Modell übernehmen
			model.updateModel(view.getStartverteilungValues(), null,
					view.getUebergangsmatrixValues());

			do {
				count++; // Schleifenzaehler inkrementieren
				isStationaereVerteilung = 0;

				folgev_alt = model.getFolgeverteilung();

				// Folgeverteilung berechnen
				model.setFolgeverteilung(model.multipliziere(
						model.getStartverteilung(), model.getUebergangsmatrix()));

				folgev_neu = model.getFolgeverteilung();

				// Folgeverteilung neu in Startverteilung kopieren
				model.setStartverteilung(folgev_neu);

				// Sind die Abweichungen der Folgeverteilung kleiner epsilon
				for (int i = 0; i < zustaende; i++) {
					if (Math.abs(folgev_alt[i] - folgev_neu[i]) > epsilon) {
						isStationaereVerteilung = isStationaereVerteilung + 1;
					}
				}
			} while (count < 1000 && isStationaereVerteilung > 0);

			// Start und Übergangsmatrix in Modell übernehmen
			view.updateView(model.getStartverteilung(),
					model.getFolgeverteilung(), model.getUebergangsmatrix());

			// Solange berechnen, bis der neue Wert aus dem
			// Folgeverteilungstextfeld alten Wert
			// entspricht.
			// Wenn nach 1000 Durchläufen nicht konvergiert, dann Abbruch
		}
	}
}
