import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import view.ReversiTextualView;
import view.TextualView;

/**
 * A class for testing the AI playing Reversi.
 */
public class AIPlayerTests {
  private IReversiModel model;
  private TextualView tv;
  private Player p1;
  private Player p2;


  /**
   * Sets up some testing conditions.
   */
  @Before
  public void setup() {
    // Radius of the game is 5
    int radius = 5;
    // Starting the game in the initial state with radius 5
    this.model = new ReversiModelImpl(radius);
    // Create a text view for the model
    this.tv = new ReversiTextualView(model);
    this.p1 = new AIPlayer(TileType.BLACK, model);
    this.p2 = new AIPlayer(TileType.WHITE, model);
  }

  @Test
  public void testIsMoveLegalFalse() {
    Assert.assertFalse(this.model.isMoveLegal(new Position3D(-2, 2, 0)));
  }

  @Test
  public void testGetAvailableMoves() {
    Assert.assertEquals(6, p1.getAvailableMoves().size());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetAvailableMovesNotYourTurn() {
    p2.getAvailableMoves();
  }

  @Test (expected = IllegalStateException.class)
  public void testHighestScoringMoveNotYourTurn() {
    p2.getOptimalMove();
  }

  @Test
  public void testFirstTwoMoves() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    Assert.assertEquals(3, p1.getScore());
    Assert.assertEquals(5, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " _ _ _ O X _ _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIFlipTwoBridges() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    Assert.assertEquals(6, p1.getScore());
    Assert.assertEquals(3, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " _ _ X X X _ _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ _ X O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIFlipTwoBridgesAgain() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    Assert.assertEquals(3, p1.getScore());
    Assert.assertEquals(7, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " _ _ X O X _ _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ O O O _ _ _\n" +
            "  _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove5() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    Assert.assertEquals(7, p1.getScore());
    Assert.assertEquals(4, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " _ _ X O X _ _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ O X X _ _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove6() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    Assert.assertEquals(3, p1.getScore());
    Assert.assertEquals(9, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " _ _ X O O _ _ _\n" +
            "_ _ _ X _ O _ _ _\n" +
            " _ _ O O O O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove7() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    Assert.assertEquals(8, p1.getScore());
    Assert.assertEquals(5, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            " _ _ X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ O O X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove8() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    Assert.assertEquals(6, p1.getScore());
    Assert.assertEquals(8, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   _ _ _ O _ _\n" +
            "  _ O _ O _ _ _\n" +
            " _ _ O X X X _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " _ _ O O X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove9() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    Assert.assertEquals(11, p1.getScore());
    Assert.assertEquals(4, p2.getScore());
    Assert.assertEquals("    _ _ _ _ _\n" +
            "   X _ _ O _ _\n" +
            "  _ X _ O _ _ _\n" +
            " _ _ X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetHighestScoringMoveNoMoves() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    p2.getOptimalMove();
  }

  @Test
  public void testP2HasNoLegalMovesAndP1Moves() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertTrue(p2.getAvailableMoves().isEmpty());

    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertEquals(14, p1.getScore());
    Assert.assertEquals(2, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X _ _ X _ _\n" +
            "  _ X _ X _ _ _\n" +
            " _ _ X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove12() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());

    Assert.assertEquals(11, p1.getScore());
    Assert.assertEquals(6, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X _ O X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " _ _ X X O X _ _\n" +
            "_ _ _ X _ O _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAICorrectlyHandlesTieBreakWithDistanceFromUpperLeftVertex() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertEquals(13, p1.getScore());
    Assert.assertEquals(5, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " _ _ X X O X _ _\n" +
            "_ _ _ X _ O _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove14() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());

    Assert.assertEquals(11, p1.getScore());
    Assert.assertEquals(8, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " _ O O O O X _ _\n" +
            "_ _ _ X _ O _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAITieBreak2() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertEquals(16, p1.getScore());
    Assert.assertEquals(4, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X X X X _ _\n" +
            "_ _ _ X _ O _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ _ _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove16() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());

    Assert.assertEquals(14, p1.getScore());
    Assert.assertEquals(7, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X X X X _ _\n" +
            "_ _ _ X _ O _ _ _\n" +
            " _ _ O X O O _ _\n" +
            "  _ _ _ O _ _ _\n" +
            "   _ _ O _ _ _\n" +
            "    _ _ _ _ _", tv.toString());
  }

  @Test
  public void testAIMove17() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertEquals(19, p1.getScore());
    Assert.assertEquals(3, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ _ O X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ X _ _ _\n" +
            "    _ X _ _ _", tv.toString());
  }

  @Test
  public void testAITieBreakWithSomeCloseOnes() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertEquals(21, p1.getScore());
    Assert.assertEquals(2, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " _ X X X X O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ X _ _ _\n" +
            "    _ X _ _ _", tv.toString());
  }

  @Test
  public void testAIMove19() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());

    Assert.assertEquals(17, p1.getScore());
    Assert.assertEquals(7, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " O O O O O O _ _\n" +
            "  _ _ _ X _ _ _\n" +
            "   _ _ X _ _ _\n" +
            "    _ X _ _ _", tv.toString());
  }

  @Test
  public void testAITieBreak4() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());

    Assert.assertEquals(19, p1.getScore());
    Assert.assertEquals(6, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X X X X _ _\n" +
            "_ _ _ X _ X _ _ _\n" +
            " O O X O O O _ _\n" +
            "  _ X _ X _ _ _\n" +
            "   _ _ X _ _ _\n" +
            "    _ X _ _ _", tv.toString());
  }

  @Test
  public void testAIMove21() {
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.pass();
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());
    this.model.placeTile(p1.getOptimalMove());
    this.model.placeTile(p2.getOptimalMove());

    Assert.assertEquals(15, p1.getScore());
    Assert.assertEquals(11, p2.getScore());
    Assert.assertEquals("    _ _ _ X _\n" +
            "   X X X X _ _\n" +
            "  _ X _ O _ _ _\n" +
            " X X X O X X _ _\n" +
            "_ _ _ O _ X _ _ _\n" +
            " O O O O O O _ _\n" +
            "  _ O _ X _ _ _\n" +
            "   O _ X _ _ _\n" +
            "    _ X _ _ _", tv.toString());
  }

  @Test
  public void testIsLegalMoveTrue() {
    Assert.assertTrue(this.model.isMoveLegal(new Position3D(1, -2, 1)));
  }

  @Test
  public void testIsLegalMoveNoBridge() {
    Assert.assertFalse(this.model.isMoveLegal(new Position3D(2, -2, 0)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIsLegalMoveOutOfBoundsThrows() {
    this.model.isMoveLegal(new Position3D(7, -7, 0));
  }

  @Test
  public void testHasLegalMoveTrue() {
    Assert.assertTrue(this.model.hasLegalMove());
  }

  @Test
  public void testHasNoLegalMoveSmallBoard() {
    ReversiModelImpl board = new ReversiModelImpl(2);

    Assert.assertFalse(board.hasLegalMove());
  }

  @Test
  public void testCheckingAllTilesForAvailableMovesViaMock() {
    ReversiModelImpl model = new ReversiModelImpl(4);
    MockModelLoggingObservations mock = new MockModelLoggingObservations(model);
    Player player = new AIPlayer(TileType.BLACK, mock);
    player.getAvailableMoves();
    for (Tile tile : model.getCopyOfBoard()) {
      //for every tile on the board, confirm it checks that position
      Assert.assertTrue(mock.getLog().toString().contains("Checking tile at "
                                                                          + tile.getPos()));
    }
  }

  @Test
  public void testAIPlayerCheckingLegalityOfTilesViaMock() {
    ReversiModelImpl model = new ReversiModelImpl(4);
    MockModelLoggingObservations mock = new MockModelLoggingObservations(model);
    Player player = new AIPlayer(TileType.BLACK, mock);
    player.getAvailableMoves();
    for (Tile tile : model.getCopyOfBoard()) {
      //for every tile on the board, confirm it checks legality of position
      Assert.assertTrue(mock.getLog().toString().contains("Checks legality of position "
                                                                         + tile.getPos()));
    }
  }

  @Test
  public void testAIPlayerFindingBestMoveObligesToArbitraryLegalityDecisionsByMockModel() {
    ReversiModelImpl model = new ReversiModelImpl(4);
    MockModelFakeMoveLegality mock = new MockModelFakeMoveLegality(model);
    Player mockPlayer = new AIPlayer(TileType.BLACK, mock);
    Player realPlayer = new AIPlayer(TileType.BLACK, model);

    // position q = 0, r = 0, s = 0 is illegal at start of game for black, there is no bridge
    Assert.assertFalse(realPlayer.getAvailableMoves().contains(new Position3D(0, 0, 0)));
    // same move is allowed by arbitrary rule in mock that this position is legal
    Assert.assertTrue(mockPlayer.getAvailableMoves().contains(new Position3D(0, 0, 0)));

    // position q = 1, r = -2, s = 1 is legal at start of game for black
    Assert.assertTrue(realPlayer.getAvailableMoves().contains(new Position3D(1, -2, 1)));
    // same move is blocked by arbitrary rule that r < 1 makes a move illegal
    Assert.assertFalse(mockPlayer.getAvailableMoves().contains(new Position3D(1, -2, 1)));
  }

  @Test
  public void testGetHighestScoringMoveWithMock() {
    ReversiModelImpl model = new ReversiModelImpl(4);
    MockModelLoggingObservations mock = new MockModelLoggingObservations(model);
    Player newPlayer = new AIPlayer(TileType.BLACK, mock);
    newPlayer.getOptimalMove();
    for (Tile tile : model.getCopyOfBoard()) {
      //for every tile on the board, confirm it checks legality of position
      Assert.assertTrue(mock.getLog().toString().contains("Checking tile at "
              + tile.getPos()));
    }
  }

  @Test
  public void testAdvancedAIBaseCase() {
    IReversiModel model1 = new ReversiModelImpl(4);
    Player pl1 = new AdvancedAIPlayer(TileType.BLACK, model1);
    model1.placeTile(pl1.getOptimalMove());
    Assert.assertEquals(TileType.BLACK,
            model1.getCopyOfTileAt(new Position3D(1, -2, 1)).getTileType());
  }

  @Test
  public void testAdvancedAIDoesntMoveNextToCorners() {
    IReversiModel model1 = new ReversiModelImpl(4);
    Player pl1 = new AdvancedAIPlayer(TileType.WHITE, model1);
    model1.placeTile(new Position3D(1, -2, 1));
    model1.placeTile(new Position3D(2, -3, 1));
    model1.pass();
    model1.placeTile(new Position3D(1, 1, -2));
    model1.placeTile(new Position3D(-1, -1, 2));
    model1.pass();
    model1.placeTile(new Position3D(2, 1, -3));
    model1.placeTile(pl1.getOptimalMove());
    Assert.assertEquals(TileType.WHITE,
            model1.getCopyOfTileAt(new Position3D(-2, 1, 1)).getTileType());
  }
}
