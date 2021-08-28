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
import java.text.DecimalFormat;

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

	private int zustaende;

	private static final long serialVersionUID = 1L;

	// ************************************************************************
	// ** Label
	// ************************************************************************
	private JLabel lbUebergangsmatrix;

	private JLabel lbStartverteilung;

	private JLabel lbFolgeverteilung;

	private JLabel lbLeer;

	// ************************************************************************
	// ** JTextField-Arrays für die Aufnahme der Textfelder
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
	public MarkovketteView(int zustaende) {

		super("MarkovRechner: " + zustaende + " Zustände");

		this.zustaende = zustaende;

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
		this.initialisiereJTextFieldArrays(zustaende);

		// Labels erzeugen
		this.lbStartverteilung = new JLabel("Startverteilung");
		this.lbFolgeverteilung = new JLabel("Folgeverteilung");
		this.lbUebergangsmatrix = new JLabel("Übergangsmatrix");
		this.lbLeer = new JLabel(" ");

		// Buttons erzeugen
		this.btStartverteilungLoeschen = new JButton("Startverteilung löschen");
		this.btAllesLoeschen = new JButton("Alles Löschen");
		this.btFolgeverteilungKopieren = new JButton("Folgeverteilung kopieren");
		this.btFolgeverteilungBerechnen = new JButton(
				"Folgeverteilung berechen");
		this.btStationaereVerteilungBerechnen = new JButton(
				"Stationäre Verteilung berechen");

		// Label "lbUebergangsmatrix" zur ContentPane hinzufügen
		gbc = this.makeGBC(this.zustaende + 1, 0, this.zustaende, 1, 1, 1);
		cP.add(this.lbUebergangsmatrix, gbc);

		// Label "lbStartverteilung" zur ContentPane hinzufügen
		gbc = this.makeGBC(0, this.zustaende + 3, this.zustaende, 1, 1, 1);
		cP.add(this.lbStartverteilung, gbc);

		// Label "lbFolgeverteilung" zur ContentPane hinzufügen
		gbc = this.makeGBC(this.zustaende + 1, this.zustaende + 3,
				this.zustaende, 1, 1, 1);
		cP.add(this.lbFolgeverteilung, gbc);

		// Label "lbLeer" zur ContentPane hinzufügen
		gbc = this.makeGBC(0, this.zustaende + 5, this.zustaende, 1, 1, 1);
		cP.add(this.lbLeer, gbc);

		// Übergangsmatrixtextfelder zur ContentPane hinzufügen
		for (int x = 0; x < this.tfArrayUebergangsmatrix.length; x++) {
			for (int y = 0; y < this.tfArrayUebergangsmatrix[x].length; y++) {
				gbc = this.makeGBC(x + this.zustaende + 1, y + 1, 1, 1, 1, 1);
				cP.add(this.tfArrayUebergangsmatrix[x][y], gbc);
			}
		}

		// Startverteilungstextfelder zur ContentPane hinzufügen
		for (int x = 0; x < this.tfArrayStartverteilung.length; x++) {
			gbc = this.makeGBC(x, this.zustaende + 2, 1, 1, 1, 1);
			cP.add(this.tfArrayStartverteilung[x], gbc);
		}

		// Folgeverteilungstextfelder zur ContentPane hinzufügen
		for (int x = 0; x < tfArrayFolgeverteilung.length; x++) {
			gbc = this.makeGBC(x + this.zustaende + 1, this.zustaende + 2, 1,
					1, 1, 1);
			cP.add(this.tfArrayFolgeverteilung[x], gbc);
		}

		// Buttons zur ContentPane hinzufügen
		gbc = this.makeGBC(0, this.zustaende + 6, this.zustaende, 1, 1, 1);
		cP.add(this.btStartverteilungLoeschen, gbc);

		gbc = this.makeGBC(0, this.zustaende + 7, this.zustaende, 1, 1, 1);
		cP.add(this.btAllesLoeschen, gbc);

		gbc = this.makeGBC(0, this.zustaende + 8, this.zustaende, 1, 1, 1);
		cP.add(this.btFolgeverteilungKopieren, gbc);

		gbc = this.makeGBC(0, this.zustaende + 9, this.zustaende, 1, 1, 1);
		cP.add(this.btFolgeverteilungBerechnen, gbc);

		gbc = this.makeGBC(0, this.zustaende + 10, this.zustaende, 1, 1, 1);
		cP.add(this.btStationaereVerteilungBerechnen, gbc);

		// WindowListener hinzufügen
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Standard-Schließ-Operation festlegen
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
		this.setVisible(true);
	}

	// ************************************************************************
	// ** Getter für das Zurückgeben der Werte der Textfelder der
	// JTextFieldArrays
	// ************************************************************************

	public double[] getStartverteilungValues() {

		DecimalFormat df = new DecimalFormat("#0.00");

		double[] werte = new double[this.zustaende];

		for (int x = 0; x < this.tfArrayStartverteilung.length; x++) {
			String formatiert = df.format(Double
					.parseDouble(this.tfArrayStartverteilung[x].getText()));
			werte[x] = Double.parseDouble(formatiert.replace(',', '.'));
		}
		return werte;
	}

	public double[] getFolgeverteilungValues() {

		DecimalFormat df = new DecimalFormat("#0.00");

		double[] werte = new double[this.zustaende];

		for (int x = 0; x < tfArrayFolgeverteilung.length; x++) {
			String formatiert = df.format(Double
					.parseDouble(this.tfArrayFolgeverteilung[x].getText()));
			werte[x] = Double.parseDouble(formatiert.replace(',', '.'));
		}
		return werte;
	}

	public double[][] getUebergangsmatrixValues() {

		double[][] werte = new double[this.zustaende][this.zustaende];

		for (int x = 0; x < this.tfArrayUebergangsmatrix.length; x++) {
			for (int y = 0; y < this.tfArrayUebergangsmatrix[x].length; y++) {
				werte[x][y] = Double
						.parseDouble(this.tfArrayUebergangsmatrix[x][y]
								.getText());
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
		this.btStationaereVerteilungBerechnen.addActionListener(actionListener);
	}

	// ************************************************************************
	// ** Sonstige Methoden
	// ************************************************************************

	// Methode, um die TextfeldArrays zu initialisieren
	private void initialisiereJTextFieldArrays(int zustaende) {

		this.tfArrayStartverteilung = new JTextField[zustaende];
		this.tfArrayFolgeverteilung = new JTextField[zustaende];
		this.tfArrayUebergangsmatrix = new JTextField[zustaende][zustaende];

		for (int x = 0; x < tfArrayStartverteilung.length; x++) {
			this.tfArrayStartverteilung[x] = new JTextField();
		}

		for (int x = 0; x < tfArrayFolgeverteilung.length; x++) {
			this.tfArrayFolgeverteilung[x] = new JTextField();
			this.tfArrayFolgeverteilung[x].setBackground(Color.LIGHT_GRAY);
			this.tfArrayFolgeverteilung[x].setEditable(false);
			this.tfArrayFolgeverteilung[x].setFocusable(false);
		}

		for (int x = 0; x < tfArrayUebergangsmatrix.length; x++) {
			for (int y = 0; y < tfArrayUebergangsmatrix[x].length; y++) {
				this.tfArrayUebergangsmatrix[x][y] = new JTextField();
			}
		}
	}

	// Methode zur zentralen Aktualisierung des View
	public void updateView(double[] startverteilung, double[] folgeverteilung,
			double[][] uebergangsmatrix) {

		DecimalFormat df = new DecimalFormat("#0.00");

		// Startverteilungstextfelder auf leer setzen
		if (startverteilung == null || startverteilung.length == 0) {

			for (int x = 0; x < tfArrayStartverteilung.length; x++) {
				this.tfArrayStartverteilung[x].setText("");
			}
			// Textfelder mit den Werten befüllen
		} else {

			for (int x = 0; x < tfArrayStartverteilung.length; x++) {
				String formatiert = df.format(startverteilung[x]);
				this.tfArrayStartverteilung[x].setText(formatiert.replace(',',
						'.'));
			}
		}

		// Folgeverteilungstextfelder auf leer setzen
		if (folgeverteilung == null || folgeverteilung.length == 0) {

			for (int x = 0; x < tfArrayFolgeverteilung.length; x++) {
				this.tfArrayFolgeverteilung[x].setText("");
			}
			// Textfelder mit den Werten befüllen
		} else {

			for (int x = 0; x < tfArrayFolgeverteilung.length; x++) {
				String formatiert = df.format(folgeverteilung[x]);
				this.tfArrayFolgeverteilung[x].setText(formatiert.replace(',',
						'.'));
			}
		}

		// Uebergangsmatrixtextfelder auf leer setzen
		if (uebergangsmatrix == null || uebergangsmatrix.length == 0) {

			for (int x = 0; x < tfArrayUebergangsmatrix.length; x++) {
				for (int y = 0; y < tfArrayUebergangsmatrix[x].length; y++) {
					this.tfArrayUebergangsmatrix[x][y].setText("");
				}
			}
			// Textfelder mit den Werten befüllen
		} else {
			for (int x = 0; x < tfArrayUebergangsmatrix.length; x++) {
				for (int y = 0; y < tfArrayUebergangsmatrix[x].length; y++) {
					String formatiert = df.format(uebergangsmatrix[x][y]);
					this.tfArrayUebergangsmatrix[x][y].setText(formatiert
							.replace(',', '.'));
				}
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
