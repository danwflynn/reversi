import org.junit.Assert;
import org.junit.Test;

import javax.swing.text.Position;

import model.Position3D;

public class ReadOnlyReversiModelTests {
  //TODO: Make tests for the read only methods to make sure they return the right stuff and
  //can't mutate the model

  //TODO: test isMoveLegal thoroughly (can just use prior examples)

  //TODO: test hasLegalMove

  @Test
  public void testPosition3DToString() {
    Position3D pos = new Position3D(-3, -2, 5);
    Assert.assertEquals("-3, -2, 5", pos.toString());
  }

  @Test
  public void testGetDistanceFromTwoBelow() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(-2, -1, 3);
    Assert.assertEquals(2, pos1.getDistanceFrom(pos2));
  }

  @Test
  public void testGetDistanceFromTwoRight() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(2, -3, 1);
    Assert.assertEquals(2, pos1.getDistanceFrom(pos2));
  }

  @Test
  public void testGetDistanceFromOneRight() {
    Position3D pos1 = new Position3D(0, -3, 3);
    Position3D pos2 = new Position3D(1, -3, 2);
    Assert.assertEquals(1, pos1.getDistanceFrom(pos2));
  }
}
