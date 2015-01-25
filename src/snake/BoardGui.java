package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class BoardGui extends JFrame {
    private static board board;
    private game game;
    private JButton button;
    public BoardGui(board board) {
        this.board = board;
        this.button = new JButton("Restart");
        button.setHorizontalAlignment(button.CENTER);
        this.game = new game(board);
        this.add(button, BorderLayout.NORTH);
        this.add(game, BorderLayout.CENTER);
    }
    
    public static void main(final String[] args) throws InterruptedException {
        board board = new board(9, 17);
        //board board = new board(30, 45);
        BoardGui game = new BoardGui(board);
        game.setTitle("Snake");
        game.setPreferredSize(new Dimension(550, 700));
        game.pack();
        game.setVisible(true);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        while (!snake.board.dead){
            System.out.println(board.display());
            game.repaint();
            Thread.sleep(250);
            snake.board.move();
        }
        game.button.setText("You lose, click here to play again");
//        game.button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    game.button.setText("restart");
//                    while (!snake.board.dead){
//                        System.out.println(board.display());
//                        game.repaint();
//                        Thread.sleep(250);
//                        snake.board.move();
//                    }
//                    board.restart();
//                    game.button.setText("You lose, click here to play again");
//                } catch (InterruptedException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//            }
//        });
        
    }
}

