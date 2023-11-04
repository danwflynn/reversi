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
    setLayout(null);

    this.boardPanel = new BoardPanel(this.model);
    Dimension boardPrefSize = this.boardPanel.getPreferredSize();
    this.boardPanel.setBounds(50, 50, boardPrefSize.width, boardPrefSize.height);
    this.add(boardPanel);

    this.hex = new HexagonTile();
    Dimension hexPrefSize = this.hex.getPreferredSize();
    this.hex.setBounds(400, 400, hexPrefSize.width, hexPrefSize.height);
    this.add(hex);

    this.setComponentZOrder(this.hex, 1);
    this.setComponentZOrder(this.boardPanel, 2);

    this.setVisible(true);

    System.out.println(boardPrefSize);
    System.out.println(hexPrefSize);

  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
