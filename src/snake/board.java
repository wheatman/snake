package snake;

import java.util.Random;

public class board {
    
    private static final double TIMESTEP = .05;
    static int length;
    static int width;
    static String[][] grid;
    private static int[] foodSpot;
    private static int[][] snakeSpots;
    static int snakeLength;
    private static String dir;
    static boolean dead = false;
    private static boolean growing = false;
    private static boolean turning = false;

    public board(int length, int width) {
        board.length = length;
        board.width = width;
        board.dir = "up";
        this.grid = new String[length][width];
        this.setFoodSpot(new int[2]);
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(length);
        this.getFoodSpot()[0] = x;
        this.getFoodSpot()[1] = y;
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
        int x = random.nextInt(width-1);
        int y = random.nextInt(length-1);
        getFoodSpot()[0] = x;
        getFoodSpot()[1] = y;
    }
    public static void move() {
        turning = false;
        int[] head = snakeSpots[0];
        if (!growing){
                for (int i = snakeLength; i > 0; i--){
                    snakeSpots[i][0] = snakeSpots[i-1][0];
                    snakeSpots[i][1] = snakeSpots[i-1][1];
                }
        } else {
            growing = false;
            
            for (int i = snakeLength; i == 0; i--){
                snakeSpots[i][0] = snakeSpots[i-1][0];
                snakeSpots[i][1] = snakeSpots[i-1][1];
            }
        }
        if (dir.equals("up")){
            snakeSpots[0][0] = head[0];
            snakeSpots[0][1] = head[1]-1;
        }
        if (dir.equals("right")){
            snakeSpots[0][0] = head[0]+1;
            snakeSpots[0][1] = head[1];
        }
        if (dir.equals("left")){
            snakeSpots[0][0] = head[0]-1;
            snakeSpots[0][1] = head[1];
        }
        if (dir.equals("down")){
            snakeSpots[0][0] = head[0];
            snakeSpots[0][1] = head[1]+1;
        }
        if (snakeSpots[0][0] >= width || snakeSpots[0][1]>=length ||
                snakeSpots[0][0] < 0 || snakeSpots[0][1]<0){
            board.die();
        }
        for (int i = snakeLength-1; i >= 1; i--){
            if (snakeSpots[0][0] == snakeSpots[i][0] && snakeSpots[0][1] == snakeSpots[i][1]){
                board.die();
            }
            
        }
        if (snakeSpots[0][0] == foodSpot[0] && snakeSpots[0][1]==foodSpot[1]){
            eat();
            spawnFood();
        }
    }
    private static void die() {
        System.out.println("you lose");
        dead = true;
        
    }
    public void turn(String turn){
        if (!turning) {
            turning = true;
            if (turn.equals("up")|| turn.equals("down")){
                if (dir.equals("right")|| dir.equals("left")) {
                    dir = turn;
                }
            }
            if (turn.equals("right")|| turn.equals("left")){
                if (dir.equals("up")|| dir.equals("down")) {
                    dir = turn;
                }
            }
        }
    }
    public static void eat() {
        growing = true;
        snakeLength += 1;
        
        
    }

    public String display() {
        this.grid = new String[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = " ";
            }
        }
        grid[getFoodSpot()[1]][getFoodSpot()[0]] = "f";
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
                if (snakeSpots[0] == getFoodSpot()){
                    eat();
                    spawnFood();                    
                }
                System.out.println(board.display());
                snake.board.move();

            }
        }  
    }
    public static int[] getFoodSpot() {
        return foodSpot;
    }
    public static void setFoodSpot(int[] foodSpot) {
        board.foodSpot = foodSpot;
    }
    
    public static int[][] getSnakeSpots() {
        return snakeSpots;
    }

        

}
