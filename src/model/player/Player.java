package model.player;

import controller.ReversiController;
import model.position.Position3D;
import model.tile.TileType;

import java.util.List;

/**
 * Player interface for managing computer players.
 */
public interface Player {
  /**
   * Gets the tile type of the given player.
   * @return black or white tile type
   */
  TileType getPlayerColor();

  /**
   * How many tiles the player has on the board of their color.
   * @return player's score
   */
  int getScore();

  /**
   * Takes a turn in a game of Reversi.
   */
  void turnAction();

  /**
   * Gets a list of all positions where a move can be made by the player on a given turn.
   * @return list of possible positions to make move
   * @throws IllegalStateException if it isn't the player's turn
   */
  List<Position3D> getAvailableMoves() throws IllegalStateException;

  /**
   * Gets the optimal move for the player based on their optimization rules.
   * @return position for optimal move
   * @throws IllegalStateException if it isn't the player's turn
   * @throws IllegalStateException if there are no legal moves
   */
  Position3D getOptimalMove() throws IllegalStateException;

  /**
   * Adds controller to observe the player.
   * @param controller listener to player
   */
  void addObserver(ReversiController controller);
}
