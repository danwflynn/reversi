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
will be in future assignments. In addition to this, the player interface allows extensibility
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

The game of Reversi is represented by the IReversiModel interface. Its implementation,
ReversiModelImpl, acts as the base for all code related to the board and rule keeping.
 - 'board' is the entire board of Reversi, which holds every Tile and their position.
 - 'turn' marks whose turn it is. The game starts with Black, and alternates with White each turn.
 - 'radius' is the radius of the hexagon, which is the distance from the center to a corner edge.
 - 'blackScore' is the score of the player playing Black tiles.
 - 'whiteScore' is the score of the player playing White tiles.
 - 'passCounter' keeps track of the number of passes done in a row in the game. Two passes will
        end the game.

Each tile's position is represented by the Position3D class. This class keeps track of where each
tile is on the board, based on Cube Coordinates. A Position3D has three integer fields: q, r, and s.
Each coordinate is one line slicing the hexagon grid in some way. As a position moves along these
slices, its three values change to represent its coordinates:
- 'q' represents one coordinate axis. This is a line slicing the hexagon grid from top left to
        bottom right; as a Position3D moves perpendicular to this line, this value increases
        (right) or decreases (left) in value.
- 'r' represents another coordinate axis. This is a line slicing the hexagon grid from left to
              right; as a Position3D moves perpendicular to this line, this value increases
              (down) or decreases (up) in value.
- 's' represents another coordinate axis. This is a line slicing the hexagon grid from top right to
                    bottom left; as a Position3D moves perpendicular to this line, this value
                    increases (right) or decreases (down) in value.

A TileType represents the values of each player in the game. These are used to represent tiles on
the board, as well as whose turn it currently is in a game.
- 'BLACK' represents the Black player.
- 'WHITE' represents the White player.
- 'EMPTY' represents an empty tile; this is not used in tracking whose turn it is.

A Tile is the interface for a tile on the board. Its implementation, GameTile, has two fields: pos
and tileType.
- 'pos' The Position3D of this tile, which is where this tile can be found on the board.
- 'tileType' What is on this tile, be it a white or black piece, or nothing.

The TextualView is an interface for handling the textual representation of the game. Its
implementation, ReversiTextualModel, has one field.
- 'model' Is the model which the textual view is trying to visually recreate.

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
