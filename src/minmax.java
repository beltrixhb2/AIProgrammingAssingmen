public class minmax {



    public int algorithmminimax(Board position, int depth, boolean max){
        int eval;
        if (depth==0||position.finish()){
            return evaluate(position);
        }else if (max){
            int maxev = -3000;
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
            return maxev;
        }else{
            int minev = 3000;
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
            return minev;
        }
    }

    public int evaluate(Board position){
        return position.evaluate();
    }

    public int decission(Board position, int depth){
        int eval;
        int maxeval = -3000;
        int[] posibilities = new int[6];
        for (int i=0;i<6;i++){
            if (position.columnfree(i)){
                Board child = new Board(position);
                child.insert(i,1);
                eval = algorithmminimax(child, depth,false);
                posibilities[i] = eval;
            }else{
                posibilities[i] = -3000;
            }
        }
        System.out.println(posibilities[0]);
        System.out.println(posibilities[1]);
        System.out.println(posibilities[2]);
        System.out.println(posibilities[3]);
        System.out.println(posibilities[4]);
        System.out.println(posibilities[5]);
        for (int i=0;i<6;i++){
            if (posibilities[i]>maxeval){
                maxeval = posibilities[i];
            }
        }
        int cdecission = (int)(Math.random()*10);
        while ((cdecission>=6)||(!(posibilities[cdecission]==maxeval))){
            cdecission = (int)(Math.random()*10);
        }

        System.out.println("I choose the column "+cdecission);
        System.out.println();
        return cdecission;
    }
}
