package view;

import java.awt.*;

import javax.swing.*;

import model.ReadonlyIReversiModel;

public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final ReadonlyIReversiModel model;
  private final HexagonTile hex;

  private BoardPanel boardPanel;


  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    super();
    this.model = model;
    this.setSize(800, 800);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    this.boardPanel = new BoardPanel(this.model);
    this.add(boardPanel, BorderLayout.CENTER);

    this.hex = new HexagonTile();
    this.hex.setPreferredSize(new Dimension(100, 100));
    this.add(hex, BorderLayout.EAST);
    this.setVisible(true);
  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
