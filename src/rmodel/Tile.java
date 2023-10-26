package rmodel;

/**
 * Class to represent a tile on the board.
 */
public class Tile {
  private final Position3D pos;
  private TileType tileType;

  public Tile(Position3D pos, TileType tileType) {
    this.pos = pos;
    this.tileType = tileType;
  }

  public void setTileType(TileType t) {
    if (!this.tileType.equals(TileType.EMPTY) && t.equals(TileType.EMPTY)) {
      throw new IllegalStateException("Can't make a tile empty when it has a piece");
    }
    this.tileType = t;
  }

  public TileType getTileType() {
    return tileType;
  }

  public Position3D getPos() {
    return this.pos;
  }
}
