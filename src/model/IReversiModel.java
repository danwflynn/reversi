package model;

import java.util.List;

/**
 * Reversi model interface.
 */
public interface IReversiModel extends ReadonlyIReversiModel {
  /**
   * Get tile at a given position (intentionally mutable so that we can flip them when necessary).
   * @param pos coordinates as a position type
   * @return the tile type at the given
   * @throws IllegalArgumentException if provided position is out of the bounds of the board
   */
  Tile getTileAt(Position3D pos) throws IllegalArgumentException;



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


}
