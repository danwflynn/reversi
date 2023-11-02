package view;

import javax.swing.*;

import model.ReadonlyIReversiModel;
import model.ReadonlyReversiModelImpl;

public class ReversiGraphicalView extends JFrame implements IGraphicalView {
  private final ReadonlyIReversiModel model;
  public ReversiGraphicalView(ReadonlyIReversiModel model) {
    this.model = model;
  }

  /**
   * Makes the View visible.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
