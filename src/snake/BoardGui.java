package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class BoardGui extends JFrame {
    private board board;

    public BoardGui(board board) {
        this.board = board;
        this.setFocusable(true);
    }
    public void paint(Graphics g) {
        Dimension size = this.getSize();
        double heightScalar = size.getHeight()/20.0; //multiply every x coordinate or dimension by this scalar
        double widthScalar = size.getWidth()/20.0; //multiply every y coordinate or dimension by this scalar
        
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < board.snakeLength; i++){
        drawRectangle(g2, object, heightScalar, widthScalar);
        }
        
        drawEllipse(g2, board.foodSpot, heightScalar, widthScalar);
        
    }
    private void drawRectangle(Graphics2D g2, Double[] object, double heightScalar, double widthScalar) {
        //set color based on gadget type
        int typeOfGadget = object[10].intValue();
        //draw and fill rectangle given coordinates and dimensions
        double x = object[2] * widthScalar;
        double y = object[3] * heightScalar;
        double width = object[4] * widthScalar;
        double height = object[5] * heightScalar;
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
        g2.draw(rectangle);
        g2.fill(rectangle);
    }
    private void drawEllipse(Graphics2D g2, Double[] object, double heightScalar, double widthScalar) {
        //set color based on gadget type
        int typeOfGadget = object[10].intValue();
        //draw and fill ellipse given coordinates and dimensions
        double x = object[1] * widthScalar;
        double y = object[2] * heightScalar;
        double width = object[3] * widthScalar;
        double height = object[4] * heightScalar;
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, height);
        g2.draw(ellipse);
        g2.fill(ellipse);
    }

    public static void main(final String[] args) {
        board board = new board(20, 10);
        System.out.println(board.display());
        BoardGui main = new BoardGui(board);
        main.setPreferredSize(new Dimension(550, 700));
        main.pack();
        main.setVisible(true);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        while (!board.dead){
            System.out.println(board.display());
        }
        
    }
}

