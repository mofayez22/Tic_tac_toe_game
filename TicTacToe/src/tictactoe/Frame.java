package tictactoe ;
/*
 * Name: Joseph Santos
 * Date: 10.14.2018
 * Course Number: CSC-220-D01
 * Course Name: Data Structures & Algorithms
 * Problem Number: 4
 * Email: jtsantos0001@student.stcc.edu
 * Here we use an old GUI and a minimax function to make a fun interactive, click-based Tic Tac Toe game.
 */

// Import libraries.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {


	/**
	 * For Some reason it wanted me to do this so the yellow squiggly would go away.
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String [] args) {
		new Frame();
	}

	private JButton button0,
					button1, 
					button2, 
					button3, 
					button4, 
					button5, 
					button6, 
					button7, 
					button8;

	private TicTacToeClass board;

	public Frame() {
		// Set up the grid
		this.setSize(300,300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("A.I. Tic Tac Toe");
		JPanel boardSize = new JPanel();
	    boardSize.setSize(300,300);
	    boardSize.setLayout(new GridLayout(3,3));
	    
	    // Buttons 0-2 Make up positions A1-A3
	    button0 = createButton("A1");
	    button1 = createButton("A2");
	    button2 = createButton("A3");
	    // Buttons 3-5 Make up positions B1-B3
	    button3 = createButton("B1");
	    button4 = createButton("B2");
	    button5 = createButton("B3");
	 // Buttons 6-8 Make up positions C1-C3
	    button6 = createButton("C1");
	    button7 = createButton("C2");
	    button8 = createButton("C3");
		boardSize.add(button0);
		boardSize.add(button1);
		boardSize.add(button2);
		boardSize.add(button3);
		boardSize.add(button4);
		boardSize.add(button5);
		boardSize.add(button6);
		boardSize.add(button7);
		boardSize.add(button8);
	    this.add(boardSize);
	    this.setVisible(true);

		// Start the game
		board = new TicTacToeClass();
	}

	private JButton createButton(String square) {
		JButton btn = new JButton();
		btn.setPreferredSize(new Dimension(50, 50));
		Font f = new Font("Dialog", Font.PLAIN, 72);
		btn.setFont(f);
    // Using lambda notation to simplify logic.
		btn.addActionListener(e -> btnClick(e, square));
		return btn;
	}

	private void btnClick(ActionEvent e, String square) {
		if (board.getSquare(square) != 0)
			return;
		JButton btn = (JButton)e.getSource();
		btn.setText("X");
		board.playMove(square, 1);
		
		// If isGameOver = 3, Its a tie.
		if (board.isGameOver() == 3) {
			JOptionPane.showMessageDialog(null, "It appears we are equally matched, human..", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			resetBoard();
			return;
		}

		// If isGameOver = 1, somehow you beat the minimax fxn.
		if (board.isGameOver() == 1) {
			JOptionPane.showMessageDialog(null,"Impressive, Human!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
			resetBoard();
			return;
		}
		String aiMove = board.getNextMove();
		board.playMove(aiMove,2);
		
		switch (aiMove){
			case "A1": button0.setText("O");
				break;
			case "A2": button1.setText("O");
				break;
			case "A3": button2.setText("O");
				break;
			case "B1": button3.setText("O");
				break;
			case "B2": button4.setText("O");
				break;
			case "B3": button5.setText("O");
				break;
			case "C1": button6.setText("O");
				break;
			case "C2": button7.setText("O");
				break;
			case "C3": button8.setText("O");
				break;
		}

		if (board.isGameOver() == 2) {
			JOptionPane.showMessageDialog(null,
				"Score 1 for our A.I. Overlords! ", "You lose!",
				JOptionPane.INFORMATION_MESSAGE);
			resetBoard();
			return;
		}
	}
	private void resetBoard() {
		board.reset();
		button0.setText("");
		button1.setText("");
		button2.setText("");
		button3.setText("");
		button4.setText("");
		button5.setText("");
		button6.setText("");
		button7.setText("");
		button8.setText("");
	}
}
