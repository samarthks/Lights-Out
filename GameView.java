import javax.swing.*;
import java.awt.*;



public class GameView extends JFrame {

  // your variables here
  private GridButton[][] gameButtons;					//this two dimensional array will hold a reference the all of the buttons on the board.
  private JButton manual;								//a button for the "Enter Manual Setup".
  private JLabel step;								//a label used to tell the user how many wins they currently have.
  private int winCount;
  private GameModel gameModel;
  private JLabel lblNewLabel;
  private JCheckBox chckbxNewCheckBox;
  private JLabel numOfSteps;
  private JPanel p;

  /**
  * Constructor used for initializing the Frame
  *
  * @param gameModel
  *            the model of the game (already initialized)
  * @param gameController
  *            the controller
  */

  public GameView(GameModel gameModel, GameController gameController) {
    this.gameModel = gameModel;

    gameButtons = new GridButton [gameModel.getHeight()][gameModel.getWidth()];



    setTitle("Lights Out 1121 Version");
    setVisible(true);
    setBounds(1000, 500, 700, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setLayout(null);
    //frame.setResizable(true);

    JButton btnNewButton = new JButton("Reset");
    btnNewButton.setBounds(577, 123, 117, 29);
    btnNewButton.addActionListener(gameController);
    //btnNewButton.addActionListener(new ActionListener() {
    //  public void actionPerformed(ActionEvent e) {
    //  }
    //});
    getContentPane().setLayout(null);
    getContentPane().add(btnNewButton);

    JButton btnNewButton_1 = new JButton("Random");
    btnNewButton_1.setBounds(577, 164, 117, 29);
    getContentPane().add(btnNewButton_1);
    btnNewButton_1.addActionListener(gameController);

    JCheckBox chckbxNewCheckBox = new JCheckBox("Solution");
    chckbxNewCheckBox.setBounds(566, 205, 128, 23);
    getContentPane().add(chckbxNewCheckBox);
    //chckbxNewCheckBox.addActionListener(gameController);
    chckbxNewCheckBox.addItemListener(gameController);


    JButton btnNewButton_2 = new JButton("Quit");
    btnNewButton_2.setBounds(577, 228, 117, 29);
    getContentPane().add(btnNewButton_2);
    btnNewButton_2.addActionListener(gameController);

    JLabel lblNewLabel = new JLabel(("Number of Steps: "));
    lblNewLabel.setBounds(265, 449, 168, 23);
    getContentPane().add(lblNewLabel);

    numOfSteps = new JLabel(Integer.toString(gameModel.getNumberOfSteps()));
    numOfSteps.setBounds(447, 452, 30, 16);
    getContentPane().add(numOfSteps);

    JPanel panel = new JPanel(new GridLayout(gameModel.getHeight(), gameModel.getWidth()));
    panel.setBounds(10, 10, 561, 418);
    getContentPane().add(panel);

    JPanel p = new JPanel();


    for(int i=0; i<gameModel.getHeight(); i++){
      for(int j=0; j<gameModel.getWidth(); j++){
        gameButtons[i][j] = new GridButton(j, i);
        gameButtons[i][j].addActionListener(gameController);
        panel.add(gameButtons[i][j]);
      }
    }
    getContentPane().add(panel);

    for(int a=0; a<gameModel.getHeight(); a++){
      for(int b=0; b<gameModel.getWidth(); b++){
        gameButtons[a][b].setState(gameModel.isON(a,b), gameModel.solutionSelects(a,b));
      }
    }}

    /**
    * updates the status of the board's GridButton instances based
    * on the current game model, then redraws the view
    */

    public void update(){
      for(int i=0; i<gameModel.getHeight(); i++){
        for(int j=0; j<gameModel.getWidth(); j++){
          gameButtons[i][j].setState(gameModel.isON(i,j), gameModel.solutionSelects(i,j));
        }
      }


      numOfSteps.setText(Integer.toString(gameModel.getNumberOfSteps()));



      if(finish()){
        Object [] options = {"Play Again", "Quit"};
				int n = JOptionPane.showOptionDialog(p, "Congratulations, you won in "+gameModel.getNumberOfSteps()+" steps! \n Would you like to play again?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
        if(n==1){
          System.exit(0);
        }
        else if(n==0){
          gameModel.reset();
          this.update();
        }
      }
      repaint();
      //pack();

    }


    private boolean finish(){
      for(int i=0; i<gameModel.getHeight(); i++){
        for(int j=0; j<gameModel.getWidth(); j++){
          if(gameModel.isON(i,j) == false)
          {
            return false;
          }

        }}
        return true;
    }

    /**
    * returns true if the ``solution'' checkbox
    * is checked
    *
    * @return the status of the ``solution'' checkbox
    */

    public boolean solutionShown(){
      //WIP
      if(chckbxNewCheckBox.isSelected()){
        return true;
      }
      return false;
    }
}
