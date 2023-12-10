package view;

import controller.ReversiController;
import model.ReadonlyIReversiModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SquareReversiView extends JFrame implements IGraphicalView {
  private final SquareBoardPanel boardPanel;

  public SquareReversiView(ReadonlyIReversiModel model) {
    super();
    int windowWidth = 1000;
    int windowHeight = 800;
    this.setSize(windowWidth, windowHeight);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //setLayout(null);

    SquareBoardPanel boardPanel = new SquareBoardPanel(model);
    this.boardPanel = boardPanel;
    //boardPanel.setBounds(50, 50, 1000, 800);
    this.add(boardPanel);

    this.addAllButtons();

    this.setVisible(true);
    //this.setResizable(false);
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
//    for (SquareTile squareTile : this.boardPanel.getButtons()) {
//      squareTile.addObserver(controller);
//    }
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
//    int ind = 1;
//    for (SquareTile squareTile : boardPanel.getButtons()) {
//      this.addMouseListener(new MouseAdapter() {
//        @Override
//        public void mouseClicked(MouseEvent e) {
//          if (squareTile.getHighlightedButton() != null) {
//            squareTile.changeSelection();
//          }
//        }
//      });
//      this.add(squareTile);
//      this.setComponentZOrder(squareTile, ind);
//      ind++;
//    }
//    this.setComponentZOrder(boardPanel, ind);
  }

  /**
   * Removes all buttons from the view, stopping the user from manipulating them in game.
   */
  @Override
  public void removeAllButtons() {
//    for (SquareTile squareTile : boardPanel.getButtons()) {
//      squareTile.highlight();
//      squareTile.squareDisable();
//    }
  }

  /**
   * Re-enables buttons in the view, allowing the user to manipulate them in game.
   */
  @Override
  public void enableAllButtons() {
//    for (SquareTile squareTile : boardPanel.getButtons()) {
//      squareTile.squareEnable();
//      squareTile.highlight();
//    }
  }

  /**
   * Ensures that all buttons in the board are de-selected.
   */
  @Override
  public void unhighlightAllButtons() {
//    for (SquareTile squareTile : boardPanel.getButtons()) {
//      squareTile.unhighlight();
//    }
  }
}
