import model.IReversiModel;
import model.ReversiModelImpl;
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
    IReversiModel model = new ReversiModelImpl(15);
    IGraphicalView view = new ReversiGraphicalView(model);
    view.setVisible(true);
  }
}