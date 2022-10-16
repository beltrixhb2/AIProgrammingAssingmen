public class Board {

    private char board[][] = new char[7][6];

    public Board() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = '*';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public int checkfinish() {
        boolean finish = false;
        int count;
        char winner = '*';
        char anterior;
        //Check rows
        for (int i = 0; i < 7; i++){
            count = 0;
            anterior = '*';
            for (int j = 0; j < 6; j++) {
                if (board[i][j]=='*'){
                    count = 0;
                    anterior = '*';
                }else{
                    if (count == 0){
                        count++;
                        if (count == 4) {
                            finish = true;
                            winner = anterior;
                        }
                    }else {
                            if (board[i][j] == anterior) {
                                count++;
                                if (count == 4) {
                                    finish = true;
                                    winner = anterior;
                                }
                            }
                    }
                    anterior = board[i][j];
                }
            }
        }
        //Check columns
        if (!finish) {
            for (int j = 0; j < 6; j++) {
                count = 0;
                anterior = '*';
                for (int i = 0; i < 7; i++) {
                    if (board[i][j] == '*') {
                        count = 0;
                        anterior = '*';
                    } else {
                        if (count == 0) {
                            count++;
                            if (count == 4) {
                                finish = true;
                                winner = anterior;
                            }
                        } else {
                            if (board[i][j] == anterior) {
                                count++;
                                if (count == 4) {
                                    finish = true;
                                    winner = anterior;
                                }
                            }
                        }
                        anterior = board[i][j];
                    }
                }
            }
        }
        //Check growing diagonals





        if (!finish){
            return 0;
        }else if(winner == 'X'){
            return 1;
        }else{
            return 2;
        }
    }

    public boolean instert(int column, int player){
        boolean correct = true;
        if (board[0][column] == '*') {
            int j = 0;
            while ((j < 7)&&(board[j][column] == '*')){
                j++;
            }
            if (player == 1){
                board[j-1][column] = 'X';
            }else{
                board[j-1][column] = 'O';
            }
        }else{
            correct = false;
        }
        return correct;
    }
}

