package model;

/**
 * Represents coordinates for a place on the board using 3d q r s system.
 */
public final class Position3D {
  private final int q;
  private final int r;
  private final int s;

  /**
   * Constructor for Position3D.
   * @param q q value
   * @param r r value
   * @param s s value
   * @throws IllegalArgumentException if values don't add to 0
   */
  public Position3D(int q, int r, int s) throws IllegalArgumentException {
    if (q + r + s != 0) {
      throw new IllegalArgumentException("Coordinates must add to 0");
    }
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * Make a copy of position.
   * @param pos position to copy
   */
  public Position3D(Position3D pos) {
    this.q = pos.getQ();
    this.r = pos.getR();
    this.s = pos.getS();
  }

  public int getQ() {
    return q;
  }

  public int getR() {
    return r;
  }

  public int getS() {
    return s;
  }

  public int getFarthestDirection() {
    return Math.max(Math.max(Math.abs(q), Math.abs(r)), Math.abs(s));
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Position3D)) {
      return false;
    } else {
      Position3D otherPosition = (Position3D) other;
      return this.q == otherPosition.q && this.r == otherPosition.r && this.s == otherPosition.s;
    }
  }

  @Override
  public int hashCode() {
    int result = 17; // A prime number to start with
    result = 31 * result + q;
    result = 31 * result + r;
    result = 31 * result + s;
    return result;
  }
}
