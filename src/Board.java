public class Board {

    private final char[][] board = new char[7][6];

    public Board() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = '*';
            }
        }
    }

    public Board(Board copy) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = copy.getxy(i, j);
            }
        }
    }


    public char getxy(int x, int y) {
        return board[x][y];
    }

    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }


    public boolean tie() {
        boolean full = true;
        for (int i = 0; i < 6; i++) {
            if (board[0][i] == '*') {
                full = false;
            }
        }
        return full;
    }

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
        //Check growing diagonals
        for (int i = 5; i >= 0; i--) {//ciclo descendente
            int col = i, row = 0;  //declaramos los valores que seran los indices a imprimir
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
            for (int j = 0; j < 6; j++) {//ciclo descendente
                row = 1 + j;
                col = 0;  //declaramos los valores que seran los indices a imprimir
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
        for (int i = 6; i >= 0; i--) {//ciclo descendente
            int col = 0, row = i;  //declaramos los valores que seran los indices a imprimir
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

        for (int j = 0; j < 6; j++) {
            int row = 6;
            int col = j;  //declaramos los valores que seran los indices a imprimir
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

    public boolean finish() {
        if (checkfinish() == 0) {
            return false;
        }
        return true;
    }

    public boolean columnfree(int column) {
        return (board[0][column] == '*');
    }


    public int evaluate() {
        boolean finish;
        int score = 0;
        int count;
        int countmax;
        int countmin;
        int countxmax;
        int countomin;
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
            for (int i = 0; i < 7; i++) {
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
