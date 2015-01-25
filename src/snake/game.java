package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class game extends JPanel {
    private board board;

    public game(board board) {
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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = this.getSize();
        double heightScalar = size.getHeight()/snake.board.length; //multiply every x coordinate or dimension by this scalar
        double widthScalar = size.getWidth()/snake.board.width; //multiply every y coordinate or dimension by this scalar
        
        Graphics2D g2 = (Graphics2D) g;
        //clear(g2,size.getWidth(), size.getHeight());
        g2.setColor(Color.black);
        for (int i = 0; i < snake.board.snakeLength; i++){
            drawRectangle(g2, snake.board.getSnakeSpots()[i][0], snake.board.getSnakeSpots()[i][1],  heightScalar, widthScalar);
        }
        
        drawEllipse(g2, snake.board.getFoodSpot()[0], snake.board.getFoodSpot()[1], heightScalar, widthScalar);
        
    }
    private void clear(Graphics2D g2, double width, double height) {
        g2.setColor(Color.white);
        Rectangle2D rectangle = new Rectangle2D.Double(0, 0, width, height);
        g2.draw(rectangle);
        g2.fill(rectangle);
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
    
}