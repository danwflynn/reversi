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

  void addObserver(ReversiController controller);
}
