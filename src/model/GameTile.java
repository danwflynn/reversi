package model;

/**
 * Class to represent a tile on the board.
 */
public class GameTile implements Tile {
  private final Position3D pos;
  private TileType tileType;

  /**
   * A class representing a Tile in the game of Reversi.
   * @param pos The position in the hexagon grid board.
   * @param tileType What player, if any, is on the tile.
   */
  public GameTile(Position3D pos, TileType tileType) {
    this.pos = pos;
    this.tileType = tileType;
  }

  @Override
  public void setTileType(TileType t) {
    if (!this.tileType.equals(TileType.EMPTY) && t.equals(TileType.EMPTY)) {
      throw new IllegalStateException("Can't make a tile empty when it has a piece");
    }
    this.tileType = t;
  }

  @Override
  public TileType getTileType() {
    return tileType;
  }

  @Override
  public Position3D getPos() {
    return this.pos;
  }

  @Override
  public String toString() {
    if (this.tileType.equals(TileType.BLACK)) {
      return "X";
    } else if (this.tileType.equals(TileType.WHITE)) {
      return "O";
    } else {
      return "_";
    }
  }
}
