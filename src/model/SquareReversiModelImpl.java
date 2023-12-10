package model;

public class SquareReversiModelImpl extends ReversiModelImpl implements IReversiModel {
  /**
   * Constructor for the class.
   *
   * @param radius amount of hexagons from the center (included) to the edge in a straight line
   * @throws IllegalArgumentException if the radius is too short
   */
  public SquareReversiModelImpl(int radius) throws IllegalArgumentException {
    super(radius);
  }

  /**
   * Constructor to make a copy of the game.
   *
   * @param rm model to copy
   */
  public SquareReversiModelImpl(ReadonlyIReversiModel rm) {
    super(rm);
  }
}
