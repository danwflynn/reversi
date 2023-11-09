package model;

import java.util.List;

/**
 * A Mock model of Reversi for testing the validity of inputs and checks.
 */
public class MockModel extends ReversiModelImpl {

  private StringBuilder log;

  /**
   * A constructor for creating a mock model out of a pre-existing model.
   * @param model The model to copy after
   */
  public MockModel(IReversiModel model) {
    super(model);
    this.log = new StringBuilder();

  }
  /**
   * Get tile at a given position (intentionally mutable so that we can flip them when necessary).
   *
   * @param pos coordinates as a position type
   * @return the tile type at the given
   * @throws IllegalArgumentException if provided position is out of the bounds of the board
   */
  @Override
  public Tile getTileAt(Position3D pos) throws IllegalArgumentException {
    this.log.append("Checking tile at ").append(pos);
    return super.getTileAt(pos);
  }

  /**
   * Do nothing on your turn.
   *
   * @throws IllegalStateException if the game is over
   */
  @Override
  public void pass() throws IllegalStateException {
    super.pass();
  }

  /**
   * Place a tile on your turn to make a move.
   *
   * @param pos coordinates as position type
   * @throws IllegalStateException    if the move is Illegal
   * @throws IllegalArgumentException if the position is out of bounds
   * @throws IllegalStateException    if the game is over
   */
  @Override
  public void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException {
    super.placeTile(pos);
  }

  /**
   * Gets a copy of the tile at a given position on the board.
   *
   * @param pos position of tile
   * @return copy of tile at the position
   * @throws IllegalArgumentException if provided position is out of the bounds of the board
   */
  @Override
  public Tile getCopyOfTileAt(Position3D pos) throws IllegalArgumentException {
    log.append("Checks and gets copy at " + pos);
    return null;
  }

  /**
   * Get the amount of hexagons starting from the center making a straight line to the edge.
   *
   * @return radius
   */
  @Override
  public int getRadius() {
    return super.getRadius();
  }

  /**
   * Get whose turn.
   *
   * @return tile type of turn
   */
  @Override
  public TileType getTurn() {
    return super.getTurn();
  }

  /**
   * Get the amount of hexagons on the board.
   *
   * @return amount of tiles
   */
  @Override
  public int getBoardSize() {
    return super.getBoardSize();
  }

  /**
   * Gets how many black tiles on the board.
   *
   * @return black score
   */
  @Override
  public int getBlackScore() {
    return super.getBlackScore();
  }

  /**
   * Gets how many white tiles on the board.
   *
   * @return white score
   */
  @Override
  public int getWhiteScore() {
    return super.getWhiteScore();
  }

  /**
   * Check if the game is over.
   *
   * @return true if the game is over
   */
  @Override
  public boolean isGameOver() {
    return super.isGameOver();
  }

  /**
   * Get how many passes were made in a row at the given moment.
   *
   * @return passes in a row
   */
  @Override
  public int getPassesInARow() {
    return super.getPassesInARow();
  }

  /**
   * Get a copy of the board, so you can do stuff with it without mutating the original game.
   *
   * @return copy of board
   */
  @Override
  public List<Tile> getCopyOfBoard() {
    return super.getCopyOfBoard();
  }

  /**
   * Is the move at the given position legal for whoever turn it is?
   *
   * @param pos position of potential move
   * @return true if legal
   */
  @Override
  public boolean isMoveLegal(Position3D pos) {
    return super.isMoveLegal(pos);
  }

  /**
   * Does the current player have a legal move?
   *
   * @return true if current player has legal move
   */
  @Override
  public boolean hasLegalMove() {
    return super.hasLegalMove();
  }

  /**
   * Returns the log of this mock.
   * @return The log value of this mock.
   */
  public StringBuilder getLog() {
    return this.log;
  }
}
