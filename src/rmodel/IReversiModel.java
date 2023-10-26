package rmodel;

public interface IReversiModel {
  TileType getTileAt(Position3D pos);

  int getRadius();

  void pass();

  void placeTile(Position3D pos);

  boolean isGameOver();
}
