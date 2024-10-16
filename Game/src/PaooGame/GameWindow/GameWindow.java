package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.Tiles.Tile;

import javax.swing.*;
import java.awt.*;

/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */

public class GameWindow
{
    Game game;
    private JFrame  wndFrame;       /*!< fereastra principala a jocului*/
    private String  wndTitle;       /*!< titlul ferestrei*/
    private static int     wndWidth;       /*!< latimea ferestrei in pixeli*/
    private static int     wndHeight;      /*!< inaltimea ferestrei in pixeli*/
    public static int maxScreenCol=16; // latimea ecranului in nr. de tile-uri
    public static int maxScreenRow=12; // inaltimea ecranului in nr. de tile-uri
    //SCREEN SETTINGS
    public static int screenWidth=maxScreenCol*Tile.TILE_WIDTH;
    public static int screenHeight=maxScreenRow*Tile.TILE_HEIGHT;

    //WORLD SETTINGS
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int maxMap=10;
    public static int currentMap=0;

    public static final int worldWidth = Tile.TILE_WIDTH  * maxWorldCol;
    public static final int worldHeight = Tile.TILE_HEIGHT * maxWorldRow;

    private Canvas  canvas;         /*!< "panza/tablou" in care se poate desena*/

//    KeyHandler keyH = new KeyHandler();

    public GameWindow(String title, int width, int height,Game game){
        this.game=game;
        wndTitle    = title;    /*!< Retine titlul ferestrei.*/
        wndWidth    = width;    /*!< Retine latimea ferestrei.*/
        wndHeight   = height;   /*!< Retine inaltimea ferestrei.*/
        wndFrame    = null;     /*!< Fereastra nu este construita.*/
        maxScreenCol = wndWidth / Tile.TILE_WIDTH;
        maxScreenRow = wndHeight / Tile.TILE_HEIGHT;
    }

    public void BuildGameWindow()
    {
        if(wndFrame != null)
        {
            return;
        }
            /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
            /// ce apare in bara de titlu
        wndFrame = new JFrame(wndTitle);
        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);
        wndFrame.setVisible(true);
        wndFrame.isFocused();

        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));

        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));

        canvas.setBackground(Color.black);

        canvas.addKeyListener(game.keyH);

        wndFrame.add(canvas);

        wndFrame.pack();
    }

    public static int GetWndWidth() {
        return wndWidth;
    }

    public static int GetWndHeight()
    {
        return wndHeight;
    }

    public Canvas GetCanvas() {
        return canvas;
    }


}
