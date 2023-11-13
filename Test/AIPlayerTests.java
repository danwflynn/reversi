import model.IReversiModel;
import model.Player;
import model.ReversiModelImpl;
import model.TileType;
import model.AIPlayer;
import model.Position3D;
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
    p2.getHighestScoringMove();
  }

  @Test
  public void testFirstTwoMoves() {
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    p2.getHighestScoringMove();
  }

  @Test
  public void testP2HasNoLegalMovesAndP1Moves() {
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());

    Assert.assertTrue(p2.getAvailableMoves().isEmpty());

    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());

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
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.pass();
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());
    this.model.placeTile(p1.getHighestScoringMove());
    this.model.placeTile(p2.getHighestScoringMove());

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
}
