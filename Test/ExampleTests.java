import model.IReversiModel;
import model.Position3D;
import model.ReversiModelImpl;
import model.TileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import view.ReversiTextualView;
import view.TextualView;

/**
 * This is a series of tests meant to provide a tutorial on how to use this code base.
 */
public class ExampleTests {
  int radius;
  IReversiModel model;
  TextualView tv;

  @Before
  public void setup() {
    // Radius of the game is 5
    radius = 5;
    // Starting the game in the initial state with radius 5
    model = new ReversiModelImpl(radius);
    // Create a text view for the model
    tv = new ReversiTextualView(model);
  }

  /**
   * Test exceptions for illegal arguments in constructors.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testPosition3DIllegalArguments() {
    // We use the cubic coordinate system for hexagonal grids
    // q r and s values must add to 0
    new Position3D(1, 1, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBoardTooSmall() {
    new ReversiModelImpl(1);
  }

  /**
   * Test that the start game conditions are accurate.
   */
  @Test
  public void TestStartGameConditions() {
    // See if radius is correct
    Assert.assertEquals(radius, model.getRadius());
    // See if the first ring has the right initial tiles
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(0, -1, 1)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(1, -1, 0)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(1, 0, -1)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(0, 1, -1)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(-1, 1, 0)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(-1, 0, 1)).getTileType());
    // See if the scores are equal
    Assert.assertEquals(model.getWhiteScore(), model.getBlackScore());
    // See if the text view toString is correct
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  /**
   * Test exceptions for moves that cannot be made.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsMoveOnCopy() {
    IReversiModel modelCopy = new ReversiModelImpl(model);
    // throw an illegal argument exception if the position is not on the board
    modelCopy.placeTile(new Position3D(100, 0, -100));
  }

  @Test (expected = IllegalStateException.class)
  public void testMoveToAlreadyTakenTileSpaceOnCopy() {
    IReversiModel modelCopy = new ReversiModelImpl(model);
    // throw an illegal state exception if you try to place a tile on an already taken space
    modelCopy.placeTile(new Position3D(0, -1, 1));
  }

  @Test (expected = IllegalStateException.class)
  public void testMoveWithNoFlipsOnCopy() {
    IReversiModel modelCopy = new ReversiModelImpl(model);
    // throw an illegal state exception if you try to place a tile that doesn't flip any others
    modelCopy.placeTile(new Position3D(0, 0, 0));
  }

  @Test (expected = IllegalStateException.class)
  public void testPassAfterGameOver() {
    IReversiModel modelCopy = new ReversiModelImpl(model);
    // throw an illegal state exception if you try to pass after game is over
    modelCopy.pass();
    modelCopy.pass();
    modelCopy.pass();
  }

  @Test (expected = IllegalStateException.class)
  public void testPlaceTileAfterGameOver() {
    IReversiModel modelCopy = new ReversiModelImpl(model);
    // throw an illegal state exception if you try to place a tile after game is over
    modelCopy.pass();
    modelCopy.pass();
    modelCopy.placeTile(new Position3D(-1, -1, 2));
  }

  /**
   * Test the outcomes of various legal moves that cover different cases.
   */
  @Test
  public void testMakeValidBlackMove() {
    // Make a copy of the model before making the move, so we can compare the before and after
    IReversiModel modelBeforeMove = new ReversiModelImpl(model);
    model.placeTile(new Position3D(-1, -1, 2));
    // see if the scores correctly change (+2 for black, -1 for white)
    // one black tile is placed, and one white tile is flipped to black
    Assert.assertEquals(modelBeforeMove.getWhiteScore() - 1, model.getWhiteScore());
    Assert.assertEquals(modelBeforeMove.getBlackScore() + 2, model.getBlackScore());
    // check the toString of the text view
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            " _ _ X X O _ _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testMakeValidWhiteMove() {
    // Make a copy of the model before making the move, so we can compare the before and after
    model.placeTile(new Position3D(-1, -1, 2));
    IReversiModel modelBeforeMove = new ReversiModelImpl(model);
    // this is the move for white
    model.placeTile(new Position3D(-2, -1, 3));
    // see if the scores correctly change (-2 for black, +3 for white)
    Assert.assertEquals(modelBeforeMove.getWhiteScore() + 3, model.getWhiteScore());
    Assert.assertEquals(modelBeforeMove.getBlackScore() - 2, model.getBlackScore());
    // check the toString of the text view
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            " _ O O O O _ _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testBlackPassMidGame() {
    // Make a copy of the model before making the move, so we can compare the before and after
    model.placeTile(new Position3D(-1, -1, 2));
    model.placeTile(new Position3D(-2, -1, 3));
    IReversiModel modelBeforeMove = new ReversiModelImpl(model);
    // black passes here
    model.pass();
    // see if the scores stay the same
    Assert.assertEquals(modelBeforeMove.getWhiteScore(), model.getWhiteScore());
    Assert.assertEquals(modelBeforeMove.getBlackScore(), model.getBlackScore());
    // check the toString of the text view
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            " _ O O O O _ _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testWhiteFlipsOverInMultipleDirections() {
    // Make a copy of the model before making the move, so we can compare the before and after
    model.placeTile(new Position3D(-1, -1, 2));
    model.placeTile(new Position3D(-2, -1, 3));
    model.pass();
    IReversiModel modelBeforeMove = new ReversiModelImpl(model);
    // place the white tile
    // this will flip over black tiles in multiple directions as per the rules of the game
    model.placeTile(new Position3D(-2, 1, 1));
    // see if the scores correctly change (-2 for black, +3 for white)
    Assert.assertEquals(modelBeforeMove.getWhiteScore() + 3, model.getWhiteScore());
    Assert.assertEquals(modelBeforeMove.getBlackScore() - 2, model.getBlackScore());
    // check the toString of the text view
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            " _ O O O O _ _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ O O O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testPlaceTileOnTheEdge() {
    // Make a copy of the model before making the move, so we can compare the before and after
    model.placeTile(new Position3D(-1, -1, 2));
    model.placeTile(new Position3D(-2, -1, 3));
    model.pass();
    model.placeTile(new Position3D(-2, 1, 1));
    // Make these 2 moves set it up so that a black tile can be placed on the edge
    model.placeTile(new Position3D(1, -2, 1));
    model.pass();
    IReversiModel modelBeforeMove = new ReversiModelImpl(model);
    // place a black tile on the edge, this is the after move and everything else counts as before
    model.placeTile(new Position3D(-3, -1, 4));
    // see if the scores correctly change (+4 for black, -3 for white)
    Assert.assertEquals(modelBeforeMove.getWhiteScore() - 3, model.getWhiteScore());
    Assert.assertEquals(modelBeforeMove.getBlackScore() + 4, model.getBlackScore());
    // check the toString of the text view
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "  _ _ _ X _ _ _\n" +
            " X X X X X _ _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ O O O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testOneMoreMoveCuzWhyNot() {
    model.placeTile(new Position3D(-1, -1, 2));
    model.placeTile(new Position3D(-2, -1, 3));
    model.pass();
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(1, -2, 1));
    model.pass();
    model.placeTile(new Position3D(-3, -1, 4));
    model.placeTile(new Position3D(2, -3, 1));
    // this time we will test the actual scores to show that the actual number is accurate
    Assert.assertEquals(7, model.getWhiteScore());
    Assert.assertEquals(5, model.getBlackScore());
    // check the toString of the text view
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " X X X O X _ _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ O O O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  /**
   * Test the text view on different radius sizes.
   */
  @Test
  public void testTextViewWithRadiusTen() {
    TextualView tv2 = new ReversiTextualView(new ReversiModelImpl(10));
    // See if the toString works on larger radius values
    Assert.assertEquals("         _ _ _ _ _ _ _ _ _ _\n" +
            "        _ _ _ _ _ _ _ _ _ _ _\n" +
            "       _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "      _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ _ _ _ _ X O _ _ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _ _ O _ X _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ _ _ _ _ X O _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "      _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "       _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "        _ _ _ _ _ _ _ _ _ _ _\n" +
            "         _ _ _ _ _ _ _ _ _ _", tv2.toString());
  }

  @Test
  public void testSmallestPossibleRadius() {
    TextualView tv3 = new ReversiTextualView(new ReversiModelImpl(2));
    Assert.assertEquals(" X O\n" +
            "O _ X\n" +
            " X O", tv3.toString());
  }
}
