# Design Document

## Functional Details and Project Structure

1. Class Location  
   The Location class encapsulates a location on the board. AI will score the location when analyzing the situation.

   Class variables include but are not limited to:

   | Variable |       Function       | Type  |
   | :------: | :------------------: | :---: |
   |    x     | Abscissa of location |  int  |
   |    y     | Ordinate of location |  int  |
   |  score   |  Score of location   |  int  |
   |  player  |  Owner of location   |  int  |

   Class Methods include but are not limited to:

   |        Name         | Function  | Return |
   | :-----------------: | :-------: | :----: |
   | setScore(int score) | set score |  void  |
   |     getScore()      | get score |  int   |
   |       getX()        |   get X   |  int   |
   |       getY()        |   get Y   |  int   |
   |     getPlayer()     | getScore  |  int   |


2. Class Chess  
   The Chess class is mainly responsible for various logical events, such as logical placement, AI situation analysis,
   victory judgment, etc.

   Class variables include but are not limited to:

   | Variable  |           Function           |       Type       |
   | :-------: | :--------------------------: | :--------------: |
   |    map    |         Map of board         |    int[ ][ ]     |
   |    AI     |  Numbers represented by AI   | static final int |
   |   HUMAN   | Numbers represented by human | static final int |
   | firstPlay |  Who will play chess first   |    static int    |

   Class Methods include but are not limited to:

   |                                    Name                                     |                           Function                            |     Return     |
   | :-------------------------------------------------------------------------: | :-----------------------------------------------------------: | :------------: |
   |                                  mapInit()                                  |                        initialize map                         |      void      |
   |                                   start()                                   |              When AI play first get the location              |    Location    |
   |                       play(int x, int y, int player)                        |                          place chess                          |    boolean     |
   |                    getAllPossibleLocation(int[ ][ ] Map)                    |                  Get all possible locations                   | List<Location> |
   | addPossibleLocationToList(List<Location> allPossibleLocation, int x, int y) | Add locations to the collection and filter duplicate elements |      void      |
   |                                 analysis()                                  |    AI analyzes the game and scores all possible locations     |    location    |
   |                           getScore(int x, int y)                            |                getScore from current position                 |      int       |
   |      getScoreBySituation(int count, boolean status1, boolean status2)       |              Calculate score based on situation               |      int       |
   |                      getXScore(int x, int y, int cur)                       |                       Horizontal score                        |      int       |
   |                      getYScore(int x, int y, int cur)                       |                        Vertical score                         |      int       |
   |                    getSkewScore1(int x, int y, int cur)                     |                    Positive oblique score                     |      int       |
   |                    getSkewScore2(int x, int y, int cur)                     |                    Negative oblique score                     |      int       |
   |                                  isDraw()                                   |                Judge whether the game is draw                 |    boolean     |
   |                        isWin(int x, int y, int cur)                         |                 Judge whether this player win                 |    boolean     |
   |                             setMap(int[][] map)                             |                       set map(for test)                       |      void      |
   |                                  getMap()                                   |                       get map(for test)                       |   int[ ][ ]    |


3. Class ChessPanel  
   The ChessPanel class is responsible for board display, interaction, and placement on the view, such as the drawing of
   the chessboard and chess pieces, the preservation of the chessboard state, the placement, and the clearing of events.

   Class variables include but are not limited to:

   |  Variable  |         Function         |      Type      |
   | :--------: | :----------------------: | :------------: |
   |    list    |     List of location     | List<Location> |
   |  sizeCell  |       Size of cell       |      int       |
   |   margin   | Distance from the border |      int       |
   |   radius   |     Radius of chess      |      int       |
   |  bgColor   | The color of background  |     Color      |
   | lineColor  |    The color of line     |     Color      |
   | pointColor |    The color of point    |     Color      |

   Class Methods include but are not limited to:  

   |                 Name                 |       Function       | Return |
   | :----------------------------------: | :------------------: | :----: |
   |          paint(Graphics g1)          |    Override paint    |  void  |
   |       drawBoard(Graphics2D g)        |      draw board      |  void  |
   |    drawChessPieces(Graphics2D g)     |     draw pieces      |  void  |
   |             clearBoard()             |   Clear the board    |  void  |
   | doPlay(int row, int col, int player) | do play do the board |  void  |
   


4. Class ChessGUI  
   The View class is mainly responsible for the display of the game window and calls to Chess and ChessPanel

   Class variables include but are not limited to:

   |  Variable  |     Function      |    Type    |
   | :--------: | :---------------: | :--------: |
   |   frame    |   Window object   |   JFrame   |
   |   chess    |   Chess object    |   Chess    |
   | chessPanel | ChessPanel object | ChessPanel |

  Class Methods include but are not limited to:  
   
   |                      Name                      |                 Function                  | Return |
   | :--------------------------------------------: | :---------------------------------------: | :----: |
   |                    create()                    |               create frame                |  void  |
   |                 restartBoard()                 | Chess reopening event processing function |  void  |
   | showChess(ChessPanel chessPanel, MouseEvent e) | The mouse click event of the board panel  |  void  |
   
## Execution plan
   ### The relationship between Classes
   
   The view object is an instance of the ChessGUI class, chessPanel is an instance of the Chessboard class, pos is an instance of the position entity class, and chess is an instance of the controller class. It can be said that chessGUI is the control center of the game, responsible for scheduling chessPanel and chess; chessPanel is responsible for the game view; chess is responsible for the game logic; pos represents a location, and the collection of Location can be used to save the state of the board.

   ### Algorithm Implementation
   
   Score all possible positions through the scoring table and choose the position with the highest score to play chess.
   The possible positions are adjacent to any piece.
   Scoring table:
   | Expleam | Score  |
   | :-----: | :----: |
   | □○○○○○□ | 200000 |
   | □○○○○○● | 200000 |
   | ●○○○○○□ | 200000 |
   | ●○○○○○● | 200000 |
   | ●○○○○●  | 50000  |
   | □○○○○●  |  3000  |
   | ●○○○○□  |  3000  |
   | ●○○○○●  |  1000  |
   |  ●○○○●  |  3000  |
   |  □○○○●  |  1000  |
   |  ●○○○□  |  1000  |
   |  ●○○○●  |  500   |
   |  ●○○●   |  500   |
   |  □○○●   |  200   |
   |  ●○○□   |  200   |
   |  ●○○●   |  100   |
   |  ●○○●   |  100   |
   |  □○○●   |   50   |
   |  ●○○□   |   50   |
   |  ●○○●   |   30   |

   ### Window Realization

   #### Frame
    
   The frame layout is controlled by the ChessGUI class, which is mainly divided into two parts: the toolbar at the top and the chessboard panel in the center. The toolbar has two Actions: reopen a game, which player first, set the board initialization and which player play first.

   #### Toolbar and Panel
   
   Toolbar is from JToolBar, and Panel is from chessPanel which drawed by JPanel.

   #### Mouse Event

   Set mouse event for chess panel, determine the board position corresponding to the click position, and determine whether the location is legal.

   
   

