public class minmax {

    public int algorithmminimax(Board position, int depth, boolean max){
        int eval;
        if (depth==0||position.finish()){
            return evaluate(position);
        }else if (max){
            int maxev = -1;
            for (int i=0;i<6;i++){
                if (position.columnfree(i)){
                    Board child = new Board(position);
                    child.instert(i,1);
                    eval = algorithmminimax(child, depth-1,false);
                    maxev = Math.max(eval, maxev);
                }
            }
            return maxev;
        }else{
            int minev = 1;
            for (int i=0;i<6;i++){
                if (position.columnfree(i)){
                    Board child = new Board(position);
                    child.instert(i,2);
                    eval = algorithmminimax(child, depth-1,true);
                    minev = Math.max(eval, minev);
                }
            }
            return minev;
        }
    }

    public int evaluate(Board position){
        int ev = position.checkfinish();
        if (ev==1){
            return 1;
        }else if (ev==2){
            return -1;
        }else return 0;
    }

    public int decission(Board position){
        int maxev = -1;
        int eval;
        int depth = 3;
        int cdecission = 10;
        for (int i=0;i<6;i++){
            if (position.columnfree(i)){
                Board child = new Board(position);
                child.instert(i,2);
                eval = algorithmminimax(child, depth-1,false);
                maxev = Math.max(eval, maxev);
                if (maxev==eval){
                    cdecission = i;
                }
            }
        }
        if (cdecission==10){
            cdecission = (int)(Math. random()*10);
            while (cdecission>6){
                cdecission = (int)(Math. random()*10);
            }
            System.out.println("I choose the column "+cdecission);
            System.out.println();
            return cdecission;
        }
        System.out.println("I choose the column "+cdecission);
        System.out.println();
        return cdecission;
    }
}
