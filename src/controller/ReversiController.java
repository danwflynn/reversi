package controller;

import model.Position3D;
import model.TileType;

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

  /**
   * Get the player color of this controller.
   * @return The color of the player.
   */
  TileType getPlayerColor();

  /**
   * Gets whose turn it is.
   * @return tile type of turn
   */
  TileType getModelTurn();

  /**
   * Reacts to the model, which gives this controller the turn in play.
   */
  void alertTurn();

  /**
   * Makes the view display the message telling the player it's their turn.
   */
  void sendTurnMessageToView();

  boolean isHuman();
}
