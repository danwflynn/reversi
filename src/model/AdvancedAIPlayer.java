package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the advanced AI that implements all the strategies for the extra credit.
 */
public class AdvancedAIPlayer extends AIPlayer implements Player {
  /**
   * Constructs an AIPlayer.
   *
   * @param playerColor The color of the player
   * @param model       The model to use as reference for the player
   */
  public AdvancedAIPlayer(TileType playerColor, IReversiModel model) {
    super(playerColor, model);
  }

  /**
   * Gets a list of moves that can be made in the corners.
   * @return List of positions for the aforementioned moves
   * @throws IllegalStateException if not the player's turn
   */
  private List<Position3D> getCornerMoves() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    List<Position3D> moves = new ArrayList<>();
    for (Position3D pos : this.getAvailableMoves()) {
      if (pos.getQ() == 0 && pos.getS() == this.model.getRadius() - 1) {
        moves.add(pos);
      }
      if (pos.getQ() == this.model.getRadius() - 1 && pos.getS() == 0) {
        moves.add(pos);
      }
      if (pos.getQ() == -1 * (this.model.getRadius() - 1) && pos.getR() == 0) {
        moves.add(pos);
      }
      if (pos.getQ() == this.model.getRadius() - 1 && pos.getR() == 0) {
        moves.add(pos);
      }
      if (pos.getQ() == -1 * (this.model.getRadius() - 1) && pos.getS() == 0) {
        moves.add(pos);
      }
      if (pos.getS() == -1 * (this.model.getRadius() - 1) && pos.getQ() == 0) {
        moves.add(pos);
      }
    }
    return moves;
  }

  /**
   * Gets available moves that are not next to corners.
   * @return list of positions for the aforementioned moves
   * @throws IllegalStateException if not the player's turn
   */
  private List<Position3D> getAvailableMovesNotNextToCorners() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    List<Position3D> availableMovesNotNextToCorners = new ArrayList<>(this.getAvailableMoves());
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getQ() == 1 && pos.getR() == -1 * (this.model.getRadius() - 1));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getQ() == 0 && pos.getR() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getQ() == -1 && pos.getR() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getS() == 1 && pos.getR() == -1 * (this.model.getRadius() - 1));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getS() == 0 && pos.getR() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getS() == -1 && pos.getR() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getQ() == -1 && pos.getR() == this.model.getRadius() - 1);
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getQ() == 0 && pos.getR() == this.model.getRadius() - 2);
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getQ() == 1 && pos.getR() == this.model.getRadius() - 2);
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getS() == -1 && pos.getR() == this.model.getRadius() - 1);
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getS() == 0 && pos.getR() == this.model.getRadius() - 2);
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getS() == 1 && pos.getR() == this.model.getRadius() - 2);
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getR() == 1 && pos.getQ() == -1 * (this.model.getRadius() - 1));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getR() == 0 && pos.getQ() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getR() == -1 && pos.getQ() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getR() == 1 && pos.getS() == -1 * (this.model.getRadius() - 1));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getR() == 0 && pos.getS() == -1 * (this.model.getRadius() - 2));
    availableMovesNotNextToCorners
            .removeIf(pos -> pos.getR() == -1 && pos.getS() == -1 * (this.model.getRadius() - 2));
    return availableMovesNotNextToCorners;
  }

  /**
   * Gets a list of moves that leaves the opponent with no moves available.
   * @return list of positions for the aforementioned moves
   * @throws IllegalStateException if not the player's turn
   */
  private List<Position3D> getMovesThatLeaveOpponentWithNothing() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    List<Position3D> moves = new ArrayList<>();
    for (Position3D pos : this.getAvailableMoves()) {
      IReversiModel tempModel = new ReversiModelImpl(this.model);
      tempModel.placeTile(pos);
      if (!tempModel.hasLegalMove()) {
        moves.add(pos);
      }
    }
    return moves;
  }

  /**
   * Gets the optimal move for the player.
   * @return position for optimal move
   * @throws IllegalStateException if it isn't the player's turn
   * @throws IllegalStateException if there are no legal moves
   */
  @Override
  public Position3D getOptimalMove() throws IllegalStateException {
    if (!this.model.getTurn().equals(this.playerColor)) {
      throw new IllegalStateException("Not the player's turn.");
    }
    List<Position3D> cornersThatLeaveOpponentWithNothing = new ArrayList<>(this.getCornerMoves());
    cornersThatLeaveOpponentWithNothing.retainAll(this.getMovesThatLeaveOpponentWithNothing());
    if (!cornersThatLeaveOpponentWithNothing.isEmpty()) {
      return super.getHighestScoringMove(cornersThatLeaveOpponentWithNothing);
    }
    if (!this.getCornerMoves().isEmpty()) {
      return super.getHighestScoringMove(this.getCornerMoves());
    }
    List<Position3D> minimizingOpponentAndNotNextToCorner =
            new ArrayList<>(this.getAvailableMovesNotNextToCorners());
    minimizingOpponentAndNotNextToCorner.retainAll(this.getMovesThatLeaveOpponentWithNothing());
    if (!minimizingOpponentAndNotNextToCorner.isEmpty()) {
      return super.getHighestScoringMove(minimizingOpponentAndNotNextToCorner);
    }
    if (!this.getMovesThatLeaveOpponentWithNothing().isEmpty()) {
      return super.getHighestScoringMove(this.getMovesThatLeaveOpponentWithNothing());
    }
    if (!this.getAvailableMovesNotNextToCorners().isEmpty()) {
      return super.getHighestScoringMove(this.getAvailableMovesNotNextToCorners());
    }
    return super.getOptimalMove();
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
