package model;

import java.util.List;

public interface ReadonlyIReversiModel {
  /**
   * Gets a copy of the tile at a given position on the board.
   * @param pos position of tile
   * @return copy of tile at the position
   * @throws IllegalArgumentException if provided position is out of the bounds of the board
   */
  Tile getCopyOfTileAt(Position3D pos) throws IllegalArgumentException;

  /**
   * Get the amount of hexagons starting from the center making a straight line to the edge.
   * @return radius
   */
  int getRadius();

  /**
   * Get whose turn.
   * @return tile type of turn
   */
  TileType getTurn();

  /**
   * Get the amount of hexagons on the board.
   * @return amount of tiles
   */
  int getBoardSize();

  /**
   * Gets how many black tiles on the board.
   * @return black score
   */
  int getBlackScore();

  /**
   * Gets how many white tiles on the board.
   * @return white score
   */
  int getWhiteScore();

  /**
   * Check if the game is over.
   * @return true if the game is over
   */
  boolean isGameOver();

  /**
   * Get how many passes were made in a row at the given moment.
   * @return passes in a row
   */
  int getPassesInARow();

  /**
   * Get a copy of the board, so you can do stuff with it without mutating the original game.
   * @return copy of board
   */
  List<Tile> getCopyOfBoard();


}
