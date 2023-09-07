package tictactoe;
import java.util.*;
public class tictactoe {
    public static boolean xWin = false;
    public static boolean oWin = false;
    public static char grid[][] = new char[3][3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeGrid(grid);
        char currentPlayer = 'X';
        boolean gameOver = false;
        while (!gameOver) {
            printGrid(grid);
            int coordinateRow = scanner.nextInt();
            int coordinateCol = scanner.nextInt();
            if (coordinateCol < 1 || coordinateCol > 3 || coordinateRow < 1 || coordinateRow > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (grid[coordinateRow - 1][coordinateCol - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                } else {
                    currentPlayer = 'X';
                }
                grid[coordinateRow - 1][coordinateCol - 1] = currentPlayer;
                printGrid(grid);
                gameOver = checkForWinner();
            }
        }
    }
    //check if draw
    public static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public static void initializeGrid(char[][] grid){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }
    public static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static boolean checkForWinner() {

        //check rows and columns
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                if (grid[i][0] == 'X') {
                    xWin = true;
                } else if (grid[i][0] == 'O') {
                    oWin = true;
                }
            }
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                if (grid[0][i] == 'X') {
                    xWin = true;
                } else if (grid[0][i] == 'O') {
                    oWin = true;
                }
            }
            //check diagonal
            if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
                if (grid[0][0] == 'X') {
                    xWin = true;
                } else if (grid[0][0] == 'O') {
                    oWin = true;
                }
            }
            if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
                if (grid[0][2] == 'X') {
                    xWin = true;
                } else if (grid[0][2] == 'O') {
                    oWin = true;
                }
            }
        }
        if(xWin) {
            System.out.println("X wins");
            return true;
        } else if (oWin) {
            System.out.println("O wins");
            return true;
        } else if (isDraw()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
}
