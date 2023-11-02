package view;

import java.awt.*;

import javax.swing.*;

import model.ReadonlyIReversiModel;
import model.ReadonlyReversiModelImpl;

public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final ReadonlyIReversiModel model;
  private final JPanel canvas;
  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    this.model = model;
    this.canvas = new JPanel();
    this.pack();
    this.setSize(1000, 1000);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    canvas.setBackground(Color.BLUE);
    this.add(canvas, BorderLayout.EAST);
  }

  /**
   * Makes the View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }
}
