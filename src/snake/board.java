package snake;

import java.util.Random;

public class board {
    
    private int length;
    private int width;
    private String[][] grid;
    private int[] foodSpot;
    private int[][] snakeSpots;
    private int snakeLength;

    public board(int length, int width) {
        this.length = length;
        this.width = width;
        this.grid = new String[length][width];
        this.foodSpot = new int[2];
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(length);
        this.foodSpot[0] = x;
        this.foodSpot[1] = y;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = " ";
            }
        }
        this.snakeSpots = new int[length*width][2];
        this.snakeSpots[0][0] = width/2;
        this.snakeSpots[0][1] = length/2;
        this.snakeLength = 1;

    }
    public void spawnFood(){
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(length);
        this.foodSpot[0] = x;
        this.foodSpot[1] = y;
    }

    public String display() {
        grid[foodSpot[1]][foodSpot[0]] = "f";
        for (int i = 0; i < snakeLength; i ++) {
            grid[snakeSpots[i][1]][snakeSpots[i][0]] = "S";
        }
        
        String boardText = " ";
        for (int i = 0; i < width; i++) {
            boardText += "-";
        }
        boardText+="\n";
        for (int i = 0; i < length; i++) {
            boardText+="|";
            for (int j = 0; j < width; j++) {
                boardText += grid[i][j];
            }
            boardText+="|\n";
        }
        boardText += " ";
        for (int i = 0; i < width; i++) {
            boardText += "-";
        }
        boardText += "\n";
        return boardText;
        
    }
    public static void main(String[] args){
        board board = new board(10, 20);
        System.out.println(board.display());

        
    }
        

}
