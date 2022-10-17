package algoritm_test;

import java.util.Stack;
class Solution {
    private int maxr;
    private int maxc;
    public int canMove(int[] loc,int[][]field){
        
        if((loc[0]<0)||(loc[0]>=maxr)){
            return 0;
        }else if((loc[1]<0)||(loc[1]>=maxc)){
            return 0;
        }else if(field[loc[0]][loc[1]] == 0){
            return 0;
        }else if(field[loc[0]][loc[1]] == 2){
            return 1;
        }else{
            return 2;
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Stack<Integer> turn = new Stack<>();
        this.maxr = board.length;
        this.maxc = board[0].length;
        int[][] move = {{-1,0},{1,0},{0,-1},{0,1}};
        int[] selection = null;
        int[] naloc = {aloc[0],aloc[1],1};
        int[] nbloc = {bloc[0],bloc[1],2};
        int[][] nboard = board;
        nboard[aloc[0]][aloc[1]] = 2;
        nboard[bloc[0]][bloc[1]] = 2;
        Stack<int[][]> path = new Stack<>();
        Stack<int[]> players = null;
        Stack<int[]> userAs = new Stack<>();
        Stack<int[]> userBs = new Stack<>();
        int[][] field = null;
        int[] player = null;
        path.push(nboard);
        userBs.push(nbloc);
        userAs.push(naloc);
        turn.push(1);
        int count = 0;
        
        while(path.size()!=0){
        field = path.pop();
        int t = turn.pop();
        if(t == 1){
            players = userAs;
            t = 2;
        }else{
            players = userBs;
            t = 1;
        }
        
        player = players.pop();
        count = 0;
        int check = -1;
        boolean state = false;
        for(int i=0;i<4;i++){
            int[] newLoc = {player[0]+move[i][0],player[1]+move[i][1],player[2]+2};
            check = this.canMove(newLoc,field); 
            if( check == 2){
                int[][] newField = field.clone();
                newField[player[0]][player[1]] = 0;
                newField[newLoc[0]][newLoc[1]] = 2;
                path.push(newField);
                players.push(newLoc);
                turn.push(t);
                count +=1;
            }
            if(check == 1){
                state = true;
            }
        }
        
        if(state){
            player[2]+=1;
        }
        
        if(count == 0){
            if(selection == null){
                if(player[2]%2 == 0){
                    selection[0] = player[2];
                    selection[1] = 0;
                }else{
                    selection[0] = player[2];
                    selection[1] = 1;
                }
            }else{
                if(player[2]%2 == 0){
                    if(selection[1]==1){
                        if(selection[0]>player[2]){
                            selection[0] = player[2];
                            selection[1] = 0;
                        }else{
                            selection[0] = player[2];
                            selection[1] = 1;
                        }  
                    }
                }else{
                    if(selection[1]==0){
                        if(selection[0]<player[2]){
                            selection[0] = player[2];
                            selection[1] = 1;
                        }
                    }
                }
                
            }
        }
        
        }
        return selection[0];
    }
}
