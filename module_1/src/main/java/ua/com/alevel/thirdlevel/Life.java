package ua.com.alevel.thirdlevel;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.IOException;

public class Life {
    private int[][] board;
    private int height;
    private int width;

    public void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            String src;
            System.out.println("Enter the height of new world");
            height = Integer.parseInt(reader.readLine());
            System.out.println("Enter the width of new world");
            width = Integer.parseInt(reader.readLine());
            setBoard();
            while (true) {
                System.out.println("o-live, .-dead");
                printBoard();
                System.out.println();
                if (nextConfiguration()) printBoard();
                else {
                    printBoard();
                    System.out.println("AUTOSTOP");
                    break;
                }
                System.out.println("Print 0 to stop steps");
                src = reader.readLine();
                if (src.compareTo("0") == 0) break;
            }
            System.out.println("Select 0 to break");
            src = reader.readLine();
            if (src.compareTo("0") == 0 || src.compareTo("q") == 0) break;
        }
    }

    private void setBoard() {
        board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = (int) (Math.random() * 2);
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 1) System.out.print("o ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    private boolean nextConfiguration() {
        int[][] helper = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 1) {
                    if (livecellsAmount(i, j) < 2 || livecellsAmount(i, j) > 3) helper[i][j] = 0;
                    if (livecellsAmount(i, j) == 2 || livecellsAmount(i, j) == 3) helper[i][j] = 1;
                }

                if (board[i][j] == 0) {
                    if (livecellsAmount(i, j) == 3) helper[i][j] = 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == helper[i][j]) count++;
            }
        }
        if (count == height * width) return false;
        count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) count++;
            }
        }
        if (count == height * width) return false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = helper[i][j];
            }
        }
        return true;
    }

    private int livecellsAmount(int i, int j) {
        int amount = 0;
        if (i + 1 < height && j + 1 < width) if (board[i + 1][j + 1] == 1) amount++;//низ диагональ правая
        if (i + 1 < height) if (board[i + 1][j] == 1) amount++;//низ
        if (i + 1 < height && j - 1 >= 0) if (board[i + 1][j - 1] == 1) amount++;//низ диагональ лево
        if (j - 1 >= 0) if (board[i][j - 1] == 1) amount++;//лево
        if (i - 1 >= 0 && j - 1 >= 0) if (board[i - 1][j - 1] == 1) amount++;//верх диагональ лево
        if (i - 1 >= 0) if (board[i - 1][j] == 1) amount++;//верх
        if (i - 1 >= 0 && j + 1 < width) if (board[i - 1][j + 1] == 1) amount++;//верх право
        if (j + 1 < width) if (board[i][j + 1] == 1) amount++;
        return amount;
    }
}