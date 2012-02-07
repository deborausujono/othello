import java.awt.*;
import javax.swing.*;

class OthelloCanvas extends JPanel {

    private final int CANVASSZ = 400;             
    private final int CANVASRC = 8;
    private final int CELLSZ = 50;

    public OthelloCanvas() {
	setPreferredSize (new Dimension(CANVASSZ+1, CANVASSZ+1));
	setBackground(Color.green);
	setOpaque(true);
    }

    public synchronized void paintComponent (Graphics g) {

	super.paintComponent(g);

	Dimension size = getSize();
	int canvasWidth = size.width;
	int canvasHeight = size.height;

	//Draw grid
	g.setColor(Color.black);
	for (int i=0; i<=CANVASRC; ++i)
	    g.drawLine(0,i*CELLSZ,CANVASRC*CELLSZ,i*CELLSZ);
	for (int j=0; j<=CANVASRC; ++j)
	    g.drawLine(j*CELLSZ,0,j*CELLSZ,CANVASRC*CELLSZ);

	//Draw pieces
	for (int i=0; i<8; i++) {
	    for (int j=0; j<8; j++) {
		if (Othello.board[i][j]!=null) {
		    if (Othello.board[i][j]=="black")
			g.setColor(Color.black);
		    else g.setColor(Color.white);
		    g.fillOval(j*CELLSZ+5, i*CELLSZ+5, 40, 40);
		}
	    }
	}
    }
}
		
		
