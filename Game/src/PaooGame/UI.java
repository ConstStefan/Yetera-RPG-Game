package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Tiles.Tile;
import PaooGame.object.Obj_Arc;
import PaooGame.object.Obj_coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    Game game;
    Graphics g;
    Font arial_40,arial_80B;
    BufferedImage coinImage;
    BufferedImage arcImage;
    public boolean messageOn=false;
    public String message="";
    public String message2="";
    public boolean showMission=false;
    int messageCounter=0;
    boolean sendNextLevelMessage=false;
    public boolean gameFinished=false;
   // public boolean gameFinished2=false;
    public double initialTime=100;

    double playTime=100;
    public int commandNum=0;
    DecimalFormat dFormat=new DecimalFormat("#0");

    public UI(Game game)
    {

        this.game=game;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_80B=new Font("Arial",Font.BOLD,80);
        Obj_coin coin=new Obj_coin();
        Obj_Arc arc=new Obj_Arc();
        coinImage=coin.image;
        arcImage=arc.image;

    }
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics g){
        this.g=g;

   /* public void draw(Graphics g,int level) {
        this.g = g;
        switch(level){
            case 0:

                break;
            case 1:

                break;
        }

    */

        if (game.gameState == game.playState) {
            if (gameFinished == true) {

                g.setFont(arial_40);
                g.setColor(Color.white);

                String text;
                int textLength;
                int x;
                int y;

                text = "You found a piece of scepter!";
                textLength = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();

                x = GameWindow.screenWidth / 2 - textLength / 2;
                y = GameWindow.screenHeight / 2 - (Tile.TILE_HEIGHT * 3);
                g.drawString(text, x, y);

                text = "Your time is:" + dFormat.format(playTime) + "!";
                textLength = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
                x = GameWindow.screenWidth / 2 - textLength / 2;
                y = GameWindow.screenHeight / 2 + (Tile.TILE_HEIGHT * 4);
                g.drawString(text, x, y);

                g.setFont(arial_80B);
                g.setColor(Color.cyan);
                text = "Congratulations!";
                textLength = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();

                x = GameWindow.screenWidth / 2 - textLength / 2;
                y = GameWindow.screenHeight / 2 + (Tile.TILE_HEIGHT * 2);
                g.drawString(text, x, y);

                if(sendNextLevelMessage==false) {
                    sendNextLevelMessage = true;


                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    game.nextLevel();
                                }
                            },
                            3000
                    );
                }



               // game.gameThread = null;

            }
                if (gameFinished == false) {

                    g.setFont(arial_40);
                    g.setColor(Color.white);
                    g.drawImage(coinImage, Tile.TILE_WIDTH / 2, Tile.TILE_HEIGHT / 2, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
                    g.drawString("x " + game.character.hasCoin + "/"+game.character.minRequiredCoin, 70, 62);
                    g.drawImage(arcImage, Tile.TILE_WIDTH / 2, Tile.TILE_HEIGHT * 2, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
                    g.drawString("x " + game.character.hasArc + "/"+game.character.minRequiredArcs, 70, 130);
                    /*if(showMission==false) {
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        g.drawString("Mission: ", GameWindow.screenWidth / 2, GameWindow.screenHeight / 2 + (Tile.TILE_HEIGHT * 2));
                                        g.drawString("Find all the objects to open the door ", GameWindow.screenWidth / 2, GameWindow.screenHeight / 2 + (Tile.TILE_HEIGHT * 2)+Tile.TILE_HEIGHT);
                                    }
                                },
                                3000
                        );
                        showMission=true;
                    }*/


                    //TIME
                    playTime -= (double) 1 / 60;
                    if (playTime <=0) {
                        game.gameState = game.gameOverState;
                    }
                    g.drawString("Time:" + dFormat.format(playTime)+" sec", Tile.TILE_WIDTH * 11, 65);
                    if(playTime>initialTime-3)
                    {
                       String text1="Mission:";
                       String text2="Find all the objects to open the door ";
                        int textLength1=(int) g.getFontMetrics().getStringBounds(text1, g).getWidth();
                        int textLength2 =(int)g.getFontMetrics().getStringBounds(text2, g).getWidth();
                        int x1=GameWindow.screenWidth / 2-textLength1/2;
                        int y1=GameWindow.screenHeight / 2 + (Tile.TILE_HEIGHT * 2);
                        g.drawString(text1, x1, y1);

                        int x2=GameWindow.screenWidth / 2-textLength2/2;
                        int y2=GameWindow.screenHeight / 2 + (Tile.TILE_HEIGHT * 3);
                        g.drawString(text2,x2,y2);



                    }
                    //Mesaj
                    if (messageOn == true) {
                        g.setFont(g.getFont().deriveFont(30F));
                        g.drawString(message, Tile.TILE_WIDTH / 2, Tile.TILE_HEIGHT * 5);
                        g.drawString(message2, Tile.TILE_WIDTH / 2, Tile.TILE_HEIGHT * 6);

                        messageCounter++;
                        if (messageCounter > 120) {
                            messageCounter = 0;
                            messageOn = false;
                        }
                    }
                }
            }


        if (game.gameState == game.pauseState) {
            drawPauseScreen();
        }
        if (game.gameState == game.gameOverState) {
            drawGameOverScreen();
        }
       /* if(game.gameState == game.wonState)
        {
            drawWonState();
        }*/
    }

    public void resetTime1(){
        playTime=100;
    }
    public void resetTime2(){
        playTime=100;
    }
    public void drawWonState(){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0,GameWindow.screenWidth,GameWindow.screenHeight);
        int x;
        int y;
        String text;
        g.setFont(g.getFont().deriveFont(Font.BOLD,110f));
        text="You Won";
        g.setColor(Color.black);
        x=getXforCenteredText(text);
        y=Tile.TILE_HEIGHT*4;
        g.drawString(text,x,y);

        g.setColor(Color.white);
        g.drawString(text,x-4,y-4);

        ///RETRY
        g.setFont(g.getFont().deriveFont(50f));
        text="Retry";
        x=getXforCenteredText(text);
        y+=Tile.TILE_HEIGHT*4;
        g.drawString(text,x,y);

        if(commandNum == 0)
        {
            g.drawString(">",x-40,y);
        }
        //BACK to the title screen
        text="Quit";
        x=getXforCenteredText(text);
        y+=55;
        g.drawString(text,x,y);
        if(commandNum == 1)
        {
            g.drawString(">",x-40,y);
        }

    }

    public void drawGameOverScreen(){
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0,0,GameWindow.screenWidth,GameWindow.screenHeight);
        int x;
        int y;
        String text;
        g.setFont(g.getFont().deriveFont(Font.BOLD,110f));
        text="GAME OVER";
        g.setColor(Color.black);
        x=getXforCenteredText(text);
        y=Tile.TILE_HEIGHT*4;
        g.drawString(text,x,y);

        g.setColor(Color.white);
        g.drawString(text,x-4,y-4);

        ///RETRY
        g.setFont(g.getFont().deriveFont(50f));
        text="Retry";
        x=getXforCenteredText(text);
        y+=Tile.TILE_HEIGHT*4;
        g.drawString(text,x,y);

        if(commandNum == 0)
        {
            g.drawString(">",x-40,y);
        }
        //BACK to the title screen
        text="Quit";
        x=getXforCenteredText(text);
        y+=55;
        g.drawString(text,x,y);
        if(commandNum == 1)
        {
            g.drawString(">",x-40,y);
        }


    }

    public void drawTitleS(Graphics g)
    {
        this.g=g;
            drawTitleScreen();
    }

    public void drawTitleScreen(){
       g.setColor(new Color(0,0,0));
       g.fillRect(0,0,GameWindow.screenWidth,GameWindow.screenHeight);

        //TITLE NAME
        g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
        String text ="Yetera";
        int x=getXforCenteredText(text);
        int y=Tile.TILE_HEIGHT*3;

        //umbra
        g.setColor(Color.gray);
        g.drawString(text,x+5,y+5);

        g.setColor(Color.white);
        g.drawString(text,x,y);

        //CHARACTER IMAGE
        x=GameWindow.screenWidth/2-(Tile.TILE_HEIGHT*2)/2;
        y+=Tile.TILE_HEIGHT*2;
        g.drawImage(Game.character.down1,x,y,Tile.TILE_WIDTH*2,Tile.TILE_HEIGHT*2,null);

        //MENU
        g.setFont(g.getFont().deriveFont(Font.BOLD,48F));
        text="NEW GAME";
        x=getXforCenteredText(text);
        y+=Tile.TILE_HEIGHT*3.5;
        g.drawString(text,x,y);
        if(commandNum == 0){
            g.drawString(">",x-Tile.TILE_HEIGHT,y);
        }

        text="LOAD GAME";
        x=getXforCenteredText(text);
        y+=Tile.TILE_HEIGHT;
        g.drawString(text,x,y);
        if(commandNum == 1){
            g.drawString(">",x-Tile.TILE_HEIGHT,y);
        }

        text="QUIT";
        x=getXforCenteredText(text);
        y+=Tile.TILE_HEIGHT;
        g.drawString(text,x,y);
        if(commandNum == 2 ){
            g.drawString(">",x-Tile.TILE_HEIGHT,y);
        }



    }
    public void drawPauseScreen() {

        g.setFont(g.getFont().deriveFont(Font.PLAIN,80F));
        g.setColor(Color.white);
        String text = "PAUSED";
        int x=getXforCenteredText(text);
        int y = GameWindow.screenHeight / 2;
        g.drawString(text, x, y);


    }
    public int getXforCenteredText(String text){
        int length = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
       int x = GameWindow.screenWidth / 2 - length / 2;
       return x;
    }
}
