package controller;

import model.IReversiModel;
import model.Player;
import model.Position3D;
import view.IGraphicalView;

/**
 * An implementation for the controller of a Reversi game, which allows a player to operate the
 * game.
 */
public class ReversiControllerImpl implements ReversiController {
  private IReversiModel model;
  private Player player;
  private IGraphicalView view;

  /**
   * Constructs a ReversiControllerImpl.
   * @param model The model to base the controller on
   * @param player The player using this controller
   * @param view The view that the player is able to see
   */
  public ReversiControllerImpl(IReversiModel model, Player player, IGraphicalView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.view.addObserver(this);
  }

  /**
   * Passes the current player's turn without placing a tile.
   */
  @Override
  public void pass() {

  }

  /**
   * Places a tile at the given Position3D.
   * @param pos The position to place at
   */
  @Override
  public void placeTile(Position3D pos) {
    model.placeTile(pos);
  }
}
