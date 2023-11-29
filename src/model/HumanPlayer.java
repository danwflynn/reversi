package model;

import controller.ReversiController;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer implements Player {

  protected final TileType playerColor;
  protected final ReadonlyIReversiModel model;
  protected ReversiController observer;

  /**
   * Constructs an AIPlayer.
   * @param playerColor The color of the player
   * @param model The model to use as reference for the player
   */
  public HumanPlayer(TileType playerColor, ReadonlyIReversiModel model) {
    if (playerColor.equals(TileType.EMPTY)) {
      throw new IllegalArgumentException("Player cannot be represented by an empty piece.");
    }
    this.playerColor = playerColor;
    this.model = model;
  }

  /**
   * Gets the tile type of the given player.
   *
   * @return black or white tile type
   */
  @Override
  public TileType getPlayerColor() {
    return this.playerColor;
  }

  /**
   * How many tiles the player has on the board of their color.
   *
   * @return player's score
   */
  @Override
  public int getScore() {
    if (this.playerColor.equals(TileType.BLACK)) {
      return this.model.getBlackScore();
    } else {
      return this.model.getWhiteScore();
    }
  }

  /**
   * Gets a list of all positions where a move can be made by the player on a given turn.
   *
   * @return list of possible positions to make move
   * @throws IllegalStateException if it isn't the player's turn
   */
  @Override
  public List<Position3D> getAvailableMoves() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    List<Position3D> availableMoves = new ArrayList<>();
    for (Tile t : this.model.getCopyOfBoard()) {
      if (this.model.isMoveLegal(t.getPos())) {
        availableMoves.add(t.getPos());
      }
    }
    return availableMoves;
  }

  /**
   * Gets the optimal move for the player based on their optimization rules.
   *
   * @return position for optimal move
   * @throws IllegalStateException if it isn't the player's turn
   * @throws IllegalStateException if there are no legal moves
   */
  @Override
  public Position3D getOptimalMove() throws IllegalStateException {
    throw new UnsupportedOperationException("Human players cannot use this method");
  }

  /**
   * Adds controller to observe the player.
   *
   * @param controller listener to player
   */
  @Override
  public void addObserver(ReversiController controller) {
    this.observer = controller;
  }

  /**
   * Takes a turn in a game of Reversi.
   */
  @Override
  public void turnAction() {
    this.observer.sendTurnMessageToView();
  }
}
