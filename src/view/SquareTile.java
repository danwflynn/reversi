package view;

import controller.ReversiController;
import model.ReadonlyIReversiModel;
import model.position.Position3D;
import model.tile.TileType;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquareTile extends JButton {
  private final ReadonlyIReversiModel model;
  private final SquareBoardPanel boardPanel;
  private final int row;
  private final int col;
  private ReversiController observer;
  private boolean enabled;

  public SquareTile(ReadonlyIReversiModel model, SquareBoardPanel squareBoardPanel, int row, int col) {
    this.model = model;
    this.boardPanel = squareBoardPanel;
    this.row = row;
    this.col = col;
    this.enabled = false;
    setBackground(Color.GRAY);
    setPreferredSize(new Dimension(50, 50)); // Set an appropriate size
    setOpaque(true);

    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (enabled) {
          toggleHighlight();
        }
      }
    });

    // Add a KeyListener to listen for key events
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Handle keyTyped events (e.g., when 'p' or 'm' is pressed)
        char keyChar = e.getKeyChar();
        if (enabled) {
          if (keyChar == 'p') {
            System.out.println("Pass");
            observer.pass();
            if (boardPanel.isHighlightedButton(SquareTile.this)) {
              unhighlight();
            }
            //hintsEnabled = false;
          } else if (keyChar == 'm') {
            if (boardPanel.isHighlightedButton(SquareTile.this)) {
              System.out.println("Declare move to " + col + ", " + row);
              observer.placeTile(new Position3D(col, row, -col - row));
              unhighlight();
              //hintsEnabled = false;
            }
          } else if (keyChar == 'h') {
            //hintsEnabled = !hintsEnabled;
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

  void toggleHighlight() {
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

  void addObserver(ReversiController controller) {
    this.observer = controller;
  }

  void squareEnable() {
    this.enabled = true;
  }

  void squareDisable() {
    this.enabled = false;
  }
}

