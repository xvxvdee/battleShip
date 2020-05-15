import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BattleShipBoard {
    private String[][] board;
    private String[][] userBoard;


    private ArrayList<String> shipOne_5 = new ArrayList<>(5);
    private ArrayList<String> shipOne_4 = new ArrayList<>(4);
    private ArrayList<String> shipOne_3 = new ArrayList<>(3);
    private ArrayList<String> shipTwo_3 = new ArrayList<>(3);
    private ArrayList<String> shipOne_2 = new ArrayList<>(2);
    private ArrayList<String> shipOne_5_check = new ArrayList<>();
    private ArrayList<String> shipOne_4_check = new ArrayList<>();
    private ArrayList<String> shipOne_3_check = new ArrayList<>();
    private ArrayList<String> shipTwo_3_check = new ArrayList<>();
    private ArrayList<String> shipOne_2_check = new ArrayList<>();
    private int[] shipSizes = {5, 4, 3, 3, 2};
    boolean[] finishGame = {false, false, false, false, false};
    private Random rand_gen = new Random();
    private boolean horizontal = true, vertical = false;
    private int count = 5;
    private int x_coordinate, y_coordinate;
    private Scanner input = new Scanner(System.in);



    //Creating board
    public void createBoard() {
        board = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = (".");
            }
        }
        addBattleShips(shipSizes, rand_gen);
    }

    //Create playerBoard
    public void createUserBoard() {
        userBoard = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                userBoard[i][j] = (".");
            }
        }
    }

    //print board
    public void printBoard() {
        for (String[] elem : board) {
            for (String y : elem) {
                System.out.print(y + "  ");
            }
            System.out.println();
        }
        System.out.println(shipOne_5);
        System.out.println(shipOne_4);
        System.out.println(shipOne_3);
        System.out.println(shipTwo_3);
        System.out.println(shipOne_2);
    }

    public void printUserBoard() {
        for (String[] elem : userBoard) {
            for (String y : elem) {
                System.out.print(y + "  ");
            }
            System.out.println();
        }
        System.out.println(shipOne_5);
        System.out.println(shipOne_4);
        System.out.println(shipOne_3);
        System.out.println(shipTwo_3);
        System.out.println(shipOne_2);
    }

    public void addBattleShips(int[] shipSizes, Random rand) {
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        //System.out.println(x + "," + y);

        for (int a = 0; a < shipSizes.length; a++) {
            int size = shipSizes[a];
            boolean set = false;
            if (horizontal) {
                do {

                    if (y + size < board.length && isEmptyForwardH(x, y, size, board)) {
                        for (int i = 0; i < size; i++) {
                            board[x][y + i] = "O";
                            storeCoordinate(x, y + i, a);
                        }
                        set = true;
                    } else if (y - size < board.length && isEmptyBackwardsH(x, y, size, board)) {
                        for (int i = 0; i < size; i++) {
                            board[x][y - i] = "O";
                            storeCoordinate(x, y - i, a);
                        }
                        set = true;
                    }
                    if (!set) {
                        x = rand.nextInt(10);
                        y = rand.nextInt(10);
                        //System.out.println("redoH: " + x + "," + y);
                    }
                } while (!set);
                horizontal = false;
                vertical = true;
            } else if (vertical) {
                do {
                    if (x + 4 < board.length && isEmptyForwardV(x, y, size, board)) {
                        for (int i = 0; i < size; i++) {
                            // System.out.println(x + i);
                            board[x + i][y] = "X";
                            storeCoordinate(x + i, y, a);

                        }
                        set = true;
                    } else if (x - 4 < board.length && isEmptyBackwardsV(x, y, size, board)) {

                        for (int i = 0; i < size; i++) {
                            //System.out.println(x - i);
                            board[x - i][y] = "X";
                            storeCoordinate(x - i, y, a);

                        }
                        set = true;
                    }
                    if (!set) {
                        x = rand.nextInt(10);
                        y = rand.nextInt(10);
                    }
                } while (!set);
                vertical = false;
                horizontal = true;
            }


        }

    }


    //HELPER METHODS

    public static boolean isEmptyForwardH(int x, int y, int size, String[][] board) {
        for (int i = 0; i < size; i++) {
            if (!(board[x][y + i].equals("."))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmptyBackwardsH(int x, int y, int size, String[][] board) {
        if (!(y > size)) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!(board[x][y - i].equals("."))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmptyForwardV(int x, int y, int size, String[][] board) {
        for (int i = 0; i < size; i++) {
            if (!(board[x + i][y].equals("."))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmptyBackwardsV(int x, int y, int size, String[][] board) {
        if (!(x > size)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(board[x - i][y].equals("."))) {
                return false;
            }
        }
        return true;
    }

    //to coordinate
    public String convertToCoordinate(int x, int y) {
        String newCoordinate = Integer.toString(x) + "," + Integer.toString(y);
        //  System.out.println(newCoordinate);
        return newCoordinate;
    }

    //storing coordinate
    public void storeCoordinate(int x, int y, int size_index) {
        switch (size_index) {
            case 0:
                shipOne_5.add(convertToCoordinate(x, y));
                break;
            case 1:
                shipOne_4.add(convertToCoordinate(x, y));
                break;
            case 2:
                shipOne_3.add(convertToCoordinate(x, y));
                break;
            case 3:
                shipTwo_3.add(convertToCoordinate(x, y));
                break;
            case 4:
                shipOne_2.add(convertToCoordinate(x, y));
                break;

        }
    }


    //playerGuessCheck
    public void playerOne_GuessCheck(int x, int y) {
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        result.add(true);
        result.add(true);
        result.add(true);
        result.add(true);
        result.add(true);

        result.set(0, changedToHit(shipOne_5,shipOne_5_check, x, y));
        result.set(1, changedToHit(shipOne_4,shipOne_4_check, x, y));
        result.set(2, changedToHit(shipOne_3,shipOne_3_check, x, y));
        result.set(3, changedToHit(shipTwo_3,shipTwo_3_check, x, y));
        result.set(4, changedToHit(shipOne_2,shipOne_2_check, x, y));
        if (!result.contains(true)) {
            System.out.println("YOU MISSED");
            userBoard[x][y]="*";
        }
        result.clear();
    }

    //sinked a ship
    public void sinked_Ship() {
        System.out.println(shipOne_2_check.size());
        if (shipOne_5_check.size() == 5) {
            shipOne_5_check.clear();
            count = changedToSink(shipOne_5, count);
        } else if (shipOne_4_check.size() == 4) {
            shipOne_4_check.clear();
            count = changedToSink(shipOne_4, count);
        } else if (shipOne_3_check.size() == 3) {
            shipOne_3_check.clear();
            count = changedToSink(shipOne_3, count);
        } else if (shipTwo_3_check.size() == 3) {
            shipTwo_3_check.clear();
            count = changedToSink(shipTwo_3, count);
        } else if (shipOne_2_check.size() == 2) {
            shipOne_2_check.clear();
            count = changedToSink(shipOne_2, count);
        }

    }

    //Changed to sink icon;
    public int changedToSink(ArrayList<String> ship, int count) {
        System.out.println("count out forloop:" +count);

        for (int i = 0; i < ship.size(); i++) {
            String[] xy = (ship.get(i).split(","));
            int x = Integer.parseInt(xy[0]), y = Integer.parseInt(xy[1]);
            userBoard[x][y] = "~";
           // finishGame[
            System.out.println("count in forloop:" +count);
        }
        System.out.println("~~~AN ENTIRE SHIP JUST SUNK..." + (count - 1) + " MORE TO GO!~~~~");

        return count - 1;
    }

    public boolean changedToHit(ArrayList<String> ship,ArrayList<String> check, int x, int y) {
        for (int i = 0; i < ship.size(); i++) {
            if (ship.get(i).equals(convertToCoordinate(x, y))) {
                userBoard[x][y] = "X";
                check.add("!");
                System.out.println("YOU HIT A SHIP!");
                return true;
            }
        }
        return false;
    }


    //Check if user won
    public boolean Winner() {
        if (count == 0) {
            System.out.println("YOU HAVE WON!");
            printUserBoard();
            printBoard();
            return true;
        }
        return false;
    }
}
