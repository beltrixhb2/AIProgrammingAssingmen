import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board tablero = new Board();
        minmax IA = new minmax();
        Scanner input = new Scanner (System.in);
        int column;
        tablero.printBoard();
        while (tablero.checkfinish()==0) {
           // column = IA.decission(tablero);
            column = input.nextInt();
            if (!tablero.insert(column,1)){
                System.out.println("The column "+column+" is already full, please chose other column");
                column = input.nextInt();
                while (!tablero.insert(column,1)){
                    System.out.println("The column "+column+" is already full, please chose other column");
                    column = input.nextInt();
                }
            }
            tablero.printBoard();
            if (tablero.checkfinish()==0){
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
        System.out.println("The winner is player " +tablero.checkfinish());
        tablero.printBoard();
    }
}