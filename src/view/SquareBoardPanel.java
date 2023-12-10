package view;

import model.ReadonlyIReversiModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SquareBoardPanel extends JPanel {
  private final List<SquareTile> buttons;
  private SquareTile highlightedButton;

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
