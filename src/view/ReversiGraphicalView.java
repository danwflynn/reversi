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

  @Override
  public void addObserver(ReversiController controller) {
    for (HexagonTile h : this.boardPanel.getButtons()) {
      h.addObserver(controller);
    }
  }

  /**
   * Tells the human player that it's their turn.
   */
  @Override
  public void addTurnMessage() {
    System.out.println("It's your turn!");
  }

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

  @Override
  public void removeAllButtons() {
    for (HexagonTile h : boardPanel.getButtons()) {
      h.highlight();
      h.hexDisable();
    }
  }

  @Override
  public void enableAllButtons() {
    for (HexagonTile h : boardPanel.getButtons()) {
      h.hexEnable();
      h.highlight();
    }
  }

  @Override
  public void unhighlightAllButtons() {
    for (HexagonTile h : boardPanel.getButtons()) {
      h.unhighlight();
    }
  }
}
