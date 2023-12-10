package model;

import model.position.Position3D;
import model.tile.GameTile;
import model.tile.Tile;
import model.tile.TileType;

import java.util.ArrayList;
import java.util.List;

public class SquareReversiModelImpl extends ReversiModelImpl implements IReversiModel {
  /**
   * Constructor for the class.
   *
   * @param diameter amount of hexagons from the center (included) to the edge in a straight line
   * @throws IllegalArgumentException if the radius is too short or diameter isn't even
   */
  public SquareReversiModelImpl(int diameter) throws IllegalArgumentException {
    super(diameter);
    if (diameter % 2 != 0) {
      throw new IllegalArgumentException("Diameter must be even");
    }
    this.radius = diameter / 2;
    this.board.clear();
    for (int x = 0; x < diameter; x++) {
      for (int y = 0; y < diameter; y++) {
        TileType type = TileType.EMPTY;
        if ((x == radius && y == radius) || (x == radius - 1 && y == radius - 1)) {
          type = TileType.BLACK;
        } else if ((x == radius && y == radius - 1) || (x == radius - 1 && y == radius)) {
          type = TileType.WHITE;
        }
        this.board.add(new GameTile(new Position3D(x, y, -x - y), type));
      }
    }
    this.updateScore();
  }

  /**
   * Constructor to make a copy of the game.
   *
   * @param rm model to copy
   */
  public SquareReversiModelImpl(ReadonlyIReversiModel rm) {
    super(rm);
  }

  private void placeTileBasicExceptions(Position3D pos) throws IllegalStateException,
          IllegalArgumentException {
    if (this.isGameOver()) {
      throw new IllegalStateException("The game is over");
    }
    if (pos.getQ() < 0 || pos.getR() < 0 || pos.getQ() >= 2 * this.radius
            || pos.getR() >= 2 * this.radius) {
      throw new IllegalArgumentException("Position out of bounds for game board");
    }
    if (!this.getTileAt(pos).getTileType().equals(TileType.EMPTY)) {
      throw new IllegalStateException("There already is a tile in this position");
    }
  }

  private void getAvailableBridgesHelper(Position3D pos, List<List<Tile>> bridges,
                                         int qInc, int rInc) {
    Position3D p = new Position3D(pos);
    List<Tile> bridge = new ArrayList<>();
    while (true) {
      int x = p.getQ() + qInc;
      int y = p.getR() + rInc;
      p = new Position3D(x, y, -x - y);
      if ((p.getQ() < 0 || p.getR() < 0 || p.getQ() >= 2 * this.radius
              || p.getR() >= 2 * this.radius)
              || this.getTileAt(p).getTileType().equals(TileType.EMPTY)) {
        break;
      }
      if (getTileAt(p).getTileType().equals(this.turn)) {
        bridges.add(bridge);
        break;
      } else {
        bridge.add(this.getTileAt(p));
      }
    }
  }

  private List<List<Tile>> getAvailableBridges(Position3D pos) {
    List<List<Tile>> bridges = new ArrayList<>();
    placeTileBasicExceptions(pos);

    getAvailableBridgesHelper(pos, bridges, 1, -1);
    getAvailableBridgesHelper(pos, bridges, 1, 0);
    getAvailableBridgesHelper(pos, bridges, 0, 1);
    getAvailableBridgesHelper(pos, bridges, -1, 1);
    getAvailableBridgesHelper(pos, bridges, -1, 0);
    getAvailableBridgesHelper(pos, bridges, 0, -1);
    getAvailableBridgesHelper(pos, bridges, 1, 1);
    getAvailableBridgesHelper(pos, bridges, -1, -1);

    return bridges;
  }

  @Override
  public void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException {
    placeTileBasicExceptions(pos);
    this.passCounter = 0;
    boolean noBridges = true;
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      if (!l.isEmpty()) {
        noBridges = false;
        break;
      }
    }
    if (noBridges) {
      throw new IllegalStateException("No available bridges.");
    }
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      this.flipBridge(l);
    }
    this.getTileAt(pos).setTileType(this.turn);
    this.updateScore();
    if (this.turn == TileType.BLACK) {
      this.turn = TileType.WHITE;
      if (whiteObserver != null && !this.isGameOver()) {
        this.whiteObserver.alertTurn();
      }
    } else {
      this.turn = TileType.BLACK;
      if (blackObserver != null && !this.isGameOver()) {
        this.blackObserver.alertTurn();
      }
    }
  }

  @Override
  public boolean isMoveLegal(Position3D pos) {
    if (this.isGameOver()) {
      return false;
    }
    if (!this.getTileAt(pos).getTileType().equals(TileType.EMPTY)) {
      return false;
    }
    boolean noBridges = true;
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      if (!l.isEmpty()) {
        noBridges = false;
        break;
      }
    }
    if (noBridges) {
      return false;
    }
    boolean legality = false;
    for (Tile t : this.board) {
      if (t.getPos().equals(pos)) {
        legality = true;
      }
    }
    return legality;
  }
}
