package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing an AI Player.
 */
public class AIPlayer implements Player {

  private final TileType playerColor;
  private final IReversiModel model;

  /**
   * Constructs an AIPlayer.
   * @param playerColor The color of the player
   * @param model The model to use as reference for the player
   */
  public AIPlayer(TileType playerColor, IReversiModel model) {
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
   * Gets a list of all positions where a move can be made by the player on a given turn.
   *
   * @return list of possible positions to make move
   * @throws IllegalStateException if it isn't the player's turn
   */
  @Override
  public List<Position3D> getAvailableMoves() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    List<Position3D> availableMoves = new ArrayList<>();
    for (Tile t : this.model.getCopyOfBoard()) {
      if (this.model.isMoveLegal(t.getPos())) {
        availableMoves.add(t.getPos());
      }
    }
    return availableMoves;
  }

  /**
   * Gets the position that increases the player's score the most out of all possible moves.
   *
   * @return position for move that maximizes score increase
   * @throws IllegalStateException if it isn't the player's turn
   * @throws IllegalStateException if there are no legal moves
   */
  @Override
  public Position3D getHighestScoringMove() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    if (this.getAvailableMoves().isEmpty()) {
      throw new IllegalStateException("No available moves.");
    }
    Position3D topLeftPos = this.model.getCopyOfTileAt(new Position3D(0,
            -1 * this.model.getRadius() + 1, this.model.getRadius() - 1)).getPos();
    Position3D currentHighestScoringMove = null;
    int currentMaxScoreIncrease = 0;
    int oldScore;
    if (this.playerColor.equals(TileType.BLACK)) {
      oldScore = this.model.getBlackScore();
    } else {
      oldScore = this.model.getWhiteScore();
    }
    for (Position3D pos : this.getAvailableMoves()) {
      int newScore;
      IReversiModel tempModel = new ReversiModelImpl(this.model);
      tempModel.placeTile(pos);
      if (this.playerColor.equals(TileType.BLACK)) {
        newScore = tempModel.getBlackScore();
      } else {
        newScore = tempModel.getWhiteScore();
      }
      if (newScore - oldScore > currentMaxScoreIncrease) {
        currentHighestScoringMove = pos;
        currentMaxScoreIncrease = newScore - oldScore;
      } else if (newScore - oldScore == currentMaxScoreIncrease) {
        assert currentHighestScoringMove != null;
        if (pos.getDistanceFrom(topLeftPos)
                < currentHighestScoringMove.getDistanceFrom(topLeftPos)) {
          currentHighestScoringMove = pos;
        } else if (pos.getDistanceFrom(topLeftPos)
                == currentHighestScoringMove.getDistanceFrom(topLeftPos)) {
          if (pos.getR() < currentHighestScoringMove.getR()) {
            currentHighestScoringMove = pos;
          }
        }
      }
    }
    return currentHighestScoringMove;
  }
}
