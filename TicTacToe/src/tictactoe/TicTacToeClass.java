package tictactoe ;

public class TicTacToeClass {
	
	// These are all the possible win combinations.
	private int winningCombos [][] = {{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};

	public TicTacToeClass() {
		this.reset();
	}
	
	private int board [];
	
	public void reset() {
		board = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2};
	}

	private int getSquare(int index) {
		if (index < 0 || index > 8)
		    throw new IllegalArgumentException("Error! Must be between 0-8");

		return board[index];
	}

	public int getSquare(String square) {
		int index = boardIndex(square);
		if (index == -1)
			throw new IllegalArgumentException("Error! Illegal move!");
		switch (getSquare(index)){
			case 3:
				return 1;
			case 5:
				return 2;
			default:
				return 0;
		}
	}

	private int boardIndex(String square) {
		switch (square) {
			case "A1":
				return 0;
			case "A2":
				return 1;
			case "A3":
				return 2;
			case "B1":
				return 3;
			case "B2":
				return 4;
			case "B3":
				return 5;
			case "C1":
				return 6;
			case "C2":
				return 7;
			case "C3":
				return 8;
			default:
				return -1;
		}
	}

	private String matchPositionToIndex(int index) {
		switch (index) {
			case 0:
				return "A1";
			case 1:
				return "A2";
			case 2:
				return "A3";
			case 3:
				return "B1";
			case 4:
				return "B2";
			case 5:
				return "B3";
			case 6:
				return "C1";
			case 7:
				return "C2";
			case 8:
				return "C3";
			default:
				return "";
		}
	}
	public void playMove(String square, int player) {
		int index = boardIndex(square);
		if (index == -1)
			throw new IllegalArgumentException("Error. Not a valid position.");
		this.isValidMove(index, player);
	}

	private void isValidMove(int index, int player) {
		if (index < 0 | index > 8)
		    throw new IllegalArgumentException("Error! Must be in squares 0 through 8");
		if (player <1 | player > 2)
			throw new IllegalArgumentException("Error! This is a single-player A.I assisted game. 2 Player max");
		if (board[index] != 2)
			throw new IllegalArgumentException("Error! This is not a valid position to take");
		if (player == 1)
			board[index] = 3;
		else
			board[index] = 5;
	}
	// Checking for who wins. If p = 27 then we win, and if p = 125 the algorithm wins.
	public int isGameOver() {
		for (int i = 0; i < 8; i++) {
			int j = getWinnerComboScore(i);
			if (j == 27)
			    return 1;     
			if (j == 125)
			    return 2;      
	    }
	    // Here we check if there are any playable positions left. If not, its  a tie.
	    int noWinner = 0;
	    for (int i = 0; i < 9; i++)
	    	if (board[i] == 2)
	            noWinner++;
	    if (noWinner == 0)
	        return 3;          
	    return 0;              
	}
	// Matches winning combos to the current board map.
	public String isHumanWinner(int player) {
		if (player < 1 || player > 2)
		    throw new IllegalArgumentException("player must be 1 or 2");

		for (int i = 0; i < 8; i++) {
			int j = getWinnerComboScore(i);
			if (   (player == 1 && j == 18) || (player == 2 && j == 50) ) {
				if (board[winningCombos[i][0]] == 2)
				    return matchPositionToIndex(winningCombos[i][0]);
				if (board[winningCombos[i][1]] == 2)
				    return matchPositionToIndex(winningCombos[i][1]);
				if (board[winningCombos[i][2]] == 2)
				    return matchPositionToIndex(winningCombos[i][2]);
		    }
		}
		return "";
	}
	private int getWinnerComboScore(int combo) {
		return board[winningCombos[combo][0]] * board[winningCombos[combo][1]] * board[winningCombos[combo][2]];
    }
	
	public String getNextMove() {
		String bestMove;
		// Find the optimal move to use to win.
		bestMove = this.isHumanWinner(2);
		if (bestMove != "")
		    return bestMove;
		// If the human player has an optimal move to win, intercept.
		bestMove = this.isHumanWinner(1);
		if (bestMove != "")
		    return bestMove;
		// Checking for potential winning combos.
		if (board[4] == 2)
		    return "B2";
		if (board[0] == 2)
		    return "A1";
		if (board[2] == 2)
		    return "A3";
		if (board[6] == 2)
		    return "C1";
		if (board[8] == 2)
		    return "C3";
		if (board[1] == 2)
		    return "A2";
		if (board[3] == 2)
		    return "B1";
		if (board[5] == 2)
		    return "B3";
		if (board[7] == 2)
		    return "C2";
		return "";    
	}
    public String toString() {
		return " " + getPlayerAt(board[0]) + " | " + getPlayerAt(board[1]) + " | " + getPlayerAt(board[2]) + "\n-----------\n" +" " +
		       getPlayerAt(board[3]) + " | " +getPlayerAt(board[4]) + " | " + getPlayerAt(board[5]) + "\n-----------\n" +" " +
		       getPlayerAt(board[6]) + " | " + getPlayerAt(board[7]) + " | " + getPlayerAt(board[8]);
    }
	private String getPlayerAt(int status) {
		if (status == 3)
		    return "X";
		if (status == 5)
		    return "O";
		return " ";
	}
}