package view;

import model.Position3D;
import model.ReadonlyIReversiModel;
import model.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * A class representing the board panel for a game of Reversi.
 */
public class BoardPanel extends JPanel {
  private final int size; // Side length of the hexagon
  private final int radius;
  private final ReadonlyIReversiModel model;
  private final ArrayList<HexagonTile> hexagonTiles;
  // reversi graphical view is passed down through the constructor to the buttons

  /**
   * Constrcuts a BoardPanel based on the given model.
   * @param model The model of the Reversi game
   */
  public BoardPanel(ReadonlyIReversiModel model) {
    this.radius = model.getRadius();
    this.size = 240 / radius;
    this.model = model;
    this.hexagonTiles = new ArrayList<>();
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    this.setPreferredSize(new Dimension(1000, 800));
    int xBound = 500 - size;
    int yBound = 400 - size;

    for (int ring = 0; ring < radius; ring++) {
      for (int row = 0; row < 2 * ring + 1; row++) {
        if (row == 0) {
          for (int i = 0; i < ring + 1; i++) {
            double x1 = -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + xBound;
            int s = -1 * i;
            int q = -1 * s - ring;
            HexagonTile hex = new HexagonTile(new Position3D(q, ring, s), this.size);
            hex.setBounds((int)x1, (int) Math.round(1.5 * size * ring + yBound), size * 2, size * 2);
            this.hexagonTiles.add(hex);
          }
        }
        if (row != 0 && row != 2 * ring) {
          int i1 = ring - Math.abs(ring - row);
          for (int i = 0; i < i1; i++) {
            double y1 = 1.5 * size * (ring - row) + yBound;
            double x1 = xBound - ring * size * (Math.sqrt(3) / 2) + (i1) * size
                    * (Math.sqrt(3) / 2) - Math.sqrt(3) * size - i * Math.sqrt(3) * size;
            double x2 = xBound + ring * size * (Math.sqrt(3) / 2) - (i1) * size
                    * (Math.sqrt(3) / 2) + Math.sqrt(3) * size + i * Math.sqrt(3) * size;
            int r = -1 * (row - radius + 1) - (radius - ring - 1);
            int q = -1 * i - 1;
            if (row < ring) {
              q *= -1;
            }
            int s = -1 * q - r;
            if (row >= ring) {
              HexagonTile hex = new HexagonTile(new Position3D(q, r, s), this.size);
              hex.setBounds((int)x1, (int)y1, size * 2, size * 2);
              this.hexagonTiles.add(hex);
              HexagonTile hex2 = new HexagonTile(new Position3D(s, r, q), this.size);
              hex2.setBounds((int)x2, (int)y1, size * 2, size * 2);
              this.hexagonTiles.add(hex2);
            } else {
              HexagonTile hex = new HexagonTile(new Position3D(s, r, q), this.size);
              hex.setBounds((int)x1, (int)y1, size * 2, size * 2);
              this.hexagonTiles.add(hex);
              HexagonTile hex2 = new HexagonTile(new Position3D(q, r, s), this.size);
              hex2.setBounds((int)x2, (int)y1, size * 2, size * 2);
              this.hexagonTiles.add(hex2);
            }
          }
        }
        if (row == 2 * ring) {
          for (int i = 0; i < ring + 1; i++) {
            double x1 = -1 * size * (Math.sqrt(3) / 2) * ring + Math.sqrt(3) * size * i + xBound;
            int r = -1 * ring;
            int q = -1 * i - r;
            HexagonTile hex = new HexagonTile(new Position3D(i, r, q), this.size);
            hex.setBounds((int)x1, (int)Math.round(-1.5 * size * ring + yBound), size * 2, size * 2);
            this.hexagonTiles.add(hex);
          }
        }
      }
    }
  }

  /**
   * Draws this board and its hex tiles in its entirety.
   * @param g the <code>Graphics</code> object to protect
   */
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
    double x = (centerX - size);
    double y = (centerY - size) + 220 / radius;

    g2d.setColor(Color.GRAY);
    for (int i = 0; i < radius; i++) {
      drawHexRing(i, g2d, x, y);
    }
  }

  /**
   * Draws a Hexagon tile with the given specifications.
   * @param g2d The Graphics2D used to draw
   * @param x The x-coordinate value of the screen
   * @param y The y-coordinate value of the screen.
   * @param size The size of the hexagon
   */
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
    g2d.setColor(Color.GRAY);
  }

  /**
   * Draws a black piece on a tile with the given specifications.
   * @param g2d The Graphics2D used to draw
   * @param x The x-coordinate value of the screen
   * @param y The y-coordinate value of the screen
   * @param size The size of the piece
   */
  private void drawBlackPiece(Graphics2D g2d, double x, double y, int size) {
    g2d.setColor(Color.BLACK);
    g2d.fillOval((int) x + size / 2, (int) y - size / 2, size, size);
    g2d.setColor(Color.GRAY);
  }

  /**
   * Draws a white piece on a tile with the given specifications.
   * @param g2d The Graphics2D used to draw
   * @param x The x-coordinate value of the screen
   * @param y The y-coordinate value of the screen
   * @param size The size of the piece
   */
  private void drawWhitePiece(Graphics2D g2d, double x, double y, int size) {
    g2d.setColor(Color.WHITE);
    g2d.fillOval((int) x + size / 2, (int) y - size / 2, size, size);
    g2d.setColor(Color.GRAY);
  }

  /**
   * Draws one ring of hexagon tiles around the center with the given specifications.
   * @param ring The ring number (increasingly from the center)
   * @param g2d The Graphics2D used to draw
   * @param x The x-coordinate value of the screen
   * @param y The y-coordinate value of the screen
   */
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

  /**
   * Checks if the Tiles at given locations on the left and right need pieces, and draws them if so.
   * @param g2d The Graphics2D used to draw
   * @param y1 The y-coordinate value of the screen
   * @param x1 The x-coordinate value of the screen on the left
   * @param x2 The x-coordinate value of the screen on the right
   * @param r The r value in hex coordinates on the hex grid
   * @param q The q value in hex coordinates on the hex grid
   * @param s The s value in hex coordinates on the hex grid
   */
  private void drawingPiecesHelper(Graphics2D g2d, double y1, double x1, double x2, int r, int q, int s) {
    checkForBlackAndWhite(g2d, y1, x1, r, s, q);
    checkForBlackAndWhite(g2d, y1, x2, r, q, s);
  }

  /**
   * Checks if the Tile needs a black or white piece, and draws it if so.
   * @param g2d The Graphics2D used to draw.
   * @param y1 The y-coordinate value of the screen
   * @param x2 The x-coordinate value of the screen
   * @param r The r value in hex coordinates on the hex grid
   * @param q The q value in hex coordinates on the hex grid
   * @param s The s value in hex coordinates on the hex grid
   */
  private void checkForBlackAndWhite(Graphics2D g2d, double y1, double x2, int r, int q, int s) {
    if (this.model.getCopyOfTileAt(new Position3D(s, r, q)).getTileType().equals(TileType.BLACK)) {
      drawBlackPiece(g2d, x2, y1, size);
    }
    if (this.model.getCopyOfTileAt(new Position3D(s, r, q)).getTileType().equals(TileType.WHITE)) {
      drawWhitePiece(g2d, x2, y1, size);
    }
  }

  /**
   * Gets all buttons drawn on this board panel.
   * @return a list of all hexagon tiles.
   */
  public ArrayList<HexagonTile> getButtons() {
    return this.hexagonTiles;
  }
}
