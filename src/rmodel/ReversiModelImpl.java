package rmodel;

import java.util.ArrayList;
import java.util.List;

public class ReversiModelImpl implements IReversiModel {
  private final List<Tile> board;
  private TileType turn;
  private final int radius;

  public ReversiModelImpl(int radius) {
    this.radius = radius;
    this.board = new ArrayList<>();
    this.turn = TileType.BLACK;
    for (int q = -1 * radius + 1; q < radius; q++) {
      for (int r = -1 * radius + 1; r < radius; r++) {
        for (int s = -1 * radius + 1; s < radius; s++) {
          if (q + r + s == 0) {
            if (Math.abs(q) + Math.abs(r) + Math.abs(s) == 2) {
              if ((q == 0 && r == -1 && s == 1)
              || (q == 1 && r == 0 && s == -1)
              || (q == -1 && r == 1 && s == 0)) {
                this.board.add(new Tile(new Position3D(q, r, s), TileType.BLACK));
              } else {
                this.board.add(new Tile(new Position3D(q, r, s), TileType.WHITE));
              }
            } else {
              this.board.add(new Tile(new Position3D(q, r, s), TileType.EMPTY));
            }
          }
        }
      }
    }
    // INVARIANT: The q r and s values of every position on the board add to 0
  }


  @Override
  public Tile getTileAt(Position3D pos) throws IllegalArgumentException {
    for (Tile tile : this.board) {
      if (tile.getPos().equals(pos)) {
        return tile;
      }
    }
    throw new IllegalArgumentException("This position is not on the board");
  }

  @Override
  public int getRadius() {
    return this.radius;
  }

  @Override
  public TileType getTurn() {
    return this.turn;
  }

  @Override
  public void pass() {
    if (this.turn == TileType.BLACK) {
      this.turn = TileType.WHITE;
    } else {
      this.turn = TileType.BLACK;
    }
  }

  private void placeTileBasicExceptions(Position3D pos) {
    if (pos.getFarthestDirection() >= radius) {
      throw new IllegalArgumentException("Position out of bounds for game board");
    }
    if (!this.getTileAt(pos).getTileType()
            .equals(TileType.EMPTY)) {
      throw new IllegalStateException("There already is a tile in this position");
    }
  }

  private void getAvailableBridgesHelper(Position3D pos, List<List<Tile>> bridges, int qInc, int rInc, int sInc) {
    Position3D p = new Position3D(pos);
    List<Tile> bridge = new ArrayList<>();
    while (true) {
      p = new Position3D(p.getQ() + qInc, p.getR() + rInc, p.getS() + sInc);
      try {
        placeTileBasicExceptions(p);
      } catch (IllegalArgumentException | IllegalStateException e) {
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

    getAvailableBridgesHelper(pos, bridges, 1, -1, 0);
    getAvailableBridgesHelper(pos, bridges, 1, 0, -1);
    getAvailableBridgesHelper(pos, bridges, 0, 1, -1);
    getAvailableBridgesHelper(pos, bridges, -1, 1, 0);
    getAvailableBridgesHelper(pos, bridges, -1, 0, 1);
    getAvailableBridgesHelper(pos, bridges, 0, -1, 1);

    return bridges;
  }

  private void flipBridge(List<Tile> bridge) {
    for (Tile t : bridge) {
      if (!t.getTileType().equals(this.turn)) {
        t.setTileType(this.turn);
      }
    }
  }

  @Override
  public void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException {
    placeTileBasicExceptions(pos);
    boolean noBridges = true;
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      if (!l.isEmpty()) {
        noBridges = false;
        break;
      }
    }
    if (noBridges) {
      throw new IllegalStateException("No available bridges");
    }
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      this.flipBridge(l);
    }
    this.pass();
  }

  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public boolean isGameOver() {
    return false;
  }
}
