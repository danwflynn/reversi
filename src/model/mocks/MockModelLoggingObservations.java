package model.mocks;

import model.IReversiModel;
import model.position.Position3D;
import model.ReversiModelImpl;
import model.tile.Tile;

/**
 * A Mock model of Reversi for testing the validity of inputs and checks.
 */
public class MockModelLoggingObservations extends ReversiModelImpl implements IReversiModel {

  private final StringBuilder log;

  /**
   * A constructor for creating a mock model out of a pre-existing model.
   * @param model The model to copy after
   */
  public MockModelLoggingObservations(IReversiModel model) {
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
    this.log.append("Checking tile at ").append(pos).append("\n");
    return super.getTileAt(pos);
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
    log.append("Checks and gets copy at ").append(pos).append("\n");
    return super.getCopyOfTileAt(pos);
  }

  /**
   * Is the move at the given position legal for whoever turn it is.
   *
   * @param pos position of potential move
   * @return true if legal
   */
  @Override
  public boolean isMoveLegal(Position3D pos) {
    this.log.append("Checks legality of position ").append(pos).append("\n");
    return super.isMoveLegal(pos);
  }

  /**
   * Returns the log of this mock.
   * @return The log value of this mock.
   */
  public StringBuilder getLog() {
    return this.log;
  }
}
