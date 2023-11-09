package view;
import model.Position3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HexagonTile extends JButton {
  private final Polygon hexagon;
  private static HexagonTile highlightedButton;
  private final Position3D cubeCoords;
  private final int size;

  public HexagonTile(Position3D cubeCoords, int size) {
    this.cubeCoords = cubeCoords;
    this.size = size;
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
          highlight();
          printCoordinates();
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
        } else if (keyChar == 'm') {
          if (highlightedButton == HexagonTile.this) {
            System.out.println("Declare move to " + cubeCoords);
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

  private Polygon createHexagon() {
    Polygon polygon = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x = (int) (size + size * Math.sin(i * 2 * Math.PI / 6));
      int y = (int) (size + size * Math.cos(i * 2 * Math.PI / 6));
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
//    } else {
//      g.setColor(Color.GREEN);
//      g.fillPolygon(hexagon);
//    }
  }

  HexagonTile getHighlightedButton() {
    return highlightedButton;
  }

  private void highlight() {
    highlightedButton = this;
    repaint();
  }

  private void unhighlight() {
    highlightedButton = null;
    repaint();
  }

  void changeSelection() {
    highlightedButton.unhighlight();
  }

  private void printCoordinates() {
    System.out.println("Button cube coordinates: " + this.cubeCoords);
  }
}
