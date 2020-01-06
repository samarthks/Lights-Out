//import LightsOut;
public class GameModel {

  // Your code here
  private static boolean[][] board;

  /**
  *  width of the game
  */
  private int width;

  /**
  * height of the game
  */
  private int height;

  private int currentIndex;

  private static int NumberOfSteps;

  private Solution solution;

  public GameModel(int width, int height) {
    this.width = width;
    this.height = height;

    currentIndex = 0;

    board = new boolean[height][width];

    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        this.board[i][j] = false;
      }
    }

    NumberOfSteps = 0;
  }

  public int getHeight(){
    return height;
  }

  public int getWidth(){
    return width;
  }

  private int getCurrentIndex(){
    return currentIndex;
  }

  public boolean isON(int i, int j){
    return this.board[i][j];
  }

  public void reset(){
    for(int i=0; i<getHeight(); i++){
      for(int j=0; j<getWidth(); j++){
        board[i][j] = false;
      }
    }
    NumberOfSteps = 0;
  }

  public void set(int i, int j, boolean value){
    this.board[j][i] = value;
  }

  public String toString(){
    StringBuffer out = new StringBuffer();
    out.append("[");
    for(int i = 0; i < height; i++){
      out.append("[");
      for(int j = 0; j < width ; j++) {
        if (j>0) {
          out.append(",");
        }
        out.append(board[i][j]);
      }
      out.append("]"+(i < height -1 ? ",\n" :""));
    }
    out.append("]");
    return out.toString();
  }

  public void click(int i,int j){
    NumberOfSteps++;
    board[i][j] = !board[i][j];
    if(i > 0) {
      board[i-1][j] = !board[i-1][j];
    }
    if((i < getHeight() -1 )) {
      board[i+1][j] = !board[i+1][j];
    }
    if((j > 0)) {
      board[i][j-1] = !board[i][j-1];
    }
    if((j < (getWidth() - 1))) {
      board[i][j+1] = !board[i][j+1];
    }
  }

  public boolean isFinished(){
    for(int i=0; i<getHeight(); i++){
      for(int j=0; j<getWidth(); j++){
        if(!isON(i,j)){
          return false;
        }
      }
    }
    return true;
  }

  public int getNumberOfSteps(){
    return NumberOfSteps;
  }

  public void randomize(){
    reset();
    do
    {
      board = new boolean [getHeight()][getWidth()];
      for(int i=0; i<getHeight(); i++){
        for(int j=0; j<getWidth(); j++){
          board[i][j] = (Math.random()<0.5);
        }
      }
    }
    while(LightsOut.solveShortest(this) == null);
  }


  public void setSolution(){
    solution = LightsOut.solveShortest(this);
  }

  public boolean solutionSelects(int i, int j){
    return solution!=null && solution.get(i, j);
  }






}
