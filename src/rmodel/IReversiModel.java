package rmodel;

public interface IReversiModel {
  /**
   * Get tile at a given position.
   * @param pos coordinates as a position type
   * @return the tile type at the given
   * @throws IllegalArgumentException if provided position is out of the bounds of the board
   */
  TileType getTileTypeAt(Position3D pos) throws IllegalArgumentException;

  int getRadius();

  void pass();

  void placeTile(Position3D pos);

  boolean isGameOver();
}
