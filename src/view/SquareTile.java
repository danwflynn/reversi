package view;

import model.ReadonlyIReversiModel;
import model.position.Position3D;
import model.tile.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareTile extends JButton {
  private final ReadonlyIReversiModel model;
  private final SquareBoardPanel boardPanel;
  private final int row;
  private final int col;

  public SquareTile(ReadonlyIReversiModel model, SquareBoardPanel squareBoardPanel, int row, int col) {
    this.model = model;
    this.boardPanel = squareBoardPanel;
    this.row = row;
    this.col = col;
    setBackground(Color.GRAY);
    setPreferredSize(new Dimension(50, 50)); // Set an appropriate size
    setOpaque(true);

    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        toggleHighlight();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int diameter = Math.min(getWidth(), getHeight()) - (400 / (model.getRadius() * 2));
    int x = (getWidth() - diameter) / 2;
    int y = (getHeight() - diameter) / 2;

    Position3D pos = new Position3D(col, row, -col - row);
    if (this.model.getCopyOfTileAt(pos).getTileType().equals(TileType.BLACK)) {
      g.setColor(Color.BLACK);
      g.fillOval(x, y, diameter, diameter);
    } else if (this.model.getCopyOfTileAt(pos).getTileType().equals(TileType.WHITE)) {
      g.setColor(Color.WHITE);
      g.fillOval(x, y, diameter, diameter);
    }
  }

  private void toggleHighlight() {
    if (boardPanel.isHighlightedButton(this)) {
      boardPanel.setHighlightedButton(null);
    } else {
      boardPanel.setHighlightedButton(this);
    }
    setBackground(boardPanel.isHighlightedButton(this) ? Color.CYAN : Color.GRAY);
  }


  public void unhighlight() {
    setBackground(Color.GRAY);
  }
}

