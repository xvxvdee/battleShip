import java.util.ArrayList;;
import java.util.HashMap;
import java.util.Random;

public class testingShitOut {
    public static void main(String[] args) {
        Random rand = new Random();
        HashMap<String, Integer> SOMETHING = new HashMap<>();
        HashMap<String, Integer> small = new HashMap<>();
        ArrayList<Integer> finalList = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        boolean check = false;
        boolean horizontal = true, vertical = false;

        String board[][] = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = (".");
            }
        }
        printBoard(board);
        System.out.println();

        String[] shipOne_5 = new String[5];
        String[] shipOne_4 = new String[4];
        String[] shipOne_3 = new String[3];


        boolean set = false;
        boolean set2 = false;
        boolean set3=false;

        int size = 5;
        int size2 = 4;
        int size3=3;

        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        System.out.println(x+","+y);

        if (horizontal) {
            do {

                if (y + 5 < board.length && isEmptyForwardH(x, y, size, board)) {
                    for (int i = 0; i < shipOne_5.length; i++) {
                        board[x][y + i] = "O";
                    }
                    set = true;
                } else if (y - 5 < board.length && isEmptyBackwardsH(x, y, size, board)) {
                    for (int i = 0; i < shipOne_5.length; i++) {
                        board[x][y - i] = "O";
                    }
                    set = true;
                }
                if(!set){
                    x = rand.nextInt(10);
                    y = rand.nextInt(10);
                    System.out.println("redoH: "+ x+","+y);
                }
            } while (!set);

        }

        do {

            if (x + 4 < board.length && isEmptyForwardV(x, y, size2, board)) {
                for (int i = 0; i < shipOne_4.length; i++) {
                    System.out.println(x+i);
                    board[x+i][y] = "X";
                }
                set2 = true;
            } else if (x - 4 < board.length && isEmptyBackwardsV(x, y, size2, board)) {
                System.out.println("here2");

                for (int i = 0; i < shipOne_4.length; i++) {
                    System.out.println(x-i);
                    board[x-i][y] = "X";
                }
                set2 = true;
            }
            if(!set2){
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                System.out.println("redoV: "+ x+","+y);
            }
        } while (!set2);

        do {

            if (y + 3 < board.length && isEmptyForwardH(x, y, size3, board)) {
                for (int i = 0; i < shipOne_3.length; i++) {
                    board[x][y + i] = "C";
                }
                set3 = true;
            } else if (y - 3 < board.length && isEmptyBackwardsH(x, y, size3, board)) {
                for (int i = 0; i < shipOne_3.length; i++) {
                    board[x][y - i] = "C";
                }
                set3 = true;
            }
            if(!set3){
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                System.out.println("redoH: "+ x+","+y);
            }
        } while (!set3);
        printBoard(board);


//        for(int i=0;i<20;i++){
//            int random =rand.nextInt(30);
//            System.out.println("i"+i);
//            if(small.containsKey(Integer.toString(random)) || SOMETHING.containsKey(Integer.toString(random))){
//                System.out.println(random);
//                System.out.println("already in here");
//                i-=1;
//                continue;
//            }
//            else {
//                small.put(Integer.toString(random), i);
//
//            }
//            if(small.size()==5){
//                System.out.println(small);
//                for(String elem:small.keySet()){
//                    SOMETHING.put(elem,small.get(elem));
//                }
//                small.clear();
//
//            }
//
//        }
//        System.out.println(SOMETHING);

//        for (int j = 0; j < 5; j++) {
//            for (int i = 0; i < 5; i++) {
//
//                do {
//                   int random = rand.nextInt(20);
//                    System.out.println("num:" + random);
//                    if (temp.contains(random)) {
//                        check=true;
//                    } else {
//                        temp.add(random);
//                        check = false;
//                    }
//                } while (check=true);
//            }
//        }

//                if (!isUnique(temp, finalList) && !isUnique(temp,temp)) {
//                    System.out.println("clearing:" + temp);
//                    //finalList.remove(find(finalList,random));
//                    temp.clear();
//                    i = 0;
//                }


//            if(isUnique(temp, finalList)) {
//                System.out.println(temp);
//                System.out.println(finalList);
//                //finalList.addAll(temp);
//            }


//        System.out.println(finalList);
    }

    public static boolean isUnique(ArrayList<Integer> list, ArrayList<Integer> FINAL) {
        System.out.println("checking uniqueness...");
        for (int elem : list) {
            if (FINAL.contains(elem)) {
                System.out.println("uniqueness: " + elem + ":" + FINAL.contains(elem));
                return false;
            }
        }
        return true;
    }

    public static int find(ArrayList<Integer> FINAL, int elem) {
        int value = 0;
        for (int i = 0; i < FINAL.size(); i++) {
            if (FINAL.get(i) == elem) {
                value = i;
            }
        }
        return value;
    }

    public static void printBoard(String[][] board) {
        for (String[] elem : board) {
            for (String y : elem) {
                System.out.print(y + "  ");
            }
            System.out.println();
        }
    }

    public static boolean isEmptyForwardH(int x, int y, int size, String[][] board) {
        for (int i = 0; i < size; i++) {
            if (!(board[x][y + i].equals("."))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmptyBackwardsH(int x, int y, int size, String[][] board) {
        if(!(y>size)){
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!(board[x][y - i].equals("."))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isEmptyForwardV(int x, int y, int size, String[][] board) {
        for (int i = 0; i < size; i++) {
            if (!(board[x+i][y].equals("."))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmptyBackwardsV(int x, int y, int size, String[][] board) {
        if(!(x>size)){
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(board[x-i][y].equals("."))) {
                return false;
            }
        }
        return true;
    }
}
