public class Othello {

    public static String[][] board = new String[8][8];

    public static void main (String[] args) {
	new OthelloFrame();
    }

    public static void freshBoard() {
	for (int i=0; i<8; i++) {
	    for (int j=0; j<8; j++)
		board[i][j] = null;
	}

	board[3][4] = "black"; board[4][3] = "black";
	board[3][3] = "white"; board[4][4] = "white";
    }

    public static boolean isReversed(String player, String opponent, int x, int y) {
	//Get player's input
	int row = (int)y/50;
	int col = (int)x/50;

	//Create a list of possible moves
	PosList[] list = new PosList[64];
	int countPosList = getPosMoves(list, player, opponent);

	//Create a list of possible reversals
	int[][] revList = new int[8][3];

	//Check input validity
	boolean isValid = false;
	int countRev = 0;

	for (int i=0; i<countPosList; i++) {
	    for (int j=0; j<8; j++) {
		if (list[i].list[j] != null && list[i].list[j].row == row && list[i].list[j].col == col) {
		    revList[countRev][0]=list[i].list[j].reverseFromRow;
		    revList[countRev][1]=list[i].list[j].reverseFromCol;
		    revList[countRev++][2]=j;
		    isValid = true;
		}
	    }
	}

	if (isValid) {
	    reverse(player, row, col, revList, countRev);
	    return true;
	}
	else return false;
    }

    public static boolean isPossible(String player, String opponent) {
	PosList[] list = new PosList[64];
	int countPosList = getPosMoves(list, player, opponent);

	if (countPosList > 0) {
	    for (int i=0; i<countPosList; i++) {
		for (int j=0; j<8; j++) {
		    if (list[i].list[j] != null) {
			return true;
		    }
		}
	    }
	}

	return false;
    }

    public static int[] computer (String player, String opponent) {
	//Create a list of possible moves
	PosList[] list = new PosList[64];
	int countPosList = getPosMoves(list, player, opponent);

	int[][] posMove = new int[countPosList*8][2];
	int countPosMove = 0;

	for (int i=0; i<countPosList; i++) {
	    for (int j=0; j<8; j++) {
		if (list[i].list[j] != null) {
		    posMove[countPosMove][0] = list[i].list[j].row;
		    posMove[countPosMove++][1] = list[i].list[j].col;
		}
	    }
	}

	int random = (int) (Math.random()*(countPosMove-1));

	int[] compMove = new int[2];
	compMove[0] = posMove[random][0];
	compMove[1] = posMove[random][1];

	return compMove;
    }

    private static int getPosMoves(PosList[] list, String player, String opponent) {
	int count = 0;
	for (int i=0; i<8; i++) {
	    for (int j=0; j<8; j++) {
		if (board[i][j] == opponent)
		    list[count++] = new PosList(i, j, player);
	    }
	}
	return count;
    }

    private static void reverse(String player, int row, int col, int[][] revList, int countRev) {
	for (int i=0; i<countRev; i++) {
	    switch (revList[i][2]) {
	    case 0: reverse0(revList, row, col, player, i); break;
	    case 1: reverse1(revList, row, col, player, i); break;
	    case 2: reverse2(revList, row, col, player, i); break;
	    case 3: reverse3(revList, row, col, player, i); break;
	    case 4: reverse4(revList, row, col, player, i); break;
	    case 5: reverse5(revList, row, col, player, i); break;
	    case 6: reverse6(revList, row, col, player, i); break;
	    case 7: reverse7(revList, row, col, player, i); break;
	    }
	}
    }

    private static void reverse0(int[][] a, int inputR, int inputC, String player, int index) {
	for (int i = a[index][0]; i>=inputR; i--)
	    board[i][inputC] = player;
    }

    private static void reverse1(int[][] a, int inputR, int inputC, String player, int index) {
	for (int i = a[index][0]; i<=inputR; i++)
	    board[i][inputC] = player;
    }

    private static void reverse2(int[][] a, int inputR, int inputC, String player, int index) {
	for (int j = a[index][1]; j>=inputC; j--)
	    board[inputR][j] = player;
    }

    private static void reverse3(int[][] a, int inputR, int inputC, String player, int index) {
	for (int j = a[index][1]; j<=inputC; j++)
	    board[inputR][j] = player;
    }

    private static void reverse4(int[][] a, int inputR, int inputC, String player, int index) {
	for (int i = a[index][0], j = a[index][1]; i<=inputR && j<=inputC; i++, j++)
	    board[i][j] = player;
    }

    private static void reverse5(int[][] a, int inputR, int inputC, String player, int index) {
	for (int i = a[index][0], j = a[index][1]; i<=inputR && j>=inputC; i++, j--)
	    board[i][j] = player;
    }

    private static void reverse6(int[][] a, int inputR, int inputC, String player, int index) {
	for (int i = a[index][0], j = a[index][1]; i>=inputR && j<=inputC; i--, j++)
	    board[i][j] = player;
    }

    private static void reverse7(int[][] a, int inputR, int inputC, String player, int index) {
	for (int i = a[index][0], j = a[index][1]; i>=inputR && j>=inputC; i--, j--)
	    board[i][j] = player;
    }

    public static String getScore() {
	int count1=0, count2=0;

	for (int i=0; i<8; i++) {
	    for (int j=0; j<8; j++) {
		if (board[i][j]=="black") count1++;
		else if (board[i][j]=="white") count2++;
	    }
	}

	return "Black: "+count1+"    White: "+count2;
    }

    public static String findWinner() {
	int count1=0, count2=0;

	for (int i=0; i<8; i++) {
	    for (int j=0; j<8; j++) {
		if (board[i][j]=="black") count1++;
		else if (board[i][j]=="white") count2++;
	    }
	}

	String winner;
	if (count1 > count2) winner="Black";
	else if (count1 < count2) winner="White";
	else winner="No one";

	return winner + " wins!";
    }
}