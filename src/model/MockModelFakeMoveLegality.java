package model;

/**
 * A mock implementation of ReversiModelImpl that verifies the validity of the AIPlayer by
 * constructing fake legalities for moves, and testing that this affects best moves found.
 */
public class MockModelFakeMoveLegality extends ReversiModelImpl implements IReversiModel {
  /**
   * Constructor to make a copy of the game.
   * @param rm model to copy
   */
  public MockModelFakeMoveLegality(IReversiModel rm) {
    super(rm);
  }

  /**
   * A mock implementation of isMoveLegal, which gives false, arbitrary information
   * on which positions on the board are legal moves.
   * @param pos position of potential move
   * @return True if the position is legal according to this method's arbitrary rules,
   *                otherwise return false.
   */
  @Override
  public boolean isMoveLegal(Position3D pos) {
    //Arbitrary rule: a move is always legal if Position coordinates = (0, 0, 0)
    if (pos.equals(new Position3D(0, 0, 0))) {
      return true;
    }
    //Arbitrary rule: a move is never legal if the 'r' value of pos is less than positive 1
    else return pos.getR() >= 1;
  }
}
