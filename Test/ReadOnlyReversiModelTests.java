import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import model.tile.GameTile;
import model.IReversiModel;
import model.position.Position3D;
import model.ReadonlyIReversiModel;
import model.ReadonlyReversiModelImpl;
import model.ReversiModelImpl;
import model.tile.Tile;
import model.tile.TileType;

/**
 * A class for tests involving the ReadOnly class, or code made after its creation.
 */
public class ReadOnlyReversiModelTests {
  @Test
  public void testReadOnlyCannotMutate() {
    Position3D pos = new Position3D(0, 0, 0);
    ReadonlyIReversiModel model = new ReadonlyReversiModelImpl(4);
    Tile tile = model.getCopyOfTileAt(pos);
    tile.setTileType(TileType.BLACK);
    Assert.assertEquals(TileType.EMPTY, model.getCopyOfTileAt(pos).getTileType());
  }

  @Test
  public void testReadOnlyObservesSameAsNormalModelForAllMethods() {
    IReversiModel model = new ReversiModelImpl(5);
    ReadonlyIReversiModel observer = new ReadonlyReversiModelImpl(5);

    Assert.assertEquals(model.getCopyOfTileAt(new Position3D(0, 0, 0)),
                          observer.getCopyOfTileAt(new Position3D(0, 0, 0)));

    Assert.assertEquals(model.isGameOver(), observer.isGameOver());
    Assert.assertEquals(model.getWhiteScore(), observer.getWhiteScore());
    Assert.assertEquals(model.getBlackScore(), observer.getBlackScore());
    Assert.assertEquals(model.getRadius(), observer.getRadius());
    Assert.assertEquals(model.getBoardSize(), observer.getBoardSize());
    List<Tile> modelList = model.getCopyOfBoard();
    List<Tile> observerList = observer.getCopyOfBoard();
    for (int tileNum = 0; tileNum < modelList.size(); tileNum++) {
      Assert.assertEquals(modelList.get(tileNum), observerList.get(tileNum));
    }
  }

  @Test
  public void testEqualsGameTileTrue() {
    GameTile tile1 = new GameTile(new Position3D(0, 0, 0), TileType.EMPTY);
    GameTile tile2 = new GameTile(new Position3D(0, 0, 0), TileType.EMPTY);
    Assert.assertEquals(tile1, tile2);
  }

  @Test
  public void testEqualsGameTileFalseDifferentPosition() {
    GameTile tile1 = new GameTile(new Position3D(0, 0, 0), TileType.EMPTY);
    GameTile tile2 = new GameTile(new Position3D(1, -1, 0), TileType.EMPTY);
    Assert.assertNotEquals(tile1, tile2);
  }

  @Test
  public void testEqualsGameTileFalseDifferentTileType() {
    GameTile tile1 = new GameTile(new Position3D(0, 0, 0), TileType.EMPTY);
    GameTile tile2 = new GameTile(new Position3D(0, 0, 0), TileType.WHITE);
    Assert.assertNotEquals(tile1, tile2);
  }

  @Test
  public void testEqualsGameTileFalseDifferentObjectType() {
    GameTile tile1 = new GameTile(new Position3D(0, 0, 0), TileType.EMPTY);
    GameTile tile2 = new GameTile(new Position3D(0, 0, 0), TileType.WHITE);
    Assert.assertNotEquals(tile1, tile2);
  }

  @Test
  public void testPosition3DToString() {
    Position3D pos = new Position3D(-3, -2, 5);
    Assert.assertEquals("-3, -2, 5", pos.toString());
  }

  @Test
  public void testGetDistanceFromTwoBelow() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(-2, -1, 3);
    Assert.assertEquals(2, pos1.getDistanceFrom(pos2));
  }

  @Test
  public void testGetDistanceFromTwoRight() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(2, -3, 1);
    Assert.assertEquals(2, pos1.getDistanceFrom(pos2));
  }

  @Test
  public void testGetDistanceFromOneRight() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(1, -3, 2);
    Assert.assertEquals(1, pos1.getDistanceFrom(pos2));
  }

  @Test
  public void testGetDistanceFromSamePosition() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(0, -3, 3);
    Assert.assertEquals(0, pos1.getDistanceFrom(pos2));
  }
}
