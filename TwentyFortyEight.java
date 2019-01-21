import java.util.Scanner;
public class TwentyFortyEight{
static int[][] board=new int[4][4];
   public static void main(String []args){
      resetBoard();
      spawnTile();
      gameRun();
      }
//Prints gameboard (so the loops don’t need to be recreated)
      public static void printBoard(){
        for(int i=0;i<=3;i++){
              System.out.println();
//if statements pad the gameboard so larger numbers don’t misalign board
              for(int j=0;j<=3;j++){
                  if(board[i][j]<10) System.out.print("  "+board[i][j]+"  ");
                  else if(board[i][j]<100) System.out.print(" "+board[i][j]+"  ");
                  else if(board[i][j]<1000)System.out.print(" "+board[i][j]+" ");
                  else System.out.print(board[i][j]+" ");
              }
          }
          System.out.println();
      }
//spawns new number
   public static void spawnTile(){
       int newTile;
       if(Math.random()>=0.9) newTile=4;
       else newTile=2;
       int randX=(int)Math.ceil(Math.random()*4)-1;
       int randY=(int)Math.ceil(Math.random()*4)-1;
       while(board[randY][randX]!=0){
           randX=(int)Math.ceil(Math.random()*4)-1;
           randY=(int)Math.ceil(Math.random()*4)-1;
       }
       board[randY][randX]=newTile;
   }
   public static void downDir(){
       for(int x=1;x<=3;x++){
       for(int i=3;i>0;i--){
          System.out.println();
          for(int j=3;j>=0;j--){
              if(board[i][j]==0){
                  board[i][j]=board[i-1][j];
                  board[i-1][j]=0;
              }
              if(board[i][j]==board[i-1][j]){
                  board[i-1][j]=0;
                  board[i][j]*=2;
              }
            
            
          }
       }
      }
   }
   public static void upDir(){
       for(int x=1;x<=3;x++){
       for(int i=0;i<3;i++){
          for(int j=3;j>=0;j--){
             if(board[i][j]==0){
                  board[i][j]=board[i+1][j];
                  board[i+1][j]=0;
             }
             if(board[i][j]==board[i+1][j]){
                 board[i+1][j]=0;
                 board[i][j]*=2;
             }
            
          }
      }
       }
   }
   public static void rightDir(){
        for(int x=1;x<=3;x++){
        for(int i=0;i<=3;i++){
          for(int j=3;j>0;j--){
              if(board[i][j]==0){
                  board[i][j]=board[i][j-1];
                  board[i][j-1]=0;
              }
              if(board[i][j]==board[i][j-1]){
                  board[i][j-1]=0;
                  board[i][j]*=2;
              }
            
          }
      }
        }
   }
   public static void leftDir(){
       for(int x=1;x<=3;x++){
       for(int i=0;i<=3;i++){
          System.out.println();
          for(int j=0;j<3;j++){
              if(board[i][j]==0){
                  board[i][j]=board[i][j+1];
                  board[i][j+1]=0;
              }
              if(board[i][j]==board[i][j+1]){
                  board[i][j+1]=0;
                  board[i][j]*=2;
              }
            
          }
      }
       }
   }
//checks every piece and surrounding piece to see if game is won or lost
   public static int checkDone(){
       for(int[] i: board){
          for(int j: i){
              if(j==2048) return 1;
              else if(j==0) return 0;
          }
       }
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               if(board[i][j]==board[i][j+1]||board[i][j]==board[i+1][j]) return 0;
             
           }
       }
       return 2;
    
   }
   public static void resetBoard(){
       for(int[] i: board){
          for(int j: i) j=0;
      }
   }
   public static void gameplay(String e){
//adding spawnTile in if branches ensures nothing is spawned with an invalid move
       if(e.equals("a")){
          leftDir();
          spawnTile();
       }
       else if(e.equals("d")){
          rightDir();
          spawnTile();
       }
       else if(e.equals("w")){
          upDir();
          spawnTile();
       }
       else if(e.equals("s")){
          downDir();
          spawnTile();
       }
       else System.out.println("Invalid move");
       checkDone();
     
   }
   public static void gameRun(){
//uses Scanner for moves since arrowkeys are better implemented in gui’s
     printBoard();
     String dir;
      Scanner scan= new Scanner(System.in);
     while(checkDone()!=2){
          dir=scan.nextLine();
          gameplay(dir);
           printBoard();
          }
          System.out.println("Would you like to play again?(Yes/No)");
          dir=scan.nextLine();
          dir=dir.toUpperCase();
//Either creates new game or ends game based on response
          if(dir.equals("YES")) gameRun();
          else if(dir.equals("NO")) System.out.println("Goodbye!");
          else System.out.println("Invalid option");
   }
     
   }
