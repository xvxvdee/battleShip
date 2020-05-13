import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class BattleShipBoard {
    private String[][] board;
    HashMap<String, Integer> coordinates = new HashMap<>();
    HashMap<String, Integer> coordinatesTemp = new HashMap<>();
    String[] ship5 = new String[5];
    String[] ship4 = new String[4];
    String[] ship3 = new String[3];
    String[] ship_3_2 = new String[3];
    String[] ship2 = new String[2];
    //key: x-value   value: y-value
    private Random rand_gen = new Random();
    private boolean horizontal = true, vertical = false;
    int size = 0;

    public BattleShipBoard() {

    }

    //Creating board
    public void createBoard() {
        board = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = (".");
            }
        }
        addBattleShips();
    }

    //print board
    public void printBoard() {
        for (String[] elem : board) {
            for (String y : elem) {
                System.out.print(y + "  ");
            }
            System.out.println();
        }
    }

    //Generate coordinates
    public int[] generateCoordinates() {
        int x;
        int y;
        do {
            x = rand_gen.nextInt(10);
            y = rand_gen.nextInt(10);
        } while (coordinates.containsKey(convertToCoordinate(x, y)));
        return new int[]{x, y};
    }

    //to coordinate
    public String convertToCoordinate(int x, int y) {
        String newCoordinate = Integer.toString(x) + "," + Integer.toString(y);
        //  System.out.println(newCoordinate);
        return newCoordinate;
    }

    //Placing battleships on board
    public void addBattleShips() {
        //The 5 ships are:  Carrier (occupies 5 spaces), Battleship (4), Cruiser (3), Submarine (3), and Destroyer (2)

        int start = 5;
        while (start != 0) {

            int[] coordin = generateCoordinates();
            int x_ship = coordin[0];
            int y_ship = coordin[1];
            System.out.println("Starting:" + x_ship + "," + y_ship);

            if (start == 5) {
                if (horizontal) {
                    placeHorizontal(start, x_ship, y_ship);
                } else if (vertical) {
                    placeVertical(start, x_ship, y_ship);
                }
            } else if (start == 4) {
                if (horizontal) {
                    placeHorizontal(start, x_ship, y_ship);
                } else if (vertical) {
                    placeVertical(start, x_ship, y_ship);
                }
            } else if (start == 3) {
                if (horizontal) {
                    placeHorizontal(start, x_ship, y_ship);
                } else if (vertical) {
                    placeVertical(start, x_ship, y_ship);
                }

            } else if (start == 2) {
                if (horizontal) {
                    placeHorizontal(start, x_ship, y_ship);
                } else if (vertical) {
                    placeVertical(start, x_ship, y_ship);
                }
            }
//            } else if (start == 1) {
//                if (horizontal) {
//                    placeHorizontal(start, x_ship, y_ship);
//                } else if (vertical) {
//                    placeVertical(start, x_ship, y_ship);
//                }
//
//            }

            start--;
            if (start == 0) {
                break;
            }
        }
        addX();
    }


    //Place battleship horizontal
    public void placeHorizontal(int start, int x, int y) {
        int unsualCase = 0;
        if (start == 1) {
            unsualCase = 3;
            int backwards = 1;
            //do {
            for (int i = 0; i < unsualCase; i++) {

                if (y + i < board.length) {
                    int newY = y + i;
                    //System.out.println("y -horizontal: " + newY);
                    if (coordinates.containsKey(convertToCoordinate(x, newY)) || coordinates.containsKey(convertToCoordinate(x, newY))) {
                        System.out.println("redo: new Y");
                        i = 0;
                        int[] redo = generateCoordinates();
                        x = redo[0];
                        y = redo[1];
                    } else {
                        coordinatesTemp.put(convertToCoordinate(x, newY), i);
                    }

                    System.out.println(x + "," + (newY));
                } else {
                    int newY = y - backwards;
                    System.out.println("y -horizontal: " + newY);
                    if (coordinates.containsKey(convertToCoordinate(x, newY)) || coordinates.containsKey(convertToCoordinate(x, newY))) {
                        System.out.println("redo: newY backwards");
                        i = 0;
                        int[] redo = generateCoordinates();
                        x = redo[0];
                        y = redo[1];
                    } else {
                        coordinatesTemp.put(convertToCoordinate(x, newY), i);
                    }
                    System.out.println(x + "," + (newY));

                    backwards++;
                }


            }
        } else {
            int backwards = 1;
            //do {
            for (int i = 0; i < start; i++) {

                if (y + i < board.length) {
                    int newY = y + i;
                    //System.out.println("y -horizontal: " + newY);
                    if (coordinates.containsKey(convertToCoordinate(x, newY)) || coordinates.containsKey(convertToCoordinate(x, newY))) {
                        System.out.println("redo: new Y");
                        i = 0;
                        int[] redo = generateCoordinates();
                        x = redo[0];
                        y = redo[1];
                    } else {
                        coordinatesTemp.put(convertToCoordinate(x, newY), i);
                    }

                    System.out.println(x + "," + (newY));
                } else {
                    int newY = y - backwards;
                    System.out.println("y -horizontal: " + newY);
                    if (coordinates.containsKey(convertToCoordinate(x, newY)) || coordinates.containsKey(convertToCoordinate(x, newY))) {
                        System.out.println("redo: newY backwards");
                        i = 0;
                        int[] redo = generateCoordinates();
                        x = redo[0];
                        y = redo[1];
                    } else {
                        coordinatesTemp.put(convertToCoordinate(x, newY), i);
                    }
                    System.out.println(x + "," + (newY));

                    backwards++;
                }
            }


        }
        // }while(!isUnique(tempList));
        horizontal = false;
        vertical = true;
        for (String elem : coordinatesTemp.keySet()) {
            coordinates.put(elem, coordinatesTemp.get(elem));
        }
        keepTrack(start, coordinatesTemp);
        System.out.println(coordinatesTemp);
        System.out.println(coordinates);
        coordinatesTemp.clear();
    }


    //Place battleship vertical
    public void placeVertical(int start, int x, int y) {

        int backwards = 1;
        for (int i = 0; i < start; i++) {

            if (x + i < board.length) {
                int newX = x + i;
                if (coordinates.containsKey(convertToCoordinate(newX, y)) || coordinates.containsKey(convertToCoordinate(newX, y))) {
                    System.out.println("redo: newX");
                    i = 0;
                    int[] redo = generateCoordinates();
                    x = redo[0];
                    y = redo[1];
                } else {
                    coordinatesTemp.put(convertToCoordinate(newX, y), i);
                }
                //tempList.add(convertToCoordinate(newX, y));

                //}
                // tempList.add(convertToCoordinate(newX, y));
                System.out.println(newX + "," + y);

            } else {
                int newX = x - backwards;
                //board[x - backwards][y] = "X";
                if (coordinates.containsKey(convertToCoordinate(newX, y)) || coordinates.containsKey(convertToCoordinate(newX, y))) {
                    System.out.println("redo: newX backwards");
                    i = 0;
                    int[] redo = generateCoordinates();
                    x = redo[0];
                    y = redo[1];
                }//                        tempList.add(convertToCoordinate(newX, y));
                else {
                    coordinatesTemp.put(convertToCoordinate(newX, y), i);
                }
                // }
                // tempList.add(convertToCoordinate(newX, y));

                System.out.println(newX + "," + y);

                backwards++;

            }
            if (coordinatesTemp.size() == start) {
                break;
            }
        }
        for (String elem : coordinatesTemp.keySet()) {
            coordinates.put(elem, coordinatesTemp.get(elem));
        }
        keepTrack(start, coordinatesTemp);
        System.out.println(coordinatesTemp);
        System.out.println(coordinates);
        coordinatesTemp.clear();
//            System.out.println("IS UNIQUE: "+isUnique(tempList));
//            if (!isUnique(tempList)) {
//                int[] tryagain = generateCoordinates();
//                x = tryagain[0];
//                y = tryagain[1];
//            }
//            System.out.println(tempList);

        // } while (!isUnique(tempList));
        vertical = false;
        horizontal = true;
    }

//    //checks if temp list is unique
//    public boolean isUnique(ArrayList<String> list) {
//        for (String elem : list) {
//            if (listOfCoordinates.contains(elem)) {
//                System.out.println(listOfCoordinates.contains(elem));
//                System.out.println(elem);
//                return false;
//            }
//        }
//        return true;
//    }

    //add x
    public void addX() {
//        for (String elem : coordinates.keySet()) {
//            String[] xy = elem.split(",");
//            board[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "X";
//
//        }

        for(int i=0;i <ship5.length;i++){
            String[] xy = ship5[i].split(",");
            board[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "5";
        }
        for(int i=0;i <ship4.length;i++){
            String[] xy = ship4[i].split(",");
            board[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "4";
        }
        for(int i=0;i <ship3.length;i++){
            String[] xy = ship3[i].split(",");
            board[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "3";
        }
        for(int i=0;i <ship2.length;i++){
            String[] xy = ship2[i].split(",");
            board[Integer.parseInt(xy[0])][Integer.parseInt(xy[1])] = "2";
        }

    }

    public void keepTrack(int start, HashMap<String, Integer> map) {

        switch (start) {
            case 1:
                int a = 0;
                while (a != 3) {
                    for (String elem : map.keySet()) {
                        System.out.println(Arrays.toString(ship_3_2)+"{{{{");
                        ship_3_2[a] = elem;
                        a++;

                    }
                }
                break;
            case 2:
                int i = 0;
                while (i != 2) {
                    for (String elem : map.keySet()) {
                        ship2[i] = elem;
                        i++;

                    }
                }
                break;
            case 3:
                int b = 0;
                while (b < 3) {
                    for (String elem : map.keySet()) {
                        ship3[b] = elem;
                        System.out.println(Arrays.toString(ship3) +"<");
                        b++;

                    }
                }
                break;
            case 4:
                int k = 0;
                while (k != 4) {
                    for (String elem : map.keySet()) {
                        ship4[k] = elem;
                        k++;

                    }
                }
                break;
            case 5:
                int l = 0;
                while (l != 5) {
                    System.out.println(l);
                    for (String elem : map.keySet()) {
                        ship5[l] = elem;
                        l++;
                    }

                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + start);
        }
        System.out.println(Arrays.toString(ship2));
        System.out.println(Arrays.toString(ship3));
        System.out.println(Arrays.toString(ship_3_2));
        System.out.println(Arrays.toString(ship4));
        System.out.println(Arrays.toString(ship5));

    }

}
