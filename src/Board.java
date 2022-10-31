public class Board {

    private final char[][] board = new char[7][6];

    /**
     * This method is the constructor that initialise a empty board
     */
    public Board() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = '*';
            }
        }
    }

    /**
     * This method copy the board
     * @param copy
     */
    public Board(Board copy) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = copy.getxy(i, j);
            }
        }
    }

    /**
     * This method return a position in the board.
     * The parameter are the coordinates
     * @param x
     * @param y
     * @return
     */
    public char getxy(int x, int y) {
        return board[x][y];
    }

    /**
     * This method prints the board
     */
    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * This method check if the board is full, that implies tie
     * @return
     */
    public boolean tie() {
        boolean full = true;
        for (int i = 0; i < 6; i++) {
            if (board[0][i] == '*') {
                full = false;
            }
        }
        return full;
    }

    /**
     * This method checks if there are 4 pieces in a row.
     * This method uses tie method
     * @return [-1,0,1]
     * IA lost, tie, PLayer win
     */
    public int checkfinish() {
        boolean finish = false;
        int count;
        char winner = '*';
        char anterior;
        //Check full
        if (tie()) {
            return 3;
        }
        //Check rows
        for (int i = 0; i < 7; i++) {
            count = 0;
            anterior = '*';
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == '*') {
                    count = 0;
                    anterior = '*';
                } else {
                    if (board[i][j] == anterior) {
                        count++;
                        if (count == 4) {
                            finish = true;
                            winner = anterior;
                        }
                    } else {
                        count = 1;
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
                        if (board[i][j] == anterior) {
                            count++;
                            if (count == 4) {
                                finish = true;
                                winner = anterior;
                            }
                        } else {
                            count = 1;
                        }
                        anterior = board[i][j];
                    }
                }
            }
        }
        //For checking the diagonals we
        //separate de diagonals in two types: growing and decreasing diagonals
        //and in each one we do two diferents loops to evaluate the diagonals above and below the main diagonals



        //Check growing diagonals
        //above the main diagonal
        for (int i = 5; i >= 0; i--) {
            int col = i, row = 0;
            count = 0;
            anterior = '*';
            while (col < 6 && row < 7) {
                if (board[row][col] == '*') {
                    count = 0;
                    anterior = '*';
                } else {
                    if (board[row][col] == anterior) {
                        count++;
                        if (count == 4) {
                            finish = true;
                            winner = anterior;
                        }
                    } else {
                        count = 1;
                    }
                    anterior = board[row][col];
                }
                col++;
                row++;

            }
            // below the main diagonal
            for (int j = 0; j < 6; j++) {
                row = 1 + j;
                col = 0;  //
                count = 0;
                anterior = '*';
                while (col < 6 && row < 7) {
                    if (board[row][col] == '*') {
                        count = 0;
                        anterior = '*';
                    } else {
                        if (board[row][col] == anterior) {
                            count++;
                            if (count == 4) {
                                finish = true;
                                winner = anterior;
                            }
                        } else {
                            count = 1;
                        }
                        anterior = board[row][col];
                    }
                    col++;
                    row++;

                }


            }
        }
        //Check decreasing diagonals
        //above the main diagonal
        for (int i = 6; i >= 0; i--) {
            int col = 0, row = i;
            count = 0;
            anterior = '*';
            while (col < 6 && row >= 0) {
                if (board[row][col] == '*') {
                    count = 0;
                    anterior = '*';
                } else {
                    if (board[row][col] == anterior) {
                        count++;
                        if (count == 4) {
                            finish = true;
                            winner = anterior;
                        }
                    } else {
                        count = 1;
                    }
                    anterior = board[row][col];
                }
                col++;
                row--;

            }
        }
        //below the main diagonal
        for (int j = 0; j < 6; j++) {
            int row = 6;
            int col = j;
            count = 0;
            anterior = '*';
            while (col < 6 && row >= 0) {
                if (board[row][col] == '*') {
                    count = 0;
                    anterior = '*';
                } else {
                    if (board[row][col] == anterior) {
                        count++;
                        if (count == 4) {
                            finish = true;
                            winner = anterior;
                        }
                    } else {
                        count = 1;
                    }
                    anterior = board[row][col];

                }
                row--;
                col++;
            }


        }


        if (!finish) {
            return 0;
        } else if (winner == 'X') {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * This method insert a piece in a column. The palyer should be declared because
     *each player has in own pieces
     * @param column
     * @param player
     * @return
     */
    public boolean insert(int column, int player) {
        boolean correct = true;
        if (board[0][column] == '*') {
            int j = 0;
            while ((j < 7) && (board[j][column] == '*')) {
                j++;
            }
            if (player == 1) {
                board[j - 1][column] = 'X';
            } else {
                board[j - 1][column] = 'O';
            }
        } else {
            correct = false;
        }
        return correct;
    }

    /**
     * This method works as a signal method using the checkfinish.
     * @return
     */
    public boolean finish() {
        if (checkfinish() == 0) {
            return false;
        }
        return true;
    }

    /**
     * This method checks if a column is empty.
     * @param column
     * @return
     */
    public boolean columnfree(int column) {
        return (board[0][column] == '*');
    }

    /**
     * This method is where we implements some kind of heuristic to or decision method for the IA
     * We have several variables that we use to count several things:
     * --Same pieces in a row
     * --Ally positions + free positions , that means postions not blocked by the oponent
     * --Ally positions ocuppied in 4 positions consecutives
     * --This things are counted for both players.
     *
     * The evaluation heuristic is :
     * Min ocuppied positions in a possible row decrease the score while max ocuppied positions increase the score.
     * One postion add or decrease 1, two pices in a row increase or decrease the score by 2 and so on.
     * When 4 in a row is detected the score is automatically +-2000 depending the player, that means a win.
     *
     * @return
     */
    public int evaluate() {
        boolean finish;
        int score = 0;
        int count;//count same pieces in a row
        int countmax;//count avaible positions for IA
        int countmin;// count avaivble positions for playeer
        int countxmax;// IA x in the posible 4
        int countomin;// player 0 in the posible 4
        char anterior;
        //Check rows
        for (int i = 0; i < 7; i++) {
            count = 0;
            anterior = '*';
            countmin = 0;
            countxmax = 0;
            countomin = 0;
            countmax = 0;
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == '*'||board[i][j] == 'O'){
                    countmin++;
                    if (board[i][j] == 'O'){
                        countmax = 0;
                        countomin++;
                        if (board[i][j] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }if (board[i][j] == '*'||board[i][j] == 'X'){
                    countmax++;
                    if (board[i][j] == 'X'){
                        countmin = 0;
                        countxmax++;
                        if (board[i][j] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }
                if (countmax >= 4) {
                    if (countxmax>1){
                        score = score + countxmax;
                    }
                }
                if (countmin >= 4) {
                    if (countomin>1){
                        score = score - countomin;
                    }
                }
                anterior = board[i][j];
                if (count == 4){
                    finish = true;
                    if (anterior == 'X'){
                        score = 2000;
                    }else{
                        score = -2000;
                    }
                }
            }
        }
        //Check columns
        for (int j = 0; j < 6; j++) {
            count = 0;
            anterior = '*';
            countmin = 0;
            countxmax = 0;
            countomin = 0;
            countmax = 0;
            for (int i = 6; i >= 0; i--) {
                if (board[i][j] == '*'||board[i][j] == 'O'){
                    countmin++;
                    if (board[i][j] == 'O'){
                        countmax = 0;
                        countomin++;
                        if (board[i][j] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }if (board[i][j] == '*'||board[i][j] == 'X'){
                    countmax++;
                    if (board[i][j] == 'X'){
                        countmin = 0;
                        countxmax++;
                        if (board[i][j] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }
                if (countmax >= 4) {
                    if (countxmax>1){
                        score = score + countxmax;
                    }
                }
                if (countmin >= 4) {
                    if (countomin>1){
                        score = score - countomin;
                    }
                }
                anterior = board[i][j];
                if (count == 4){
                    finish = true;
                    if (anterior == 'X'){
                        score = 2000;
                    }else{
                        score = -2000;
                    }
                }
            }
        }
        //Check growing diagonals
        for (int i = 5; i >= 0; i--) {//ciclo descendente
            int col = i, row = 0;  //declaramos los valores que seran los indices a imprimir
            count = 0;
            anterior = '*';
            countmin = 0;
            countxmax = 0;
            countomin = 0;
            countmax = 0;
            while (col < 6 && row < 7) {
                if (board[row][col] == '*'||board[row][col] == 'O'){
                    countmin++;
                    if (board[row][col] == 'O'){
                        countmax = 0;
                        countomin++;
                        if (board[row][col] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }if (board[row][col] == '*'||board[row][col] == 'X'){
                    countmax++;
                    if (board[row][col] == 'X'){
                        countmin = 0;
                        countxmax++;
                        if (board[row][col] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }
                if (countmax >= 4) {
                    if (countxmax>1){
                        score = score + countxmax;
                    }
                }
                if (countmin >= 4) {
                    if (countomin>1){
                        score = score - countomin;
                    }
                }
                anterior = board[row][col];
                if (count == 4){
                    finish = true;
                    if (anterior == 'X'){
                        score = 2000;
                    }else{
                        score = -2000;
                    }
                }
                col++;
                row++;

            }
            for (int j = 0; j < 6; j++) {//ciclo descendente
                row = 1 + j;
                col = 0;  //declaramos los valores que seran los indices a imprimir
                count = 0;
                anterior = '*';
                countmin = 0;
                countxmax = 0;
                countomin = 0;
                countmax = 0;
                while (col < 6 && row < 7) {
                    if (board[row][col] == '*'||board[row][col] == 'O'){
                        countmin++;
                        if (board[row][col] == 'O'){
                            countmax = 0;
                            countomin++;
                            if (board[row][col] == anterior){
                                count++;
                            }else{
                                count = 1;
                            }
                        }else{
                            count = 0;
                        }
                    }if (board[row][col] == '*'||board[row][col] == 'X'){
                        countmax++;
                        if (board[row][col] == 'X'){
                            countmin = 0;
                            countxmax++;
                            if (board[row][col] == anterior){
                                count++;
                            }else{
                                count = 1;
                            }
                        }else{
                            count = 0;
                        }
                    }
                    if (countmax >= 4) {
                        if (countxmax>1){
                            score = score + countxmax;
                        }
                    }
                    if (countmin >= 4) {
                        if (countomin>1){
                            score = score - countomin;
                        }
                    }
                    anterior = board[row][col];
                    if (count == 4){
                        finish = true;
                        if (anterior == 'X'){
                            score = 2000;
                        }else{
                            score = -2000;
                        }
                    }
                    col++;
                    row++;

                }


            }
        }
        //Check decreasing diagonals
        for (int i = 6; i >= 0; i--) {//ciclo descendente
            int col = 0, row = i;  //declaramos los valores que seran los indices a imprimir
            count = 0;
            anterior = '*';
            countmin = 0;
            countxmax = 0;
            countomin = 0;
            countmax = 0;
            while (col < 6 && row >= 0) {
                if (board[row][col] == '*'||board[row][col] == 'O'){
                    countmin++;
                    if (board[row][col] == 'O'){
                        countmax = 0;
                        countomin++;
                        if (board[row][col] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }if (board[row][col] == '*'||board[row][col] == 'X'){
                    countmax++;
                    if (board[row][col] == 'X'){
                        countmin = 0;
                        countxmax++;
                        if (board[row][col] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }
                if (countmax >= 4) {
                    if (countxmax>1){
                        score = score + countxmax;
                    }
                }
                if (countmin >= 4) {
                    if (countomin>1){
                        score = score - countomin;
                    }
                }
                anterior = board[row][col];
                if (count == 4){
                    finish = true;
                    if (anterior == 'X'){
                        score = 2000;
                    }else{
                        score = -2000;
                    }
                }
                col++;
                row--;

            }
        }

        for (int j = 0; j < 6; j++) {
            int row = 6;
            int col = j;  //declaramos los valores que seran los indices a imprimir
            count = 0;
            anterior = '*';
            countmin = 0;
            countxmax = 0;
            countomin = 0;
            countmax = 0;
            while (col < 6 && row >= 0) {
                if (board[row][col] == '*'||board[row][col] == 'O'){
                    countmin++;
                    if (board[row][col] == 'O'){
                        countmax = 0;
                        countomin++;
                        if (board[row][col] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }if (board[row][col] == '*'||board[row][col] == 'X'){
                    countmax++;
                    if (board[row][col] == 'X'){
                        countmin = 0;
                        countxmax++;
                        if (board[row][col] == anterior){
                            count++;
                        }else{
                            count = 1;
                        }
                    }else{
                        count = 0;
                    }
                }
                if (countmax >= 4) {
                    if (countxmax>1){
                        score = score + countxmax;
                    }
                }
                if (countmin >= 4) {
                    if (countomin>1){
                        score = score - countomin;
                    }
                }
                anterior = board[row][col];
                if (count == 4){
                    finish = true;
                    if (anterior == 'X'){
                        score = 2000;
                    }else{
                        score = -2000;
                    }
                }
                row--;
                col++;
            }


        }

        if (tie()) {
            return 0;
        }else{
            return score;
        }

    }

}
