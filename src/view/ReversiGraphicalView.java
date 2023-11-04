package view;

import java.awt.*;

import javax.swing.*;

import model.ReadonlyIReversiModel;

public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final ReadonlyIReversiModel model;

  private BoardPanel boardPanel;


  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    super();
    this.model = model;
    this.setSize(800, 800);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);

    this.boardPanel = new BoardPanel(this.model);
    Dimension boardPrefSize = this.boardPanel.getPreferredSize();
    this.boardPanel.setBounds(50, 50, boardPrefSize.width, boardPrefSize.height);
    this.add(boardPanel);

    System.out.println(this.boardPanel.getButtons().size() + " is the size");
    int ind = 1;
    for (HexagonTile h : this.boardPanel.getButtons()) {
      this.add(h);
      this.setComponentZOrder(h, ind);
      ind++;
    }
    this.setComponentZOrder(this.boardPanel, ind);

    this.setVisible(true);
  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
