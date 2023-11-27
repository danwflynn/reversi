package model;

import java.util.List;

/**
 * Player interface for managing computer players.
 */
public interface IComputerPlayer extends IHumanPlayer {
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
}
