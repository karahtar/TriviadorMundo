package view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Triviador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartGameInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Triviador partida;
	
	private JFrame frame;
	
	private JButton newGameButton;
	private JButton loadGameButton;
	
	private JLabel backgroundImage;

	public StartGameInterface(Triviador partida) {
		this.partida = partida;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300, 200, 800, 600);
		frame.getContentPane().setLayout(null);
		
		newGameButton = new JButton("New Game");
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setIcon(new ImageIcon(StartGameInterface.class.getResource("/view/resources/nuevaPartida.jpg")));
		newGameButton.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unused" })
			public void actionPerformed(ActionEvent e) {
				partida.startNewGame();
				GameBoard gameBoard = new GameBoard(partida);
				frame.setVisible(false);
			}
		});
		newGameButton.setBounds(270, 329, 297, 62);
		frame.getContentPane().add(newGameButton);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setOpaque(false);
		loadGameButton.setContentAreaFilled(false);
		loadGameButton.setBorderPainted(false);
		loadGameButton.setIcon(new ImageIcon(StartGameInterface.class.getResource("/view/resources/cargarPartida.jpg")));
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		loadGameButton.setBounds(270, 418, 297, 62);
		frame.getContentPane().add(loadGameButton);
		
		backgroundImage = new JLabel();
		backgroundImage.setBackground(new Color(255, 255, 255));
		backgroundImage.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(backgroundImage);
		backgroundImage.setIcon(new ImageIcon(StartGameInterface.class.getResource("/view/resources/triviadorHome.jpg")));
		
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}
}
