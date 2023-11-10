package model;

/**
 * This is the tile interface.
 * Each player is represented by a black or white tile.
 * Tiles can also be empty to represent a space where nobody has placed their piece yet.
 */
public interface Tile {
  /**
   * Sets the tileType of this Tile to the given TileType.
   * @param t The tileType to set on this Tile.
   */
  void setTileType(TileType t);

  /**
   * Gets the tileType of this Tile.
   * @return The tileType value of this Tile.
   */
  TileType getTileType();

  /**
   * Gets the position of this Tile.
   * @return The Position3D value of this Tile.
   */
  Position3D getPos();

  /**
   * String version of Tile used for textview.
   * @return one of _ O X
   */
  String toString();

  /**
   * Overriding equals.
   * @param other object to compare
   * @return true if equal
   */
  @Override
  boolean equals(Object other);
}
