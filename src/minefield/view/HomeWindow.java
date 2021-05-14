package minefield.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import minefield.exceptions.ColumnException;
import minefield.exceptions.MineTooHighException;
import minefield.exceptions.MineTooLowException;
import minefield.exceptions.RowException;
import minefield.object.Field;
import minefield.util.Difficulty;
import minefield.util.GameUtility;
import minefield.util.StringToShow;
import minefield.view.object.Home;
import minefield.view.object.HomeListener;

/*
 * ==========================
 * =		Difficolta'		=
 * =		<Scelta>		=
 * =		Righe:  		=
 * =		Colonne:		=
 * =		Mine:   		=
 * ==========================
 * =		Safe?			=
 * =		<Scelta>		=
 * ==========================
 * =		START			=
 * ==========================
 */

/**
 * La classe HomeWindow permette di creare la finestra con il menu iniziale.
 * L'utente in questo modo può scegliere la difficolta' del gioco
 * @author Dawit&Stefano
 *
 */
public class HomeWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private Home home;
	private HomeListener listener;
	
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private JRadioButton custom;
	
	private TextField rowT;
	private TextField columnT;
	private TextField mineT;
	
	private JLabel row;
	private JLabel column;
	private JLabel mine;

	JPanel customPanel;
	
	private JRadioButton safe;
	private JRadioButton notSafe;

	/**
	 * 
	 * Costruttore per creare la finestra per impostare la partita.
	 * All'inizio sarà evidenziato il bottone con difficolta' facile e il safeStart.
	 */
	public HomeWindow() {
		home = new Home();
		listener = new HomeListener(this);
		
		JPanel difficultyPanel = new JPanel();
		JLabel textDifficulty = new JLabel(StringToShow.QUESTION_DIFFICULTY);
		easy = new JRadioButton(StringToShow.DIFFICULTY_EASY);
		medium = new JRadioButton(StringToShow.DIFFICULTY_MEDIUM);
		hard = new JRadioButton(StringToShow.DIFFICULTY_HARD);
		custom = new JRadioButton(StringToShow.DIFFICULTY_CUSTOM);
		ButtonGroup difficultyGroup = new ButtonGroup();
		
		row = new JLabel(StringToShow.ROW);
		column = new JLabel(StringToShow.COLUMN);
		mine = new JLabel(StringToShow.MINE);
		
		rowT = new TextField();
		columnT = new TextField();
		mineT = new TextField();
		
		customPanel = new JPanel();
		customPanel.add(row);
		customPanel.add(rowT);
		customPanel.add(column);
		customPanel.add(columnT);
		customPanel.add(mine);
		customPanel.add(mineT);
		customPanel.setLayout(new GridLayout(3, 3));
		
		JPanel safePanel = new JPanel();
		JLabel textSafe = new JLabel(StringToShow.QUESTION_SAFE);
		safe = new JRadioButton(StringToShow.SAFE_YES);
		notSafe = new JRadioButton(StringToShow.SAFE_NO);
		ButtonGroup safeGroup = new ButtonGroup();
		
		JPanel startPanel = new JPanel();
		JButton start = new JButton(StringToShow.START_BUTTON);
		
		easy.setSelected(true);
		safe.setSelected(true);
		
		difficultyGroup.add(easy);
		difficultyGroup.add(medium);
		difficultyGroup.add(hard);
		difficultyGroup.add(custom);
		
		safeGroup.add(safe);
		safeGroup.add(notSafe);
		
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
		difficultyPanel.add(textDifficulty);
		difficultyPanel.add(easy);
		difficultyPanel.add(medium);
		difficultyPanel.add(hard);
		difficultyPanel.add(custom);
		difficultyPanel.add(customPanel);
		
		safePanel.setLayout(new BoxLayout(safePanel, BoxLayout.Y_AXIS));
		safePanel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));
		safePanel.add(textSafe);
		safePanel.add(safe);
		safePanel.add(notSafe);
		
		startPanel.setLayout(new FlowLayout());
		startPanel.add(start);
		
		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		c.add(difficultyPanel);
		c.add(safePanel);
		c.add(startPanel);
		
		easy.addActionListener(listener);
		medium.addActionListener(listener);
		hard.addActionListener(listener);
		custom.addActionListener(listener);
		safe.addActionListener(listener);
		notSafe.addActionListener(listener);
		start.addActionListener(listener);
		
		Dimension dim = new Dimension(300, 250);
		this.setSize(dim);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(StringToShow.GAME_TITLE);
		
		customPanel.setVisible(false);
		
		
		Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dimScreen.width - dim.width);
		int y = (dimScreen.height - dim.height);
		setLocation(x/2, y/2);
	}
	
	/**
	 * Metodo che serve al listener per aggiornare la finestra quando si selezionano i radio button
	 */
	public void updateWindow() {
		if(easy.isSelected()) {
			setEasy();
			customPanel.setVisible(false);
		}
		else if(medium.isSelected()) {
			setMedium();
			customPanel.setVisible(false);
		}
		else if(hard.isSelected()) {
			setHard();
			customPanel.setVisible(false);
		}
		else {
			setCustom();
			Dimension dimCustom = new Dimension(300, 350);
			this.setSize(dimCustom);
			customPanel.setVisible(true);
		}
		
		if(safe.isSelected())
			setSafeStart();
		else
			setNotSafeStart();
	}
	
	/**
	 * Crea una nuova finestra di gioco con le opzioni scelte, nasconde questa finestra e
	 * genera il campo con la difficoltà scelta.
	 */
	public void pressStartButton() {
		Field field;
		try {
			field = GameUtility.buildNewField(home.safe, home.difficulty);
		}
		catch (IllegalArgumentException e) {
			columnT.setBackground(Color.WHITE);
			rowT.setBackground(Color.WHITE);
			mineT.setBackground(Color.WHITE);
			
			try {
				home.column = Integer.parseInt(columnT.getText());
			}
			catch(NumberFormatException f) {textBoxError(columnT); return;}
			
			try {
				home.row = Integer.parseInt(rowT.getText());
			}
			catch(NumberFormatException f) {textBoxError(rowT); return;}
			
			try {
				home.mine = Integer.parseInt(mineT.getText());
			}
			catch(NumberFormatException f) {textBoxError(mineT); return;}
			
			try {
				field = GameUtility.buildNewField(home.safe, home.row, home.column, home.mine);
			}
			catch(MineTooHighException g) {textBoxError(mineT); return;}
			catch(MineTooLowException h) {textBoxError(mineT); return;}
			catch(ColumnException g) {textBoxError(columnT); return;}
			catch(RowException g) {textBoxError(rowT); return;}
		}
		
		setVisible(false);

		GameWindow game = new GameWindow(field, this);
		game.setVisible(true);
	}
	
	/**
	 * Permette di impostare la difficolta' della partita a facile
	 */
	public void setEasy() {
		home.difficulty=Difficulty.EASY;
	}
	
	/**
	 * Permette di impostare la difficolta' della partita a media
	 */
	private void setMedium() {
		home.difficulty=Difficulty.MEDIUM;
	}
	
	/**
	 * Permette di impostare la difficolta' della partita a difficile
	 */
	private void setHard() {
		home.difficulty=Difficulty.HARD;
	}
	
	/**
	 * Permette di impostare la difficolta' della partita a personalizzata
	 */
	private void setCustom() {
		home.difficulty=Difficulty.CUSTOM;
	}
	
	/**
	 * Permette di cominciare la partita in modo sicuro
	 */
	private void setSafeStart() {
		home.safe=true;
	}
	
	/**
	 * Permette di cominciare la partita in modo non sicuro
	 */
	private void setNotSafeStart() {
		home.safe=false;
	}
	
	/**
	 * Permette di impostare lo sfondo ROSSO in un textField quando ci sono dei parametri errati
	 */
	private void textBoxError(TextField t) {
		t.setBackground(Color.RED);
	}
	
}
