package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

public class HexagonTile extends JButton {
  private Polygon hexagon;
  private boolean isHighlighted;

  public HexagonTile() {
    hexagon = createHexagon();
    setContentAreaFilled(false);
    setPreferredSize(new Dimension(150, 150));
    setBorderPainted(false);
    isHighlighted = false;
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Toggle the state when the button is clicked
        isHighlighted = !isHighlighted;
        // Repaint the button to reflect the change
        repaint();
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
    if (isHighlighted) {
      g.setColor(Color.CYAN);
      g.fillPolygon(hexagon);
    }
    g.setColor(Color.BLACK);
    g.drawPolygon(hexagon);
  }

  @Override
  public boolean contains(int x, int y) {
    return hexagon.contains(x, y);
  }
}
