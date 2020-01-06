// YOUR IMPORT HERE
import javax.swing.*;
import java.awt.*;
public class GridButton extends JButton {

private int x,y;

    // YOUR VARIABLES HERE
//private GameModel model;
    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     *
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row){

        // YOUR CODE HERE
        //model=new GameModel(int column, int row);
        this.y=column;
        this.x=row;
        // Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
      	// setBorder(emptyBorder);
      	// setBorderPainted(false);
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */
    public void setState(boolean isOn, boolean isClicked) {


        if(isOn == false&& isClicked ==true){
          setIcon(new ImageIcon("Icons/Light-3.png"));
        }
        else if(isOn == true&& isClicked ==true){
          setIcon(new ImageIcon("Icons/Light-2.png"));
        }
        else if(isOn == false&& isClicked ==false){
          setIcon(new ImageIcon("Icons/Light-1.png"));
        }
        else if(isOn == true&& isClicked ==false){
          setIcon(new ImageIcon("Icons/Light-0.png"));
        }

    }
    /**
     * Getter method for the attribute row.
     *
     * @return the value of the attribute row
     */

    public int getRow() {
        // YOUR CODE HERE
        return x;
    }

    /**
     * Getter method for the attribute column.
     *
     * @return the value of the attribute column
     */

    public int getColumn() {
        // YOUR CODE HERE
        return y;
    }
    }
