//*****************************************************************************
//** Copyright © 2011 Philipp Michelfeit, All Rights Reserved 				 **
//*****************************************************************************

package mvc.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Die View-Klasse enthält nur die Präsentation. Hier sollte man keinerlei
 * Programmlogik finden. Alle Berechnungen und Reaktionen auf Benutzeraktionen
 * sollten allesamt im Controller stehen.
 * 
 * @author Philipp Michelfeit, 11E1FA
 * @version 1.0
 */
public class MarkovketteView extends JFrame {

	private int anz_zustaende;

	private static final long serialVersionUID = 1L;

	// ************************************************************************
	// ** Label
	// ************************************************************************
	private JLabel lbUebergangsmatrix;

	private JLabel lbStartverteilung;

	private JLabel lbFolgeverteilung;
	
	private JLabel lbPlatzhalter;

	// ************************************************************************
	// ** Textfelder
	// ************************************************************************
	private JTextField[][] tfArrayUebergangsmatrix;

	private JTextField[] tfArrayStartverteilung;

	private JTextField[] tfArrayFolgeverteilung;

	// ************************************************************************
	// ** Buttons
	// ************************************************************************
	private JButton btStartverteilungLoeschen;

	private JButton btAllesLoeschen;

	private JButton btFolgeverteilungKopieren;

	private JButton btFolgeverteilungBerechnen;

	private JButton btStationaereVerteilungBerechnen;

	// ************************************************************************
	// ** Konstruktor der Klasse MarkovketteView
	// ************************************************************************
	/**
	 * Konstruktor der Klasse MarkovKetteView
	 */
	public MarkovketteView(int anz_zustaende) {
		// TODO Auto-generated constructor stub
		super("MarkovRechner: " + anz_zustaende + " Zustände");

		this.anz_zustaende = anz_zustaende;

		// Container-Objekt initialisieren
		Container cP = this.getContentPane();

		// Lokale Variable für GridBagConstraints-Objekte
		GridBagConstraints gbc = this.makeGBC(0, 0, 0, 0, 0, 0);

		// Layoutmanager festsetzen
		cP.setLayout(new GridBagLayout());

		// Setzt das JFrame-Icon
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
				"/calculator-icon.png"));
		Image image = imageIcon.getImage();
		this.setIconImage(image);

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

		// Neue Ein- / Ausgabetextfelder erzeugen
		this.initialisiereJTextFieldArrays(this.anz_zustaende);

		// Labels erzeugen
		this.lbStartverteilung = new JLabel("Startverteilung");
		this.lbFolgeverteilung = new JLabel("Folgeverteilung");
		this.lbUebergangsmatrix = new JLabel("Übergangsmatrix");
		this.lbPlatzhalter = new JLabel(" ");

		// Buttons erzeugen
		this.btStartverteilungLoeschen = new JButton("Startverteilung löschen");
		this.btAllesLoeschen = new JButton("Alles Löschen");
		this.btFolgeverteilungKopieren = new JButton("Folgeverteilung kopieren");
		this.btFolgeverteilungBerechnen = new JButton(
				"Folgeverteilung berechen");
		this.btStationaereVerteilungBerechnen = new JButton(
				"Stationäre Verteilung berechen");

		// Label "lbUebergangsmatrix" zur ContentPane hinzufügen
		gbc = this.makeGBC(this.anz_zustaende + 1, 0, this.anz_zustaende, 1, 1, 1);
		cP.add(this.lbUebergangsmatrix, gbc);

		// Label "lbStartverteilung" zur ContentPane hinzufügen
		gbc = this.makeGBC(0, this.anz_zustaende + 3, this.anz_zustaende, 1, 1, 1);
		cP.add(this.lbStartverteilung, gbc);

		// Label "lbFolgeverteilung" zur ContentPane hinzufügen
		gbc = this.makeGBC(this.anz_zustaende + 1, this.anz_zustaende + 3, this.anz_zustaende, 1, 1,
				1);
		cP.add(this.lbFolgeverteilung, gbc);
		
		// Label "lbPlatzhalter" zur ContentPane hinzufügen
		gbc = this.makeGBC(0, this.anz_zustaende + 5, this.anz_zustaende, 1, 1,
				1);
		cP.add(this.lbPlatzhalter, gbc);

		// Übergangsmatrixtextfelder zur ContentPane hinzufügen
		for (int i = 0; i < tfArrayUebergangsmatrix.length; i++) {
			for (int j = 0; j < tfArrayUebergangsmatrix[i].length; j++) {
				gbc = this.makeGBC(i + this.anz_zustaende + 1, j + 1, 1, 1, 1,
						1);
				cP.add(this.tfArrayUebergangsmatrix[i][j], gbc);
			}
		}

		// Startverteilungstextfelder zur ContentPane hinzufügen
		for (int i = 0; i < this.tfArrayStartverteilung.length; i++) {
			gbc = this.makeGBC(i, this.anz_zustaende + 2, 1, 1, 1, 1);
			cP.add(this.tfArrayStartverteilung[i], gbc);
		}

		// Folgeverteilungstextfelder zur ContentPane hinzufügen
		for (int i = 0; i < tfArrayFolgeverteilung.length; i++) {
			gbc = this.makeGBC(i + this.anz_zustaende + 1,
					this.anz_zustaende + 2, 1, 1, 1, 1);
			cP.add(this.tfArrayFolgeverteilung[i], gbc);
		}

		// Buttons zur ContentPane hinzufügen
		gbc = this.makeGBC(0, this.anz_zustaende + 6, this.anz_zustaende, 1, 1,
				1);
		cP.add(this.btStartverteilungLoeschen, gbc);

		gbc = this.makeGBC(0, this.anz_zustaende + 7, this.anz_zustaende,  1, 1,
				1);
		cP.add(this.btAllesLoeschen, gbc);

		gbc = this.makeGBC(0, this.anz_zustaende + 8, this.anz_zustaende,  1, 1,
				1);
		cP.add(this.btFolgeverteilungKopieren, gbc);

		gbc = this.makeGBC(0, this.anz_zustaende + 9, this.anz_zustaende,  1, 1,
				1);
		cP.add(this.btFolgeverteilungBerechnen, gbc);

		gbc = this.makeGBC(0, this.anz_zustaende + 10, this.anz_zustaende,  1, 1,
				1);
		cP.add(this.btStationaereVerteilungBerechnen, gbc);

		// Standard-Schließ-Operation festlegen
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// WindowListener hinzufügen
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Fenstergröße ändern erlauben / verbieten
		this.setResizable(true);

		// Fenster automatisch positionieren
		this.pack();

		// Minimal mögliche Fenstergröße setzen
		this.setMinimumSize(new Dimension(500, 300));

		// Fenster in der Bildschirmmitte positionieren
		this.setLocation(
				(Toolkit.getDefaultToolkit().getScreenSize().width - this
						.getWidth()) / 2, (Toolkit.getDefaultToolkit()
						.getScreenSize().height - this.getHeight()) / 2);

		// Sichtbarkeit des Fensters einstellen
		this.setVisible(false);
	}

	// ************************************************************************
	// ** Getter für das Zurückgeben der Werte der Textfelder der JTextFieldArrays
	// ************************************************************************

	public double[] getStartverteilungValues() {
		
		double[] werte = new double[this.anz_zustaende];
		
		for (int i = 0; i < tfArrayStartverteilung.length; i++) {
			werte[i] = Double.parseDouble(this.tfArrayStartverteilung[i].getText());
		}
		return werte;
	}

	public double[] getFolgeverteilungValues() {
		
		double[] werte = new double[this.anz_zustaende];
		
		for (int i = 0; i < tfArrayFolgeverteilung.length; i++) {
			werte[i] = Double.parseDouble(this.tfArrayFolgeverteilung[i].getText());
		}
		return werte;
	}

	public double[][] getUebergangsmatrixValues() {
		
		double[][] werte = new double[this.anz_zustaende][this.anz_zustaende];
		
		for (int i = 0; i < werte.length; i++) {
			for (int j = 0; j < werte[i].length; j++) {
				werte[i][j] = Double.parseDouble(this.tfArrayUebergangsmatrix[i][j].getText());
				// this.tfArrayUebergangsmatrix[i][j].setSize(1, 1);
			}
		}
		return werte;
	}

	// ************************************************************************
	// ** Setter für das Setzen der ActionListener der Buttons
	// ************************************************************************
	public void setStartverteilungLoeschenListener(ActionListener actionListener) {
		this.btStartverteilungLoeschen.addActionListener(actionListener);
	}

	public void setAllesLoeschenListener(ActionListener actionListener) {
		this.btAllesLoeschen.addActionListener(actionListener);
	}

	public void setFolgeverteilungKopierenListener(ActionListener actionListener) {
		this.btFolgeverteilungKopieren.addActionListener(actionListener);
	}

	public void setFolgeverteilungBerechnenListener(
			ActionListener actionListener) {
		this.btFolgeverteilungBerechnen.addActionListener(actionListener);
	}

	public void setStationaereVerteilungBerechnenListener(
			ActionListener actionListener) {
		this.btFolgeverteilungKopieren.addActionListener(actionListener);
	}

	// ************************************************************************
	// ** Sonstige Methoden
	// ************************************************************************

	// Methode, um die TextfeldArrays zu initialisieren
	private void initialisiereJTextFieldArrays(int zustaende) {

		this.tfArrayStartverteilung = new JTextField[zustaende];
		this.tfArrayFolgeverteilung = new JTextField[zustaende];
		this.tfArrayUebergangsmatrix = new JTextField[zustaende][zustaende];

		for (int i = 0; i < tfArrayStartverteilung.length; i++) {
			this.tfArrayStartverteilung[i] = new JTextField();
			// this.tfStartverteilung[i].setSize(1, 1);
		}

		for (int i = 0; i < tfArrayFolgeverteilung.length; i++) {
			this.tfArrayFolgeverteilung[i] = new JTextField();
//			this.tfArrayFolgeverteilung[i].setBackground(Color.LIGHT_GRAY);
			// this.tfFolgeverteilung[i].setSize(1, 1);
//			this.tfArrayFolgeverteilung[i].setEditable(false);
//			this.tfArrayFolgeverteilung[i].setFocusable(false);
		}

		for (int i = 0; i < tfArrayUebergangsmatrix.length; i++) {
			for (int j = 0; j < tfArrayUebergangsmatrix[i].length; j++) {
				this.tfArrayUebergangsmatrix[i][j] = new JTextField();
				// this.tfArrayUebergangsmatrix[i][j].setSize(1, 1);
			}
		}
	}

	// Methode zur zentralen Aktualisierung des View
	public void updateView(double[] startverteilung, double[] folgeverteilung,
			double[][] uebergangsmatrix) {

		if (startverteilung == null || startverteilung.length == 0) {

			for (int i = 0; i < tfArrayStartverteilung.length; i++) {
				this.tfArrayStartverteilung[i].setText("");
				// this.tfStartverteilung[i].setSize(1, 1);
			}
		}

		if (folgeverteilung == null || folgeverteilung.length == 0) {

			for (int i = 0; i < tfArrayFolgeverteilung.length; i++) {
				this.tfArrayFolgeverteilung[i].setText("");
				// this.tfStartverteilung[i].setSize(1, 1);
			}
		}

		if (uebergangsmatrix == null || uebergangsmatrix.length == 0) {
			for (int i = 0; i < uebergangsmatrix.length; i++) {
				for (int j = 0; j < uebergangsmatrix[i].length; j++) {
					this.tfArrayUebergangsmatrix[i][j].setText("");
				}
			}
		}

		// Startverteilung
		for (int i = 0; i < startverteilung.length; i++) {
			tfArrayStartverteilung[i].setText(Double
					.toString(startverteilung[i]));
		}

		// Folgeverteilung
		for (int i = 0; i < folgeverteilung.length; i++) {
			tfArrayFolgeverteilung[i].setText(Double
					.toString(folgeverteilung[i]));
		}

		// Übergangsmatrix
		for (int i = 0; i < uebergangsmatrix.length; i++) {
			for (int j = 0; j < uebergangsmatrix[i].length; j++) {
				tfArrayUebergangsmatrix[i][j].setText(Double
						.toString(uebergangsmatrix[i][j]));
				// this.tfArrayUebergangsmatrix[i][j].setSize(1, 1);
			}
		}
	}

	// Factory-Methode, um ein Standard-GridBagConstraints-Objekt zu erzeugen
	private GridBagConstraints makeGBC(int gridx, int gridy, int gridwidth,
			int gridheight, int weightx, int weighty) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.fill = GridBagConstraints.BOTH; // Standard-Werte
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = new Insets(0, 0, 0, 0);

		return gbc;
	}
}
