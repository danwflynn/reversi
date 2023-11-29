package view;

import controller.ReversiController;

/**
 * An interface for a graphical GUI view.
 */
public interface IGraphicalView {

  /**
   * Makes the View visible.
   */
  void makeVisible();

  /**
   * Sets the View as visible or not.
   * @param b Whether to make the view visible.
   */
  void setVisible(boolean b);

  /**
   * Lets a controller observe the view
   * @param controller listener to view
   */
  void addObserver(ReversiController controller);

  /**
   * Tells the human player that it's their turn.
   */
  void addTurnMessage();

  /**
   * Creates a dialogue box informing the user of an illegal move they tried to make.
   * @param s The excpetion message as given by the model.
   */
  void addIllegalMoveMessage(String s);

  /**
   * Adds all buttons to the view, giving the user the option to manipulate them in game.
   */
  void addAllButtons();

  /**
   * Removes all buttons from the view, stopping the user from manipulating them in game.
   */
  void removeAllButtons();

  /**
   * Re-enables buttons in the view, allowing the user to manipulate them in game.
   */
  void enableAllButtons();

  /**
   * Ensures that all buttons in the board are de-selected.
   */
  void unhighlightAllButtons();
}
