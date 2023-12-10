package view;

import controller.ReversiController;
import model.ReadonlyIReversiModel;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

/**
 * Square version of the view that uses a grid for the square game.
 */
public class SquareReversiView extends JFrame implements IGraphicalView {
  private final SquareBoardPanel boardPanel;

  /**
   * Constructor for square view.
   * @param model square model
   */
  public SquareReversiView(ReadonlyIReversiModel model) {
    super();
    int windowWidth = 1000;
    int windowHeight = 800;
    this.setSize(windowWidth, windowHeight);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    SquareBoardPanel boardPanel = new SquareBoardPanel(model);
    this.boardPanel = boardPanel;
    this.add(boardPanel);

    this.addAllButtons();

    this.setVisible(true);
  }

  /**
   * Makes the View visible.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Lets a controller observe the view.
   *
   * @param controller listener to view
   */
  @Override
  public void addObserver(ReversiController controller) {
    for (SquareTile squareTile : this.boardPanel.getButtons()) {
      squareTile.addObserver(controller);
    }
  }

  /**
   * Tells the human player that it's their turn.
   */
  @Override
  public void addTurnMessage() {
    JOptionPane.showMessageDialog(this, "It's your turn!");
  }

  /**
   * Creates a dialogue box informing the user of an illegal move they tried to make.
   *
   * @param s The exception message as given by the model.
   */
  @Override
  public void addIllegalMoveMessage(String s) {
    JOptionPane.showMessageDialog(this, "Illegal move " + s);
  }

  /**
   * Adds all buttons to the view, giving the user the option to manipulate them in game.
   */
  @Override
  public void addAllButtons() {
    // Does nothing in this case
  }

  /**
   * Removes all buttons from the view, stopping the user from manipulating them in game.
   */
  @Override
  public void removeAllButtons() {
    for (SquareTile squareTile : boardPanel.getButtons()) {
      squareTile.toggleHighlight();
      squareTile.unhighlight();
      squareTile.squareDisable();
    }
  }

  /**
   * Re-enables buttons in the view, allowing the user to manipulate them in game.
   */
  @Override
  public void enableAllButtons() {
    for (SquareTile squareTile : boardPanel.getButtons()) {
      squareTile.squareEnable();
      squareTile.toggleHighlight();
      squareTile.unhighlight();
    }
  }

  /**
   * Ensures that all buttons in the board are de-selected.
   */
  @Override
  public void unhighlightAllButtons() {
    for (SquareTile squareTile : boardPanel.getButtons()) {
      squareTile.unhighlight();
    }
  }
}
