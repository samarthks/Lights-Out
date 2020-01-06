import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

// YOUR OTHER IMPORTS HERE IF NEEDED



public class GameController implements ActionListener, ItemListener {

    // YOUR VARIABLES HERE
    private GameModel model;
    private GameView view;


    /**
     * Constructor used for initializing the controller. It creates the game's view
     * and the game's model instances
     *
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {

        model = new GameModel(width, height);
        view = new GameView(model, this);

        this.model = model;
        this.view = view;

    }


    /**
     * Callback used when the user clicks a button (reset,
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {


        // YOUR CODE HERE
        //get column and row from button then do model.click(i, k)
        if (e.getActionCommand().equals("Reset")) {
      reset();
    }

    else if
      (e.getActionCommand().equals("Quit")) {
      System.exit(0);
    }

    else if(e.getActionCommand().equals("Random")){
      model.randomize();
      view.update();
    }

//solution left


    else {
      if(e.getSource() instanceof GridButton){
        GridButton gb = (GridButton) e.getSource();
        int col = gb.getColumn();
        int row = gb.getRow();

        model.click(row, col);
        view.update();
      }
    }
  }

    private void solution(){
      model.setSolution();
      view.update();

    }

    private void reset(){
      model.reset();
      view.update();
    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void  itemStateChanged(ItemEvent e){

        // YOU CODE HERE
    }

    // YOUR OTHER METHODS HERE

}
