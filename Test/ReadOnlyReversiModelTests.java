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
}
