
package dropnumber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Cell {
    public int row;
    public int column;
    public int value;
    
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.value = -1;
    }
    
    public Cell copy() {
        Cell newcell = new Cell(row, column);
        newcell.value = value;
        return newcell;
    }
}

class Column {
    double left;
    double right;
    
    public Column() {
        this.left = -1.0;
        this.right = -1.0;
    }
}

// MVC Design Pattern - Model
public class DropNumber {
    public final static int BOARD_WIDTH = 5;
    public final static int BOARD_HEIGHT = 6;
    private static int boardWidth;
    private static int boardHeight;
    private static double columnHeight;
    private static double columnWidth;
    
    // Singleton Design Pattern (Creational)
    private static DropNumber instance = null;
    
    private Cell[][] board;
    private Column[] columns = new Column[BOARD_WIDTH];
    private int score = 0;
    private int bestScore = 0;
    private String bestScoreFile = "best.dat";
    
    // Singleton Design Pattern
    private DropNumber() {
        board = new Cell[BOARD_HEIGHT][BOARD_WIDTH];
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            for (int c = 0; c < BOARD_WIDTH; c++) {
                board[r][c] = new Cell(r, c);
            }
        }
        for (int i = 0; i < BOARD_WIDTH; i++) {
            columns[i] = new Column();
        }
        Scanner scn = null;
        try {
            // citire 
            scn = new Scanner(new File(bestScoreFile));
            bestScore = scn.nextInt();
        }
        catch (FileNotFoundException fnfe) {
           
        }
        finally {
            // dealocare resurse
            if (scn != null) {
                scn.close();
            }
        }
    }
    
    // Singleton Design Pattern
    // Get the only object available
    public static DropNumber getInstance() {
        if (instance == null)
            instance = new DropNumber();
        return instance;
    }
    
    public void resetBoard() {
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            for (int c = 0; c < BOARD_WIDTH; c++) {
                board[r][c].value = -1;
            }
        }
    }
    
    public boolean hasSupport(int r, int c) {
        while (r < BOARD_HEIGHT) {
            if (board[r][c].value == -1)
                return false;
            r++;
        }
        return true;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getBestScore() {
        return bestScore;
    }
    
    public void addToScore(int val) {
        score += val;    
    }
    
    public boolean updateBestScore() {
        if (score > bestScore) {
            bestScore = score;
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileWriter(bestScoreFile, false));
                pw.print("" + bestScore);
            }
            catch (IOException e) {
               
            }
            finally {
                // dealocare resurse
                if (pw != null) {
                    pw.flush();
                    pw.close();
                }
            }
            return true;
        }
        return false;
    }

        
    public double getColumnLeft(int col) {
        return columns[col].left;
    }
    
    public double getColumnRight(int col) {
        return columns[col].right;
    }
        
    public static int getBoardWidth() {
        return boardWidth;
    }

    public static int getBoardHeight() {
        return boardHeight;
    }

    public static double getColumnHeight() {
        return columnHeight;
    }

    public static double getColumnWidth() {
        return columnWidth;
    }
        
    public void setColumnLeft(int col, double val) {
        columns[col].left = val;
    }
    
    public void setColumnRight(int col, double val) {
        columns[col].right = val;
    }
    
    public static void setBoardWidth(int w) {
        DropNumber.boardWidth = w;
    }

    public static void setBoardHeight(int h) {
        DropNumber.boardHeight = h;
    }

    public static void setColumnHeight(double columnHeight) {
        DropNumber.columnHeight = columnHeight;
    }

    public static void setColumnWidth(double columnWidth) {
        DropNumber.columnWidth = columnWidth;
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || col < 0 || row >= BOARD_HEIGHT || col >= BOARD_WIDTH)
            return null;
        return board[row][col];
    }
    
    public int getCellValue(int row, int col) {
        if (row < 0 || row >= BOARD_HEIGHT || col < 0 || col >= BOARD_WIDTH)
            return -1;
        return board[row][col].value;
    }
    
    public void setCellValue(int row, int col, int val) {
        if (row >= 0 && row < BOARD_HEIGHT && col >= 0 && col < BOARD_WIDTH)
            board[row][col].value = val;
    }
}

