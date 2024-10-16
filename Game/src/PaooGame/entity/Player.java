package PaooGame.entity;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.ImageLoader;
import PaooGame.KeyHandler;
import PaooGame.Main;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    Graphics g;
    KeyHandler keyH;
    //Game game;

    public final int screenX;
    public final int screenY;
    public int hasCoin=0;
    public int hasArc=0;
    public int minRequiredCoin=4;
    public int minRequiredArcs=5;

    public Player(Graphics g, KeyHandler keyH, Game game){
        super(game);
        this.g = g;
        this.keyH = keyH;


        screenX = GameWindow.GetWndWidth() / 2 - Tile.TILE_WIDTH / 2;
        screenY = GameWindow.GetWndHeight() / 2 - Tile.TILE_HEIGHT / 2;

        solidArea = new Rectangle();
        solidArea.x=14;
        solidArea.y=24;
        solidArea.width=20;
        solidArea.height=24;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = 25 * Tile.TILE_HEIGHT;
        worldY = 24* Tile.TILE_WIDTH;
        speed = 4;
        direction = "down";
    }
    public void setDefaultPositions(){
        worldX = 25 * Tile.TILE_HEIGHT;
        worldY = 24* Tile.TILE_WIDTH;
        direction="down";
        hasCoin=0;
        hasArc=0;
    }

    public void getPlayerImage(){

        up1 = ImageLoader.LoadImage("/textures/sus3.png");
        up2 = ImageLoader.LoadImage("/textures/sus1.png");
        left1 = ImageLoader.LoadImage("/textures/runstanga.png");
        left2 = ImageLoader.LoadImage("/textures/runstanga1.png");
        right1 = ImageLoader.LoadImage("/textures/run0.png");
        right2 = ImageLoader.LoadImage("/textures/run1.png");
        down1 = ImageLoader.LoadImage("/textures/sus3.png");
        down2 = ImageLoader.LoadImage("/textures/sus1.png");

    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // check collision
            collisionOn = false;
            game.collChecker.checkTile(this);

            ///verificare coliziune obiecte
            int ObjIndex=game.collChecker.checkObject(this,true);
            pickUpObject(ObjIndex);


            if(collisionOn == false){
                switch(direction){
                    case "up":
                        if(worldY - speed > 0)
                            worldY -= speed;
                        else
                            worldY -= worldY;
                        break;
                    case "down":
                        if (worldY + speed < GameWindow.worldHeight-Tile.TILE_HEIGHT)
                            worldY += speed;
                        else
                            worldY += (GameWindow.worldHeight -Tile.TILE_HEIGHT - worldY);
                        break;
                    case "left":
                        if(worldX - speed > 0)
                            worldX -= speed;
                        else
                            worldX -= worldX;
                        break;
                    case "right":
                        if(worldX + speed < GameWindow.worldWidth-Tile.TILE_WIDTH)
                            worldX += speed;
                        else
                            worldX += (GameWindow.worldWidth - Tile.TILE_WIDTH - worldX);
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index){
        if(index!=999)
        {
            String  objectName=game.obj[index].name;
            switch(objectName){
                case "door":
                    if(hasCoin>=minRequiredCoin && hasArc>=minRequiredArcs){
                        game.obj[index]=null;
                        hasCoin=0;
                        hasArc=0;
                    }
                    else
                    {
                        game.ui.showMessage("You dont have enough pieces!");
                    }
                    System.out.println("Coins:"+hasCoin);
                    System.out.println("Arcs:"+hasArc);
                    break;
                case "Arc":
                    hasArc++;
                    game.obj[index]=null;
                    game.ui.showMessage("You got one piece of ark!");
                    break;
                case "Coin":
                    hasCoin++;
                    game.obj[index]=null;
                    game.ui.showMessage("You got a bronze coin!");
                    break;
                /*case "Arc2":
                    game.ui.gameFinished2=true;
                    break;*/
                case "Chest":
                    game.ui.gameFinished=true;
                    break;
            }
        }

    }
    public void resetObjects(){
        hasCoin=0;
        hasArc=0;
    }

    public void draw(Graphics g){
//        g.setColor(Color.white);
//        g.fillRect(x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g.drawImage(image, screenX, screenY, 48, 48, null);
    }

}
