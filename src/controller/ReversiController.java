package controller;

import model.Position3D;

/**
 * An interface for the controller for a game of Reversi, allowing a player to operate the game.
 */
public interface ReversiController {

  /**
   * Passes the current player's turn without placing a tile.
   */
  void pass();

  /**
   * Places a tile at the given Position3D.
   * @param pos The position to place at
   */
  void placeTile(Position3D pos);
}
