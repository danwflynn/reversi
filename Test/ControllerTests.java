import controller.ReversiController;
import controller.ReversiControllerImpl;
import model.*;
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
  private Player p1;
  private Player p2;
  private IGraphicalView view1;
  private IGraphicalView view2;
  private ReversiController controller1;
  private ReversiController controller2;

  /**
   * Sets up initial testing conditions for convenience.
   */
  @Before
  public void setup() {
    this.model = new ReversiModelImpl(7);
    this.p1 = new AIPlayer(TileType.BLACK, model);
    this.p2 = new AIPlayer(TileType.WHITE, model);
    this.view1 = new ReversiGraphicalView(model);
    this.view2 = new ReversiGraphicalView(model);
    this.controller1 = new ReversiControllerImpl(model, p1, view1);
    this.controller2 = new ReversiControllerImpl(model, p2, view2);
    this.view1.setVisible(true);
    this.view2.setVisible(true);
  }

  @Test
  public void testAIRunTheWholeGame() {
    this.model.startGame();
    Assert.assertEquals(47, model.getBlackScore());
    Assert.assertEquals(43, model.getWhiteScore());
  }


  @Test
  public void testControllerObservationalMethods() {
    Assert.assertEquals(TileType.BLACK, this.controller1.getPlayerColor());
    Assert.assertEquals(TileType.BLACK, this.controller1.getModelTurn());
    Assert.assertFalse(this.controller1.isHuman());
  }
}
