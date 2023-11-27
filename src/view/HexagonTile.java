package view;

import controller.ReversiController;
import model.Position3D;

import javax.swing.JButton;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class representing a HexagonTile in Reversi.
 * These are the clickable buttons that can be selected.
 * They are placed over the board and are invisible when not selected.
 */
public class HexagonTile extends JButton {
  private final Polygon hexagon;
  private static HexagonTile highlightedButton;
  private final Position3D cubeCoords;
  private final int size;
  private ReversiController observer;
  private boolean enabled;

  /**
   * Constructs a Hexagon Tile with the given coordinates and size.
   * @param cubeCoords The cube coordinates of the tile
   * @param size The size of the tile
   */
  public HexagonTile(Position3D cubeCoords, int size) {
    this.cubeCoords = cubeCoords;
    this.size = size;
    this.enabled = true;
    hexagon = createHexagon();
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
          if (enabled) {
            highlight();
            printCoordinates();
          }
        }
      }
    });

    // Add a KeyListener to listen for key events
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Handle keyTyped events (e.g., when 'p' or 'm' is pressed)
        char keyChar = e.getKeyChar();
        if (keyChar == 'p') {
          System.out.println("Pass");
          observer.pass();
          if (highlightedButton == HexagonTile.this) {
            unhighlight();
          }
        } else if (keyChar == 'm') {
          if (highlightedButton == HexagonTile.this) {
            System.out.println("Declare move to " + cubeCoords);
            observer.placeTile(cubeCoords);
            unhighlight();
          }
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Handle keyPressed events (if needed)
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Handle keyReleased events (if needed)
      }
    });

    setFocusable(true); // Allow the button to receive keyboard focus
  }

  /**
   * Returns a Polygon representing the Hexagon Tile.
   * @return A Polygon of the tile
   */
  private Polygon createHexagon() {
    Polygon polygon = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x = (int) (size + size * Math.sin(i * 2 * Math.PI / 6));
      int y = (int) (size + size * Math.cos(i * 2 * Math.PI / 6));
      polygon.addPoint(x, y);
    }
    return polygon;
  }

  /**
   * Draws the tile, lighting it up if needed.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this == highlightedButton) {
      g.setColor(Color.CYAN);
      g.fillPolygon(hexagon);
    }
  }

  /**
   * Gets the button on the board that is highlighted.
   * @return The HexagonTile highlightedButton
   */
  HexagonTile getHighlightedButton() {
    return highlightedButton;
  }

  /**
   * Makes this HexagonTile light up.
   */
  void highlight() {
    highlightedButton = this;
    repaint();
  }

  /**
   * Removes the light on this Hexagon tile.
   */
  private void unhighlight() {
    highlightedButton = null;
    repaint();
  }

  /**
   * Removes the highlight of the previously lit-up tile.
   */
  void changeSelection() {
    highlightedButton.unhighlight();
  }

  /**
   * Prints the coordinates of this tile to the System console.
   */
  private void printCoordinates() {
    System.out.println("Button cube coordinates: " + this.cubeCoords);
  }

  void hexEnable() {
    this.enabled = true;
  }

  void hexDisable() {
    this.enabled = false;
  }

  /**
   * Adds the observer to this Hex Tile.
   * @param controller The observer
   */
  void addObserver(ReversiController controller) {
    this.observer = controller;
    if (!controller.getPlayerColor().equals(controller.getModelTurn())) {
      this.hexDisable();
    }
  }
}
