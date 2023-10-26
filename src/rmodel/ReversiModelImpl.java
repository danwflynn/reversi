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
            this.board.add(new Tile(new Position3D(q, r, s), TileType.EMPTY));
          }
        }
      }
    }
  }


  @Override
  public TileType getTileAt(Position3D pos) {
    return null;
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
