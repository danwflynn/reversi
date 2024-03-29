package model.player;

import model.IReversiModel;
import model.SquareReversiModelImpl;
import model.position.Position3D;
import model.ReversiModelImpl;
import model.tile.TileType;

import java.util.List;

/**
 * A class representing an AI Player.
 * This is the basic version that makes the highest scoring move every time.
 */
public class AIPlayer extends HumanPlayer implements Player {


  /**
   * Constructs an AIPlayer.
   * @param playerColor The color of the player
   * @param model       The model to use as reference for the player
   */
  public AIPlayer(TileType playerColor, IReversiModel model) {
    super(playerColor, model);
  }

  /**
   * Gets the position that increases the player's score the most out of provided possible moves.
   * Tie-break is made by minimizing distance from top left corner.
   *
   * @param possibleMoves moves to choose from
   * @return position for move that maximizes score increase
   * @throws IllegalStateException if it isn't the player's turn
   * @throws IllegalStateException if there are no legal moves
   */
  protected Position3D getHighestScoringMove(List<Position3D> possibleMoves)
          throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    if (possibleMoves.isEmpty()) {
      throw new IllegalStateException("No available moves.");
    }
    // Slight change here to make top left position 0, 0 for square
    Position3D topLeftPos = new Position3D(0, 0, 0);
    if (!(this.model instanceof SquareReversiModelImpl)) {
      topLeftPos = this.model.getCopyOfTileAt(new Position3D(0,
              -1 * this.model.getRadius() + 1, this.model.getRadius() - 1)).getPos();
    }
    Position3D currentHighestScoringMove = null;
    int currentMaxScoreIncrease = 0;
    int oldScore;
    if (this.playerColor.equals(TileType.BLACK)) {
      oldScore = this.model.getBlackScore();
    } else {
      oldScore = this.model.getWhiteScore();
    }
    for (Position3D pos : possibleMoves) {
      int newScore;
      IReversiModel tempModel = new ReversiModelImpl(this.model);
      // Make temp model a square version if necessary
      if (this.model instanceof SquareReversiModelImpl) {
        tempModel = new SquareReversiModelImpl(this.model);
      }
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

  /**
   * Gets the optimal move for the player based on the rules of the AI.
   * @return position for optimal move
   * @throws IllegalStateException if it isn't the player's turn
   * @throws IllegalStateException if there are no legal moves
   */
  @Override
  public Position3D getOptimalMove() throws IllegalStateException {
    return this.getHighestScoringMove(this.getAvailableMoves());
  }

  /**
   * Takes a turn in a game of Reversi.
   */
  @Override
  public void turnAction() {
    if (!this.getAvailableMoves().isEmpty()) {
      this.observer.placeTile(this.getOptimalMove());
    } else {
      this.observer.pass();
    }
  }
}
