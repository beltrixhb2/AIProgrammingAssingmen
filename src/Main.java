import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board tablero = new Board();
        Scanner input = new Scanner (System.in);
        int column;
        tablero.printBoard();
        while (tablero.checkfinish()==0) {
            column = input.nextInt();
            tablero.instert(column,1);
            tablero.printBoard();
            if (tablero.checkfinish()==0){
                column = input.nextInt();
                tablero.instert(column,2);
                tablero.printBoard();
            }
        }
        System.out.println("Ha ganado el jugador " +tablero.checkfinish());
    }
}