package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the board and the rules.
 */
public class ReversiModelImpl implements IReversiModel {
  private final List<Tile> board;
  private TileType turn;
  private final int radius;
  private int blackScore;
  private int whiteScore;
  private int passCounter;

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
    } else {
      this.turn = TileType.BLACK;
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

  private void flipBridge(List<Tile> bridge) {
    for (Tile t : bridge) {
      if (!t.getTileType().equals(this.turn)) {
        t.setTileType(this.turn);
      }
    }
  }

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
    } else {
      this.turn = TileType.BLACK;
    }
    this.passCounter = 0;
  }

  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public int getBlackScore() {
    return this.blackScore;
  }

  @Override
  public int getWhiteScore() {
    return this.whiteScore;
  }

  @Override
  public boolean isGameOver() {
    return this.passCounter == 2;
  }

  @Override
  public int getPassesInARow() {
    return this.passCounter;
  }

  @Override
  public List<Tile> getCopyOfBoard() {
    List<Tile> copyBoard = new ArrayList<>();
    for (Tile t : this.board) {
      copyBoard.add(new GameTile(t));
    }
    return copyBoard;
  }

  /**
   * Is the move at the given position legal for whoever turn it is?
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
   * Does the current player have a legal move?
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
