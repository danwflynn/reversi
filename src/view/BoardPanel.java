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

  private final ArrayList<HexagonTile> hexagonTiles;
  // reversi graphical view is passed down through the constructor to the buttons

  private final int width;
  private final int height;

  public BoardPanel(ReadonlyIReversiModel model, int width, int height) {
    int size1;
    this.radius = model.getRadius();
    size1 = 240 / radius;
//    if (this.radius > 8) {
//      size1 = 25;
//    }
    this.size = size1;
    this.model = model;
    this.width = width;
    this.height = height;
    this.hexagonTiles = new ArrayList<>();
    this.setLayout(null);
    //this.setPreferredSize(new Dimension(120 * radius, (int)(60 * radius * Math.sqrt(3))));

    int row = 0;
    for (double y = -1.5 * size * this.radius + 1.5 * size; y < 1.5 * size * this.radius; y = y + 1.5 * size) {
      double xStart = (-1 * size * Math.sqrt(3) * this.radius + Math.sqrt(3) * size) + (size / 2 * Math.sqrt(3) * Math.abs(model.getRadius() - 1 - row));
      for (double x = xStart; x < size * Math.sqrt(3) * this.radius; x = x + size * Math.sqrt(3)) {
        ArrayList<Position3D> position3DArrayList = new ArrayList<>();
        for (Tile t : this.model.getCopyOfBoard()) {
          position3DArrayList.add(t.getPos());
        }
        if (position3DArrayList.contains(this.getCubeCoordinates(x, y))) {
          // Tile t = this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y));
          // You can make it so buttons only go on empty cells by adding an if block (t = empty)

          HexagonTile hex = new HexagonTile(this.getCubeCoordinates(x, y), this.size);
          int yOffset = 0;
          if (this.size == 25) {
            yOffset = 2;
          }
          int xOffset = 0;
          if (this.size == 25) {
            xOffset = (int)(-1 * Math.abs(y / 40));
          }
          int xBound = (int) (this.width / 2 - size / 2 + x + 0.5) - xOffset;
          int yBound = (int) (this.height / 2 - 34 * size / 30 + y) + yOffset;

          hex.setBounds(xBound, yBound, size * 2, size * 2);
          this.hexagonTiles.add(hex);
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

//    // Draw a small black circle if needed
//    if (this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y)).getTileType().equals(TileType.BLACK)) {
//      drawBlackPiece(g2d, x, y, size);
//    }
//    // Draw a white circle
//    if (this.model.getCopyOfTileAt(this.getCubeCoordinates(x, y)).getTileType().equals(TileType.WHITE)) {
//      drawWhitePiece(g2d, x, y, size);
//    }
    g2d.setColor(Color.GRAY);
  }

  private void drawBlackPiece(Graphics2D g2d, double x, double y, int size) {
    g2d.setColor(Color.BLACK);
    g2d.fillOval((int) x + size / 2, (int) y - size / 2, size, size);
    g2d.setColor(Color.GRAY);
  }

  private void drawWhitePiece(Graphics2D g2d, double x, double y, int size) {
    g2d.setColor(Color.WHITE);
    g2d.fillOval((int) x + size / 2, (int) y - size / 2, size, size);
    g2d.setColor(Color.GRAY);
  }

  private void drawHexRing(int ring, Graphics2D g2d, double x, double y) {
    for (int row = 0; row < 2 * ring + 1; row++) {
      if (row == 0) {
        for (int i = 0; i < ring + 1; i++) {
          double x1 = -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + x;
          drawHexagon(g2d, x1, 1.5 * size * ring + y, size);
          int s = -1 * i;
          int q = -1 * s - ring;
          if (this.model.getCopyOfTileAt(new Position3D(q, ring, s)).getTileType().equals(TileType.BLACK)) {
            drawBlackPiece(g2d, x1, 1.5 * size * ring + y, size);
          }
          if (this.model.getCopyOfTileAt(new Position3D(q, ring, s)).getTileType().equals(TileType.WHITE)) {
            drawWhitePiece(g2d, x1, 1.5 * size * ring + y, size);
          }
        }
      }
      if (row != 0 && row != 2 * ring) {
        int i1 = ring - Math.abs(ring - row);
        for (int i = 0; i < i1; i++) {
          double y1 = 1.5 * size * (ring - row) + y;
          double x1 = x - ring * size * (Math.sqrt(3) / 2) + (i1) * size
                  * (Math.sqrt(3) / 2) - Math.sqrt(3) * size - i * Math.sqrt(3) * size;
          drawHexagon(g2d, x1, y1, size);
          double x2 = x + ring * size * (Math.sqrt(3) / 2) - (i1) * size
                  * (Math.sqrt(3) / 2) + Math.sqrt(3) * size + i * Math.sqrt(3) * size;
          drawHexagon(g2d, x2, y1, size);
          int r = -1 * (row - radius + 1);
          int q = -1 * i - 1;
          if (row < ring) {
            q *= -1;
          }
          int s = -1 * q - r;
          if (row >= ring) {
            drawingPiecesHelper(g2d, y1, x1, x2, r, q, s);
          } else {
            drawingPiecesHelper(g2d, y1, x1, x2, r, s, q);
          }
        }

      }
      if (row == 2 * ring) {
        for (int i = 0; i < ring + 1; i++) {
          double x1 = -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + x;
          drawHexagon(g2d, x1, -1.5 * size * ring + y, size);
          int r = -1 * ring;
          int q = -1 * i - r;
          if (this.model.getCopyOfTileAt(new Position3D(i, r, q)).getTileType().equals(TileType.BLACK)) {
            drawBlackPiece(g2d, x1, -1.5 * size * ring + y, size);
          }
          if (this.model.getCopyOfTileAt(new Position3D(i, r, q)).getTileType().equals(TileType.WHITE)) {
            drawWhitePiece(g2d, x1, -1.5 * size * ring + y, size);
          }
        }
      }
    }
  }

  private void drawingPiecesHelper(Graphics2D g2d, double y1, double x1, double x2, int r, int q, int s) {
    if (this.model.getCopyOfTileAt(new Position3D(q, r, s)).getTileType().equals(TileType.BLACK)) {
      drawBlackPiece(g2d, x1, y1, size);
    }
    if (this.model.getCopyOfTileAt(new Position3D(q, r, s)).getTileType().equals(TileType.WHITE)) {
      drawWhitePiece(g2d, x1, y1, size);
    }
    if (this.model.getCopyOfTileAt(new Position3D(s, r, q)).getTileType().equals(TileType.BLACK)) {
      drawBlackPiece(g2d, x2, y1, size);
    }
    if (this.model.getCopyOfTileAt(new Position3D(s, r, q)).getTileType().equals(TileType.WHITE)) {
      drawWhitePiece(g2d, x2, y1, size);
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

  public ArrayList<HexagonTile> getButtons() {
    return this.hexagonTiles;
  }
}
