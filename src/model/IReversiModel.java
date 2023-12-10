package model;

import controller.ReversiController;
import model.position.Position3D;
import model.tile.Tile;

/**
 * Reversi model interface.
 * All the immutable methods are inherited from the read only interface.
 * The functionality remains the same as part 1 but with some extra methods.
 * This interface governs the game through its board and rules.
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
   * Starts the game, allowing players to make moves.
   */
  void startGame();

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
   * Adds the observer for the controller for the White player.
   * @param controller The controller observing this model.
   */
  void addWhiteObserver(ReversiController controller);

  /**
   * Adds the observer for the controller for the Black player.
   * @param controller The controller observing this model.
   */
  void addBlackObserver(ReversiController controller);

  /**
   * Determines if both players in the game are human players, as opposed to AI.
   * @return True if both players are human, and false otherwise.
   */
  boolean bothPlayersHuman();
}
