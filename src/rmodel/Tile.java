package rmodel;

/**
 * Class to represent a tile on the board.
 */
public class Tile {
  private final Position3D pos;
  private TileType tileType;

  /**
   * A class representing a Tile in the game of Reversi.
   * @param pos The position in the hexagon grid board.
   * @param tileType What player, if any, is on the tile.
   */
  public Tile(Position3D pos, TileType tileType) {
    this.pos = pos;
    this.tileType = tileType;
  }

  /**
   * Sets the tileType of this Tile to the given TileType.
   * @param t The tileType to set on this Tile.
   */
  public void setTileType(TileType t) {
    if (!this.tileType.equals(TileType.EMPTY) && t.equals(TileType.EMPTY)) {
      throw new IllegalStateException("Can't make a tile empty when it has a piece");
    }
    this.tileType = t;
  }

  /**
   * Gets the tileType of this Tile.
   * @return The tileType value of this Tile.
   */
  public TileType getTileType() {
    return tileType;
  }

  /**
   * Gets the position of this Tile.
   * @return The Position3D value of this Tile.
   */
  public Position3D getPos() {
    return this.pos;
  }
}
