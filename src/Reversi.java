import controller.ReversiController;
import controller.ReversiControllerImpl;
import model.*;
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
    IReversiModel model = new ReversiModelImpl(7);
    Player p1 = new AIPlayer(TileType.BLACK, model);
    IGraphicalView view = new ReversiGraphicalView(model);
    ReversiController controller = new ReversiControllerImpl(model, p1, view);
    view.setVisible(true);
  }
}