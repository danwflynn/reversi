import controller.ReversiController;
import controller.ReversiControllerImpl;
import model.IReversiModel;
import model.ReversiModelImpl;
import model.player.Player;
import model.player.HumanPlayer;
import model.tile.TileType;
import view.IGraphicalView;
import view.ReversiGraphicalView;

/**
 * The main class for running a game of Reversi.
 */
public final class Reversi {
  /**
   * Main function.
   * @param args why do we need to java doc this
   */
  public static void main(String[] args) {
    IReversiModel model = new ReversiModelImpl(8);
    Player p1 = new HumanPlayer(TileType.BLACK, model);
    Player p2 = new HumanPlayer(TileType.WHITE, model);
    IGraphicalView view1 = new ReversiGraphicalView(model);
    IGraphicalView view2 = new ReversiGraphicalView(model);
    ReversiController controller1 = new ReversiControllerImpl(model, p1, view1);
    ReversiController controller2 = new ReversiControllerImpl(model, p2, view2);
    view1.setVisible(true);
    view2.setVisible(true);
    model.startGame();
  }
}