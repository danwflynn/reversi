import model.IReversiModel;
import model.Position3D;
import model.ReversiModelImpl;
import view.IGraphicalView;
import view.ReversiGraphicalView;

public final class Reversi {
  public static void main(String[] args) {
    IReversiModel model = new ReversiModelImpl(10);
//    model.placeTile(new Position3D(2, -1, -1));
//    model.placeTile(new Position3D(3, -2, -1));
//    model.placeTile(new Position3D(3, -1, -2));
//    model.placeTile(new Position3D(3, 0, -3));
//    model.placeTile(new Position3D(-1, 2, -1));
//    model.placeTile(new Position3D(-2, 3, -1));
//    model.placeTile(new Position3D(-1, -1, 2));
//    model.placeTile(new Position3D(-2, -1, 3));
//    model.placeTile(new Position3D(-1, 3, -2));
//    model.placeTile(new Position3D(0, 3, -3));
//    model.placeTile(new Position3D(1, -2, 1));
//    model.placeTile(new Position3D(1, -3, 2));
//    model.placeTile(new Position3D(2, -3, 1));
//    model.placeTile(new Position3D(3, -3, 0));
//    model.placeTile(new Position3D(1, 1, -2));
//    model.placeTile(new Position3D(1, 2, -3));
//    model.placeTile(new Position3D(2, 1, -3));
//    model.placeTile(new Position3D(-2, 1, 1));
//    model.placeTile(new Position3D(-1, -2, 3));
//    model.placeTile(new Position3D(0, -3, 3));
//    model.placeTile(new Position3D(-3, 1, 2));
//    model.placeTile(new Position3D(-3, 2, 1));
//    model.placeTile(new Position3D(-3, 3, 0));
//    model.pass();
//    model.placeTile(new Position3D(1, -4, 3));
//    model.placeTile(new Position3D(1, -5, 4));
//    model.pass();
//    model.pass();
    IGraphicalView view = new ReversiGraphicalView(model);
    view.setVisible(true);
  }
}