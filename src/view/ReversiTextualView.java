package view;

import model.IReversiModel;
import model.Position3D;

/**
 * Reversi textual view implementation.
 */
public class ReversiTextualView implements TextualView {
  private final IReversiModel model;

  /**
   * Constructs from model.
   * @param model reversi game
   */
  public ReversiTextualView(IReversiModel model) {
    this.model = model;
  }

  @Override
  public String toString() {
    StringBuilder drawString = new StringBuilder();
    for (int row = 0; row < 2 * this.model.getRadius() - 1; row++) {
      int r = -1 * this.model.getRadius() + 1 + row;
      drawString.append(" ".repeat(Math.max(0, Math.abs(r))));
      for (int col = 0; col < 2 * this.model.getRadius() - 1 - Math.abs(r); col++) {
        int q = Math.max(col - row, -1 * this.model.getRadius() + 1 + col);
        int s = Math.min(this.model.getRadius() - 1 - col,
                2 * this.model.getRadius() - 2 - row - col);
        drawString.append(this.model.getTileAt(new Position3D(q, r, s)).toString());
        if (col < 2 * this.model.getRadius() - 2 - Math.abs(r)) {
          drawString.append(" ");
        }
      }
      if (row < 2 * this.model.getRadius() - 2) {
        drawString.append("\n");
      }
    }
    return drawString.toString();
  }
}
