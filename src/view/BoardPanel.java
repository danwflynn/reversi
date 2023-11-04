package view;

import model.Position3D;
import model.ReadonlyIReversiModel;
import model.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class BoardPanel extends JPanel {
  private final int size; // Side length of the hexagon
  private final int radius;
  private final ReadonlyIReversiModel model;

  private ArrayList<HexagonTile> hexagonTiles;

  public BoardPanel(ReadonlyIReversiModel model) {
    this.size = 30;
    this.radius = model.getRadius();
    this.model = model;
    this.setLayout(null);
    this.setPreferredSize(new Dimension(120 * radius, (int)(60 * radius * Math.sqrt(3))));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    hexagonTiles = new ArrayList<HexagonTile>();

    // Get the size of the panel
    int width = getWidth();
    int height = getHeight();

    // Calculate the center of the panel
    int centerX = width / 2;
    int centerY = height / 2;

    // Calculate the coordinates for the hexagon based on the center
    double x = centerX - size / 2;
    double y = centerY - size / 2;

    g2d.setColor(Color.GRAY);
    for (int i = 0; i < radius; i++) {
      drawHexRing(i, g2d, x, y);
    }
  }

  private void drawHexagon(Graphics2D g2d, double x, double y, int size) {
    Path2D path = new Path2D.Double();
    double angle = Math.toRadians(60); // 30 degrees in radians

    for (int i = 0; i < 6; i++) {
      double xPoint = x + size * Math.cos(i * angle);
      double yPoint = y + size * Math.sin(i * angle);

      if (i == 0) {
        path.moveTo(xPoint, yPoint);
      } else {
        path.lineTo(xPoint, yPoint);
      }
    }

    path.closePath();

    // Rotate the hexagon by 90 degrees
    AffineTransform rotateTransform = AffineTransform.getRotateInstance(Math.toRadians(90), x + size / 2, y + size / 2);
    path.transform(rotateTransform);

    g2d.fill(path);

    // Draw a black outline around the hexagon
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2.0f));
    g2d.draw(path);

    // Draw a small black circle if needed
    if (this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y)).getTileType().equals(TileType.BLACK)) {
      g2d.fillOval((int) x + 15, (int) y - 15, 30, 30);
    }
    // Draw a white circle
    if (this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y)).getTileType().equals(TileType.WHITE)) {
      g2d.setColor(Color.WHITE);
      g2d.fillOval((int) x + 15, (int) y - 15, 30, 30);
    }
    g2d.setColor(Color.GRAY);
  }

  private void drawHexRing(int ring, Graphics2D g2d, double x, double y) {
    for (int row = 0; row < 2 * ring + 1; row++) {
      if (row == 0) {
        for (int i = 0; i < ring + 1; i++) {
          drawHexagon(g2d, -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + x, 1.5 * size * ring + y, size);
        }
      }
      if (row != 0 && row != 2 * ring) {
        for (int i = 0; i < ring - Math.abs(ring - row); i ++) {
          drawHexagon(g2d, x - ring * size * (Math.sqrt(3) / 2) + (ring - Math.abs(ring - row)) * size * (Math.sqrt(3) / 2) - Math.sqrt(3) * size - i * Math.sqrt(3) * size, 1.5 * size * (ring - row) + y, size);
          drawHexagon(g2d, x + ring * size * (Math.sqrt(3) / 2) - (ring - Math.abs(ring - row)) * size * (Math.sqrt(3) / 2) + Math.sqrt(3) * size + i * Math.sqrt(3) * size, 1.5 * size * (ring - row) + y, size);
        }
      }
      if (row == 2 * ring) {
        for (int i = 0; i < ring + 1; i++) {
          drawHexagon(g2d, -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + x, -1.5 * size * ring + y, size);
        }
      }
    }
  }

  private Position3D getCubeCoordinates(double x, double y) {
    int r = (int)Math.ceil((y - getHeight() / 2 - size / 2) / (1.5 * size));
    int row = r + this.model.getRadius() - 1;
    int col = ((int)((x - getWidth() / 2 - size / 2 + size) / (Math.sqrt(3) / 2 * size)) + 2 * (this.model.getRadius() - 1) - Math.abs(r)) / 2;
    int q = Math.max(col - row, -1 * this.model.getRadius() + 1 + col);
    int s = -1 * q - r;
    return new Position3D(q, r, s);
  }
}
