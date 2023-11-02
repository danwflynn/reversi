package model;

import java.util.List;

/*
  A class for the implementation of a Readonly version of the Reversi model.
 */
public class ReadonlyReversiModelImpl implements ReadonlyIReversiModel {
  private final IReversiModel model;

  /**
   * Constructs a ReadonlyReversiModelImpl with the given model.
   * @param model The model to call observer methods.
   */
  ReadonlyReversiModelImpl(ReversiModelImpl model) {
    this.model = model;
  }

  /**
   * Constructs a ReadonlyReversiModelImpl with a new model using the given radius.
   * @param radius The radius used to make the model from which observer methods are called.
   */
  ReadonlyReversiModelImpl(int radius) {
    this.model = new ReversiModelImpl(radius);
  }
  /**
   * Get the amount of hexagons starting from the center making a straight line to the edge.
   *
   * @return radius
   */
  @Override
  public int getRadius() {
    return this.model.getRadius();
  }

  /**
   * Get whose turn.
   *
   * @return tile type of turn
   */
  @Override
  public TileType getTurn() {
    return this.model.getTurn();
  }

  /**
   * Get the amount of hexagons on the board.
   *
   * @return amount of tiles
   */
  @Override
  public int getBoardSize() {
    return this.model.getBoardSize();
  }

  /**
   * Gets how many black tiles on the board.
   *
   * @return black score
   */
  @Override
  public int getBlackScore() {
    return this.model.getBlackScore();
  }

  /**
   * Gets how many white tiles on the board.
   *
   * @return white score
   */
  @Override
  public int getWhiteScore() {
    return this.model.getWhiteScore();
  }

  /**
   * Check if the game is over.
   *
   * @return true if the game is over
   */
  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  /**
   * Get how many passes were made in a row at the given moment.
   *
   * @return passes in a row
   */
  @Override
  public int getPassesInARow() {
    return this.model.getPassesInARow();
  }

  /**
   * Get a copy of the board, so you can do stuff with it without mutating the original game.
   *
   * @return copy of board
   */
  @Override
  public List<Tile> getCopyOfBoard() {
    return this.model.getCopyOfBoard();
  }
}
