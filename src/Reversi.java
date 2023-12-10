import controller.ReversiController;
import controller.ReversiControllerImpl;
import model.IReversiModel;
import model.ReversiModelImpl;
import model.SquareReversiModelImpl;
import model.player.AIPlayer;
import model.player.Player;
import model.player.HumanPlayer;
import model.tile.TileType;
import view.IGraphicalView;
import view.ReversiGraphicalView;
import view.SquareReversiView;

/**
 * The main class for running a game of Reversi.
 */
public final class Reversi {
  /**
   * Main function.
   * @param args why do we need to java doc this
   */
  public static void main(String[] args) {
    if (args.length != 4) {
      throw new IllegalArgumentException("Must have 4 args: hex/square human/ai human/ai " +
              "[int that's >= 2]");
    }

    IReversiModel model;
    Player p1;
    Player p2;
    IGraphicalView view1;
    IGraphicalView view2;

    if (args[0].equals("hex")) {
      model = new ReversiModelImpl(Integer.parseInt(args[3]));
    } else if (args[0].equals("square")) {
      model = new SquareReversiModelImpl(Integer.parseInt(args[3]));
    } else {
      throw new IllegalArgumentException("First argument must be \"hex\" or \"square\"");
    }

    if (args[1].equals("human")) {
      p1 = new HumanPlayer(TileType.BLACK, model);
    } else if (args[1].equals("ai")) {
      p1 = new AIPlayer(TileType.BLACK, model);
    } else {
      throw new IllegalArgumentException("Second argument must be \"human\" or \"ai\"");
    }

    if (args[2].equals("human")) {
      p2 = new HumanPlayer(TileType.WHITE, model);
    } else if (args[2].equals("ai")) {
      p2 = new AIPlayer(TileType.WHITE, model);
    } else {
      throw new IllegalArgumentException("Third argument must be \"human\" or \"ai\"");
    }

    if (args[0].equals("hex")) {
      view1 = new ReversiGraphicalView(model);
      view2 = new ReversiGraphicalView(model);
    } else {
      view1 = new SquareReversiView(model);
      view2 = new SquareReversiView(model);
    }

    ReversiController controller1 = new ReversiControllerImpl(model, p1, view1);
    ReversiController controller2 = new ReversiControllerImpl(model, p2, view2);
    view1.setVisible(true);
    view2.setVisible(true);
    model.startGame();
  }
}