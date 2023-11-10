package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.ReadonlyIReversiModel;

public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final ReadonlyIReversiModel model;

  private final BoardPanel boardPanel;


  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    super();
    this.model = model;
    int windowWidth = 1000;
    int windowHeight = 800;
    this.setSize(windowWidth, windowHeight);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);

    this.boardPanel = new BoardPanel(this.model);
    Dimension boardPrefSize = this.boardPanel.getPreferredSize();
    this.boardPanel.setBounds(50, 50, 1000, 800);
    this.add(boardPanel);

    int ind = 1;
    for (HexagonTile h : this.boardPanel.getButtons()) {
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

    this.setComponentZOrder(this.boardPanel, ind);
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
