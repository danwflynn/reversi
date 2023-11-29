import controller.ReversiController;
import controller.ReversiControllerImpl;
import model.TileType;
import model.AIPlayer;
import model.IReversiModel;
import model.Player;
import model.ReversiModelImpl;
import model.HumanPlayer;
import model.Position3D;
import model.AdvancedAIPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import view.IGraphicalView;
import view.ReversiGraphicalView;

/**
 * A class for tests involving the controller in the game of Reversi.
 */
public class ControllerTests {
  private IReversiModel model;
  private IReversiModel hModel;
  private ReversiController hController1;
  private ReversiController hController2;

  /**
   * Sets up initial testing conditions for convenience.
   */
  @Before
  public void setup() {
    this.model = new ReversiModelImpl(7);
    Player p1 = new AIPlayer(TileType.BLACK, model);
    Player p2 = new AIPlayer(TileType.WHITE, model);
    IGraphicalView view1 = new ReversiGraphicalView(model);
    IGraphicalView view2 = new ReversiGraphicalView(model);
    ReversiController controller1 = new ReversiControllerImpl(model, p1, view1);
    ReversiController controller2 = new ReversiControllerImpl(model, p2, view2);
    view1.setVisible(true);
    view2.setVisible(true);
    this.hModel = new ReversiModelImpl(7);
    Player hp1 = new HumanPlayer(TileType.BLACK, hModel);
    Player hp2 = new HumanPlayer(TileType.WHITE, hModel);
    IGraphicalView hView1 = new ReversiGraphicalView(hModel);
    IGraphicalView hView2 = new ReversiGraphicalView(hModel);
    this.hController1 = new ReversiControllerImpl(hModel, hp1, hView1);
    this.hController2 = new ReversiControllerImpl(hModel, hp2, hView2);
    hView1.setVisible(true);
    hView2.setVisible(true);
  }

  @Test
  public void testAIRunTheWholeGame() {
    this.model.startGame();
    Assert.assertEquals(47, model.getBlackScore());
    Assert.assertEquals(43, model.getWhiteScore());
  }

  @Test
  public void testAdvancedAIRunTheWholeGame() {
    IReversiModel advModel = new ReversiModelImpl(7);
    Player ap1 = new AdvancedAIPlayer(TileType.BLACK, advModel);
    Player ap2 = new AdvancedAIPlayer(TileType.WHITE, advModel);
    IGraphicalView aView1 = new ReversiGraphicalView(advModel);
    IGraphicalView aView2 = new ReversiGraphicalView(advModel);
    ReversiController aController1 = new ReversiControllerImpl(advModel, ap1, aView1);
    ReversiController aController2 = new ReversiControllerImpl(advModel, ap2, aView2);
    aView1.setVisible(true);
    aView2.setVisible(true);
    advModel.startGame();
    Assert.assertEquals(45, advModel.getBlackScore());
    Assert.assertEquals(45, advModel.getWhiteScore());
  }

  @Test
  public void testHumanPlayerCanMakeMovesAndPass() {
    this.hModel.startGame();
    this.hController1.placeTile(new Position3D(1, -2, 1));
    Assert.assertEquals(5, hModel.getBlackScore());
    Assert.assertEquals(2, hModel.getWhiteScore());
    Assert.assertEquals(TileType.WHITE, hModel.getTurn());
    this.hController2.placeTile(new Position3D(new Position3D(-1, 2, -1)));
    Assert.assertEquals(4, hModel.getBlackScore());
    Assert.assertEquals(4, hModel.getWhiteScore());
    Assert.assertEquals(TileType.BLACK, hModel.getTurn());
    this.hController1.pass();
    this.hController2.pass();
    Assert.assertTrue(hModel.isGameOver());
  }

  @Test
  public void testHumanVsAI() {
    IReversiModel ahModel = new ReversiModelImpl(5);
    Player human = new HumanPlayer(TileType.BLACK, ahModel);
    Player ai = new AIPlayer(TileType.WHITE, ahModel);
    IGraphicalView hv = new ReversiGraphicalView(ahModel);
    IGraphicalView av = new ReversiGraphicalView(ahModel);
    ReversiController hc = new ReversiControllerImpl(ahModel, human, hv);
    ReversiController ac = new ReversiControllerImpl(ahModel, ai, av);
    hv.makeVisible();
    av.makeVisible();
    ahModel.startGame();
    hc.placeTile(new Position3D(1, -2, 1));
    Assert.assertEquals(TileType.BLACK, ahModel.getTurn());
    Assert.assertEquals(3, ahModel.getBlackScore());
    Assert.assertEquals(5, ahModel.getWhiteScore());
    hc.pass();
    Assert.assertEquals(TileType.BLACK, ahModel.getTurn());
    Assert.assertEquals(1, ahModel.getBlackScore());
    Assert.assertEquals(8, ahModel.getWhiteScore());
  }

  @Test
  public void testAIVsHuman() {
    IReversiModel ahModel = new ReversiModelImpl(5);
    Player ai = new AIPlayer(TileType.BLACK, ahModel);
    Player human = new HumanPlayer(TileType.WHITE, ahModel);
    IGraphicalView hv = new ReversiGraphicalView(ahModel);
    IGraphicalView av = new ReversiGraphicalView(ahModel);
    ReversiController hc = new ReversiControllerImpl(ahModel, human, hv);
    ReversiController ac = new ReversiControllerImpl(ahModel, ai, av);
    hv.makeVisible();
    av.makeVisible();
    ahModel.startGame();
    Assert.assertEquals(TileType.WHITE, ahModel.getTurn());
    Assert.assertEquals(5, ahModel.getBlackScore());
    Assert.assertEquals(2, ahModel.getWhiteScore());
    hc.placeTile(new Position3D(-1, 2, -1));
    Assert.assertEquals(7, ahModel.getBlackScore());
    Assert.assertEquals(2, ahModel.getWhiteScore());
    hc.pass();
    Assert.assertEquals(TileType.WHITE, ahModel.getTurn());
    Assert.assertEquals(10, ahModel.getBlackScore());
    Assert.assertEquals(0, ahModel.getWhiteScore());
    hc.pass();
    Assert.assertTrue(ahModel.isGameOver());
  }
}
