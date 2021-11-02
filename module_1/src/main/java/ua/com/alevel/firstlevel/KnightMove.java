package ua.com.alevel.firstlevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnightMove {
    public static void run(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println();
            String src;
            System.out.println("Enter current position of knight (example:2-8)->");
            src = reader.readLine();
            if (!dataValidation(src)) {
                System.out.println("Invalid input");
                break;
            }
            String[] current = src.split("-");
            System.out.println("Enter wanted position of knight->");
            src = reader.readLine();
            if (!dataValidation(src)) {
                System.out.println("Invalid input");
                break;
            }
            String[] wanted = src.split("-");
            boardGenerate(Integer.parseInt(current[0]), Integer.parseInt(current[1]), Integer.parseInt(wanted[0]), Integer.parseInt(wanted[1]));
            if (checkPossibilityOfMove(Integer.parseInt(current[0]), Integer.parseInt(current[1]), Integer.parseInt(wanted[0]), Integer.parseInt(wanted[1])))
                System.out.println("Move is possible");
            else System.out.println("Move is impossible");
            System.out.println("Select Q/q to break");
            src = reader.readLine();
            if (src.compareTo("Q") == 0 || src.compareTo("q") == 0) break;
        }
    }

    public static boolean dataValidation(String src) {
        String[] current = src.split("-");
        Pattern pattern = Pattern.compile("^[1-8]-[1-8]$");
        Matcher matcher = pattern.matcher(src);
        return matcher.find();
    }

    public static boolean checkPossibilityOfMove(int currentX, int currentY, int wantedX, int wantedY) {
        int x = Math.abs(currentX - wantedX);
        int y = Math.abs(currentY - wantedY);
        return ((x == 1 && y == 2) || (x == 2 && y == 1));
    }

    static void boardGenerate(int currentX, int currentY, int wantedX, int wantedY) {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = '_';
            }
        }
        board[currentX][currentY] = 'K';
        board[wantedX][wantedY] = '?';
        for (char i = 1; i <= 8; i++) {
            board[i][0] = (char) (48 + i);
            board[0][i] = (char) (48 + i);
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }
}