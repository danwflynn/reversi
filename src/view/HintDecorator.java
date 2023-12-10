package view;

import controller.ReversiController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Using the decorator pattern on the hex tile to add hints.
 */
public class HintDecorator extends HexagonTile {
  private final HexagonTile decoratedTile;

  /**
   * Wrapper constructor for the hex tile.
   * @param decoratedTile delegate
   */
  public HintDecorator(HexagonTile decoratedTile) {
    super(decoratedTile.cubeCoords, decoratedTile.size);
    this.decoratedTile = decoratedTile;
  }

  @Override
  protected void paintComponent(Graphics g) {
    decoratedTile.paintComponent(g);

    if (this == highlightedButton) {
      g.setColor(Color.CYAN);
      g.fillPolygon(hexagon);

      if (hintsEnabled) {
        g.setColor(Color.BLACK);
        int fontSize = size / 2;
        Font font = new Font("Arial", Font.PLAIN, fontSize);
        g.setFont(font);
        g.drawString(String.valueOf(calculateScoreIncrease()), size, size);
      }
    }
  }

  @Override
  HexagonTile getHighlightedButton() {
    return decoratedTile.getHighlightedButton();
  }

  @Override
  void highlight() {
    decoratedTile.highlight();
    repaint();
  }

  @Override
  void unhighlight() {
    decoratedTile.unhighlight();
    repaint();
  }

  @Override
  void changeSelection() {
    decoratedTile.changeSelection();
  }

  @Override
  void hexEnable() {
    decoratedTile.hexEnable();
  }

  @Override
  void hexDisable() {
    decoratedTile.hexDisable();
  }

  @Override
  void addObserver(ReversiController controller) {
    decoratedTile.addObserver(controller);
  }
}
