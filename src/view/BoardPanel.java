package view;

import model.Position3D;
import model.ReadonlyIReversiModel;
import model.Tile;
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
  private ReversiGraphicalView reversiGraphicalView;

  public BoardPanel(ReadonlyIReversiModel model, ReversiGraphicalView reversiGraphicalView) {
    this.radius = model.getRadius();
    this.size = 30;
    this.model = model;
    this.reversiGraphicalView = reversiGraphicalView;
    this.hexagonTiles = new ArrayList<>();
    this.setLayout(null);
    this.setPreferredSize(new Dimension(120 * radius, (int)(60 * radius * Math.sqrt(3))));

    int row = 0;
    for (double y = -45 * this.radius + 45; y < 45 * this.radius; y = y + 45) {
      double xStart = (-30 * Math.sqrt(3) * this.radius + Math.sqrt(3) * 30) + (15 * Math.sqrt(3) * Math.abs(model.getRadius() - 1 - row));
      for (double x = xStart; x < 30 * Math.sqrt(3) * this.radius; x = x + 30 * Math.sqrt(3)) {
        ArrayList<Position3D> position3DArrayList = new ArrayList<>();
        for (Tile t : this.model.getCopyOfBoard()) {
          position3DArrayList.add(t.getPos());
        }
        if (position3DArrayList.contains(this.getCubeCoordinates(x, y))) {
          Tile t = this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y));
          if (t.getTileType().equals(TileType.EMPTY)) {
            HexagonTile hex = new HexagonTile(this, this.reversiGraphicalView);
            hex.setBounds((int) (385.5 + x), (int) (366 + y), 60, 60);
            this.hexagonTiles.add(hex);
          }
        }
      }
      row += 1;
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

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
    double angle = Math.toRadians(60); // 60 degrees in radians

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
      g2d.fillOval((int) x + size / 2, (int) y - size / 2, size, size);
    }
    // Draw a white circle
    if (this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y)).getTileType().equals(TileType.WHITE)) {
      g2d.setColor(Color.WHITE);
      g2d.fillOval((int) x + size / 2, (int) y - size / 2, size, size);
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
        int i1 = ring - Math.abs(ring - row);
        for (int i = 0; i < i1; i ++) {
          double y1 = 1.5 * size * (ring - row) + y;
          drawHexagon(g2d, x - ring * size * (Math.sqrt(3) / 2) + (i1) * size * (Math.sqrt(3) / 2) - Math.sqrt(3) * size - i * Math.sqrt(3) * size, y1, size);
          drawHexagon(g2d, x + ring * size * (Math.sqrt(3) / 2) - (i1) * size * (Math.sqrt(3) / 2) + Math.sqrt(3) * size + i * Math.sqrt(3) * size, y1, size);
        }

      }
      if (row == 2 * ring) {
        for (int i = 0; i < ring + 1; i++) {
          drawHexagon(g2d, -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + x, -1.5 * size * ring + y, size);
        }
      }
    }
  }

  public Position3D getCubeCoordinates(double x, double y) {
    int r = (int)Math.ceil((y - getHeight() / 2 - size / 2) / (1.5 * size));
    int row = r + this.model.getRadius() - 1;
    int col = ((int)((x - getWidth() / 2 - size / 2 + size) / (Math.sqrt(3) / 2 * size)) + 2 * (this.model.getRadius() - 1) - Math.abs(r)) / 2;
    int q = Math.max(col - row, -1 * this.model.getRadius() + 1 + col);
    int s = -1 * q - r;
    return new Position3D(q, r, s);
  }

  public ArrayList<HexagonTile> getButtons() {
    return this.hexagonTiles;
  }
}
