package model;

/**
 * This is the player interface.
 */
public interface Tile {
  /**
   * Sets the tileType of this Tile to the given TileType.
   * @param t The tileType to set on this Tile.
   */
  public void setTileType(TileType t);

  /**
   * Gets the tileType of this Tile.
   * @return The tileType value of this Tile.
   */
  public TileType getTileType();

  /**
   * Gets the position of this Tile.
   * @return The Position3D value of this Tile.
   */
  public Position3D getPos();
}
