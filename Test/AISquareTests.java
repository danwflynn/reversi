import model.IReversiModel;
import model.SquareReversiModelImpl;
import model.player.AIPlayer;
import model.player.Player;
import model.tile.TileType;
import org.junit.Assert;
import org.junit.Test;

public class AISquareTests {
  @Test
  public void testAIMakesNoMoves() {
    IReversiModel sm = new SquareReversiModelImpl(2);
    Player p1 = new AIPlayer(TileType.BLACK, sm);
    Player p2 = new AIPlayer(TileType.WHITE, sm);
    Assert.assertEquals(0, p1.getAvailableMoves().size());
    sm.pass();
    Assert.assertEquals(0, p2.getAvailableMoves().size());
  }

  @Test (expected = IllegalStateException.class)
  public void testAIWrongTurn() {
    IReversiModel sm = new SquareReversiModelImpl(2);
    Player p1 = new AIPlayer(TileType.BLACK, sm);
    Player p2 = new AIPlayer(TileType.WHITE, sm);
    p2.getOptimalMove();
  }

  @Test (expected = IllegalStateException.class)
  public void testAIWrongTurn2() {
    IReversiModel sm = new SquareReversiModelImpl(2);
    Player p1 = new AIPlayer(TileType.BLACK, sm);
    Player p2 = new AIPlayer(TileType.WHITE, sm);
    p2.getAvailableMoves();
  }

  @Test
  public void testSquareAIMakeFirstTwoMoves() {
    IReversiModel sm = new SquareReversiModelImpl(8);
    Player p1 = new AIPlayer(TileType.BLACK, sm);
    Player p2 = new AIPlayer(TileType.WHITE, sm);
    sm.placeTile(p1.getOptimalMove());
    sm.placeTile(p2.getOptimalMove());
    Assert.assertEquals(3, p1.getScore());
    Assert.assertEquals(3, p2.getScore());
  }

  @Test
  public void testSquareAIMakeSeveralMoves() {
    IReversiModel sm = new SquareReversiModelImpl(8);
    Player p1 = new AIPlayer(TileType.BLACK, sm);
    Player p2 = new AIPlayer(TileType.WHITE, sm);
    for (int i = 0; i < 10; i++) {
      sm.placeTile(p1.getOptimalMove());
      sm.placeTile(p2.getOptimalMove());
    }
    Assert.assertEquals(13, p1.getScore());
    Assert.assertEquals(11, p2.getScore());
  }

  @Test
  public void testSquareAIPassToEndGame() {
    IReversiModel sm = new SquareReversiModelImpl(8);
    Player p1 = new AIPlayer(TileType.BLACK, sm);
    Player p2 = new AIPlayer(TileType.WHITE, sm);
    for (int i = 0; i < 30; i++) {
      sm.placeTile(p1.getOptimalMove());
      sm.placeTile(p2.getOptimalMove());
    }
    sm.pass();
    sm.pass();
    Assert.assertTrue(sm.isGameOver());
  }
}
