package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class BoardGui extends JFrame {
    private board board;

    public BoardGui(board board) {
        this.board = board;
        this.setFocusable(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                new KeyEventDispatcher()  { 
                    public boolean dispatchKeyEvent(KeyEvent e){
                        if(e.getID() == KeyEvent.KEY_PRESSED){
                            handleKeyPress(e.getKeyCode());
                        }
                        return false;
                    } 
            });
    }

    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
        case 37:// LEFT KEY
            board.turn("left");
            break;
        case 38:// UP KEY
            board.turn("up");
            break;
        case 39:// RIGHT KEY
            board.turn("right");
            break;
        case 40:// DOWN KEY
            board.turn("down");
            break;
        }
    }
    public void paint(Graphics g) {
        Dimension size = this.getSize();
        double heightScalar = size.getHeight()/board.length; //multiply every x coordinate or dimension by this scalar
        double widthScalar = size.getWidth()/board.width; //multiply every y coordinate or dimension by this scalar
        
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < snake.board.snakeLength; i++){
            System.out.println(i);
            drawRectangle(g2, board.getSnakeSpots()[i][0], board.getSnakeSpots()[i][1],  heightScalar, widthScalar);
        }
        
        drawEllipse(g2, board.getFoodSpot()[0], board.getFoodSpot()[1], heightScalar, widthScalar);
        
    }
    private void drawRectangle(Graphics2D g2,int x, int y, double heightScalar, double widthScalar) {
        double xPos = x * widthScalar;
        double yPos = y * heightScalar;
        double width = widthScalar;
        double height = heightScalar;
        Rectangle2D rectangle = new Rectangle2D.Double(xPos, yPos, width, height);
        g2.draw(rectangle);
        g2.fill(rectangle);
    }
    private void drawEllipse(Graphics2D g2,int x, int y, double heightScalar, double widthScalar) {
        double xPos = x * widthScalar;
        double yPos = y * heightScalar;
        double width = widthScalar;
        double height = heightScalar;
        Ellipse2D ellipse = new Ellipse2D.Double(xPos, yPos, width, height);
        g2.draw(ellipse);
        g2.fill(ellipse);
    }

    public static void main(final String[] args) throws InterruptedException {
        board board = new board(75, 50);
        System.out.println(board.display());
        BoardGui main = new BoardGui(board);
        main.setPreferredSize(new Dimension(550, 700));
        main.pack();
        main.setVisible(true);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        while (!snake.board.dead){
            //System.out.println(board.display());
            main.repaint();
            Thread.sleep(250);
            board.move();
        }
        
    }
}

