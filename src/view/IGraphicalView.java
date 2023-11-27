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

  void addAllButtons();

  void removeAllButtons();

  void enableAllButtons();

  void unhighlightAllButtons();
}
