package PaooGame.entity;

import PaooGame.Game;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    Game game;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;
    public Entity(Game game)
    {
        this.game=game;
    }
    public void draw(Graphics g)
    {
        BufferedImage image=null;
        int screenX = worldX - game.character.worldX + game.character.screenX;
        int screenY = worldY - game.character.worldY + game.character.screenY;

        if(worldX + Tile.TILE_WIDTH > game.character.worldX - game.character.screenX &&
                worldX - Tile.TILE_WIDTH < game.character.worldX +game.character.screenX &&
                worldY + Tile.TILE_HEIGHT >game.character.worldY -game.character.screenY &&
                worldY - Tile.TILE_HEIGHT <game.character.worldY +game.character.screenY) {


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
            g.drawImage(image,screenX,screenY,Tile.TILE_WIDTH,Tile.TILE_HEIGHT,null);
        }
    }
}
