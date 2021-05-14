# Design Document

## Design of classes

1. Class Location  
   The Location class encapsulates a location on the board.
   AI will score the location when analyzing the situation.

   Class variables include but are not limited to:

   Variable |       Function       |   Type   |
   :------: | :------------------: | :------: |
   x        | Abscissa of location |   int    |
   y        | Ordinate of location |   int    |
   score    |   Score of location  |   int    |
   player   |   Owner of location  |   int    |
   
2. Class Chess  
   The Chess class is mainly responsible for various logical events, 
   such as logical placement, AI situation analysis, victory judgment, etc.

   Class variables include but are not limited to:

   Variable  |           Function            |          Type         |
   :------:  | :---------------------------: | :-------------------: |
   map       |          Map of board         |   int[ ][ ]           |
   AI        |   Numbers represented by AI   |   static final int    |
   HUMAN     | Numbers represented by human  |   static final int    |
   firstPlay |   Who will play chess first   |   static int          |
   
3. Class ChessPanel  
   The ChessPanel class is responsible for board display, interaction, and placement on the view,
   such as the drawing of the chessboard and chess pieces, 
   the preservation of the chessboard state, the placement, 
   and the clearing of events.

   Class variables include but are not limited to:

   Variable  |          Function           |        Type         |
   :------:  |  :------------------------: |  :--------------:   |
   list      |      List of location       |   List<Location>    |
   sizeCell  |      Size of cell           |        int          |
   margin    |   Distance from the border  |        int          |
   radius    |      Radius of chess        |        int          |

4. Class ChessGUI  
   The View class is mainly responsible for the display of the game 
   window and calls to Chess and ChessPanel

   Class variables include but are not limited to:

   Variable    |          Function           |       Type       |
   :--------:  | :-------------------------: | :--------------: |
   frame       |       Window object         |      JFrame      |
   chess       |        Chess object         |      Chess       |
   chessPanel  |     ChessPanel object       |     ChessPanel   |
   
