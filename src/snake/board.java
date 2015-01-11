package snake;

import java.util.Random;

public class board {
    
    private static final double TIMESTEP = .05;
    private static int length;
    private static int width;
    static String[][] grid;
    private static int[] foodSpot;
    private static int[][] snakeSpots;
    private static int snakeLength;
    private static String dir;
    private static boolean dead = false;

    public board(int length, int width) {
        board.length = length;
        board.width = width;
        board.dir = "up";
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
        board.snakeSpots = new int[length*width][2];
        board.snakeSpots[0][0] = width/2;
        board.snakeSpots[0][1] = length/2;
        board.snakeLength = 1;

    }
    public int getLength(){
        return length;
    }
    public int getWidth(){
        return width;
    }
    public static void spawnFood(){
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(length);
        foodSpot[0] = x;
        foodSpot[1] = y;
    }
    public static void move() {
        if (snakeLength>2){
            for (int i = snakeLength; i > 1; i--){
                snakeSpots[i][0] = snakeSpots[i-1][0];
                snakeSpots[i][1] = snakeSpots[i-1][1];
            }
        }
        if (dir.equals("up")){
            snakeSpots[0][0] = snakeSpots[0][0];
            snakeSpots[0][1] = snakeSpots[0][1]-1;
        }
        if (dir.equals("right")){
            snakeSpots[0][0] = snakeSpots[0][0]+1;
            snakeSpots[0][1] = snakeSpots[0][1];
        }
        if (dir.equals("left")){
            snakeSpots[0][0] = snakeSpots[0][0]-1;
            snakeSpots[0][1] = snakeSpots[0][1];
        }
        if (dir.equals("down")){
            snakeSpots[0][0] = snakeSpots[0][0];
            snakeSpots[0][1] = snakeSpots[0][1]+1;
        }
        if (snakeSpots[0][0] >= width || snakeSpots[0][1]>=length ||
                snakeSpots[0][0] < 0 || snakeSpots[0][1]<0){
            board.die();
        }
    }
    private static void die() {
        System.out.println("you lose");
        dead = true;
        
    }
    public void turn(String turn){
        dir = turn;
    }
    public static void eat() {
        int[] end = snakeSpots[snakeLength-1];
        snakeLength += 1;
        board.move();
        snakeSpots[snakeLength-1] = end;
        
    }

    public String display() {
        this.grid = new String[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = " ";
            }
        }
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
        long startTime = System.currentTimeMillis();
        while (!dead){
            if ((System.currentTimeMillis() - startTime) % (100*TIMESTEP) == 0) {
                if (snakeSpots[0] == foodSpot){
                    eat();
                    spawnFood();                    
                }
                System.out.println(board.display());
                snake.board.move();

            }
        }  
    }
        

}
