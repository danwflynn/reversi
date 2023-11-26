package controller;

import model.IReversiModel;
import model.Player;
import model.Position3D;
import view.IGraphicalView;

public class ReversiControllerImpl implements ReversiController {
  private IReversiModel model;
  private Player player;
  private IGraphicalView view;

  public ReversiControllerImpl(IReversiModel model, Player player, IGraphicalView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.view.addObserver(this);
  }

  @Override
  public void pass() {

  }

  @Override
  public void placeTile(Position3D pos) {
    model.placeTile(pos);
  }
}
