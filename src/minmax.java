public class minmax {



    public int algorithmminimax(Board position, int depth, boolean max){
        int eval;
        //Base case, when you reached the depth of the algorithm or position of the game has a winner, it returns the heuristic evaluation of the position
        if (depth==0||position.finish()){
            return position.evaluate();
        //The rest of the cases
        //If max has the next movement
        }else if (max){
            //Maxev stores the maximum evaluation of the positions already explored, first is declared with a realy low value to always be changed
            int maxev = -3000;
            //Loop to explore all the columns but only the free columns, evaluate all the possible positions with the next movement and update maxev
            for (int i=0;i<6;i++){
                if (position.columnfree(i)){
                    Board child = new Board(position);
                    child.insert(i,1);
                    eval = algorithmminimax(child, depth-1,false);
                    if (maxev<eval){
                        maxev = eval;
                    }
                }
            }
            //Returns the maximum evaluation possible that is what max seachs for
            return maxev;
        //If min has the next movement
        }else{
            //minev stores the minimum evaluation of the positions already explored, first is declared with a realy big value to always be changed
            int minev = 3000;
            //Loop to explore all the columns but only the free columns, evaluate all the possible positions with the next movement and update minev
            for (int i=0;i<6;i++){
                if (position.columnfree(i)){
                    Board child = new Board(position);
                    child.insert(i,2);
                    eval = algorithmminimax(child, depth-1,true);
                    if (minev>eval){
                        minev = eval;
                    }
                }
            }
            //Returns the minimum evaluation possible that is what min seachs for
            return minev;
        }
    }

    //Method for max to choose the best option
    public int decission(Board position, int depth){
        int eval;
        //Maxev stores the maximum evaluation of the positions already explored, first is declared with a realy low value to always be changed
        int maxeval = -3000;
        int[] posibilities = new int[6];
        //posiblilites is an array that stores the evaluation of all the possible options of columns
        for (int i=0;i<6;i++){
            if (position.columnfree(i)){
                Board child = new Board(position);
                child.insert(i,1);
                eval = algorithmminimax(child, depth,false);
                posibilities[i] = eval;
            //if the column is not free, the evaluation is too low so the algorithm doesn't choose it
            }else{
                posibilities[i] = -3000;
            }
        }
        //It prints the evaluation of the possibilities, just for infrmation of the options the algorithm had
        System.out.println(posibilities[0]);
        System.out.println(posibilities[1]);
        System.out.println(posibilities[2]);
        System.out.println(posibilities[3]);
        System.out.println(posibilities[4]);
        System.out.println(posibilities[5]);
        //Choose the best evaluation
        for (int i=0;i<6;i++){
            if (posibilities[i]>maxeval){
                maxeval = posibilities[i];
            }
        }
        //it makes the decission, this makes that when there are two columns with the same evaluation, it choose one of them ramdomly
        int cdecission = (int)(Math.random()*10);
        while ((cdecission>=6)||(!(posibilities[cdecission]==maxeval))){
            cdecission = (int)(Math.random()*10);
        }

        //print the decission
        System.out.println("I choose the column "+cdecission);
        System.out.println();
        return cdecission;
    }
}
