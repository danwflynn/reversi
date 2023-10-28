package model;

public interface IReversiModel {
  /**
   * Get tile at a given position.
   * @param pos coordinates as a position type
   * @return the tile type at the given
   * @throws IllegalArgumentException if provided position is out of the bounds of the board
   */
  Tile getTileAt(Position3D pos) throws IllegalArgumentException;

  /**
   * Get the amount of hexagons starting from the center making a straight line to the edge.
   * @return radius
   */
  int getRadius();

  /**
   * Get whose turn.
   * @return tile type of turn
   */
  TileType getTurn();

  /**
   * Do nothing on your turn.
   * @throws IllegalStateException if the game is over
   */
  void pass() throws IllegalStateException;

  /**
   * Place a tile on your turn to make a move.
   * @param pos coordinates as position type
   * @throws IllegalStateException if the move is Illegal
   * @throws IllegalArgumentException if the position is out of bounds
   * @throws IllegalStateException if the game is over
   */
  void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException;

  /**
   * Get the amount of hexagons on the board.
   * @return amount of tiles
   */
  int getBoardSize();

  /**
   * Gets how many black tiles on the board.
   * @return black score
   */
  int getBlackScore();

  /**
   * Gets how many white tiles on the board.
   * @return white score
   */
  int getWhiteScore();

  /**
   * Check if the game is over.
   * @return true if the game is over
   */
  boolean isGameOver();
}
