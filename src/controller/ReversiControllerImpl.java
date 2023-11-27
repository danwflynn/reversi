package controller;

import model.IReversiModel;
import model.Player;
import model.Position3D;
import model.TileType;
import view.IGraphicalView;

/**
 * An implementation for the controller of a Reversi game, which allows a player to operate the
 * game.
 */
public class ReversiControllerImpl implements ReversiController {
  private final IReversiModel model;
  private final Player player;
  private final IGraphicalView view;

  /**
   * Constructs a ReversiControllerImpl.
   * @param model The model to base the controller on
   * @param player The player using this controller
   * @param view The view that the player is able to see
   */
  public ReversiControllerImpl(IReversiModel model, Player player, IGraphicalView view) {
    this.model = model;
    this.player = player;
    this.player.addObserver(this);
    if (player.getPlayerColor().equals(TileType.BLACK)) {
      this.model.addBlackObserver(this);
    } else {
      this.model.addWhiteObserver(this);
    }
    this.view = view;
    this.view.addObserver(this);

  }

  /**
   * Passes the current player's turn without placing a tile.
   */
  @Override
  public void pass() {
    model.pass();
    view.removeAllButtons();
    this.view.unhighlightAllButtons();
  }

  /**
   * Places a tile at the given Position3D.
   * @param pos The position to place at
   */
  @Override
  public void placeTile(Position3D pos) {
    this.view.unhighlightAllButtons();
    model.placeTile(pos);
    view.removeAllButtons();
  }

  /**
   * Get the player color of this controller.
   *
   * @return The color of the player.
   */
  @Override
  public TileType getPlayerColor() {
    return this.player.getPlayerColor();
  }

  /**
   * Gets whose turn it is.
   *
   * @return tile type of turn
   */
  @Override
  public TileType getModelTurn() {
    return this.model.getTurn();
  }

  /**
   * Reacts to the model, which gives this controller the turn in play.
   */
  @Override
  public void alertTurn() {
    this.view.enableAllButtons();
    this.view.unhighlightAllButtons();
    this.player.turnAction();
  }

  /**
   * Makes the view display the message telling the player it's their turn.
   */
  @Override
  public void sendTurnMessageToView() {
    this.view.addTurnMessage();
  }
}
