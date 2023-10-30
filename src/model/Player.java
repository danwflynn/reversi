package model;

import java.util.List;

/**
 * Player interface for managing players.
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
   * Passes the player's turn by calling pass() on the model.
   * @throws IllegalStateException if it isn't the player's turn
   */
  void pass() throws IllegalStateException;

  /**
   * Places the player's tile at the given position by calling placeTile(pos) on the model.
   * @param pos position to make the move
   * @throws IllegalStateException if it isn't the player's turn or if the move is illegal
   * @throws IllegalArgumentException if the position is out of bounds
   */
  void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException;

  /**
   * Gets a list of all positions where a move can be made by the player on a given turn.
   * @return list of possible positions to make move
   * @throws IllegalStateException if it isn't the player's turn
   */
  List<Position3D> getAvailableMoves() throws IllegalStateException;

  /**
   * Gets the position that increases the player's score the most out of all possible moves.
   * @return position for move that maximizes score increase
   * @throws IllegalStateException if it isn't the player's turn
   */
  Position3D getHighestScoringMove() throws IllegalStateException;
}