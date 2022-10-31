import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Declare the variables
        Board tablero = new Board();
        minmax IA = new minmax();
        Scanner input = new Scanner (System.in);
        int column;
        //Input of the depth of the algorithm
        System.out.println("Choose the depth of the algorithm");
        int depth = input.nextInt();
        tablero.printBoard();
        //Loop of the gameplay until the game is ended with a winner or with a tie
        while (tablero.checkfinish()==0) {
            //The IA calls the method decission to choose the column and then insert the token
            column = IA.decission(tablero, depth);
            tablero.insert(column,1);
            tablero.printBoard();
            //If the game is not finished, it is the turn of the second player
            if (tablero.checkfinish()==0){
                //The human player choose the column to insert the token and the program checks if it is not full
                column = input.nextInt();
                if (!tablero.insert(column,2)){
                    System.out.println("The column "+column+" is already full, please chose other column");
                    column = input.nextInt();
                    while (!tablero.insert(column,2)){
                        System.out.println("The column "+column+" is already full, please chose other column");
                        column = input.nextInt();
                    }
                }
                tablero.printBoard();
            }
        }
        //When the game is finished, print the winner
        int winner = tablero.checkfinish();
        if (winner == 3){
            System.out.println("Oh my god, we have a tie");
        }else if (winner == 3){
            System.out.println("The winner is player X");
        }else{
            System.out.println("The winner is player O");
        }
    }
}