//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.model;

/**
 * Das Model ist komplett unabh‰ngig von den anderen Klassen und weiﬂ nicht was
 * um es herum geschieht.
 * 
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class MarkovketteModel {

	// ************************************************************************
	// ** Instanzvariablen
	// ************************************************************************
	// private int anz_zustaende;

	private double[][] uebergangsmatrix;

	private double[] startverteilung;

	private double[] folgeverteilung;

	private int zustaende;

	/**
	 * Konstruktor der Klasse MarkovketteModel
	 * 
	 * @param zustaende
	 *            Uebergangsmatrix
	 */
	// ************************************************************************
	// ** Konstruktor der Klasse MarkovketteModel
	// ************************************************************************
	public MarkovketteModel(int zustaende) {

		this.zustaende = zustaende;
		this.uebergangsmatrix = new double[zustaende][zustaende];
		this.startverteilung = new double[zustaende];
		this.folgeverteilung = new double[zustaende];
	}

	// ************************************************************************
	// ** Getter
	// ************************************************************************

	public double[][] getUebergangsmatrix() {
		double[][] werte = new double[this.zustaende][this.zustaende];

		for (int x = 0; x < this.uebergangsmatrix.length; x++) {
			for (int y = 0; y < this.uebergangsmatrix[x].length; y++) {
				werte[x][y] = this.uebergangsmatrix[x][y];
			}
		}
		return werte;
	}

	public double[] getStartverteilung() {
		double[] werte = new double[this.zustaende];

		for (int x = 0; x < this.startverteilung.length; x++) {
			werte[x] = this.startverteilung[x];
		}
		return werte;
	}

	public double[] getFolgeverteilung() {
		double[] werte = new double[this.zustaende];

		for (int x = 0; x < this.folgeverteilung.length; x++) {
			werte[x] = this.folgeverteilung[x];
		}
		return werte;
	}

	// ************************************************************************
	// ** Setter
	// ************************************************************************

	public void setStartverteilung(double[] startverteilung) {
		for (int x = 0; x < this.startverteilung.length; x++) {
			this.startverteilung[x] = startverteilung[x];
		}
	}

	public void setFolgeverteilung(double[] folgeverteilung) {
		for (int x = 0; x < this.startverteilung.length; x++) {
			this.folgeverteilung[x] = folgeverteilung[x];
		}
	}

	public void setUebergangsmatrix(double[][] uebergangsmatrix) {

		for (int x = 0; x < this.uebergangsmatrix.length; x++) {
			for (int y = 0; y < this.uebergangsmatrix[x].length; y++) {
				this.uebergangsmatrix[x][y] = uebergangsmatrix[x][y];
			}
		}
	}

	// ************************************************************************
	// ** Methode, um Matrizen zu multiplizieren
	// ************************************************************************
	/**
	 * @param a
	 *            Anfangs-/Folgeverteilung
	 * @param b
	 *            Uebergangsmatrix
	 * @return erg Folgeverteilung
	 */
	public double[] multipliziere(double[] a, double[][] b) {
		double[] erg = new double[a.length];
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a.length; y++) {
				erg[x] = erg[x] + a[y] * b[x][y];
			}
		}
		return erg;
	}

	public void updateModel(double[] startverteilung, double[] folgeverteilung,
			double[][] uebergangsmatrix) {

		// Wenn Startverteilung nicht gesetzt, dann ist this.startverteilung
		// null
		if (startverteilung == null || startverteilung.length == 0) {
			this.startverteilung = new double[this.zustaende];
			// Startverteilung in this.startverteilung eintragen
		} else {

			for (int x = 0; x < this.startverteilung.length; x++) {
				this.startverteilung[x] = startverteilung[x];
			}
		}

		// Wenn Folgeverteilung nicht gesetzt, dann ist this.folgeverteilung
		// null
		if (folgeverteilung == null || folgeverteilung.length == 0) {
			this.folgeverteilung = new double[this.zustaende];
			// Folgeverteilung in this.folgeverteilung eintragen
		} else {
			for (int x = 0; x < this.folgeverteilung.length; x++) {
				this.folgeverteilung[x] = folgeverteilung[x];
			}
		}

		if (uebergangsmatrix == null || uebergangsmatrix.length == 0) {
			this.uebergangsmatrix = new double[this.zustaende][this.zustaende];
		} else {
			for (int x = 0; x < this.uebergangsmatrix.length; x++) {
				for (int y = 0; y < this.uebergangsmatrix[x].length; y++) {
					this.uebergangsmatrix[x][y] = uebergangsmatrix[x][y];
				}
			}
		}
	}
}
