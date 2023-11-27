package model;

public class HumanPlayer implements IHumanPlayer {

  protected final TileType playerColor;
  protected final IReversiModel model;

  /**
   * Constructs an AIPlayer.
   * @param playerColor The color of the player
   * @param model The model to use as reference for the player
   */
  public HumanPlayer(TileType playerColor, IReversiModel model) {
    if (playerColor.equals(TileType.EMPTY)) {
      throw new IllegalArgumentException("Player cannot be represented by an empty piece.");
    }
    this.playerColor = playerColor;
    this.model = model;
  }
  /**
   * Gets the tile type of the given player.
   *
   * @return black or white tile type
   */
  @Override
  public TileType getPlayerColor() {
    return this.playerColor;
  }

  /**
   * How many tiles the player has on the board of their color.
   *
   * @return player's score
   */
  @Override
  public int getScore() {
    if (this.playerColor.equals(TileType.BLACK)) {
      return this.model.getBlackScore();
    } else {
      return this.model.getWhiteScore();
    }
  }

  /**
   * Passes the player's turn by calling pass() on the model.
   *
   * @throws IllegalStateException if it isn't the player's turn
   */
  @Override
  public void pass() throws IllegalStateException {
    this.model.pass();
  }

  /**
   * Places the player's tile at the given position by calling placeTile(pos) on the model.
   *
   * @param pos position to make the move
   * @throws IllegalStateException    if it isn't the player's turn or if the move is illegal
   * @throws IllegalArgumentException if the position is out of bounds
   */
  @Override
  public void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException {
    this.model.placeTile(pos);
  }

  /**
   * Takes a turn in a game of Reversi.
   */
  @Override
  public void turnAction() {
    //move
  }
}
