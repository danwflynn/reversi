package rmodel;

import java.util.ArrayList;
import java.util.List;

public class ReversiModelImpl implements IReversiModel {
  private final List<Tile> board;
  private boolean blackTurn;
  private final int radius;

  public ReversiModelImpl(int radius) {
    this.radius = radius;
    this.board = new ArrayList<>();
    this.blackTurn = true;
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
  public TileType getTileTypeAt(Position3D pos) throws IllegalArgumentException {
    for (Tile tile : this.board) {
      if (tile.getPos() == pos) {
        return tile.getTileType();
      }
    }
    throw new IllegalArgumentException("This position is not on the board");
  }

  @Override
  public int getRadius() {
    return 0;
  }

  @Override
  public void pass() {

  }

  @Override
  public void placeTile(Position3D pos) {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }
}
