package PaooGame;

import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static final int width = 768;
    public static final int height = 576;
    public static void main(String[] args)
    {
        Game paooGame =Game.getInstance();
      //  Game paooGame = new Game("Yetera", width, height);
        paooGame.StartGame();
    }
}
