import model.IReversiModel;
import model.Position3D;
import model.ReversiModelImpl;
import model.TileType;
import org.junit.Assert;
import org.junit.Test;

public class ExampleTests {
  @Test
  public void TestStartGame() {
    // Radius of the game is 5
    int radius = 5;

    // Starting the game in the initial state with radius 5
    IReversiModel model = new ReversiModelImpl(radius);
    // See if radius is correct
    Assert.assertEquals(radius, model.getRadius());
    // See if the first ring has the right initial tiles
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(0, -1, 1)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(1, -1, 0)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(1, 0, -1)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(0, 1, -1)).getTileType());
    Assert.assertEquals(TileType.BLACK, model.getTileAt(new Position3D(-1, 1, 0)).getTileType());
    Assert.assertEquals(TileType.WHITE, model.getTileAt(new Position3D(-1, 0, 1)).getTileType());
  }
}
