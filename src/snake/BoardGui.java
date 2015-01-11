package snake;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;

public class BoardGui extends JFrame {
    private GridLayout layout;

    public BoardGui(board board) {
        this.setLayout(new GridLayout(board.getLength(), board.getWidth()));
        for (int i = 0; i < board.getLength(); i++){
            for (int j = 0; j < board.getWidth(); j++){
                this.add(new JLabel(board.grid[i][j]));
            }
        }


    }

    public static void main(final String[] args) {
        board board = new board(20, 10);
        System.out.println(board.display());
        BoardGui main = new BoardGui(board);
        main.setPreferredSize(new Dimension(550, 700));
        main.pack();
        main.setVisible(true);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        for (int i = 0; i < 10000000; i++){
            snake.board.spawnFood(); 
            main.repaint();
            System.out.println(board.display());
        }
        
    }
}

