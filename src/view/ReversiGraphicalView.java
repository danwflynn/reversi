package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.ReadonlyIReversiModel;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * An implementation of the GUI view for a game of Reversi.
 */
public class ReversiGraphicalView extends JFrame implements IGraphicalView {

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
    boardPanel.setBounds(50, 50, 1000, 800);
    this.add(boardPanel);

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
    this.setVisible(true);
    this.setResizable(false);

  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
