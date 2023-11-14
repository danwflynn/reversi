import model.IReversiModel;
import model.Position3D;
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
    IReversiModel model = new ReversiModelImpl(4);
    model.placeTile(new Position3D(1, -2, 1));
    model.placeTile(new Position3D(2, -3, 1));
    model.pass();
    model.placeTile(new Position3D(1, 1, -2));
    model.placeTile(new Position3D(-1, -1, 2));
    model.pass();
    model.placeTile(new Position3D(2, 1, -3));
    IGraphicalView view = new ReversiGraphicalView(model);
    view.setVisible(true);
  }
}