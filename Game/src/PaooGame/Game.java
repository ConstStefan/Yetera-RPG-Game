package PaooGame;

import PaooGame.Exceptions.MapException;
import PaooGame.Exceptions.TileException;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;
import PaooGame.KeyHandler;
import PaooGame.Tiles.TileManager;
import PaooGame.entity.Player;
import PaooGame.object.SuperObject;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */
public class Game implements Runnable
{
    // public static KeyListener keyH;
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    public boolean         runState;   /*!< Flag ce starea firului de executie.*/
    public Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    public int  gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int gameOverState=3;
    public final int wonState=4;
    public int level=0;
    private BufferStrategy  bs;/*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/

    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private Graphics g;          /*!< Referinta catre un context grafic.*/

    private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/
    private static Game instance = new Game("Yetera", 768, 576);
    public static Game getInstance(){
        return instance;
    }
    public Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height,this);
            /// Resetarea flagului runState ce indica starea firului de executie (started/stopped)
        runState = false;
    }

    private void InitGame(int width, int height)
    {
        wnd = new GameWindow("Yetera", width, height,this);
        wnd.BuildGameWindow();
        try {
            Assets.Init();
        } catch (TileException e) {
            e.printStackTrace();
        }
    }
    public String[] maps={"/maps/worldmap.txt","/maps/worldmaplvl2.txt"};

    public KeyHandler keyH = new KeyHandler(this);
    public AssetSetter aSetter=new AssetSetter(this);
    public UI ui=new UI(this);
    public static Player character;
    public TileManager tileM;
    public  CollisionChecker collChecker;
    public SuperObject obj[]=new SuperObject[15];
    public void run() {
        setupGame();
        InitGame(GameWindow.GetWndWidth(), GameWindow.GetWndHeight());
        character = new Player(g, keyH, this);
        tileM =  new TileManager( character);
        collChecker = new CollisionChecker(this, tileM,wnd);
        long oldTime = System.nanoTime();
        long curentTime;

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

            /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true) {

            curentTime = System.nanoTime();
            if((curentTime - oldTime) > timeFrame) {

                Update();
                Draw();
                oldTime = curentTime;

            }
        }
    }

    public void  nextLevel(){
        level++;
        try {
            tileM.loadMap(maps[level]);
        } catch (MapException e) {
            e.printStackTrace();
        }
        character.setDefaultValues();
        ui.gameFinished=false;
        aSetter.setObjectLvl2();
        ui.playTime=100;
        ui.initialTime=100;
        character.minRequiredArcs=6;
        character.minRequiredCoin=5;
        /*(if(ui.gameFinished2==true)
        {
            gameState=wonState;
        }*/


    }
    public void retry()
    {
        character.setDefaultPositions();
        if (level==0) {
            ui.resetTime1();
            aSetter.setObject();
        }
        else{
            ui.resetTime2();
            aSetter.setObjectLvl2();
        }

    }
    public void restart(){
        character.minRequiredArcs=5;
        character.minRequiredCoin=4;
        character.setDefaultPositions();
        aSetter.setObject();
        ui.resetTime1();
        level=0;
        try {
            tileM.loadMap(maps[level]);
        } catch (MapException e) {
            e.printStackTrace();
        }
    }


    public void setupGame(){
        aSetter.setObject();
        gameState=titleState;
        //aSetter.setObject();
    }

    public synchronized void StartGame() {
        if(runState == false) {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
        else {
            return;
        }
    }

    public synchronized void StopGame() {
        if(runState == true) {
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try {
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex) {
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else {
            return;
        }
    }

    private void Update() {
        if(gameState==playState)
        {
            character.update();
        }
        if(gameState==pauseState){

        }



    }

    private void Draw() {

        bs = wnd.GetCanvas().getBufferStrategy();

        if(bs == null) {

            try {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
/*

            Tile.grassTile.Draw(g, 0 * Tile.TILE_WIDTH, 0);
            Tile.soilTile.Draw(g, 1 * Tile.TILE_WIDTH, 0);
            Tile.waterTile.Draw(g, 2 * Tile.TILE_WIDTH, 0);
            Tile.mountainTile.Draw(g, 3 * Tile.TILE_WIDTH, 0);
            Tile.treeTile.Draw(g, 4 * Tile.TILE_WIDTH, 0);

            g.drawRect(2 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
*/
        //Tile.Background.customDraw(g);

        ///TITLE SCREEN
      if(gameState==titleState){
          ui.drawTitleS(g);
      }
      else {
          tileM.Draw(g);
          //object
          for (int i = 0; i < obj.length; i++) {
              if (obj[i] != null) {
                  obj[i].draw(g, this);
              }
          }
          //PLAYER
          character.draw(g);
          //UI
          ui.draw(g);
      }

        //MAP


        bs.show();
        g.dispose();
    }
}

