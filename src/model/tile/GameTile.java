package model.tile;

import model.position.Position3D;

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

  /**
   * Copy constructor.
   * @param t old tile
   */
  public GameTile(Tile t) {
    this.pos = t.getPos();
    this.tileType = t.getTileType();
  }

  /**
   * Sets the tile type of this Tile.
   * @param t The tileType to set on this Tile.
   */
  @Override
  public void setTileType(TileType t) {
    if (!this.tileType.equals(TileType.EMPTY) && t.equals(TileType.EMPTY)) {
      throw new IllegalStateException("Can't make a tile empty when it has a piece");
    }
    this.tileType = t;
  }

  /**
   * Gets this Tile's tile type.
   * @return The tileType parameter of this Tile.
   */
  @Override
  public TileType getTileType() {
    return tileType;
  }

  /**
   * Gets this Tile's position.
   * @return The position of this Tile
   */
  @Override
  public Position3D getPos() {
    return new Position3D(this.pos);
  }

  /**
   * Turns the Tile into its respective String representative.
   * @return 'X' for player black, 'O' for player white, and '_' if empty.
   */
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

  /**
   * Determines if this GameTile is the same as the given Object.
   * @param o The object to compare this GameTile to
   * @return True if this GameTile and the given object have the same attributes, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof GameTile)) {
      return false;
    }
    else {
      GameTile other = (GameTile) o;
      return other.tileType.equals(this.tileType)
              && other.pos.equals(this.pos);
    }
  }

  /**
   * Override hashcode for checking if two GameTiles are equal.
   * @return hashcode.
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + pos.hashCode();
    result = 31 * result + tileType.hashCode();
    return result;
  }
}
