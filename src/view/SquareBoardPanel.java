package view;

import model.ReadonlyIReversiModel;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the board of the square game for the view.
 */
public class SquareBoardPanel extends JPanel {
  private final List<SquareTile> buttons;
  private SquareTile highlightedButton;

  /**
   * Constructor for square board.
   * @param model square model
   */
  public SquareBoardPanel(ReadonlyIReversiModel model) {
    buttons = new ArrayList<>();
    setLayout(new GridLayout(model.getRadius() * 2, model.getRadius() * 2));

    // Create n by n grid of squares
    for (int i = 0; i < model.getRadius() * 2; i++) {
      for (int j = 0; j < model.getRadius() * 2; j++) {
        SquareTile squareTile = new SquareTile(model,this, i, j); // Pass coordinates to each tile
        squareTile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttons.add(squareTile);
        add(squareTile);
      }
    }

    setBackground(Color.LIGHT_GRAY);
    highlightedButton = null;
  }

  public List<SquareTile> getButtons() {
    return buttons;
  }

  /**
   * Sets which button will be highlighted when selecting.
   * @param squareTile tile to select
   */
  public void setHighlightedButton(SquareTile squareTile) {
    if (highlightedButton != null && highlightedButton != squareTile) {
      highlightedButton.unhighlight();
    }
    highlightedButton = squareTile;
  }

  public boolean isHighlightedButton(SquareTile squareTile) {
    return highlightedButton == squareTile;
  }
}
