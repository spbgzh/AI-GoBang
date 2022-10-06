This is demo of my project, my project is AI-golang, a golang game between human and computer.
The main implementation is whenever human paly, the machine scores all possible location,
and play at the location with highest score.
There are four classes in my progect.

First of all, I want to dispaly the Location class, which embodies the OOP.
It store the location of chess in the panel, and its current score and ascription(human or AI),
in this class have common set and get function and with 3 different constructer.

Second one is ChessPanel class which is extended from Jpanel class, its responsible for board dispaly and chess placement.
The list store the existing pices location. And the following variable is the drawing parameters.
This original paint fuctions is been overrided, which added anti-aliasing make chess pieces smoother, and draw board and pieces.
in the drawboard function, it draws the lines and points. and the color of background .
in the drawChessPieces function it draws Chess Pieces in the order and its serial number.
And when the game is over clearboard function clear the chess and repaint the board.
Other class using doplay function to add the piece in the board. and repaint the board to make sure new piece display.

Secondly is the ChessGUI class, its the frame of the game, and also the shell of the panel.
and monitor mouse event.
the Jprame allow me to set the windows, I set frame s name and its icon,
apart from the panel, it have a toolbar which contain two buttons
first button is a tool to decide who play first,and second one can restart the game.
whenever you have a mouse evert the showchsee function transform the click location to board location.
and if the location is legal, then do play at the location and judge whether hunam is win, or lose or draw.
if human win, game is over and restart, else AI play and then judge whether AI is win.

Last but not least, its the chess class, which responsible for the logical event
when the game is started, it initialise the map of the board. the map is for AI to analysis the possible locations.
when AI play first, the start function find a random location in the middle of the board to start the game
this play function is to judge whether humans play is legal.
the following functions are for AI to find the possibel location, this example shows what is a possibel location,
any piecess up, down, left, and right is possible location, and it can be repetitive,
so addPossibleLocationToList function make sure the list dont have same location.
the analysis function decide where AI play, its the core of the project.
Obviously it traverse all possible location and scores those locations and randomly choose a location among
all hightest scores.
but how it score?
here is a score table, if with current location huamn or AI have 5 picess, with this location AI can win the game
this location must have highest score or  with this location huaman can win the game, so AI need to stop him
if there are 4 pices this score must lower than 5 but higher than 3 so that is howthis score table decide where AI to play.
and the total score for current location is the summation of AI point of view and human point of view
and the following functions is for judging how many pices are connected in four dirctions.
And now I will show How does this game work.
