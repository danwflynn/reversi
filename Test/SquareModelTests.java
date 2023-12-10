import model.IReversiModel;
import model.SquareReversiModelImpl;
import model.position.Position3D;
import org.junit.Assert;
import org.junit.Test;
import view.SquareTextualView;
import view.TextualView;

/**
 * Tests for square model.
 */
public class SquareModelTests {
  @Test
  public void testSmallestBoard() {
    IReversiModel sm = new SquareReversiModelImpl(2);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    TextualView tv = new SquareTextualView(sm);
    Assert.assertEquals("X O \n" +
            "O X \n", tv.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOddDiameterThrowsException() {
    IReversiModel sm = new SquareReversiModelImpl(3);
  }

  @Test
  public void testSquareMakeHorizontalMove() {
    IReversiModel sm = new SquareReversiModelImpl(4);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(0, 2, -2));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(1, sm.getWhiteScore());
    TextualView tv = new SquareTextualView(sm);
    Assert.assertEquals("_ _ _ _ \n" +
            "_ X O _ \n" +
            "X X X _ \n" +
            "_ _ _ _ \n", tv.toString());
  }

  @Test
  public void testSquareMakeDiagonalMove() {
    IReversiModel sm = new SquareReversiModelImpl(4);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(0, 2, -2));
    sm.placeTile(new Position3D(0, 3, -3));
    Assert.assertEquals(3, sm.getBlackScore());
    Assert.assertEquals(3, sm.getWhiteScore());
    TextualView tv = new SquareTextualView(sm);
    Assert.assertEquals("_ _ _ _ \n" +
            "_ X O _ \n" +
            "X O X _ \n" +
            "O _ _ _ \n", tv.toString());
  }

  @Test
  public void testSquareMakeVerticalMove() {
    IReversiModel sm = new SquareReversiModelImpl(4);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(2, 0, -2));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(1, sm.getWhiteScore());
    TextualView tv = new SquareTextualView(sm);
    Assert.assertEquals("_ _ X _ \n" +
            "_ X X _ \n" +
            "_ O X _ \n" +
            "_ _ _ _ \n", tv.toString());
  }

  @Test (expected = IllegalStateException.class)
  public void testNoBrides() {
    IReversiModel sm = new SquareReversiModelImpl(20);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(7, 0, -7));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(1, sm.getWhiteScore());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBounds() {
    IReversiModel sm = new SquareReversiModelImpl(4);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(-1, 0, 1));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(1, sm.getWhiteScore());
  }

  @Test
  public void testFlipMultipleDirections() {
    IReversiModel sm = new SquareReversiModelImpl(6);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(3, 1, -4));
    sm.placeTile(new Position3D(4, 1, -5));
    sm.placeTile(new Position3D(1, 3, -4));
    sm.placeTile(new Position3D(3, 4, -7));
    sm.placeTile(new Position3D(4, 3, -7));
    sm.placeTile(new Position3D(1, 2, -3));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(6, sm.getWhiteScore());
  }

  @Test
  public void testFlipOverMultipleStraight() {
    IReversiModel sm = new SquareReversiModelImpl(6);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(2, 4, -6));
    sm.placeTile(new Position3D(1, 2, -3));
    sm.pass();
    sm.placeTile(new Position3D(2, 5, -7));
  }
}
