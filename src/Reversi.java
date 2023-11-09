import model.*;
import view.IGraphicalView;
import view.ReversiGraphicalView;

public final class Reversi {
  public static void main(String[] args) {
    IReversiModel model = new ReversiModelImpl(2);
    IGraphicalView view = new ReversiGraphicalView(model);
    view.setVisible(true);
  }
}