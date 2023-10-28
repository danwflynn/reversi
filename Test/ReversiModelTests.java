import org.junit.Assert;
import org.junit.Test;

import model.IReversiModel;
import model.Position3D;
import model.ReversiModelImpl;
import model.GameTile;
import model.TileType;
import view.ReversiTextualView;
import view.TextualView;

/**
 * Tests for the reversi model.
 */
public class ReversiModelTests {

  @Test
  public void testPosition3DEqualsOther() {
    Position3D pos1 = new Position3D(0, 0, 0);
    Position3D pos2 = new Position3D(0, 0, 0);
    Assert.assertEquals(pos1, pos2);
  }

  @Test
  public void testPosition3DEqualsOtherButQIsDifferent() {
    Position3D pos1 = new Position3D(0, 0, 0);
    Position3D pos2 = new Position3D(1, 0, -1);
    Assert.assertNotEquals(pos1, pos2);
  }

  @Test
  public void testPosition3DEqualsOtherButRIsDifferent() {
    Position3D pos1 = new Position3D(0, 0, 0);
    Position3D pos2 = new Position3D(0, 1, -1);
    Assert.assertNotEquals(pos1, pos2);
  }

  @Test
  public void testPosition3DEqualsOtherButSIsDifferent() {
    Position3D pos1 = new Position3D(0, 0, 0);
    Position3D pos2 = new Position3D(0, -1, 1);
    Assert.assertNotEquals(pos1, pos2);
  }

  @Test
  public void testPosition3DNotEqualToNull() {
    Position3D pos1 = new Position3D(0, 0, 0);
    Assert.assertNotNull(pos1);
  }

  @Test
  public void testConstructPosition3DIllegalCoordinates() {
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      Position3D pos1 = new Position3D(1, 1, 1);
    });
  }

  @Test
  public void testSetTileTypeChangesProperly() {
    Position3D pos1 = new Position3D(0, 0, 0);
    GameTile tile = new GameTile(pos1, TileType.EMPTY);
    tile.setTileType(TileType.WHITE);
    Assert.assertEquals(TileType.WHITE, tile.getTileType());
  }

  @Test
  public void testSetTileDoesNotWorkIfTileAlreadySet() {
    Position3D pos1 = new Position3D(0, 0, 0);
    GameTile tile = new GameTile(pos1, TileType.WHITE);
    Assert.assertThrows(IllegalStateException.class, () -> {
      tile.setTileType(TileType.EMPTY);
    });
  }

  @Test
  public void testInitialStateOfGameBlackUpLeft() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(0, -1, 1);
    Assert.assertEquals(TileType.BLACK, model.getTileAt(pos1).getTileType());
  }

  //getPossibleLine(Position3D vector, Position3D start, List listToAddTo)


  @Test
  public void testInitialStateOfGameBlackBottomLeft() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(-1, 1, 0);
    Assert.assertEquals(TileType.BLACK, model.getTileAt(pos1).getTileType());
  }

  @Test
  public void testInitialStateOfGameBlackRight() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(1, 0, -1);
    Assert.assertEquals(TileType.BLACK, model.getTileAt(pos1).getTileType());
  }

  @Test
  public void testInitialStateOfGameWhiteLeft() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(-1, 0 , 1);
    Assert.assertEquals(TileType.WHITE, model.getTileAt(pos1).getTileType());
  }

  @Test
  public void testInitialStateOfGameWhiteUpRight() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(1, -1, 0);
    Assert.assertEquals(TileType.WHITE, model.getTileAt(pos1).getTileType());
  }

  @Test
  public void testInitialStateOfGameWhiteBottomRight() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(0, 1, -1);
    Assert.assertEquals(TileType.WHITE, model.getTileAt(pos1).getTileType());
  }

  @Test
  public void testTileGetPosition() {
    GameTile tile = new GameTile(new Position3D(1, 1,  -2), TileType.BLACK);
    Assert.assertEquals(new Position3D(1, 1, -2), tile.getPos());
  }

  @Test
  public void testGetTileAtTooBigThrowsException() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(5, -4, -1);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.getTileAt(pos1);
    });
  }

  @Test
  public void testMakeReversiBoardHasCorrectNumberOfTiles() {
    IReversiModel model = new ReversiModelImpl(4);
    Assert.assertEquals(37, model.getBoardSize());
  }

  @Test
  public void testPlaceTileThrowsIfTileAlreadyOnDestination() {
    IReversiModel model = new ReversiModelImpl(4);
    Assert.assertThrows(IllegalStateException.class, () -> {
      model.placeTile(new Position3D(0, 1, -1));
    });
  }

  @Test
  public void testPlaceTileOutOfBoundsThrows() {
    IReversiModel model = new ReversiModelImpl(4);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.placeTile(new Position3D(-5, 3, 2));
    });
  }

  @Test
  public void testPassChangesProperly() {
    IReversiModel model = new ReversiModelImpl(4);
    Assert.assertEquals(TileType.BLACK, model.getTurn());
    model.pass();
    Assert.assertEquals(TileType.WHITE, model.getTurn());
    model.pass();
    Assert.assertEquals(TileType.BLACK, model.getTurn());
  }

  @Test
  public void testGetFarthestDirectionNegativeThree() {
    Position3D pos1 = new Position3D(-3, 2, 1);
    Assert.assertEquals(3, pos1.getFarthestDirection());
  }

  @Test
  public void testGetFarthestDirectionPositiveOne() {
    Position3D pos1 = new Position3D(1, -1, 0);
    Assert.assertEquals(1, pos1.getFarthestDirection());
  }

  @Test
  public void testPracticeConstructorPosition3D() {
    Position3D pos1 = new Position3D(1, 1, -2);
    Position3D pos2 = new Position3D(pos1);
    Assert.assertTrue(-2 == pos2.getS() && 1 == pos2.getQ() && 1 == pos2.getR());
  }

  @Test
  public void testMoveTileBlackNegTwoPosOnePosOne() {
    IReversiModel model = new ReversiModelImpl(6);
    model.placeTile(new Position3D(-2, 1, 1));
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(-2, 1, 1)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(-1, 0, 1)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(0, -1, 1)).getTileType());
  }

  @Test
  public void testTwoMovesWhitePlacesOnNegOneNegOnePosTwo() {
    IReversiModel model = new ReversiModelImpl(6);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-1, -1, 2));
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(-2, 1, 1)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(-1, 0, 1)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(-1, -1, 2)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(0, -1, 1)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(1, -1, 0)).getTileType());
  }

  @Test
  public void testInitialScores() {
    IReversiModel model = new ReversiModelImpl(6);
    Assert.assertEquals(3, model.getBlackScore());
    Assert.assertEquals(3, model.getWhiteScore());
  }

  @Test
  public void testScoresAfterMakingMove() {
    IReversiModel model = new ReversiModelImpl(6);
    model.placeTile(new Position3D(-2, 1, 1));
    Assert.assertEquals(5, model.getBlackScore());
    Assert.assertEquals(2, model.getWhiteScore());
  }

  @Test
  public void testScoresAfterMakingTwoMoves() {
    IReversiModel model = new ReversiModelImpl(6);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-1, -1, 2));
    Assert.assertEquals(4, model.getBlackScore());
    Assert.assertEquals(4, model.getWhiteScore());
  }

  @Test
  public void testScoresAfterMoreMoves() {
    IReversiModel model = new ReversiModelImpl(6);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-1, -1, 2));
    model.placeTile(new Position3D(1, -2, 1));
    Assert.assertEquals(7, model.getBlackScore());
    Assert.assertEquals(2, model.getWhiteScore());
    model.placeTile(new Position3D(2, -1, -1));
    Assert.assertEquals(4, model.getBlackScore());
    Assert.assertEquals(6, model.getWhiteScore());
    model.placeTile(new Position3D(1, 1, -2));
    Assert.assertEquals(8, model.getBlackScore());
    Assert.assertEquals(3, model.getWhiteScore());
    model.placeTile(new Position3D(-1, 2, -1));
    Assert.assertEquals(4, model.getBlackScore());
    Assert.assertEquals(8, model.getWhiteScore());
  }

  @Test
  public void testGameOver() {
    IReversiModel model = new ReversiModelImpl(6);
    Assert.assertFalse(model.isGameOver());
    model.pass();
    Assert.assertFalse(model.isGameOver());
    model.placeTile(new Position3D(-2, 1, 1));
    Assert.assertFalse(model.isGameOver());
    model.pass();
    Assert.assertFalse(model.isGameOver());
    model.pass();
    Assert.assertTrue(model.isGameOver());
  }

  @Test (expected = IllegalStateException.class)
  public void testGameOverPassException() {
    IReversiModel model = new ReversiModelImpl(6);
    model.pass();
    model.pass();
    model.pass();
  }

  @Test (expected = IllegalStateException.class)
  public void testGameOverPlaceTileException() {
    IReversiModel model = new ReversiModelImpl(6);
    model.pass();
    model.pass();
    model.placeTile(new Position3D(-2, 1, 1));
  }

  @Test
  public void testSmallestToStringPossible() {
    String resultStr = " X O \n" +
            "O _ X \n" +
            " X O \n";
    IReversiModel model = new ReversiModelImpl(2);
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testToStringOfInitialGameRadius6() {
    String resultStr = "     _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            " _ _ _ _ X O _ _ _ _ \n" +
            "_ _ _ _ O _ X _ _ _ _ \n" +
            " _ _ _ _ X O _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "     _ _ _ _ _ _ \n";
    IReversiModel model = new ReversiModelImpl(6);
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testToStringOfInitialGameRadius4() {
    String resultStr = "   _ _ _ _ \n" +
            "  _ _ _ _ _ \n" +
            " _ _ X O _ _ \n" +
            "_ _ O _ X _ _ \n" +
            " _ _ X O _ _ \n" +
            "  _ _ _ _ _ \n" +
            "   _ _ _ _ \n";
    IReversiModel model = new ReversiModelImpl(4);
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testToStringOfMoveRadius4() {
    String resultStr = "   _ _ _ _ \n" +
            "  _ _ _ _ _ \n" +
            " _ _ X O _ _ \n" +
            "_ _ X _ X _ _ \n" +
            " _ X X O _ _ \n" +
            "  _ _ _ _ _ \n" +
            "   _ _ _ _ \n";
    IReversiModel model = new ReversiModelImpl(4);
    model.placeTile(new Position3D(-2, 1, 1));
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testToStringOfSecondMoveRadius4() {
    String resultStr = "   _ _ _ _ \n" +
            "  _ _ _ _ _ \n" +
            " _ _ X O _ _ \n" +
            "_ _ X _ X _ _ \n" +
            " O O O O _ _ \n" +
            "  _ _ _ _ _ \n" +
            "   _ _ _ _ \n";
    IReversiModel model = new ReversiModelImpl(4);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-3, 1, 2));
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testToStringOfPassThenMoveRadius4() {
    String resultStr = "   _ _ _ _ \n" +
            "  _ _ O _ _ \n" +
            " _ _ O O _ _ \n" +
            "_ _ O _ X _ _ \n" +
            " O O O O _ _ \n" +
            "  _ _ _ _ _ \n" +
            "   _ _ _ _ \n";
    IReversiModel model = new ReversiModelImpl(4);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-3, 1, 2));
    model.pass();
    model.placeTile(new Position3D(1, -2, 1));
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testToStringOfPassThenMoveDoubleFlipRadius4() {
    String resultStr = "   _ _ _ _ \n" +
            "  _ _ _ _ _ \n" +
            " _ O O O _ _ \n" +
            "_ _ O _ X _ _ \n" +
            " O O O O _ _ \n" +
            "  _ _ _ _ _ \n" +
            "   _ _ _ _ \n";
    IReversiModel model = new ReversiModelImpl(4);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-3, 1, 2));
    model.pass();
    model.placeTile(new Position3D(-1, -1, 2));
    TextualView tv = new ReversiTextualView(model);
    Assert.assertEquals(resultStr, tv.toString());
  }

  @Test
  public void testReversiModelImplCopyConstructor() {
    IReversiModel oldModel = new ReversiModelImpl(4);
    TextualView oldTextView = new ReversiTextualView(oldModel);
    oldModel.placeTile(new Position3D(-2, 1, 1));
    IReversiModel copyModel = new ReversiModelImpl(oldModel);
    TextualView newTextView = new ReversiTextualView(copyModel);
    Assert.assertEquals(oldTextView.toString(), newTextView.toString());
    copyModel.placeTile(new Position3D(-3, 1, 2));
    Assert.assertNotEquals(oldTextView.toString(), newTextView.toString());
  }
}
