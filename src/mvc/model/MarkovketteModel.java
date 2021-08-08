//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.model;

/**
 * Das Model ist komplett unabhängig von den anderen Klassen und weiß nicht was
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

    /**
     * Konstruktor der Klasse MarkovketteModel
     *
     * @param zustaende
     */
    // ************************************************************************
    // ** Konstruktor der Klasse MarkovketteModel
    // ************************************************************************
    public MarkovketteModel(int zustaende) {
        // Leerer Konstruktor
        this.uebergangsmatrix = new double[zustaende][zustaende];
        this.startverteilung = new double[zustaende];
        this.folgeverteilung = new double[zustaende];
    }

    // ************************************************************************
    // ** Getter
    // ************************************************************************

    public double[][] getUebergangsmatrix() {
        return this.uebergangsmatrix;
    }

    public double[] getStartverteilung() {
        return this.startverteilung;
    }

    public double[] getFolgeverteilung() {
        return this.folgeverteilung;
    }

    // ************************************************************************
    // ** Setter
    // ************************************************************************

    public void setStartverteilung(double[] startverteilung) {
        this.startverteilung = startverteilung;
    }

    public void setFolgeverteilung(double[] folgeverteilung) {
        this.folgeverteilung = folgeverteilung;
    }

    public void setUebergangsmatrix(double[][] uebergangsmatrix) {

        for (int i = 0; i < uebergangsmatrix.length; i++) {
            for (int j = 0; j < uebergangsmatrix[i].length; j++) {
                this.uebergangsmatrix[i][j] = uebergangsmatrix[i][j];
                // this.tfArrayUebergangsmatrix[i][j].setSize(1, 1);
            }
        }
    }

    // ************************************************************************
    // ** Methode, um Matrizen zu multiplizieren
    // ************************************************************************

    /**
     * @param a Anfangs-/Folgeverteilung
     * @param b Uebergangsmatrix
     * @return erg Folgeverteilung
     */
    public double[] multipliziere(double[] a, double[][] b) {
        double[] erg = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            erg[i] = 0;
            for (int j = 0; j < a.length; j++) {
                erg[i] = erg[i] + a[j] * b[j][i];
            }
        }
        return erg;
    }

    // updateModel evtl. in updateView umbenennen
    public void updateModel(double[] startverteilung, double[] folgeverteilung,
                            double[][] uebergangsmatrix) {

        // TODO: Andere Codezeilen noch entsprechend nach dem if -> else Muster anpassen !!!
        // Wenn Parameter nicht gefüllt
        if (startverteilung == null
                || startverteilung.length != this.startverteilung.length) {
            this.startverteilung = null;
        } else {
            // Wenn Parameter gefüllt
            if (startverteilung != null
                    && startverteilung.length == this.startverteilung.length)
                for (int i = 0; i < startverteilung.length; i++) {
                    this.startverteilung[i] = startverteilung[i];
                }
        }

        if (folgeverteilung == null || folgeverteilung.length == 0) {
            this.folgeverteilung = null;
        }

        if (uebergangsmatrix == null || uebergangsmatrix.length == 0) {
            this.uebergangsmatrix = null;
        }

        // Folgeverteilung
        if (folgeverteilung != null || folgeverteilung.length != 0) {
            if (this.folgeverteilung != null) {
                this.folgeverteilung = null;
            }
            for (int i = 0; i < folgeverteilung.length; i++) {
                this.folgeverteilung[i] = folgeverteilung[i];
            }
        }

        // Übergangsmatrix
        if (uebergangsmatrix != null || uebergangsmatrix.length != 0) {
            if (this.uebergangsmatrix != null) {
                this.uebergangsmatrix = null;
            }
            for (int i = 0; i < uebergangsmatrix.length; i++) {
                for (int j = 0; j < uebergangsmatrix[i].length; j++) {
                    this.uebergangsmatrix[i][j] = uebergangsmatrix[i][j];
                    // this.tfArrayUebergangsmatrix[i][j].setSize(1, 1);
                }
            }
        }
    }
}
