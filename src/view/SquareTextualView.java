package view;

import model.IReversiModel;
import model.position.Position3D;

/**
 * Textual view for the square game.
 */
public class SquareTextualView implements TextualView {
  IReversiModel model;

  /**
   * SQT constructor.
   * @param model square model
   */
  public SquareTextualView(IReversiModel model) {
    this.model = model;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int y = 0; y < model.getRadius() * 2; y++) {
      for (int x = 0; x < model.getRadius() * 2; x++) {
        result.append(model.getCopyOfTileAt(new Position3D(x, y, -x - y)).toString()).append(" ");
      }
      result.append("\n");
    }
    return result.toString();
  }
}
