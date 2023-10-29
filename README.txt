Overview:

This project is a model and textview implementation of the game Reversi with a hexagonal grid as
the game board as opposed to other versions which use a square or rectangle to represent a board.
This code base contains 2 packages in its current state. There is a model package which manages
the players (having their respective tiles), the board (which is inside the Reversi model
class), and the rules of the game (also managed in Reversi model class). This code allows for
multiple forms of extensibility. If someone wanted to make a new version of the game (similar but
with slightly different rules), they could extend the ReversiModelImpl class and override the
necessary methods. In that case, the private fields and methods would need to become protected to
be used. This current codebase also allows room for a controller and gui to be implemented as they
are going to be future assignments. In addition to this, the player interface allows extensibility
for future implementation with human and AI players. The player interface lets players make moves
in the game, check their attributes, and see which moves are optimal (likely to be useful for AI
version).

Quick Start:
@Test
  public void testScoresAfterMoreMoves() {
    IReversiModel model = new ReversiModelImpl(6);
    model.placeTile(new Position3D(-2, 1, 1));
    model.placeTile(new Position3D(-1, -1, 2));
    model.placeTile(new Position3D(1, -2, 1));
    Assert.assertEquals(7, model.getBlackScore());
    Assert.assertEquals(2, model.getWhiteScore());
    model.placeTile(new Position3D(2, -1, -1));
    Assert.assertEquals(4, model.getBlackScore());
    Assert.assertEquals(6, model.getWhiteScore());
    model.placeTile(new Position3D(1, 1, -2));
    Assert.assertEquals(8, model.getBlackScore());
    Assert.assertEquals(3, model.getWhiteScore());
    model.placeTile(new Position3D(-1, 2, -1));
    Assert.assertEquals(4, model.getBlackScore());
    Assert.assertEquals(8, model.getWhiteScore());
  }

This is an example of a test that makes legal moves and checks to see if the score is correct on
every single move.

Key Components:

Here is how you create a new game:
IReversi model = new ReversiModelImpl(4);

Here we can see that the constructor parameter is 4 which means that the radius of the board is 4.
The radius is the amount of hexagons from the middle tile (included) going in a straight line in
any direction to an edge tile (also included). The radius is also equal to each side length in
tiles because it is an equilateral hexagon. The constructor uses this make the board the proper
size with the first ring of tiles around the center initialized to alternating black and white
tiles with the rest as empty tiles. There is no start game method as the game can be played the
instant that it is constructed.

How to pass a turn:
model.pass();

This will pass whoever turn it is. There is a counter to see if 2 passes have been made in a row.
2 passes in a row ends the game. There is no need to check if there are no valid moves because the
players will be forced to pass anyway because any illegal move will throw the proper exceptions.
There is a field in the model (TileType turn) that keeps track of who's turn it is. This field
will switch back and forth between black and white whenever somebody passes or places a tile.

How to place tiles:
model.placeTile(new Position3D(-2, 1, 1);

This method places a tile at the given position (using the q r s cube coordinate system for
hexagonal grids) for whoever turn it is. Just like pass, it already knows whose turn it is because
of the turn field. If the position is out of bounds the method throws an IllegalArgumentException.
If the position is in bounds but the move is not legally possible due to the rules of the game, an
IllegalStateException is thrown. When a legal move is made, the score is updated. The scores of
each player are equal to the amount of tiles they have on the board of their color. There are
multiple private helper methods that manage the flipping of tiles when a move is made. This is
talked about more in key subcomponents.

Text view:
There is a text view class and interface just like in the klondike project. Right now, there is
just a toString method that gives the string representation of the game as specified by the
assignment.

Player Interface:
The player interface currently doesn't have any class(es) that implement it because the assignment
only requires that we DESIGN the interface. The player interface allows players to make moves when
it is their turn by calling their respective pass and placeTile methods. There are also getters
written to get the players' scores and tile types because they are going to be needed in certain
situations. There are also 2 methods that get all available moves and the highest scoring move for
a player. This could be especially useful for an AI implementation because an AI might want that
specific information.

Key Subcomponents:

Managing tiles:
The GameTile class contains the TileType and its position using cube coordinates. The board field
inside the model class is a list of Tile. There is a getTileAt(Position3D pos) which searches the
list of the tile at that position. We return the actual tile (not a copy) because want to be able
to flip the tile by using the setter that we created. The GameTile class implements a tile
interface because the assignment specified that we need an interface for players and in this
codebase, the players are represented by their given tiles.

Flipping tiles when placing a tile:
When you place a tile in Reversi, you flip over any tile lying along the straight line(s) starting
at the tile you just places and ending at the tile(s) along the line(s) that is/are of the type of
the placed tile. In our program, these lines are referred to as bridges. When you place a tile,
you DFS for all available bridges from the position (throw illegal state if there are none) and
then add the bridges (represented by List<Tile>) to a list of bridges (List<List<Tile>>). Once the
list of generated, every tile inside of this 2d list that isn't of the turn type is flipped. These
steps are all executed through the following private helper methods:
private void placeTileBasicExceptions(Position3D pos)
private void getAvailableBridgesHelper(Position3D pos, List<List<Tile>> bridges,
                                        int qInc, int rInc, int sInc)
private List<List<Tile>> getAvailableBridges(Position3D pos)
private void flipBridge(List<Tile> bridge)

There is also a private void helper called updateScore() which is called during placeTile().

Source Organization:

Test:
ReversiModelTests: The entirety of the testing

view:
TextualView: Text view interface
ReversiTextualView: Text view implementation

model:
TileType: BLACK, WHITE, EMPTY enum
TIle: Tile interface which represents player
ReversiModelImpl: Manages the game's state, rules, and moves
Position3D: Position represented by q r s cube coordinates (q + r + s == 0)
Player: Player interface
IReversiModel: Reversi model interface containing all methods that alter/check the game state
GameTile: Implementation of a tile to be used in game
