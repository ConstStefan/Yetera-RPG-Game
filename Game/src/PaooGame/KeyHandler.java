package PaooGame;

import PaooGame.GameWindow.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    Game game;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(Game game){
        this.game=game;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(game.gameState==game.titleState){
            if (code == KeyEvent.VK_W) {
               game.ui.commandNum--;
               if(game.ui.commandNum<0)
               {
                   game.ui.commandNum=2;
               }
            }

            if (code == KeyEvent.VK_S) {
                game.ui.commandNum++;
                if(game.ui.commandNum>2)
                {
                    game.ui.commandNum=0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(game.ui.commandNum == 0) {
                    game.gameState=game.playState;

                }
                if(game.ui.commandNum == 1)
                {
                    //
                }
                if(game.ui.commandNum==2){
                    System.exit(1);
                }
            }
        }
        if(game.gameState==game.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                    game.gameState = game.pauseState;

            }
        }
        else if(game.gameState==game.pauseState)
        {
            if (code == KeyEvent.VK_P){
                game.gameState=game.playState;
            }
        }
         /*if(game.gameState==game.pauseState) {
            if (code == KeyEvent.VK_P) {
                game.gameState = game.playState;
            }
        }*/
           else if (game.gameState == game.gameOverState) {
                if (code == KeyEvent.VK_W) {
                    game.ui.commandNum--;
                    if (game.ui.commandNum < 0) {
                        game.ui.commandNum = 1;
                    }

                }
                if (code == KeyEvent.VK_S) {
                    game.ui.commandNum++;
                    if (game.ui.commandNum > 1) {
                        game.ui.commandNum = 0;
                    }

                }
                if (code == KeyEvent.VK_ENTER) {
                    if (game.ui.commandNum == 0) {
                        game.retry();
                        game.gameState = game.playState;
                        //game.retry();
                    } else if (game.ui.commandNum == 1) {
                        game.restart();
                        game.gameState = game.titleState;

                    }
                }
            }
           else if(game.gameState==game.wonState){
            if (code == KeyEvent.VK_W) {
                game.ui.commandNum--;
                if (game.ui.commandNum < 0) {
                    game.ui.commandNum = 1;
                }

            }
            if (code == KeyEvent.VK_S) {
                game.ui.commandNum++;
                if (game.ui.commandNum > 1) {
                    game.ui.commandNum = 0;
                }

            }
            if (code == KeyEvent.VK_ENTER) {
                if (game.ui.commandNum == 0) {
                    game.retry();
                    game.gameState = game.playState;
                    //game.retry();
                } else if (game.ui.commandNum == 1) {
                    game.restart();
                    game.gameState = game.titleState;

                }
            }

        }

        }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }

        if(code == KeyEvent.VK_S){
            downPressed = false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
