package model;

import java.util.ArrayList;
import java.util.List;

import controller.ReversiController;

/**
 * Manages the board and the rules.
 * The board is a list of tiles (all tiles contain their own position).
 * The scores are kept and updated every time a move is made.
 * There is a counter to detect 2 passes in a row.
 */
public class ReversiModelImpl implements IReversiModel {
  private final List<Tile> board;
  private TileType turn;
  private final int radius;
  private int blackScore;
  private int whiteScore;
  private int passCounter;
  private ReversiController blackObserver;
  private ReversiController whiteObserver;
  /**
   * Constructor for the class.
   * @param radius amount of hexagons from the center (included) to the edge in a straight line
   * @throws IllegalArgumentException if the radius is too short
   */
  public ReversiModelImpl(int radius) throws IllegalArgumentException {
    if (radius < 2) {
      throw new IllegalArgumentException("Radius must be 2 or greater");
    }
    this.blackScore = 3;
    this.whiteScore = 3;
    this.passCounter = 0;
    this.radius = radius;
    this.board = new ArrayList<>();
    this.turn = TileType.BLACK;
    for (int q = -1 * radius + 1; q < radius; q++) {
      for (int r = -1 * radius + 1; r < radius; r++) {
        for (int s = -1 * radius + 1; s < radius; s++) {
          if (q + r + s == 0) {
            if (Math.abs(q) + Math.abs(r) + Math.abs(s) == 2) {
              if ((q == 0 && r == -1 && s == 1)
                      || (q == 1 && r == 0 && s == -1)
                      || (q == -1 && r == 1 && s == 0)) {
                this.board.add(new GameTile(new Position3D(q, r, s), TileType.BLACK));
              } else {
                this.board.add(new GameTile(new Position3D(q, r, s), TileType.WHITE));
              }
            } else {
              this.board.add(new GameTile(new Position3D(q, r, s), TileType.EMPTY));
            }
          }
        }
      }
    }
    // INVARIANT: The q, r, and s values of every position on the board add to 0
  }



  /**
   * Constructor to make a copy of the game.
   * @param rm model to copy
   */
  public ReversiModelImpl(IReversiModel rm) {
    this.board = rm.getCopyOfBoard();
    this.turn = rm.getTurn();
    this.radius = rm.getRadius();
    this.blackScore = rm.getBlackScore();
    this.whiteScore = rm.getWhiteScore();
    this.passCounter = rm.getPassesInARow();
  }

  /**
   * Gets the Tile on the board at the given position.
   * @param pos coordinates as a position type
   * @return The Tile at the given position.
   * @throws IllegalArgumentException If the position is not on the board.
   */
  @Override
  public Tile getTileAt(Position3D pos) throws IllegalArgumentException {
    for (Tile tile : this.board) {
      if (tile.getPos().equals(pos)) {
        return tile;
      }
    }
    throw new IllegalArgumentException("This position is not on the board");
  }

  /**
   * Starts the game, allowing players to make moves.
   */
  @Override
  public void startGame() {
    if (blackObserver != null) {
      this.blackObserver.alertTurn();
    }
  }

  /**
   * Gets a copy of the tile at the given position.
   * @param pos position of tile
   * @return A copy of the Tile at the given position.
   * @throws IllegalArgumentException If the position is not on the board.
   */
  @Override
  public Tile getCopyOfTileAt(Position3D pos) throws IllegalArgumentException {
    for (Tile tile : this.board) {
      if (tile.getPos().equals(pos)) {
        return new GameTile(tile);
      }
    }
    throw new IllegalArgumentException("This position is not on the board");
  }

  /**
   * Gets the radius of the board.
   * @return The radius as an int.
   */
  @Override
  public int getRadius() {
    return this.radius;
  }

  /**
   * Gets whose turn it currently is.
   * @return The player whose turn it is.
   */
  @Override
  public TileType getTurn() {
    return this.turn;
  }

  /**
   * Passes to the next player.
   * @throws IllegalStateException If the game is already over.
   */
  @Override
  public void pass() throws IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("The game is over");
    }
    if (this.turn == TileType.BLACK) {
      this.turn = TileType.WHITE;
      if (whiteObserver != null) {
        this.whiteObserver.alertTurn();
      }
    } else {
      this.turn = TileType.BLACK;
      if (blackObserver != null) {
        this.blackObserver.alertTurn();
      }
    }
    this.passCounter += 1;

  }

  /**
   * Finds exceptions to placing Tiles and throws appropriate exceptions.
   * @param pos The position that is being placed on.
   * @throws IllegalStateException If the game is over or if there is already a tile there
   * @throws IllegalArgumentException If the position is not on the board.
   */
  private void placeTileBasicExceptions(Position3D pos) throws IllegalStateException,
          IllegalArgumentException {
    if (this.isGameOver()) {
      throw new IllegalStateException("The game is over");
    }
    if (pos.getFarthestDirection() >= radius) {
      throw new IllegalArgumentException("Position out of bounds for game board");
    }
    if (!this.getTileAt(pos).getTileType().equals(TileType.EMPTY)) {
      throw new IllegalStateException("There already is a tile in this position");
    }
  }

  /**
   * Helps getAvailableBridges by filling bridges of tiles to be flipped with the given destination.
   * @param pos The position to place a tile on
   * @param bridges The list of bridges to fill with Tiles to flip
   * @param qInc How much (if at all) the q value increases in the direction of the bridge
   * @param rInc How much (if at all) the r value increases in the direction of the bridge
   * @param sInc How much (if at all) the s value increases in the direction of the bridge
   */
  private void getAvailableBridgesHelper(Position3D pos, List<List<Tile>> bridges,
                                         int qInc, int rInc, int sInc) {
    Position3D p = new Position3D(pos);
    List<Tile> bridge = new ArrayList<>();
    while (true) {
      p = new Position3D(p.getQ() + qInc, p.getR() + rInc, p.getS() + sInc);
      if (p.getFarthestDirection() >= radius
              || this.getTileAt(p).getTileType().equals(TileType.EMPTY)) {
        break;
      }
      if (getTileAt(p).getTileType().equals(this.turn)) {
        bridges.add(bridge);
        break;
      } else {
        bridge.add(this.getTileAt(p));
      }
    }
  }

  /**
   * Gets the available bridges that will be flipped if a tile is placed in the given position.
   * @param pos The destination position
   * @return The list of bridges containing tiles to be flipped.
   */
  private List<List<Tile>> getAvailableBridges(Position3D pos) {
    List<List<Tile>> bridges = new ArrayList<>();
    placeTileBasicExceptions(pos);

    getAvailableBridgesHelper(pos, bridges, 1, -1, 0);
    getAvailableBridgesHelper(pos, bridges, 1, 0, -1);
    getAvailableBridgesHelper(pos, bridges, 0, 1, -1);
    getAvailableBridgesHelper(pos, bridges, -1, 1, 0);
    getAvailableBridgesHelper(pos, bridges, -1, 0, 1);
    getAvailableBridgesHelper(pos, bridges, 0, -1, 1);

    return bridges;
  }

  /**
   * Flips the tiles of a bridge for tile capture.
   * @param bridge The bridge of Tiles to flip.
   */
  private void flipBridge(List<Tile> bridge) {
    for (Tile t : bridge) {
      if (!t.getTileType().equals(this.turn)) {
        t.setTileType(this.turn);
      }
    }
  }

  /**
   * Updates the score based on the number of white and black tiles on the board.
   */
  private void updateScore() {
    int b = 0;
    int w = 0;
    for (Tile t : this.board) {
      if (t.getTileType().equals(TileType.BLACK)) {
        b += 1;
      }
      if (t.getTileType().equals(TileType.WHITE)) {
        w += 1;
      }
    }
    this.blackScore = b;
    this.whiteScore = w;
  }

  /**
   * Places a tile at the position given, based on whose turn it is.
   * @param pos coordinates as position type
   * @throws IllegalStateException If the move does not capture any pieces,
   *                                or if the move is illegal.
   * @throws IllegalArgumentException If the position argument is not on the board.
   */
  @Override
  public void placeTile(Position3D pos) throws IllegalStateException, IllegalArgumentException {
    placeTileBasicExceptions(pos);
    boolean noBridges = true;
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      if (!l.isEmpty()) {
        noBridges = false;
        break;
      }
    }
    if (noBridges) {
      throw new IllegalStateException("No available bridges");
    }
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      this.flipBridge(l);
    }
    this.getTileAt(pos).setTileType(this.turn);
    this.updateScore();
    if (this.turn == TileType.BLACK) {
      this.turn = TileType.WHITE;
      if (whiteObserver != null) {
        this.whiteObserver.alertTurn();
      }
    } else {
      this.turn = TileType.BLACK;
      if (blackObserver != null) {
        this.blackObserver.alertTurn();
      }
    }
    this.passCounter = 0;
  }

  /**
   * Adds the observer for the controller for the White player.
   *
   * @param controller The controller observing this model.
   */
  @Override
  public void addWhiteObserver(ReversiController controller) {
    if (!(controller.getPlayerColor().equals(TileType.WHITE))) {
      throw new IllegalArgumentException("This player is not white.");
    }
    this.whiteObserver = controller;
  }

  /**
   * Adds the observer for the controller for the Black player.
   *
   * @param controller The controller observing this model.
   */
  @Override
  public void addBlackObserver(ReversiController controller) {
    if (!(controller.getPlayerColor().equals(TileType.BLACK))) {
      throw new IllegalArgumentException("This player is not black.");
    }
    this.blackObserver = controller;
  }

  /**
   * Gets the size of the board in tiles.
   * @return The size of the board
   */
  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  /**
   * Gets the score of the black player.
   * @return this model's blackScore
   */
  @Override
  public int getBlackScore() {
    return this.blackScore;
  }

  /**
   * Gets the score of the white player.
   * @return this model's whiteScore
   */
  @Override
  public int getWhiteScore() {
    return this.whiteScore;
  }

  /**
   * Determines if the game has ended.
   * @return 'true' is the game has ended, and false otherwise
   */
  @Override
  public boolean isGameOver() {
    return this.passCounter == 2;
  }

  /**
   * Returns how many times players have passed, in a row.
   * @return this model's passCounter
   */
  @Override
  public int getPassesInARow() {
    return this.passCounter;
  }

  /**
   * Returns a copy of this model's board.
   * @return this model's board field
   */
  @Override
  public List<Tile> getCopyOfBoard() {
    List<Tile> copyBoard = new ArrayList<>();
    for (Tile t : this.board) {
      copyBoard.add(new GameTile(t));
    }
    return copyBoard;
  }

  /**
   * Is the move at the given position legal for whoever turn it is.
   * @param pos position of potential move
   * @return true if legal
   */
  @Override
  public boolean isMoveLegal(Position3D pos) {
    if (this.isGameOver()) {
      return false;
    }
    if (!this.getTileAt(pos).getTileType().equals(TileType.EMPTY)) {
      return false;
    }
    boolean noBridges = true;
    for (List<Tile> l : this.getAvailableBridges(pos)) {
      if (!l.isEmpty()) {
        noBridges = false;
        break;
      }
    }
    if (noBridges) {
      return false;
    }
    boolean legality = false;
    for (Tile t : this.board) {
      if (t.getPos().equals(pos)) {
        legality = true;
      }
    }
    return legality;
  }

  /**
   * Does the current player have a legal move.
   *
   * @return true if current player has legal move
   */
  @Override
  public boolean hasLegalMove() {
    for (Tile t : this.board) {
      if (this.isMoveLegal(t.getPos())) {
        return true;
      }
    }
    return false;
  }
}
