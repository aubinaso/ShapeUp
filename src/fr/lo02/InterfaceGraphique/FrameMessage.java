package fr.lo02.InterfaceGraphique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.lo02.modele.RealPlayer;

import java.awt.Font;
import java.awt.Color;
/**
 * cette classe représente l'écran de message en vue Graphique
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 * 
 * @see GamePanel
 * @see GamePanel#seeLabel
 * @see BoutonHandCard
 * @see HandCard
 * @see RealPlayer
 *
 */
public class FrameMessage extends JFrame {
	/**
	 * le message affiché
	 */
	JLabel label;
	/**
	 * Create the application.
	 */
	public FrameMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.getContentPane().setBackground(Color.ORANGE);
		this.setBounds(100, 100, 450, 174);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Open Sans Semibold", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 30, 414, 87);
		this.getContentPane().add(label);
	}
	/**
	 * getters du message affiché
	 * @return un {@link JLabel}
	 */
	public JLabel getLabel() {
		return label;
	}
	/**
	 * setters du message.
	 * @param label le nouveau label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}
}
