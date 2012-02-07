import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OthelloFrame extends JFrame implements MouseListener, ActionListener{

    private OthelloCanvas myCanvas;
    private JLabel score;
    private JLabel turn;
    private JLabel comment;
    private JButton restart;
    private JRadioButton blackComp;
    private JRadioButton whiteComp;
    private JRadioButton noComp;
    private char computer;
    private String player = "black";
    private String opponent = "white";

    public OthelloFrame () {
	super("Othello");

	setLocation(0, 0);
	addWindowListener(new MyWindowAdapter());

	myCanvas = new OthelloCanvas();
	myCanvas.addMouseListener(this);

	score = new JLabel(""); 
	turn = new JLabel("");
	comment = new JLabel(""); 

	restart = new JButton("Start New Game");
	restart.addMouseListener(this);

	noComp = new JRadioButton("Play against user");
	noComp.setSelected(true);
	noComp.addActionListener(this);
	blackComp = new JRadioButton("Play against black computer");
	blackComp.addActionListener(this);
	whiteComp = new JRadioButton("Play against white computer");
	whiteComp.addActionListener(this);

	ButtonGroup group = new ButtonGroup();
	group.add(noComp);
	group.add(blackComp);
	group.add(whiteComp);

	JPanel canvasPanel = new JPanel();
	canvasPanel.setLayout(new FlowLayout());
	canvasPanel.add(myCanvas);

	JPanel radioPanel = new JPanel();
	radioPanel.setLayout(new FlowLayout());
	radioPanel.add(noComp);
	radioPanel.add(blackComp);
	radioPanel.add(whiteComp);

	JPanel container = new JPanel();
	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));  
        container.add(canvasPanel);
	container.add(score);
	score.setAlignmentX(0.5f);
	container.add(turn);
	turn.setAlignmentX(0.5f);
	container.add(comment);
	comment.setAlignmentX(0.5f);
	container.add(restart);
	restart.setAlignmentX(0.5f);
	container.add(radioPanel);

	add(container);
	setSize(600, 550);
	setVisible(true);

	newGame();
    }

    //Listening mouse event
    public void mouseClicked(MouseEvent e) {
	if (e.getSource() == myCanvas) {
	    play(e.getX(), e.getY());
	}
    
	else if (e.getSource() == restart) {
	    newGame();
	}
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    //Listening action event
    public void actionPerformed(ActionEvent e) {
	if (noComp.isSelected()) {
            computer = 'n';
        }
	else if (blackComp.isSelected()) {
	    computer = 'b';
	}
	else if (whiteComp.isSelected()) {
	    computer = 'w';
	}
    }

    private void newGame() {
	Othello.freshBoard();

	player = "black";
	opponent = "white";

	myCanvas.repaint();
	score.setText(Othello.getScore());
	turn.setText("It's " + player + "'s turn.");
	comment.setText(" ");

	if (computer == 'b') getCompMove();    
    }

    public void play(int row, int col) {
	if (Othello.isReversed(player, opponent, row, col)) {
	    myCanvas.repaint();
	    score.setText(Othello.getScore());
	    comment.setText(" ");

	    //Get turn
	    switchPlayer();
	    if (!Othello.isPossible(player, opponent)) {
		if (!Othello.isPossible(opponent, player)) {
		    endGame();
		    return;
		}
		else {
		    comment.setText("Oops, "+player+" has no valid move.");
		    switchPlayer();
		}
	    }

	    turn.setText("It's "+player+"'s turn.");

	    //Get computer's move
	    if ((computer == 'b' && player == "black") || 
		(computer == 'w' && player == "white")) {
		getCompMove();
	    }	    
	}
	else comment.setText("Invalid move. Please try again.");
    }

    private void getCompMove() {
	int[] compMove = Othello.computer(player, opponent);
	int compRow = compMove[0]*50, compCol = compMove[1]*50;
	play(compCol, compRow);
    }

    private void switchPlayer() {
	if (player == "black") {
	    player = "white";
	    opponent = "black";
	}
	else {
	    player = "black";
	    opponent = "white";
	}
    }

    private void endGame() {
	turn.setText("No more turns.");
	comment.setText("Game over. "+Othello.findWinner());
    }

    private class MyWindowAdapter extends WindowAdapter {

	public void windowClosing (WindowEvent e) {
	    dispose();
	    System.exit(0);
	}
    }
}





