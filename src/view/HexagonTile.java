package view;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class HexagonTile extends JButton {
  private Path2D hexTile = new Path2D.Float();

  public HexagonTile(String label) {
    super(label);
    setPreferredSize(new Dimension(100, 100));
    int w = getWidth();
    int h = getHeight();
    hexTile.reset();
    hexTile.moveTo(w / 2, 0);
    hexTile.lineTo(w, h / 4);
    hexTile.lineTo(w, 3 * h / 4);
    hexTile.lineTo(w / 2, h);
    hexTile.lineTo(0, 3 * h / 4);
    hexTile.lineTo(0, h / 4);
    hexTile.closePath();


  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(getBackground());
    g2.fill(this.hexTile);
    super.paintComponent(g);
  }
}
