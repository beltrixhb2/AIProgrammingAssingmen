public class minmax {



    public int algorithmminimax(Board position, int depth, boolean max){
        int eval;
        if (depth==0||position.finish()){
            return evaluate(position);
        }else if (max){
            int maxev = 0;
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
            int minev = 0;
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
        int ev = position.checkfinish();
        if (ev==1){
            return 1;
        }else if (ev==2){
            return -1;
        }else return 0;
    }

    public int decission(Board position){
        int maxev = 0;
        int eval;
        int depth = 6;
        int cdecission = 10;
        for (int i=0;i<6;i++){
            if (position.columnfree(i)){
                Board child = new Board(position);
                child.insert(i,1);
                eval = algorithmminimax(child, depth-1,false);
                if (maxev<eval){
                    maxev = eval;
                }
                if (maxev==eval){
                    cdecission = i;
                }
            }
        }
        if (cdecission==10){
            cdecission = (int)(Math.random()*10);
            while (cdecission>=6){
                cdecission = (int)(Math.random()*10);
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
