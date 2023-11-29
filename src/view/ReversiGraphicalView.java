package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.ReversiController;
import model.ReadonlyIReversiModel;

import javax.swing.*;

/**
 * An implementation of the GUI view for a game of Reversi.
 */
public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final BoardPanel boardPanel;

  /**
   * Constructs a graphical view with the given model.
   * @param model The model to represent in the GUI.
   */
  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    super();
    int windowWidth = 1000;
    int windowHeight = 800;
    this.setSize(windowWidth, windowHeight);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);

    BoardPanel boardPanel = new BoardPanel(model);
    this.boardPanel = boardPanel;
    boardPanel.setBounds(50, 50, 1000, 800);
    this.add(boardPanel);

    this.addAllButtons();

    this.setVisible(true);
    this.setResizable(false);

  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Add an observer to this graphical view.
   * @param controller The listener to view
   */
  @Override
  public void addObserver(ReversiController controller) {
    for (HexagonTile h : this.boardPanel.getButtons()) {
      h.addObserver(controller);
    }
  }

  /**
   * Tells the human player that it's their turn with a dialog box.
   */
  @Override
  public void addTurnMessage() {
    //Create a one-time dialog box, where argument #1 is the frame to put it over,
    //and argument #2 is the String to display.
    //Note that while the dialog box is showing, all code is frozen and inputs cannot be taken in.
    JOptionPane.showMessageDialog(this, "It's your turn!");
  }

  /**
   * Adds a dialogue message, telling the user about an illegal move they made.
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
    int ind = 1;
    for (HexagonTile h : boardPanel.getButtons()) {
      this.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (h.getHighlightedButton() != null) {
            h.changeSelection();
          }
        }
      });
      this.add(h);
      this.setComponentZOrder(h, ind);
      ind++;
    }
    this.setComponentZOrder(boardPanel, ind);
  }

  /**
   * Removes all buttons from the view, stopping the user from manipulating them in game.
   */
  @Override
  public void removeAllButtons() {
    for (HexagonTile h : boardPanel.getButtons()) {
      h.highlight();
      h.hexDisable();
    }
  }

  /**
   * Re-enables buttons in the view, allowing the user to manipulate them in game.
   */
  @Override
  public void enableAllButtons() {
    for (HexagonTile h : boardPanel.getButtons()) {
      h.hexEnable();
      h.highlight();
    }
  }

  /**
   * Ensures that all buttons in the board are de-selected.
   */
  @Override
  public void unhighlightAllButtons() {
    for (HexagonTile h : boardPanel.getButtons()) {
      h.unhighlight();
    }
  }
}
