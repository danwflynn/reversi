import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import javax.swing.text.Position;

import rmodel.IReversiModel;
import rmodel.Position3D;
import rmodel.ReversiModelImpl;
import rmodel.Tile;
import rmodel.TileType;

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
    Tile tile = new Tile(pos1, TileType.EMPTY);
    tile.setTileType(TileType.WHITE);
    Assert.assertEquals(TileType.WHITE, tile.getTileType());
  }

  @Test
  public void testSetTileDoesNotWorkIfTileAlreadySet() {
    Position3D pos1 = new Position3D(0, 0, 0);
    Tile tile = new Tile(pos1, TileType.WHITE);
    Assert.assertThrows(IllegalStateException.class, () -> {
      tile.setTileType(TileType.EMPTY);
    });
  }

  @Test
  public void testInitialStateOfGameBlackUpLeft() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(0, -1, 1);
    Assert.assertEquals(TileType.BLACK, model.getTileTypeAt(pos1));
  }

  @Test
  public void testInitialStateOfGameBlackBottomLeft() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(-1, 1, 0);
    Assert.assertEquals(TileType.BLACK, model.getTileTypeAt(pos1));
  }

  @Test
  public void testInitialStateOfGameBlackRight() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(1, 0, -1);
    Assert.assertEquals(TileType.BLACK, model.getTileTypeAt(pos1));
  }

  @Test
  public void testInitialStateOfGameWhiteLeft() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(-1, 0 , 1);
    Assert.assertEquals(TileType.WHITE, model.getTileTypeAt(pos1));
  }

  @Test
  public void testInitialStateOfGameWhiteUpRight() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(1, -1, 0);
    Assert.assertEquals(TileType.WHITE, model.getTileTypeAt(pos1));
  }

  @Test
  public void testInitialStateOfGameWhiteBottomRight() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(0, 1, -1);
    Assert.assertEquals(TileType.WHITE, model.getTileTypeAt(pos1));
  }

  @Test
  public void testTileGetPosition() {
    Tile tile = new Tile(new Position3D(1, 1,  -2), TileType.BLACK);
    Assert.assertEquals(new Position3D(1, 1, -2), tile.getPos());
  }

  @Test
  public void testGetTileAtTooBigThrowsException() {
    IReversiModel model = new ReversiModelImpl(3);
    Position3D pos1 = new Position3D(5, -4, -1);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.getTileTypeAt(pos1);
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
}
