public class PosList {

    public int row;
    public int col;
    public String player;
    public Possible[] list = new Possible[8];
    
    public PosList (int i, int j, String p) {
	row = i;
	col = j;
	player = p;

	checkMove0();
	checkMove1();
	checkMove2();
	checkMove3();
	checkMove4();
	checkMove5();
	checkMove6();
	checkMove7();
    }

    //Checking row-1
    private void checkMove0 () {
	if (row != 0 && Othello.board[row-1][col] == null) {
	    for (int i=row; i<8; i++) {
		if (Othello.board[i][col]==player) {
		    list[0] = new Possible(row-1, col, i, col); break;
		}
		else if (Othello.board[i][col] == null) break;
	    }
	}
    }

    //Checking row+1
    private void checkMove1 () {
	if (row != 7 && Othello.board[row+1][col] == null) {
	    for (int i=row; i>=0; i--) {
		if (Othello.board[i][col]==player) {
		    list[1] = new Possible(row+1, col, i, col); break;
		}
		else if (Othello.board[i][col] == null) break;
	    }
	}
    }

    //Checking col-1
    private void checkMove2 () {
	if (col != 0 && Othello.board[row][col-1] == null) {
	    for (int j=col; j<8; j++) {
		if (Othello.board[row][j]==player) {
		    list[2] = new Possible(row, col-1, row, j); break;
		}
		else if (Othello.board[row][j] == null) break;
	    }
	}
    }

    //Checking col+1
    private void checkMove3 () {
	if (col != 7 && Othello.board[row][col+1] == null) {
	    for (int j=col; j>=0; j--) {
		if (Othello.board[row][j]==player) {
		    list[3] = new Possible(row, col+1, row, j); break;
		}
		else if (Othello.board[row][j] == null) break;
	    }
	}
    }

    //Checking row+1, col+1
    private void checkMove4 () {
	if (row != 7 && col != 7 && Othello.board[row+1][col+1] == null) {
	    for (int i=row, j=col; i>=0 && j>=0; i--, j--) {
		if (Othello.board[i][j]==player) {
		    list[4] = new Possible(row+1, col+1, i, j); break;
		}
		else if (Othello.board[i][j] == null) break;
	    }
	}
    }

    //Checking row+1, col-1
    private void checkMove5 () {
	if (row != 7 && col != 0 && Othello.board[row+1][col-1] == null) {
	    for (int i=row, j=col; i>=0 && j<8; i--, j++) {
		if (Othello.board[i][j]==player) {
		    list[5] = new Possible(row+1, col-1, i, j); break;
		}
		else if (Othello.board[i][j] == null) break;
	    }
	}
    }

    //Checking row-1, col+1
    private void checkMove6 () {
	if (row != 0 && col != 7 && Othello.board[row-1][col+1] == null) {
	    for (int i=row, j=col; i<8 && j>=0; i++, j--) {
		if (Othello.board[i][j]==player) {
		    list[6] = new Possible(row-1, col+1, i, j); break;
		}
		else if (Othello.board[i][j] == null) break;
	    }
	}
    }

    //Checking row-1, col-1
    private void checkMove7 () {
	if (row != 0 && col!= 0 && Othello.board[row-1][col-1] == null) {
	    for (int i=row, j=col; i<8 && j<8; i++, j++) {
		if (Othello.board[i][j]==player) {
		    list[7] = new Possible(row-1, col-1, i, j); break;
		}
		else if (Othello.board[i][j] == null) break;
	    }
	}
    }
}