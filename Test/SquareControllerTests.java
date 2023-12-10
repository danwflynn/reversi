import controller.ReversiController;
import controller.ReversiControllerImpl;
import model.SquareReversiModelImpl;
import model.tile.TileType;
import model.player.AIPlayer;
import model.IReversiModel;
import model.player.Player;
import model.player.HumanPlayer;
import model.position.Position3D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import view.IGraphicalView;
import view.SquareReversiView;

/**
 * A class for tests involving the controller in the game of Reversi.
 */
public class SquareControllerTests {
  private IReversiModel model;
  private IReversiModel hModel;
  private ReversiController hController1;
  private ReversiController hController2;

  /**
   * Sets up initial testing conditions for convenience.
   */
  @Before
  public void setup() {
    this.model = new SquareReversiModelImpl(8);
    Player p1 = new AIPlayer(TileType.BLACK, model);
    Player p2 = new AIPlayer(TileType.WHITE, model);
    IGraphicalView view1 = new SquareReversiView(model);
    IGraphicalView view2 = new SquareReversiView(model);
    ReversiController controller1 = new ReversiControllerImpl(model, p1, view1);
    ReversiController controller2 = new ReversiControllerImpl(model, p2, view2);
    view1.setVisible(true);
    view2.setVisible(true);
    this.hModel = new SquareReversiModelImpl(8);
    Player hp1 = new HumanPlayer(TileType.BLACK, hModel);
    Player hp2 = new HumanPlayer(TileType.WHITE, hModel);
    IGraphicalView hView1 = new SquareReversiView(hModel);
    IGraphicalView hView2 = new SquareReversiView(hModel);
    this.hController1 = new ReversiControllerImpl(hModel, hp1, hView1);
    this.hController2 = new ReversiControllerImpl(hModel, hp2, hView2);
    hView1.setVisible(true);
    hView2.setVisible(true);
  }

  @Test
  public void testAIRunTheWholeGame() {
    this.model.startGame();
    Assert.assertEquals(32, model.getBlackScore());
    Assert.assertEquals(32, model.getWhiteScore());
  }

  @Test
  public void testHumanPlayerCanMakeMovesAndPass() {
    this.hModel.startGame();
    this.hController1.placeTile(new Position3D(4, 2, -6));
    Assert.assertEquals(4, hModel.getBlackScore());
    Assert.assertEquals(1, hModel.getWhiteScore());
    Assert.assertEquals(TileType.WHITE, hModel.getTurn());
    this.hController2.placeTile(new Position3D(new Position3D(5, 4, -9)));
    Assert.assertEquals(3, hModel.getBlackScore());
    Assert.assertEquals(3, hModel.getWhiteScore());
    Assert.assertEquals(TileType.BLACK, hModel.getTurn());
    this.hController1.pass();
    this.hController2.pass();
    Assert.assertTrue(hModel.isGameOver());
  }
}
