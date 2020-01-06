import java.util.ArrayList;



public class LightsOut {


     /**
     * default width of the game.
     */
    public static final int DEFAULT_WIDTH = 10;
     /**
     * default height of the game.
     */
    public static final int DEFAULT_HEIGTH = 8;


    
@SuppressWarnings("unchecked")
    public static ArrayList<Solution> solve(int width, int height){

        QueueImplementation q  = new QueueImplementation();
        ArrayList<Solution> solutions  = new ArrayList<Solution>();

        q.enqueue(new Solution(width,height));
        long start = System.currentTimeMillis();
        while(!q.isEmpty()){
            Solution s  = (Solution)q.dequeue();
            if(s.isReady()){
                // by construction, it is successfull
                System.out.println("Solution found in " + (System.currentTimeMillis()-start) + " ms" );
                solutions.add(s);
            } else {
                boolean withTrue = s.stillPossible(true);
                boolean withFalse = s.stillPossible(false);
                if(withTrue && withFalse) {
                    Solution s2 = new Solution(s);
                    s.setNext(true);
                    q.enqueue(s);
                    s2.setNext(false);
                    q.enqueue(s2);
                } else if (withTrue) {
                    s.setNext(true);
                    if(s.finish()){
                        q.enqueue(s);
                    }
                } else if (withFalse) {
                    s.setNext(false);
                    if( s.finish()){
                        q.enqueue(s);
                    }
                }
            }
        }
        return solutions;
    }
    @SuppressWarnings("unchecked")
    public static ArrayList<Solution> solve(GameModel model){

      QueueImplementation q  = new QueueImplementation();
        ArrayList<Solution> solutions  = new ArrayList<Solution>();
        System.out.println("Starting from:");
        System.out.println(model);
        q.enqueue(new Solution(model.getWidth(),model.getHeight()));
        long start = System.currentTimeMillis();
        while(!q.isEmpty()){
            Solution s  = (Solution)q.dequeue();
            if(s.isReady()){
                // by construction, it is successful
                System.out.println("Solution found in " + (System.currentTimeMillis()-start) + " ms" );
                solutions.add(s);
            } else {
                boolean withTrue = s.stillPossible(true, model);
                boolean withFalse = s.stillPossible(false, model);
                if(withTrue && withFalse) {
                    Solution s2 = new Solution(s);
                    s.setNext(true);
                    q.enqueue(s);
                    s2.setNext(false);
                    q.enqueue(s2);
                } else if (withTrue) {
                    s.setNext(true);
                    if(s.finish(model)){
                        q.enqueue(s);
                    }
                } else if (withFalse) {
                    s.setNext(false);
                    if( s.finish(model)){
                        q.enqueue(s);
                    }
                }
            }
        }
        return solutions;
    }
    @SuppressWarnings("unchecked")
public static Solution solveShortest(GameModel model){
  ArrayList<Solution> solutions  = solve(model);

    Solution smallest = solutions.get(0);

    for(int i=0; i<solutions.size(); i++){
      if(smallest.getSize()>solutions.get(i).getSize()){
        smallest = solutions.get(i);}
        if(smallest.getSize()==model.getWidth()*model.getHeight())
        {model.reset();
      smallest.getSize();}
    }
    return smallest;
  }

   /**
     * <b>main</b> of the application. Creates the instance of  GameController
     * and starts the game. If two parameters width and height
     * are passed, they are used.
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 1).
     *
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
        int width   = DEFAULT_WIDTH;
        int height  = DEFAULT_HEIGTH;

      

        if (args.length == 2) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<1){
                    System.out.println("Invalid argument, using default...");
                    width = DEFAULT_WIDTH;
                }
                height = Integer.parseInt(args[1]);
                if(height<1){
                    System.out.println("Invalid argument, using default...");
                    height = DEFAULT_HEIGTH;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                height  = DEFAULT_HEIGTH;
            }
        }
        GameController game = new GameController(width, height);
    }


}
