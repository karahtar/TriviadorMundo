package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import controller.Triviador;
import model.FileManager;
import model.Territory;
import java.awt.Color;

public class GameBoard extends JFrame implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Triviador partida;
	
	private JFrame mainFrame;
	private JLayeredPane layeredPane;
	
	JLabel lblEsElTurno;
	private JLabel playerOneLabel;
	private JLabel playerOneArmyField;
	private JLabel playerTwoLabel;
	private JLabel playerTwoArmyField;
	private JLabel playerThreeLabel;
	private JLabel playerThreeArmyField;
	
	private JLabel roundLabel;
	private final JLabel roundCount;
	
	private JLabel attackFromLabel;
	private JComboBox<String> attackFromComboBox;
	private JLabel attackToLabel;
	private JComboBox<String> attackToComboBox;
	
	private JButton attackButton;
	private JButton NextTurnButton;
	private JButton saveGameButton;
	
	private JLabel backgroundImage;
	
	public GameBoard(Triviador partida) {
		
		this.partida = partida;
		
		
		mainFrame = new JFrame();
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setTitle("Triviador");
	    mainFrame.setSize(800, 600);
	    mainFrame.setLocation(300,200);
	    mainFrame.setLocationRelativeTo(null);
	    
	    layeredPane = new JLayeredPane();
	    mainFrame.getContentPane().add(layeredPane, BorderLayout.CENTER);
	    
	    lblEsElTurno = new JLabel("Es el turno del jugador: " + partida.getActivePlayer().getName());
	    lblEsElTurno.setForeground(Color.WHITE);
	    lblEsElTurno.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    lblEsElTurno.setBounds(25, 500, 243, 16);
	    layeredPane.add(lblEsElTurno);
	    
	    playerOneLabel = new JLabel("Player 1");
	    playerOneLabel.setForeground(Color.WHITE);
	    playerOneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    playerOneLabel.setBounds(26, 524, 51, 16);
	    layeredPane.add(playerOneLabel);
	    playerOneArmyField = new JLabel(partida.getPlayer1Score().toString());
	    playerOneArmyField.setForeground(Color.WHITE);
	    playerOneArmyField.setBounds(26, 546, 64, 26);
	    layeredPane.add(playerOneArmyField);
	    
	    playerTwoLabel = new JLabel("Player 2");
	    playerTwoLabel.setForeground(Color.WHITE);
	    playerTwoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    playerTwoLabel.setBounds(92, 524, 51, 16);
	    layeredPane.add(playerTwoLabel);
	    playerTwoArmyField = new JLabel(partida.getPlayer2Score().toString());
	    playerTwoArmyField.setForeground(Color.WHITE);
	    playerTwoArmyField.setBounds(92, 546, 60, 26);
	    layeredPane.add(playerTwoArmyField);
	    
	    playerThreeLabel = new JLabel("Player 3");
	    playerThreeLabel.setForeground(Color.WHITE);
	    playerThreeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    playerThreeLabel.setBounds(156, 524, 51, 16);
	    layeredPane.add(playerThreeLabel);
	    playerThreeArmyField = new JLabel(partida.getPlayer3Score().toString());
	    playerThreeArmyField.setForeground(Color.WHITE);
	    playerThreeArmyField.setBounds(156, 546, 60, 26);
	    layeredPane.add(playerThreeArmyField);
	    
	    roundLabel = new JLabel("Ronda:");
	    roundLabel.setForeground(Color.WHITE);
	    roundLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    roundLabel.setBounds(518, 524, 49, 16);
	    layeredPane.add(roundLabel);
	    roundCount = new JLabel(partida.getRoundCount().toString());
	    roundCount.setForeground(Color.WHITE);
	    roundCount.setBounds(519, 543, 51, 26);
	    layeredPane.add(roundCount);
	    
	    attackFromLabel = new JLabel("Atacar desde:");
	    attackFromLabel.setForeground(Color.WHITE);
	    attackFromLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    attackFromLabel.setBounds(216, 523, 94, 16);
	    layeredPane.add(attackFromLabel);
	    attackFromComboBox = new JComboBox<String>();
	    attackFromComboBox.setBounds(214, 544, 112, 27);
	    initializeAttackFromComboBox();
	    attackFromComboBox.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		initializeAttackToComboBox((String) attackFromComboBox.getSelectedItem());
	    	}
	    });
	    layeredPane.add(attackFromComboBox);
	    
	    attackToLabel = new JLabel("Atacar a:");
	    attackToLabel.setForeground(Color.WHITE);
	    attackToLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	    attackToLabel.setBounds(326, 524, 103, 16);
	    layeredPane.add(attackToLabel);
	    attackToComboBox = new JComboBox<String>();
	    attackToComboBox.setBounds(323, 544, 114, 27);
	    layeredPane.add(attackToComboBox);
	    
	    attackButton = new JButton("Atacar");
	    attackButton.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
	    attackButton.setSelectedIcon(new ImageIcon(GameBoard.class.getResource("/view/resources/flag.png")));
	    attackButton.setBounds(439, 519, 71, 54);
	    attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Territory attackingTerritory = partida.stringToTerritory((String) attackFromComboBox.getSelectedItem());
					Territory defendingTerritory = partida.stringToTerritory((String) attackToComboBox.getSelectedItem());
					if(attackingTerritory != null && defendingTerritory != null) {
						partida.setAttackingTerritory(attackingTerritory);
						partida.setDefendingTerritory(defendingTerritory);
						new MultipleQuestionsInterface(partida);
						mainFrame.setVisible(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	    layeredPane.add(attackButton);
	    
	    NextTurnButton = new JButton("Pasar Turno");
	    NextTurnButton.setBounds(572, 517, 103, 58);
	    NextTurnButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {	
	    		partida.changeTurn();
	    		if(partida.getGameWinner()!=null){
	    			JOptionPane.showMessageDialog(null, "El ganador de la partida es: "+ partida.getGameWinner().getName() + " con " + partida.getGameWinner().getAmountArmies() + " puntos!");
	    			mainFrame.setVisible(false);
	    		}
	    		else{
					partida.getActivePlayer().resetAlreadyAttacked();
	    			new GameBoard(partida);
					mainFrame.setVisible(false);	
	    		}
	    	}
	    });
	    layeredPane.add(NextTurnButton);
	    
	    saveGameButton = new JButton("Guardar");
	    saveGameButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Guardando juego...");
	    		FileManager file = new FileManager();
	    		file.saveGame(partida);//Guardo el juego
	    		System.out.println("Juego Guardado");
	    	}
	    });
	    saveGameButton.setBounds(679, 518, 103, 57);
	    layeredPane.add(saveGameButton);
	    
	    backgroundImage = new JLabel();
	    backgroundImage.setBounds(0, -4, 800, 600);
	    layeredPane.add(backgroundImage);
	    backgroundImage.setIcon(new ImageIcon(GameBoard.class.getResource("/view/resources/triviadorBoard.jpg")));
	    
	    mainFrame.setVisible(true);   
	}
	
	public void initializeAttackFromComboBox() {
		while(attackFromComboBox.getModel().getSize() > 0)
			attackFromComboBox.removeItemAt(0);
		for(Territory territory: partida.getActivePlayer().getTerritories())
			attackFromComboBox.addItem(territory.getName());
	}
	
	public void initializeAttackToComboBox(String s) {
		Territory attackingTerritory = partida.stringToTerritory(s);
		while(attackToComboBox.getModel().getSize() > 0)
			attackToComboBox.removeItemAt(0);
		for(Territory territory: attackingTerritory.getAdjacents())
			if(!attackingTerritory.getOwner().equals(territory.getOwner())) {
				if(!attackingTerritory.hasAttackedTerritory(territory))
					attackToComboBox.addItem(territory.getName());
			}
	}
	
	public void setTotalArmiesPlayerOne(String armies){
		playerOneArmyField.setText(armies);
	}
	
	public void setTotalArmiesPlayerTwo(String armies){
		playerTwoArmyField.setText(armies);
	}
	
	public void setTotalArmiesPlayerThree(String armies){
		playerThreeArmyField.setText(armies);
	}
}
	
