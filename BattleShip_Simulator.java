import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BattleShip_Simulator {
    public static void main(String[] args) {

        BattleShipBoard newGame = new BattleShipBoard();
        Scanner input = new Scanner(System.in);
        String x = "", y = "";
        int x_coordinate = 0;
        int y_coordinate = 0;
        boolean result1 = false;
        boolean result2 = false;
        boolean check = false;

        ArrayList<String> userInputs = new ArrayList<>();

        newGame.createBoard();
        newGame.createUserBoard();
        newGame.printBoard();
        System.out.println();
        newGame.printUserBoard();
        for (int i = 0; i < 20; i++) {

            do {
                System.out.print("Enter the x-coordinate: ");
                x = input.next();
                System.out.print("Enter the y-coordinate: ");
                y = input.next();
                check=invalidInputCheck(newGame,x, y,userInputs);
                System.out.println();
            }
            while (!Character.isDigit(x.charAt(0)) || !Character.isDigit(y.charAt(0)) || x.length() > 1 || y.length() > 1||check);

            x_coordinate= Integer.parseInt(x);
            y_coordinate=Integer.parseInt(y);
            userInputs.add(newGame.convertToCoordinate(x_coordinate, y_coordinate));
            System.out.println(userInputs.size() + " <<");
            System.out.println(userInputs);
            newGame.playerOne_GuessCheck(x_coordinate, y_coordinate);
            newGame.sinked_Ship();
            newGame.printUserBoard();
            if (newGame.Winner()) {
                System.out.println(newGame.Winner());
                break;
            }
        }

    }


    public static boolean invalidInputCheck(BattleShipBoard game, String x, String y, ArrayList<String> inputs) {
        try {
            int int_x = Integer.parseInt(x);
            int int_y = Integer.parseInt(y);
            String point = game.convertToCoordinate(int_x, int_y);
            if ((x.length() != 1 && !(int_x >= 0 && int_x <= 9)) || y.length() != 1 && !(int_y >= 0 && int_y <= 9)) {
                System.out.println("That coordinate is not on the board! Please enter a number from 0-9! Guess Again!");
                return true;
            }
            if (inputs.contains(point)) {
                System.out.println("You've already guessed: " + point+" Guess Again!");
                return true;

            }
        } catch (NumberFormatException n) {
            System.out.println("Please enter a number. Guess Again!");
            return true;
        }
        return false;
    }

}

