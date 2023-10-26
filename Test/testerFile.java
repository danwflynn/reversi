import org.junit.Assert;
import org.junit.Test;
import rmodel.IReversiModel;
import rmodel.Position3D;
import rmodel.ReversiModelImpl;
import rmodel.Tile;
import rmodel.TileType;

public class testerFile {

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

  //put this in protected test in model.
//  @Test
//  public void testMakeReversiBoardHasCorrectNumberOfTiles() {
//    IReversiModel model = new ReversiModelImpl(4);
//    Assert.assertEquals(37, model.board.size());
//  }
}
