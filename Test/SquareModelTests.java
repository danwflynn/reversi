import model.IReversiModel;
import model.SquareReversiModelImpl;
import model.position.Position3D;
import org.junit.Assert;
import org.junit.Test;

public class SquareModelTests {
  @Test
  public void testSmallestBoard() {
    IReversiModel sm = new SquareReversiModelImpl(2);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
  }

  @Test
  public void testSquareMakeHorizontalMove() {
    IReversiModel sm = new SquareReversiModelImpl(4);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(0, 2, -2));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(1, sm.getWhiteScore());
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
  }

  @Test
  public void testSquareMakeVerticalMove() {
    IReversiModel sm = new SquareReversiModelImpl(4);
    Assert.assertEquals(2, sm.getBlackScore());
    Assert.assertEquals(2, sm.getWhiteScore());
    sm.placeTile(new Position3D(2, 0, -2));
    Assert.assertEquals(4, sm.getBlackScore());
    Assert.assertEquals(1, sm.getWhiteScore());
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
}
