PART 2 AT THE BOTTOM

IMPORTANT:
The testing of this code base is split into files that serve different purposes.
ReversiModelTests is all the testing done alongside the development of the components of the
reversi model. ReadOnlyReversiModelTests is for tests focusing on the ReadOnly version of the model.
AIPlayerTests is for tests focusing on the nature of the AIPlayer of the game of Reversi.
ExampleTests is a reversi model tutorial for the reader's convenience. The setup for
the ExampleTests class creates a reversi game model with radius 5 along with its text view. The
tutorial starts with exceptions thrown by illegal constructor arguments, then it tests and
demonstrates the starting conditions of the game. The next section is exceptions for moves that
cannot be made. The next section demonstrates a play-through of a game where legal moves are being
made in different situations that cover different cases. The final sections just verifies that the
text view will work on different sizes.

Overview:

This project is a model and textview implementation of the game Reversi with a hexagonal grid as
the game board as opposed to other versions which use a square or rectangle to represent a board.
This code base contains 2 packages in its current state. There is a model package which manages
the players (having their respective tiles), the board (which is inside the Reversi model
class), and the rules of the game (also managed in Reversi model class). This code allows for
multiple forms of extensibility. If someone wanted to make a new version of the game (similar but
with slightly different rules), they could extend the ReversiModelImpl class and override the
necessary methods. In that case, the private fields and methods would need to become protected to
be used. This current codebase also allows room for a controller to be implemented as they
will be in future assignments. In addition to this, the player interface allows extensibility
for future implementation with human players. The player interface lets players make moves
in the game, check their attributes, and see which moves are optimal.

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

The player can initiate the GUI by inserting this model like so in the 'Reversi' class's main:

  public static void main(String[] args) {
    IReversiModel model = new ReversiModelImpl(15);
    IGraphicalView view = new ReversiGraphicalView(model);
    view.setVisible(true);
  }

Key Components:
Everything About Reversi Model:

Here is how you create a new game:
IReversi model = new ReversiModelImpl(4);

Here we can see that the constructor parameter is 4 which means that the radius of the board is 4.
The radius is the amount of hexagons from the middle tile (included) going in a straight line in
any direction to an edge tile (also included). The radius is also equal to each side length in
tiles because it is an equilateral hexagon. The constructor uses this make the board the proper
size with the first ring of tiles around the center initialized to alternating black and white
tiles with the rest as empty tiles. There is no start game method as the game can be played the
instant that it is constructed. Initiating the GUI is shown above.

How to pass a turn:
model.pass();

This will pass the current player's turn. There is a counter to see if 2 passes have been made in a
row. 2 passes in a row ends the game. There is no need to check if there are no valid moves because
the players will be forced to pass anyway because any illegal move will throw the proper exceptions.
There is a field in the model (TileType turn) that keeps track of who's turn it is. This field
will switch back and forth between black and white whenever somebody passes or places a tile.

How to place tiles:
model.placeTile(new Position3D(-2, 1, 1));

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

GUI view:
The Graphical User Interface (shown in IGraphicalView and its implementation, ReversiGraphicalView)
can be accessed via the main method. The gameplay under this GUI is described in the 'User Controls'
section.

Player Interface:
The player interface currently has one implementation: the AIPlayer. The player interface allows
players to make moves when it is their turn by calling their respective pass and placeTile methods.
There are also getters written to get the players' scores and tile types because they are going to
be needed in certain situations. There are also 2 methods that get all available moves and the
highest scoring move for a player. These are especially useful for the AI implementation because
this helps it build specific strategies.

___________________
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

There are also multiple 'mock' implementation, which extend the main implementation of the model.
These mock implementation are designed to probe the nature of the design, testing that the methods
and fields of the model are handled properly by other methods and classes.

- MockModelLoggingObservations confirms every time a Tile is checked, including for legality.
        It has an extra StringBuilder 'log' field, which holds information on every time the above
        actions are prompted, as well as a getter for this field. All other functionality is the
        same as the main implementation.
- MockModelFakeMoveLegality confirms that the AIPlayer properly follows what its model confirms
        about the legality of certain moves on the board. It does this by creating fake, arbitrary
        rules about the legality of placing on certain tiles, and testing that these rules are
        followed faithfully. This overrides the original functionality of the main implementation.
        All other functionality is unchanged.

On top of this, there is a ReadonlyIReversiModel, which is an interface only holding observer
methods in the model of the game. The true IReversiModel with all functionality extends this
interface, adding on all methods that can change the fields and aspects of the model itself.
This ReadOnly interface also has a direct implementation, ReadonlyReversiModelImpl. Besides the
missing void methods that can directly change the model, all other promised functionality is
present.

Each tile's position is represented by the Position3D class. This class keeps track of where each
tile is on the board, based on Cube Coordinates. A Position3D has three integer fields: q, r, and
s.
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


The Player interface handles players that play the game of Reversi. There is currently one
implementation of this, AIPlayer, which has two fields:
- 'playerColor' is whether the player is playing the Black or White pieces.
- 'model' is the Model of the Reversi game which the player uses to get information about the game's
        state.


The TextualView is an interface for handling the textual representation of the game. Its
implementation, ReversiTextualModel, has one field.
- 'model' Is the model which the textual view is trying to visually recreate.

The IGraphicalView handles the GUI of the game. Its implementation, ReversiGraphicalView, has no
explicit fields, but extends JFrame and acts as the main frame / window of the GUI.

BoardPanel is a class representing the board of tiles of hexagons. There are three fields:
- 'size' The side length of a hexagon on the board (in pixels)
- 'radius' The radius of the hexagon board in the game.
- 'model' The Readonly model whose observations are used to construct a board of the current game.

HexagonTile represents a single Hexagon tile in the game. It has four fields:
- 'hexagon' is the Polygon image which is used to create the visual part of the Tile.
- 'highlightedButton' is a static field representing which HexagonTile on the board is highlighted.
- 'cubeCoords' is a Position3D representing where this tile is on the board in cube coordinates.
- 'size' is the side length of this hexagon tile.

Source Organization:

Test:
ReversiModelTests: The testing done alongside the development of the code base
ExampleTests: A series of tests made after the model development with the purpose of showcasing
    important aspects of the game
ReadOnlyReversiModelTests: Tests specifically for the Readonly version of the game's model.
AIPlayerTests: Tests specifically for the AIPlayer and its functionality.

view:
TextualView: Text view interface
ReversiTextualView: Text view implementation
IGraphicalView: The GUI's interface
ReversiGraphicalView: The GUI's implementation of IGraphicalView
BoardPanel: The panel of hexagon tiles on which the game is played
HexagonTile: A single hexagon tile on the board of tiles.

model:
TileType: BLACK, WHITE, EMPTY enum
TIle: Tile interface which represents player
ReversiModelImpl: Manages the game's state, rules, and moves
Position3D: Position represented by q r s cube coordinates (q + r + s == 0)
Player: Player interface
AIPlayer: An implementation of Player for Artificial Intelligence, with programmed strategies
IReversiModel: Reversi model interface containing all methods that alter/check the game state
GameTile: Implementation of a tile to be used in game
MockModelFakeMoveLegality: A mock model for testing loyalty to move legality described by the model
MockModelLoggingObservations: A mock model that logs checks to tiles and legal moves
ReadonlyReversiModel: An interface for the model's methods that don't directly mutate the model
ReadonlyReversiModelImpl: An implementation of ReadonlyReversiModel, with no mutator methods


____________________
Changes for part 2

We have created two new methods, isLegalMove and hasLegalMove, which check if there is the given
position is a legal move for the current player in the model, and checks if there is any legal move
on the board at all for the current player, respectively. isLegalMove takes a Position3D argument,
and hasLegalMove takes no arguments.

There is a new function, getCopyOfTileAt which returns a copy of the tile at the given position
on the model's board which is only observable. The other method, getTileAt, returns the reference
of the actual tile for mutation purposes.


____________________
User Controls for GUI:

In the Graphical User Interface, the user is able to do three things:
- Select a tile: By clicking on the various hexagons, the player can select (and deselect) a tile
        that they would like to place on.
        The behavior of selecting and deselecting matches the assignment specifications exactly.
- Place a tile: After a valid tile is selected, the player can press 'm' on their keyboard to
        declare that they want to place a tile there.
        The current implementation prints the declared move to the console.
- Pass: To pass, a player can press 'p', ending their turn without placing a tile.
        The current implementation prints pass to the console when p is pressed

AI Players:
AIPlayer is the basic AI that makes the highest scoring move every time
AdvancedAIPlayer is the extra credit version of the AI that tries the following things in order:
    1. Move to a corner that leaves the opponent with no moves
    2. Move to a corner
    3. Make sure you're not moving next to a corner if possible while still blocking opponent
    4. Still make sure you're not moving next to a corner if possible
    5. Try to make a move that leaves the opponent with no moves
    6. If all of the above fail, just make the highest scoring move

The player interface is used for the AI players. All players have methods to make moves if they
want (more on that when the controller gets implemented). The player interface also has
getAvailableMoves and getOptimalMove methods that get the position(s) of the aforementioned. The AI
players get their optimal move from their corresponding rules.