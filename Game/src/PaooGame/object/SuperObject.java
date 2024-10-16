package PaooGame.object;

import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public void draw(Graphics g, Game game)
    {
        int screenX = worldX - game.character.worldX + game.character.screenX;
        int screenY = worldY - game.character.worldY + game.character.screenY;

        if(worldX + Tile.TILE_WIDTH > game.character.worldX - game.character.screenX &&
                worldX - Tile.TILE_WIDTH < game.character.worldX +game.character.screenX &&
                worldY + Tile.TILE_HEIGHT >game.character.worldY -game.character.screenY &&
                worldY - Tile.TILE_HEIGHT <game.character.worldY +game.character.screenY) {
            g.drawImage(image,screenX,screenY,Tile.TILE_WIDTH,Tile.TILE_HEIGHT,null);
        }
    }
}
