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

    //this.add(new BoardPanel(this.model));
    this.hex = new HexagonTile();
    this.add(hex, BorderLayout.CENTER);

    this.setVisible(true);
  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
