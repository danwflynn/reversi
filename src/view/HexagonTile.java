package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HexagonTile extends JButton {
  private Polygon hexagon;
  private static HexagonTile highlightedButton;
  private BoardPanel boardPanel;

  public HexagonTile(BoardPanel boardPanel) {
    hexagon = createHexagon();
    this.boardPanel = boardPanel;
    setContentAreaFilled(false);
    setPreferredSize(new Dimension(150, 150));
    setBorderPainted(false);

    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (highlightedButton == HexagonTile.this) {
          unhighlight();
        } else {
          if (highlightedButton != null) {
            highlightedButton.unhighlight();
          }
          highlight();
          printCoordinates();
        }
      }
    });
  }

  private Polygon createHexagon() {
    Polygon polygon = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x = (int) (30 + 30 * Math.sin(i * 2 * Math.PI / 6));
      int y = (int) (30 + 30 * Math.cos(i * 2 * Math.PI / 6));
      polygon.addPoint(x, y);
    }
    return polygon;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this == highlightedButton) {
      g.setColor(Color.CYAN);
      g.fillPolygon(hexagon);
    }
  }

  private void highlight() {
    highlightedButton = this;
    repaint();
  }

  private void unhighlight() {
    highlightedButton = null;
    repaint();
  }

  private void printCoordinates() {
    int xCoord = this.getLocation().x;
    int yCoord = this.getLocation().y;
    System.out.println("Button cube coordinates: " + this.boardPanel.getCubeCoordinates(xCoord, yCoord));
  }
}
