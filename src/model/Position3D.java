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

  /**
   * Get the Q value for this Position3D.
   * @return This Position3D's 'q' value.
   */
  public int getQ() {
    return q;
  }

  /**
   * Get the R value for this Position3D.
   * @return This Position3D's 'r' value.
   */
  public int getR() {
    return r;
  }

  /**
   * Get the S value for this Position3D.
   * @return This Position3D's 's' value.
   */
  public int getS() {
    return s;
  }

  /**
   * Gets the farthest direction of the three values of the Position3D.
   * @return The furthest direction as an int
   */
  public int getFarthestDirection() {
    return Math.max(Math.max(Math.abs(q), Math.abs(r)), Math.abs(s));
  }

  /**
   * Gets the distance from this Position3D to the given Position3D.
   * @param pos The other position to compare to
   * @return The distance in number of hexagons to traverse to get to the given Position3D
   */
  public int getDistanceFrom(Position3D pos) {
    return (Math.abs(this.q - pos.q) + Math.abs(this.r - pos.r) + Math.abs(this.s - pos.s)) / 2;
  }

  /**
   * Determines if this Position3D is the same position as the given Object.
   * @param other The object to compare to
   * @return 'true' if the two Position3D's are the same, and false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Position3D)) {
      return false;
    } else {
      Position3D otherPosition = (Position3D) other;
      return this.q == otherPosition.q && this.r == otherPosition.r && this.s == otherPosition.s;
    }
  }

  /**
   * Gets the hashCode of this Position3D, which is made by its three parameters.
   * @return The hashCode of this Position3D as an int
   */
  @Override
  public int hashCode() {
    int result = 17; // A prime number to start with
    result = 31 * result + q;
    result = 31 * result + r;
    result = 31 * result + s;
    return result;
  }

  /**
   * Represents this Position3D as a String.
   * @return This Position3D's values in a comma separated String.
   */
  @Override
  public String toString() {
    return this.q + ", " + this.r + ", " + this.s;
  }
}
